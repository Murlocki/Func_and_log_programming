package org.example
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*


//Вызываем специальную функцию для конфигурации приложения
fun Application.module(){
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    val authors = Serialization.deserializeAuthors()
    //authors.add(Author(6,"aaaa","bbbb"))
    val clients = Serialization.deserializeClients()
    val genres = Serialization.deserializeGenres()
    val itemTypes = Serialization.deserializeItemTypes()
    val itemsInLibrary = Serialization.deserializeItemInLibrary()
    val recordItems = Serialization.deserializeRecords()
    routing {
        //Запросы для получения текущего содержания коллекции
        get("/authors"){
            call.respond(authors) }
        get("/clients"){
            call.respond(clients)
        }
        get("/genres"){
            call.respond(genres)
        }

        get("/itemTypes"){
            call.respond(itemTypes)
        }
        get("/itemsInLibrary"){
            call.respond(itemsInLibrary)
        }
        get("/recordItems"){
            call.respond(recordItems)
        }

        // Запросы для получения объектов коллекции по Id
        getById("/authors/{id}", authors) { it.id }
        getById("/clients/{id}", clients) { it.id }
        getById("/genres/{id}", genres) { it.id }
        getById("/itemTypes/{id}", itemTypes) { it.id }
        getById("/itemsInLibrary/{id}", itemsInLibrary) { it.id }
        getById("/recordItems/{id}", recordItems) { it.id }

        //Пути для вывода в файл содержимого коллекции
        post("/updateAuthors") {
            Serialization.serializeAuthors(authors)
            call.respond(HttpStatusCode.Accepted)
        }
        post("/updateClients") {
            Serialization.serializeClients(clients)
            call.respond(HttpStatusCode.Accepted)
        }
        post("/updateGenres") {
            Serialization.serializeGenres(genres)
            call.respond(HttpStatusCode.Accepted)
        }
        post("/updateItemTypes") {
            Serialization.serializeItemTypes(itemTypes)
            call.respond(HttpStatusCode.Accepted)
        }
        post("/updateItemsInLibrary") {
            Serialization.serializeItemInLibrary(itemsInLibrary)
            call.respond(HttpStatusCode.Accepted)
        }
        post("/updateRecordItems") {
            Serialization.serializeRecords(recordItems)
            call.respond(HttpStatusCode.Accepted)
        }

        //Осмысленные 5 запросов

        //Самый популярный предмет в библиотеке

        fun getMostPopularItem() = itemsInLibrary.maxBy({recordItems.count{ el->el.itemInLibrary==it}})

        get("/get-most-popular-item"){
            call.respond(listOf(getMostPopularItem()))
        }
        //Записи о всех клиентах взявших книгу по Id

        fun getAllClientsOfItem(itemId:Int) = recordItems.filter { it.itemInLibrary.id==itemId }.map{it.client}.distinct()

        get("/get-item-clients/{itemId}"){
            val itemId = call.parameters["itemId"]?.toIntOrNull()
            val clientsList = itemId?.let { getAllClientsOfItem(itemId) }
            if(clientsList!=null){
                call.respond(clientsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
        //Все книги какого-либо из клиентов
        fun getAllClientItem(clientId:Int) = recordItems.filter { it.client.id==clientId }.map { it.itemInLibrary }.distinct()

        get("/get-all-client-items/{clientId}"){
            val clientId = call.parameters["clientId"]?.toIntOrNull()
            val itemsList = clientId?.let { getAllClientItem(clientId)}
            if(itemsList!=null){
                call.respond(itemsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
        //Самый популярный жанр

        fun getMostPopularGenre() = genres.maxBy { recordItems.count{ el->el.itemInLibrary.genres.contains(it)} }

        get("/get-most-popular-genre"){
            if(recordItems.size==0){
                call.respond(HttpStatusCode.NoContent)
            }
            val maxGenre = getMostPopularGenre()
            call.respond(listOf(maxGenre))
        }
        //Все книги с участием автора
        fun getAllAuthorsBooks(authorId:Int) = itemsInLibrary.filter {
            it.authors?.any {
                el -> el.id == authorId
            } ?:
            false
        }

        get("/get-all-author-items/{authorId}"){
            val authorId = call.parameters["authorId"]?.toIntOrNull()
            val itemsList = authorId?.let { it1 -> getAllAuthorsBooks(it1) }
            if(itemsList!=null){
                call.respond(itemsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }


        //Api для вывода в excel
        //Сохранение самого популярного объекта
        post("/save-most-popular-item"){
            val mostPopularItem = listOf(getMostPopularItem())
            ExcelReport.generateReportItems(mostPopularItem,"src/main/resources/ExcelReports/mostPopulartItem.xlsx")
            call.respond(HttpStatusCode.Accepted)
        }
        //Сохранение объектов 1 клиента
        post("/save-all-client-items/{clientId}"){
            val clientId = call.parameters["clientId"]?.toIntOrNull()
            val itemsList = clientId?.let { it1 -> getAllClientItem(it1) }
            if(itemsList!=null){
                ExcelReport.generateReportItems(itemsList,"src/main/resources/ExcelReports/allClientItems.xlsx")
                call.respond(HttpStatusCode.Accepted)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }

        //Сохранение клиентов 1 объекта
        post("/save-all-item-clients/{itemId}"){
            val itemId = call.parameters["itemId"]?.toIntOrNull()
            val itemsList = itemId?.let { getAllClientsOfItem(itemId) }
            if(itemsList!=null){
                ExcelReport.generateReportClient(itemsList,"src/main/resources/ExcelReports/allItemClients.xlsx")
                call.respond(HttpStatusCode.Accepted)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }

        //Самый популярный жанр
        post("/save-most-popular-genre"){
            if(recordItems.size==0){
                call.respond(HttpStatusCode.NoContent)
            }
            ExcelReport.generateReportGenre(listOf(getMostPopularGenre()),"src/main/resources/ExcelReports/mostPopulartGenre.xlsx")
            call.respond(HttpStatusCode.Accepted)
        }

        //Все работы автора
        post("/save-all-author-items/{authorId}"){
            val authorId = call.parameters["authorId"]?.toIntOrNull()
            val itemsList = authorId?.let { it1 -> getAllAuthorsBooks(it1) }
            if(itemsList!=null){
                ExcelReport.generateReportItems(itemsList,"src/main/resources/ExcelReports/allAuthorItems.xlsx")
                call.respond(HttpStatusCode.Accepted)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}


val gson: Gson = GsonBuilder().setPrettyPrinting().create()

fun <T> Route.getById(path: String, collection: List<T>, idSelector: (T) -> Int) {
    get(path) {
        println("Requested path: $path")
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid ID")
            return@get
        }
        val item = collection.find { idSelector(it) == id }
        println("Requested item: $item")

        if (item != null) {
            val jsonResponse = gson.toJson(item)
            call.respondText(jsonResponse, ContentType.Application.Json)
        } else {
            call.respond(HttpStatusCode.NotFound, "Item with ID $id not found")
        }
    }
}



fun startServer() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
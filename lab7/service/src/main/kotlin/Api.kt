package org.example
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
            call.respond(authors)
        }
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
        get("/get-most-popular-item"){
            call.respond(listOf(itemsInLibrary.maxBy({recordItems.count{ el->el.itemInLibrary==it}})))
        }
        //Записи о всех клиентах взявших книгу по Id
        get("/get-item-clients/{itemId}"){
            val itemId = call.parameters["itemId"]?.toIntOrNull()
            val clientsList = itemId?.let { recordItems.filter { it.itemInLibrary.id==itemId }.map{it.client}.distinct()}
            if(clientsList!=null){
                call.respond(clientsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
        //Все книги какого-либо из клиентов
        get("/get-all-client-items/{clientId}"){
            val clientId = call.parameters["clientId"]?.toIntOrNull()
            val itemsList = clientId?.let { recordItems.filter { it.client.id==clientId }.map { it.itemInLibrary }.distinct()}
            if(itemsList!=null){
                call.respond(itemsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
        //Самый популярный жанр
        get("/get-most-popular-genre"){
            if(recordItems.size==0){
                call.respond(HttpStatusCode.NoContent)
            }
            val maxGenre = genres.maxBy { recordItems.count{ el->el.itemInLibrary.genres.contains(it)} }
            call.respond(listOf(maxGenre))
        }
        //Все книги с участием автора
        get("/get-all-author-items/{authorId}"){
            val authorId = call.parameters["authorId"]?.toIntOrNull()
            val itemsList = authorId?.let { itemsInLibrary.filter {
                it.authors?.any { el -> el.id == authorId } ?: false
            }}
            if(itemsList!=null){
                call.respond(itemsList)
            }
            else{
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}

fun startServer() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
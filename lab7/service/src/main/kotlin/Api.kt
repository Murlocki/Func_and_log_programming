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

        
    }
}

fun startServer() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
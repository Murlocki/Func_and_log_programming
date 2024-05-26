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
    val clients = Serialization.deserializeClients()
    val genres = Serialization.deserializeGenres()
    val itemTypes = Serialization.deserializeItemTypes()
    val itemsInLibrary = Serialization.deserializeItemInLibrary()

    routing {
        get("/authors"){
            call.respond(authors)
        }
        get("/clients"){
            call.respond(clients)
        }
    }
}

fun startServer() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
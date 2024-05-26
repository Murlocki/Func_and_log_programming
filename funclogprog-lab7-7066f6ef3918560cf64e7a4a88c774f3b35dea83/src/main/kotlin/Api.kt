import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.module() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    val users = deserializeData("users.json")

    routing {
        get("/most-sold-product") {
            val mostSoldProduct = users.flatMap { it.orders }
                .flatMap { it.orderItems }
                .groupBy { it.product }
                .maxByOrNull { it.value.sumOf { item -> item.quantity } }
                ?.key
            call.respond(mostSoldProduct ?: HttpStatusCode.NotFound)
        }

        get("/most-active-user") {
            val mostActiveUser = users.maxByOrNull { it.orders.size }
            call.respond(mostActiveUser ?: HttpStatusCode.NotFound)
        }

        get("/category-with-most-products") {
            val mostProductsCategory = users.flatMap { it.orders }
                .flatMap { it.orderItems }
                .groupBy { it.product.category }
                .maxByOrNull { it.value.size }
                ?.key
            call.respond(mostProductsCategory ?: HttpStatusCode.NotFound)
        }

        get("/products-by-category/{categoryId}") {
            val categoryId = call.parameters["categoryId"]?.toIntOrNull()
            val productsByCategory = categoryId?.let {
                users.flatMap { it.orders }
                    .flatMap { it.orderItems }
                    .map { it.product }
                    .filter { it.category.id == categoryId }
                    .distinct()
            }
            if (!productsByCategory.isNullOrEmpty()) {
                call.respond(productsByCategory)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/orders-by-user/{userId}") {
            val userId = call.parameters["userId"]?.toIntOrNull()
            val ordersByUser = userId?.let {
                users.find { it.id == userId }?.orders
            }
            if (ordersByUser != null) {
                call.respond(ordersByUser)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

fun startServer() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String, val email: String, val orders: List<Order>)

@Serializable
data class Product(val id: Int, val name: String, val category: Category, val price: Double, val stock: Int)

@Serializable
data class Category(val id: Int, val name: String, val products: List<Product>)

@Serializable
data class Order(val id: Int, val user: User, val orderItems: List<OrderItem>, val totalAmount: Double)

@Serializable
data class OrderItem(val product: Product, val quantity: Int, val price: Double)

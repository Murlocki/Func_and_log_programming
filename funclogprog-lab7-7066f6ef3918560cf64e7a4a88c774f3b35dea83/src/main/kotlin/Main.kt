fun main() {
    // Пример заполнения данными и сериализации
    val category = Category(1, "Electronics", listOf())
    val product = Product(1, "Smartphone", category, 699.99, 50)
    val user = User(1, "John Doe", "john.doe@example.com", listOf())
    val orderItem = OrderItem(product, 1, 699.99)
    val order = Order(1, user, listOf(orderItem), 699.99)

    val users = listOf(user.copy(orders = listOf(order)))

    // Сериализация данных
    serializeData(users, "users.json")

    // Генерация отчета
    generateReport(users, "report.xlsx")

    // Запуск сервера
    startServer()
}

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

fun generateReport(users: List<User>, filename: String) {
    val workbook: Workbook = XSSFWorkbook()
    val sheet: Sheet = workbook.createSheet("Report")

    var rowNum = 0
    val headerRow: Row = sheet.createRow(rowNum++)
    val headers = listOf("User Name", "Order ID", "Product", "Quantity", "Price")
    headers.forEachIndexed { index, header ->
        headerRow.createCell(index).setCellValue(header)
    }

    users.forEach { user ->
        user.orders.forEach { order ->
            order.orderItems.forEach { item ->
                val row: Row = sheet.createRow(rowNum++)
                row.createCell(0).setCellValue(user.name)
                row.createCell(1).setCellValue(order.id.toString())
                row.createCell(2).setCellValue(item.product.name)
                row.createCell(3).setCellValue(item.quantity.toString())
                row.createCell(4).setCellValue(item.price.toString())
            }
        }
    }

    FileOutputStream(filename).use { outputStream ->
        workbook.write(outputStream)
    }
    workbook.close()
}

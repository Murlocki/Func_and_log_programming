package org.example
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import kotlin.js.ExperimentalJsFileName

class ExcelReport {
    companion object {
        fun generateReportItems(list: List<ItemInLibrary>,fileName: String){
                val workbook: Workbook = XSSFWorkbook()
                val sheet: Sheet = workbook.createSheet("Report")

                var rowNum = 0
                val headerRow: Row = sheet.createRow(rowNum++)
                val headers = listOf("Id","Name","TypeId","GenreIdList","AuthorsIdList")
                headers.forEachIndexed { index, header ->
                    headerRow.createCell(index).setCellValue(header)
                }

                list.forEach { item ->
                            val row: Row = sheet.createRow(rowNum++)
                            row.createCell(0).setCellValue(item.id.toString())
                            row.createCell(1).setCellValue(item.name)
                            row.createCell(2).setCellValue(item.itemType.id.toString())
                            row.createCell(3).setCellValue(item.genres.map { it.id }.toString())
                            row.createCell(4).setCellValue(item.authors?.map { it.id }.toString())
                        }

                FileOutputStream(fileName).use { outputStream ->
                    workbook.write(outputStream)
                }
                workbook.close()
        }

        //Отчет по клиентам
        fun generateReportClient(list: List<Client>,fileName: String){
            val workbook: Workbook = XSSFWorkbook()
            val sheet: Sheet = workbook.createSheet("Report")

            var rowNum = 0
            val headerRow: Row = sheet.createRow(rowNum++)
            val headers = listOf("Id","phoneNumber","name","surname","patronymic")
            headers.forEachIndexed { index, header ->
                headerRow.createCell(index).setCellValue(header)
            }

            list.forEach { item ->
                val row: Row = sheet.createRow(rowNum++)
                row.createCell(0).setCellValue(item.id.toString())
                row.createCell(1).setCellValue(item.phoneNumber)
                row.createCell(2).setCellValue(item.name)
                row.createCell(3).setCellValue(item.surname)
                row.createCell(4).setCellValue(item.patronymic)
            }

            FileOutputStream(fileName).use { outputStream ->
                workbook.write(outputStream)
            }
            workbook.close()
        }

        //Отчет по жанрам
        fun generateReportGenre(list: List<Genre>,fileName: String){
            val workbook: Workbook = XSSFWorkbook()
            val sheet: Sheet = workbook.createSheet("Report")

            var rowNum = 0
            val headerRow: Row = sheet.createRow(rowNum++)
            val headers = listOf("Id","name")
            headers.forEachIndexed { index, header ->
                headerRow.createCell(index).setCellValue(header)
            }

            list.forEach { item ->
                val row: Row = sheet.createRow(rowNum++)
                row.createCell(0).setCellValue(item.id.toString())
                row.createCell(1).setCellValue(item.name)
            }

            FileOutputStream(fileName).use { outputStream ->
                workbook.write(outputStream)
            }
            workbook.close()
        }


        //Отчет по авторам
        fun generateReportAuthor(list: List<Author>,fileName: String){
            val workbook: Workbook = XSSFWorkbook()
            val sheet: Sheet = workbook.createSheet("Report")

            var rowNum = 0
            val headerRow: Row = sheet.createRow(rowNum++)
            val headers = listOf("Id","name","surname","patronymic")
            headers.forEachIndexed { index, header ->
                headerRow.createCell(index).setCellValue(header)
            }

            list.forEach { item ->
                val row: Row = sheet.createRow(rowNum++)
                row.createCell(0).setCellValue(item.id.toString())
                row.createCell(1).setCellValue(item.name)
                row.createCell(2).setCellValue(item.surname)
                row.createCell(3).setCellValue(item.patronymic)
            }

            FileOutputStream(fileName).use { outputStream ->
                workbook.write(outputStream)
            }
            workbook.close()
        }
    }
}

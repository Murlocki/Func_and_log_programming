package DocumentCollection

import VehiclePassport.VehiclePassport
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random


class VehiclePassportFile {

    fun checkContained(newVec:VehiclePassport,listDocs: MutableList<VehiclePassport>): String {
        if(listDocs.contains(newVec)){
            return "Уже был"
        }
        else{
            listDocs.add(newVec)
            return "Еще не был"
        }
    }

    fun checkFormat(line:String,listDocs:MutableList<VehiclePassport>): Boolean{
        val data = line.split(",")
        try {
            val newVec =  VehiclePassport(data[0], data[1])
            val contained = checkContained(newVec,listDocs)
            println("$line Окей;$contained")
            return true
        }catch (exception:Exception){
            println(line+" Ошибка формата")
        }
        return false
    }
    fun createVehiclePassport(line: String): VehiclePassport {
        val data = line.split(",")
        return VehiclePassport(data[0], data[1])
    }
    fun timeTests() {
        //generateTestDataToFile("f.txt",50)
        println("файл готов")

        val listDocs: MutableList<VehiclePassport> = mutableListOf()

        val docs = File("f.txt").readLines().filter { line ->
            checkFormat(line,listDocs)
        }.map { createVehiclePassport(it) }
        println(docs)

        val arrayCollection = ArrayDocument(docs.toTypedArray())
        println("ArrayDocumentCollection готов")
        val listCollection = ListDocument(docs)
        println("ListDocumentCollection готов")
        val binaryListCollection = BinaryListDocument(docs)
        println("BinaryListDocumentCollection готов")
        val hashSetCollection = HashSetDocument(docs.toHashSet())
        println("HashSetDocumentCollection готов")
        val treeSetCollection = TreeSetDocument(TreeSet(docs))
        println("TreeSet готов")

        val positiveSearchTimes = mutableMapOf<DocumentCollection<VehiclePassport>, MutableList<Long>>()

        var i = 0
        for (doc in docs) {
            val searchResults = mapOf(
                arrayCollection to arrayCollection.measureSearchTime(doc),
                listCollection to listCollection.measureSearchTime(doc),
                binaryListCollection to binaryListCollection.measureSearchTime(doc),
                hashSetCollection to hashSetCollection.measureSearchTime(doc),
                treeSetCollection to treeSetCollection.measureSearchTime(doc)
            )
            println("Нашли $i")
            i += 1

            val positiveSearchTime = searchResults.mapValues { it.value }

            positiveSearchTime.forEach { (collection, time) ->
                positiveSearchTimes.getOrPut(collection) { mutableListOf() }.add(time)
            }
        }
        println(positiveSearchTimes)
        println("\nСреднее время ответа:")
        positiveSearchTimes.forEach { (collection, times) ->
            val averageTime = times.average()
            println("${collection.javaClass.simpleName}: $averageTime мс")
        }
    }

    fun generateTestDataToFile(filename: String, count: Int) {
        val random = Random(1234)
        val seriesLength = 2
        val numberLength = 6
        val numbers = "0123456789"
        val characters ="АБВБГДЕЁЖЗИЙКЛМН"
        val file = File(filename)
        if (file.exists()) {
            file.delete()
        }

        for (i in 1..count) {
            val series = (1..seriesLength).map { numbers[random.nextInt(numbers.length)] }.joinToString("")
            val number = (1..numberLength).map { numbers[random.nextInt(numbers.length)] }.joinToString("")
            val charSer =  characters[random.nextInt(characters.length)]
            val line = "$series Т$charSer,$number\n"
            file.appendText(line)
        }
    }
}
package org.example
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Serialization {

    //Функция вывода коллекции объектов в Json
    fun serializeData(users: List<Any?>, filename: String) {
        val json = Json { prettyPrint = true }
        val jsonString = json.encodeToString(users)
        File(filename).writeText(jsonString)
    }

    //Функция извлечения объектов из Json
    fun deserializeData(filename: String): List<Any?> {
        val json = Json { prettyPrint = true }
        val jsonData = File(filename).readText()
        return json.decodeFromString(jsonData)
    }
}
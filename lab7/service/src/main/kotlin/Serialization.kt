package org.example
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import java.io.File

class Serialization {

    fun <T> serializeData(list: List<T>, serializationStrategy: SerializationStrategy<List<T>>, filename: String) {
        val json = Json { prettyPrint = true }
        val jsonString = json.encodeToString(serializationStrategy,list)
        File(filename).writeText(jsonString)
    }

    //Функция извлечения объектов из Json
    fun <T> deserializeData(filename: String,deserialization: KSerializer<List<T>>): List<T> {
        val json = Json { prettyPrint = true }
        val jsonData = File(filename).readText()
        return json.decodeFromString(deserialization,jsonData)
    }
}
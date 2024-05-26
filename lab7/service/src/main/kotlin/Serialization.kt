package org.example
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

class Serialization {

    //Сериализация itemType
    fun serializeItemTypes(list: List<ItemType>) = serializeData(list,
        ListSerializer(ItemType.serializer()),"src\\main\\resources\\itemTypes.json")

    fun deserializeItemTypes(): List<ItemType> = deserializeData("src\\main\\resources\\itemTypes.json",ListSerializer(ItemType.serializer()))

    //Сериализация жанров
    fun serializeGenres(list: List<Genre>) = serializeData(list,
        ListSerializer(Genre.serializer()),"src\\main\\resources\\genres.json")

    fun deserializeGenres(): List<Genre> = deserializeData("src\\main\\resources\\genres.json",ListSerializer(Genre.serializer()))

    //Сериализация содержимого библиотеки
    fun serializeItemInLibrary(list: List<ItemInLibrary>) = serializeData(list,
        ListSerializer(ItemInLibrary.serializer()),"src\\main\\resources\\itemInLibrary.json")

    fun deserializeItemInLibrary(): List<ItemInLibrary> = deserializeData(
        "src\\main\\resources\\itemInLibrary.json",
        ListSerializer(ItemInLibrary.serializer()))

    //Сериализация Клиентов
    fun serializeClients(list: List<Client>) = serializeData(list,
        ListSerializer(Client.serializer()),"src\\main\\resources\\clients.json")

    fun deserializeClients(): List<Client> = deserializeData(
        "src\\main\\resources\\clients.json",
        ListSerializer(Client.serializer()))

    //Сериализация авторов
    fun serializeAuthors(list: List<Author>) = serializeData(list,
        ListSerializer(Author.serializer()),"src\\main\\resources\\authors.json")

    fun deserializeAuthors(): List<Author> = deserializeData(
        "src\\main\\resources\\authors.json",
        ListSerializer(Author.serializer()))

    //Сериализация записей журналов
    fun serializeRecords(list: List<RecordItem>) = serializeData(list,
        ListSerializer(RecordItem.serializer()),"src\\main\\resources\\records.json")

    fun deserializeRecords(): List<RecordItem> = deserializeData(
        "src\\main\\resources\\records.json",
        ListSerializer(RecordItem.serializer()))
    private fun <T> serializeData(list: List<T>, serializationStrategy: SerializationStrategy<List<T>>, filename: String) {
        val json = Json { prettyPrint = true }
        val jsonString = json.encodeToString(serializationStrategy,list)
        File(filename).writeText(jsonString)
    }

    //Функция извлечения объектов из Json
    private fun <T> deserializeData(filename: String,deserialization: KSerializer<List<T>>): List<T> {
        val json = Json { prettyPrint = true }
        val jsonData = File(filename).readText()
        return json.decodeFromString(deserialization,jsonData)
    }
}
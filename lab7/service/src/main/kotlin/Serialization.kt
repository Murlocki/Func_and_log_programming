package org.example
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

class Serialization {

    companion object {
        //Сериализация itemType
        fun serializeItemTypes(list: List<ItemType>) = serializeData(list.toSet().toList(),
            ListSerializer(ItemType.serializer()),"src/main/resources/jsonFiles/itemTypes.json")

        fun deserializeItemTypes(): MutableList<ItemType> = deserializeData("src/main/resources/jsonFiles/itemTypes.json",ListSerializer(ItemType.serializer())).toMutableList()

        //Сериализация жанров
        fun serializeGenres(list: List<Genre>) = serializeData(list.toSet().toList(),
            ListSerializer(Genre.serializer()),"src/main/resources/jsonFiles/genres.json")

        fun deserializeGenres(): MutableList<Genre> = deserializeData("src/main/resources/jsonFiles/genres.json",ListSerializer(Genre.serializer())).toMutableList()
        //Сериализация содержимого библиотеки
        fun serializeItemInLibrary(list: List<ItemInLibrary>) = serializeData(list.toSet().toList(),
            ListSerializer(ItemInLibrary.serializer()),"src/main/resources/jsonFiles/itemInLibrary.json")

        fun deserializeItemInLibrary(): MutableList<ItemInLibrary> = deserializeData(
            "src/main/resources/jsonFiles/itemInLibrary.json",
            ListSerializer(ItemInLibrary.serializer())).toMutableList()

        //Сериализация Клиентов
        fun serializeClients(list: List<Client>) = serializeData(list.toSet().toList(),
            ListSerializer(Client.serializer()),"src/main/resources/jsonFiles/clients.json")

        fun deserializeClients(): MutableList<Client> = deserializeData(
            "src/main/resources/jsonFiles/clients.json",
            ListSerializer(Client.serializer())).toMutableList()

        //Сериализация авторов
        fun serializeAuthors(list: List<Author>) = serializeData(list.toSet().toList(),
            ListSerializer(Author.serializer()),"src/main/resources/jsonFiles/authors.json")

        fun deserializeAuthors(): MutableList<Author> = deserializeData(
            "src/main/resources/jsonFiles/authors.json",
            ListSerializer(Author.serializer())).toMutableList()

        //Сериализация записей журналов
        fun serializeRecords(list: List<RecordItem>) = serializeData(list.toSet().toList(),
            ListSerializer(RecordItem.serializer()),"src/main/resources/jsonFiles/records.json")

        fun deserializeRecords(): List<RecordItem> = deserializeData(
            "src/main/resources/jsonFiles/records.json",
            ListSerializer(RecordItem.serializer())).toMutableList()
        private fun <T> serializeData(list: List<T>, serializationStrategy: SerializationStrategy<List<T>>, filename: String) {
            val json = Json { prettyPrint = true }
            val jsonString = json.encodeToString(serializationStrategy,list)
            File(filename).writeText(jsonString)
        }

        //Функция извлечения объектов из Json
        private fun <T> deserializeData(filename: String,deserialization: KSerializer<List<T>>): MutableList<T> {
            val json = Json { prettyPrint = true }
            val jsonData = File(filename).readText()
            return json.decodeFromString(deserialization,jsonData).toMutableList()
        }
    }
}
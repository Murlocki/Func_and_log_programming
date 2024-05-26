package org.example
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class Data(val a: Int, val b: String)

fun main() {
    val i = ItemInLibrary(1,"Вл",ItemType(1,"Книга"), listOf<Genre>(), listOf<Author>())

//    val json = Json.encodeToString(ItemInLibrary.serializer(),i)
//    println(json)
//    val k = Json.decodeFromString<ItemInLibrary>(json)
//    println(k)
    val ser = Serialization()
    ser.serializeData(listOf(i,i),ListSerializer(ItemInLibrary.serializer()),"f.txt")
    println(ser.deserializeData("f.txt",ListSerializer(ItemInLibrary.serializer())))
}
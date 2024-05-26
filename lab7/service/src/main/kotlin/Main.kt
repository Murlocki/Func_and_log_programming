package org.example
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
data class Data(val a: Int, val b: String)

fun main() {
    val json = Json.encodeToString(Client(1,phoneNumber = "349494949","aaaa","bbbbb"))
    println(json)
    val k = Json.decodeFromString<Client>(json)
    println(k)
}
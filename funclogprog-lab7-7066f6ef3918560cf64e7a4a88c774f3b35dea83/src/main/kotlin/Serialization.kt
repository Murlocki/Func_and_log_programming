import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun serializeData(users: List<User>, filename: String) {
    val json = Json { prettyPrint = true }
    val jsonString = json.encodeToString(users)
    File(filename).writeText(jsonString)
}

fun deserializeData(filename: String): List<User> {
    val json = Json { prettyPrint = true }
    val jsonData = File(filename).readText()
    return json.decodeFromString(jsonData)
}

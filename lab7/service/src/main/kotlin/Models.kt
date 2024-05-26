package org.example

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date


@Serializable
data class Genre(val id:Int, val name:String)

@Serializable
data class ItemType(val id:Int, val name:String)

@Serializable
data class ItemInLibrary(val id:Int, val name:String,val itemType: ItemType, val genres:List<Genre>, val authors: List<Author>)

@Serializable
sealed class Person {
    abstract val id: Int
    abstract val name: String
    abstract val surname: String
    abstract val patronymic: String?
}

@Serializable
data class Client(
    override val id: Int,
    val phoneNumber: String,
    override val name: String,
    override val surname: String,
    override val patronymic: String? = null,
): Person(){
    init {
        require("\\+7[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}".toRegex().matches(phoneNumber))
    }
}

@Serializable
data class Author(
    override val id: Int,
    val phoneNumber: String,
    override val name: String,
    override val surname: String,
    override val patronymic: String? = null,
): Person()

@Serializable
data class Records(
    val client: Client,
    val itemInLibrary: ItemInLibrary,
    val startDate: String,
    val endDate: String
){
    init {
        require("[0-3][0-9]\\.[01][0-9]\\.[0-9]{4}".toRegex().matches(startDate)){"Неверно введена дата взятия"}
        require("[0-3][0-9]\\.[01][0-9]\\.[0-9]{4}".toRegex().matches(endDate)){"Неверно введена дата окончания"}
    }
}
import kotlinx.serialization.builtins.ListSerializer
import org.example.*

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class SerializeTests {
    @Test
    fun testItemTypeSerDer(){
        val item1 = ItemType(1,"Книга")
        val item2 = ItemType(2,"Журнал")
        val item3 = ItemType(3,"Статья")
        val item4 = ItemType(4,"Брошюра")
        val item5 = ItemType(5,"Комикс")
        Serialization.serializeItemTypes(listOf(item1,item2,item3,item4,item5))


        Assertions.assertEquals("[ItemType(id=1, name=Книга), ItemType(id=2, name=Журнал), ItemType(id=3, name=Статья), ItemType(id=4, name=Брошюра), ItemType(id=5, name=Комикс)]",
            Serialization.deserializeItemTypes().toString())
    }

    @Test
    fun testGenresSerDer(){
        val genre1 = Genre(1,"Научная фантастика")
        val genre2 = Genre(2,"Фентези")
        val genre3 = Genre(3,"Историческая проза")
        val genre4 = Genre(4,"Супергерои")
        val genre5 = Genre(5,"Религия")
        Serialization.serializeGenres(listOf(genre1,genre2,genre3,genre4,genre5))


        Assertions.assertEquals("[Genre(id=1, name=Научная фантастика), Genre(id=2, name=Фентези), Genre(id=3, name=Историческая проза), Genre(id=4, name=Супергерои), Genre(id=5, name=Религия)]",
            Serialization.deserializeGenres().toString())
    }

    @Test
    fun testClientsSerDer(){
        val client1 = Client(1,"+7(953)-877-98-98","Июуй","Хуанг")
        val client2 = Client(2,"+7(953)-877-95-98","Иван","Абрамович","Абрамсович")
        val client3 = Client(3,"+7(953)-877-93-98","Егор","Либерти","Егоревыч")
        val client4 = Client(4,"+7(953)-877-92-98","Чан","Кайши")
        val client5 = Client(5,"+7(953)-877-91-98","Иуда","Искариот")
        Serialization.serializeClients(listOf(client1,client2,client3,client4,client5))

        Assertions.assertEquals(
            listOf(client1,client2,client3,client4,client5),
            Serialization.deserializeClients())
    }

    @Test
    fun testAuthorsSerDer(){
        val author1 = Author(1,"Чан","Хуано")
        val author2 = Author(2,"Владимир","Абрамович","Зюганов")
        val author3 = Author(3,"Володимир","Минька","Егоревыч")
        val author4 = Author(4,"Канто","Иш")
        val author5 = Author(5,"Толкиен","Джон Рональд Руэл")
        Serialization.serializeAuthors(listOf(author1,author2,author3,author4,author5))


        Assertions.assertEquals(
            listOf(author1,author2,author3,author4,author5),
            Serialization.deserializeAuthors())
    }

    @Test
    fun testItemsInLibrarySerDer(){
        val item1 = ItemInLibrary(1,"Властелин Колец", ItemType(1,"Книга"), listOf(Genre(2,"Фентези")), listOf(Author(5,"Толкиен","Джон Рональд Руэл")))
        val item2 = ItemInLibrary(2,"Марск и Маркс",ItemType(1,"Книга"),listOf(Genre(3,"Историческая проза")),listOf(Author(2,"Владимир","Абрамович","Зюганов")) )
        val item3 = ItemInLibrary(3,"Китайские мифы",
            ItemType(1,"Книга"),
            listOf(
                Genre(2,"Фентези"),
                Genre(3,"Историческая проза")
            ),
            listOf(Author(4,"Канто","Иш"))
            )
        val item4 = ItemInLibrary(4,"Брошюра о независимости США",
            ItemType(4,"Брошюра"),
            listOf(
                Genre(3,"Историческая проза")
            ),
            listOf(Author(1,"Чан","Хуано"))
        )
        val item5 = ItemInLibrary(5,"Библия",
            ItemType(1,"Книга"),
            listOf(
                Genre(5,"Религия")
            ),
        )
        Serialization.serializeItemInLibrary(listOf(item1,item2,item3,item4,item5))
        Assertions.assertEquals(
            listOf(item1,item2,item3,item4,item5),
            Serialization.deserializeItemInLibrary())
    }

    @Test
    fun testRecordItemSerDer(){
        val item1 = RecordItem(1,
            Client(1,"+7(953)-877-98-98","Июуй","Хуанг"),
            ItemInLibrary(4,"Брошюра о независимости США",
                ItemType(4,"Брошюра"),
                listOf(
                    Genre(3,"Историческая проза")
                ),
                listOf(Author(1,"Чан","Хуано"))
            ),
            "12.09.2034",
            "13.10.2034"
        )
        val item2 = RecordItem(2,
            Client(1,"+7(953)-877-98-98","Июуй","Хуанг"),
            ItemInLibrary(5,"Библия",
                ItemType(1,"Книга"),
                listOf(
                    Genre(5,"Религия")
                ),
            ),
            "12.09.2034",
            "13.11.2044"
        )
        val item3 = RecordItem(3,
            Client(3,"+7(953)-877-93-98","Егор","Либерти","Егоревыч"),
            ItemInLibrary(5,"Библия",
                ItemType(1,"Книга"),
                listOf(
                    Genre(5,"Религия")
                ),
            ),
            "14.12.2034",
            "15.12.2034"
        )
        val item4 = RecordItem(4,
            Client(1,"+7(953)-877-98-98","Июуй","Хуанг"),
            ItemInLibrary(4,"Брошюра о независимости США",
                ItemType(4,"Брошюра"),
                listOf(
                    Genre(3,"Историческая проза")
                ),
                listOf(Author(1,"Чан","Хуано"))
            ),
            "12.09.2034",
            "13.10.2034"
        )
        val item5 = RecordItem(1,
            Client(5,"+7(953)-877-91-98","Иуда","Искариот"),
            ItemInLibrary(4,"Брошюра о независимости США",
                ItemType(4,"Брошюра"),
                listOf(
                    Genre(3,"Историческая проза")
                ),
                listOf(Author(1,"Чан","Хуано"))
            ),
            "12.09.2034",
            "13.10.2034"
        )
        Serialization.serializeRecords(listOf(item1,item2,item3,item4,item5))
        Assertions.assertEquals(
            listOf(item1,item2,item3,item4,item5),
            Serialization.deserializeRecords())
    }
}
import VehiclePassport.Companion.compareBySeriesAndNumber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class VehiclePassportTests {
    @Test
    fun printTest(){
        val passport = VehiclePassport(1234,233, LocalDate.of(2004,12,23))
        Assertions.assertEquals("Серия:1234,Номер:233,Дата выдачи:2004-12-23",passport.toString())
    }


    @Test
    fun compareEqualNumberTest(){
        val passport = VehiclePassport(1234,233, LocalDate.now())
        val passport2 = VehiclePassport(123,233, LocalDate.now())
        Assertions.assertEquals(0,VehiclePassport.compareByNumber(passport,passport2))
    }
    @Test
    fun compareNumberTest(){
        val passport = VehiclePassport(1234,23, LocalDate.now())
        val passport2 = VehiclePassport(123,233, LocalDate.now())
        Assertions.assertEquals(-1,VehiclePassport.compareByNumber(passport,passport2))
        Assertions.assertEquals(1,VehiclePassport.compareByNumber(passport2,passport))
    }


    @Test
    fun compareEqualSeriesest(){
        val passport = VehiclePassport(123,233, LocalDate.now())
        val passport2 = VehiclePassport(123,233, LocalDate.now())
        Assertions.assertEquals(0,VehiclePassport.compareBySeries(passport,passport2))
    }
    @Test
    fun compareSeriesTest(){
        val passport = VehiclePassport(1234,23, LocalDate.now())
        val passport2 = VehiclePassport(123,233, LocalDate.now())
        Assertions.assertEquals(1,VehiclePassport.compareBySeries(passport,passport2))
        Assertions.assertEquals(-1,VehiclePassport.compareBySeries(passport2,passport))
    }


    @Test
    fun equalsNumberSeriesTest(){
        val passport = VehiclePassport(1234,23, LocalDate.now())
        val passport2 = VehiclePassport(123,233, LocalDate.now())
        Assertions.assertEquals(false, passport == passport2)
    }
    @Test
    fun equalsTrueNumberSeriesTest(){
        val passport = VehiclePassport(123,23, LocalDate.now())
        val passport2 = VehiclePassport(123,23, LocalDate.now())
        Assertions.assertEquals(true, passport == passport2)
    }


    @Test
    fun sortByDataTest(){
        val passport = VehiclePassport(123,23, LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport(123,23, LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport(123,23, LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport2,passport,passport3), listToSort.sorted())
    }

    @Test
    fun sortBySeriesNumberTest(){
        val passport = VehiclePassport(123,21, LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport(123,25, LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport(123,13, LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport3,passport,passport2), listToSort.sortedWith(::compareBySeriesAndNumber) )
    }

    @Test
    fun sortBySeriesNumberTest2(){
        val passport = VehiclePassport(123,23, LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport(123,23, LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport(123,23, LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport3,passport,passport2), listToSort.sortedWith(::compareBySeriesAndNumber) )
    }
}
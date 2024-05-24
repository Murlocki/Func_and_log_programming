
import VehiclePassport.VehiclePassport
import VehiclePassport.VehiclePassport.Companion.compareByDate
import VehiclePassport.VehiclePassport.Companion.compareBySeriesAndNumber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.Objects.hash
import java.util.TreeSet

internal class VehiclePassportTests {
    @Test
    fun printTest(){
        val passport = VehiclePassport("12 ТК","233333", LocalDate.of(2004,12,23))
        Assertions.assertEquals("Серия:12 ТК,Номер:233333,Дата выдачи:2004-12-23",passport.toString())
    }


    @Test
    fun compareEqualNumberTest(){
        val passport = VehiclePassport("12 ТК","233333", LocalDate.now())
        val passport2 = VehiclePassport("12 ТК","233333", LocalDate.now())
        Assertions.assertEquals(0,VehiclePassport.compareByNumber(passport,passport2))
    }
    @Test
    fun compareNumberTest(){
        val passport = VehiclePassport("12 ТК","233333", LocalDate.now())
        val passport2 = VehiclePassport("12 ТК","233334", LocalDate.now())
        Assertions.assertEquals(-1,VehiclePassport.compareByNumber(passport,passport2))
        Assertions.assertEquals(1,VehiclePassport.compareByNumber(passport2,passport))
    }


    @Test
    fun compareEqualSeriesest(){
        val passport = VehiclePassport("12 ТК","233333", LocalDate.now())
        val passport2 = VehiclePassport("12 ТК","233333", LocalDate.now())
        Assertions.assertEquals(0,VehiclePassport.compareBySeries(passport,passport2))
    }
    @Test
    fun compareSeriesTest(){
        val passport = VehiclePassport("34 ТК","233333", LocalDate.now())
        val passport2 = VehiclePassport("13 ТК","233334", LocalDate.now())
        Assertions.assertEquals(true,VehiclePassport.compareBySeries(passport,passport2)>0)
        Assertions.assertEquals(true,VehiclePassport.compareBySeries(passport2,passport)<0)
    }


    @Test
    fun equalsNumberSeriesTest(){
        val passport = VehiclePassport("34 ТК","233333", LocalDate.now())
        val passport2 = VehiclePassport("13 ТК","233334", LocalDate.now())
        Assertions.assertEquals(false, passport == passport2)
    }
    @Test
    fun equalsTrueNumberSeriesTest(){
        val passport = VehiclePassport("13 ТК","233334", LocalDate.now())
        val passport2 = VehiclePassport("13 ТК","233334", LocalDate.now())
        Assertions.assertEquals(true, passport == passport2)
    }


    @Test
    fun sortByDataTest(){
        val passport = VehiclePassport("13 ТК","233333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("13 ТК","233333", LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport("13 ТК","233333", LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport2,passport,passport3), listToSort.sortedWith(::compareByDate))
    }

    @Test
    fun sortBySeriesNumberTest(){
        val passport = VehiclePassport("13 ТК","213333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("13 ТК","253333", LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport("13 ТК","133333", LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport3,passport,passport2), listToSort.sortedWith(::compareBySeriesAndNumber) )
    }

    @Test
    fun sortBySeriesNumberTest2(){
        val passport = VehiclePassport("13 ТК","233333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("13 ТК","233333", LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport("11 ТК","233333", LocalDate.now())

        val listToSort = listOf(passport,passport3,passport2)

        Assertions.assertEquals(listOf(passport3,passport,passport2), listToSort.sortedWith(::compareBySeriesAndNumber) )
    }

    @Test
    fun correctHashSetTest(){
        val hashed = HashSet<VehiclePassport>()

        val passport = VehiclePassport("11 ТК","233333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("12 ТК","233333", LocalDate.of(1234,12,23))
        val passport3 = VehiclePassport("13 ТК","233333", LocalDate.of(1234,12,23))

        hashed.add(passport)
        hashed.add(passport3)
        hashed.add(passport3)

        Assertions.assertEquals(passport3,hashed.first())
        Assertions.assertEquals(passport,hashed.last())
    }

    @Test
    fun correctTreeSetTest(){
        val tree = TreeSet<VehiclePassport>(){el1,el2-> compareByDate(el1,el2) }

        val passport = VehiclePassport("12 ТК","233333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("13 ТК","233333", LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport("11 ТК","233333", LocalDate.of(1199,12,13))
        val passport4 = VehiclePassport("11 ТК","233333", LocalDate.of(1233,12,13))
        tree.add(passport)
        tree.add(passport2)
        tree.add(passport3)
        tree.add(passport4)
        Assertions.assertEquals(passport3,tree.first())
        Assertions.assertEquals(passport,tree.last())
    }

    @Test
    fun correctTreeSetCustomCompTest(){
        val tree = TreeSet<VehiclePassport>(){el1,el2-> compareBySeriesAndNumber(el1,el2) }

        val passport = VehiclePassport("12 ТК","233333", LocalDate.of(1234,12,23))
        val passport2 = VehiclePassport("13 ТК","233333", LocalDate.of(1200,12,23))
        val passport3 = VehiclePassport("11 ТК","233333", LocalDate.of(1199,12,13))
        val passport4 = VehiclePassport("11 ТК","233333", LocalDate.of(1233,12,13))
        tree.add(passport)
        tree.add(passport2)
        tree.add(passport3)
        tree.add(passport4)
        Assertions.assertEquals(passport3,tree.first())
        Assertions.assertEquals(passport2,tree.last())
    }

}
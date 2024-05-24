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
}
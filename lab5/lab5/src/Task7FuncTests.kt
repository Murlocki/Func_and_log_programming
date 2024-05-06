import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Task7FuncTests {
    @Test
    fun nodEndCondTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(true, task7Funcs.nodEndCond(15,5))
        Assertions.assertEquals(false, task7Funcs.nodEndCond(4,5))
    }
    @Test
    fun nodStepTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(0, task7Funcs.nodStepFunc(15,5))
        Assertions.assertEquals(4, task7Funcs.nodStepFunc(4,5))
    }
    @Test
    fun nodExtraCondTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.nodExtraCond(-15,5))
        Assertions.assertEquals(true, task7Funcs.nodExtraCond(0,5))
        Assertions.assertEquals(true, task7Funcs.nodExtraCond(5,0))
        Assertions.assertEquals(false, task7Funcs.nodExtraCond(4,5))
    }
    @Test
    fun nodCallTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(5, task7Funcs.nodCall(-15,5))
        Assertions.assertEquals(5, task7Funcs.nodCall(0,5))
        Assertions.assertEquals(5, task7Funcs.nodCall(5,0))
        Assertions.assertEquals(1, task7Funcs.nodCall(4,5))
    }
    @Test
    fun twoSimpleSpecialCondTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.twoSimpleSpecialCond(-15,5))
        Assertions.assertEquals(true, task7Funcs.twoSimpleSpecialCond(0,5))
        Assertions.assertEquals(true, task7Funcs.twoSimpleSpecialCond(5,0))
        Assertions.assertEquals(false, task7Funcs.twoSimpleSpecialCond(4,5))
    }
    @Test
    fun twoSimpleTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.twoSimple(-15,5))
        Assertions.assertEquals(true, task7Funcs.twoSimple(4,5))
        Assertions.assertEquals(true, task7Funcs.twoSimple(5,4))
        Assertions.assertEquals(false, task7Funcs.twoSimple(0,5))
        Assertions.assertEquals(false, task7Funcs.twoSimple(0,-5))
        Assertions.assertEquals(false, task7Funcs.twoSimple(-5,0))
    }
    @Test
    fun digitsEndTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.digitsEnd(15))
        Assertions.assertEquals(true, task7Funcs.digitsEnd(0))
    }
    @Test
    fun deleteLastDigitTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(1, task7Funcs.deleteLastDigit(15))
        Assertions.assertEquals(0, task7Funcs.deleteLastDigit(0))
        Assertions.assertEquals(0, task7Funcs.deleteLastDigit(5))
        Assertions.assertEquals(4, task7Funcs.deleteLastDigit(43))
    }
    @Test
    fun nextDelTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(14, task7Funcs.nextDel(15))
        Assertions.assertEquals(0, task7Funcs.nextDel(1))
        Assertions.assertEquals(4, task7Funcs.nextDel(5))
    }
    @Test
    fun endOfDelTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.endOfDel(15))
        Assertions.assertEquals(true, task7Funcs.endOfDel(0))
        Assertions.assertEquals(false, task7Funcs.endOfDel(5))
    }
    @Test
    fun getNextCountCondTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(true, task7Funcs.getNextCountCond(15,4))
        Assertions.assertEquals(false, task7Funcs.getNextCountCond(0,5))
        Assertions.assertEquals(false, task7Funcs.getNextCountCond(5,0))
        Assertions.assertEquals(false, task7Funcs.getNextCountCond(5,10))
    }
    @Test
    fun getNextCountTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(15, task7Funcs.getNextCount(15,4,4))
        Assertions.assertEquals(1, task7Funcs.getNextCount(0,5,6))
        Assertions.assertEquals(5, task7Funcs.getNextCount(5,0,2))
        Assertions.assertEquals(5, task7Funcs.getNextCount(5,2,0))
    }
    @Test
    fun countSimpleDigitsTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(1, task7Funcs.countSimpleDigitsCall(15,4))
        Assertions.assertEquals(0, task7Funcs.countSimpleDigitsCall(0,5))
        Assertions.assertEquals(2, task7Funcs.countSimpleDigitsCall(4,17))

    }
    @Test
    fun maxDelTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(3, task7Funcs.maxDelCall(15))
        Assertions.assertEquals(1, task7Funcs.maxDelCall(0))
        Assertions.assertEquals(17, task7Funcs.maxDelCall(-17))
        Assertions.assertEquals(1, task7Funcs.maxDelCall(1))
    }
    @Test
    fun checkCountTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(1, task7Funcs.checkCount(15,15,4,2,1))
        Assertions.assertEquals(1, task7Funcs.checkCount(15,0,4,2,1))
        Assertions.assertEquals(2, task7Funcs.checkCount(4,15,1,2,1))
    }
    @Test
    fun equalsCustomTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(true, task7Funcs.equalsCustom(15,15))
        Assertions.assertEquals(false, task7Funcs.equalsCustom(-10,10))
        Assertions.assertEquals(true, task7Funcs.equalsCustom(-10,-10))
    }
    @Test
    fun greaterCustomTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(false, task7Funcs.greaterCustom(15,15))
        Assertions.assertEquals(false, task7Funcs.greaterCustom(-10,10))
        Assertions.assertEquals(true, task7Funcs.greaterCustom(10,-10))
    }
    @Test
    fun returnSecondIntArgTest(){
        val task7Funcs = Task7Functions()
        Assertions.assertEquals(15, task7Funcs.returnSecondIntArg(15,15))
        Assertions.assertEquals(10, task7Funcs.returnSecondIntArg(-10,10))
        Assertions.assertEquals(-10, task7Funcs.returnSecondIntArg(10,-10))
    }
}
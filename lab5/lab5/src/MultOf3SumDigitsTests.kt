import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MultOf3SumDigitsTests {
    //Сумма цифр кратных 3 цикл
    @Test
    fun cycleSum3DigitsPositive(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(15, cycFuncs.sum3Digits(8976))
    }
    @Test
    fun cycleSum3DigitsNegative(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(15, cycFuncs.sum3Digits(-8976))
    }
    @Test
    fun cycleSum3DigitsZero(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(0, cycFuncs.sum3Digits(0))
    }

    //Сумма цифр кратных 3рекурсия вверх
    @Test
    fun upRecSum3DigitsPositive(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(15, recFuncs.sum3DigitsUp(8976))
    }
    @Test
    fun upRecSum3DigitsNegative(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(15, recFuncs.sum3DigitsUp(-8976))
    }
    @Test
    fun upRecSum3DigitsZero(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(0, recFuncs.sum3DigitsUp(0))
    }


    //Сумма цифр кратных 3 хвостовая рекурсия
    @Test
    fun tailRecSum3DigitsPositive(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(15, tailRecFuncs.sum3DigitsUpCall(8976))
    }
    @Test
    fun tailRecSum3DigitsNegative(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(15, tailRecFuncs.sum3DigitsUpCall(-8976))
    }
    @Test
    fun tailRecSum3DigitsZero(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(0, tailRecFuncs.sum3DigitsUpCall(0))
    }
}
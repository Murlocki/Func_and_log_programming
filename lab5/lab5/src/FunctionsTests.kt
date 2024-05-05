import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class FunctionsTests {
    //Макс цифра цикл
    @Test
    fun cycleMaxDigitPositive(){
        val cycFuncs = CycleFunctions()
        assertEquals(9,cycFuncs.maxDigit(8976))
    }
    @Test
    fun cycleMaxDigitNegative(){
        val cycFuncs = CycleFunctions()
        assertEquals(9,cycFuncs.maxDigit(-8976))
    }
    @Test
    fun cycleMaxDigitZero(){
        val cycFuncs = CycleFunctions()
        assertEquals(0,cycFuncs.maxDigit(0))
    }

    //Макс цифра рекурсия вверх
    @Test
    fun upRecMaxDigitPositive(){
        val recFuncs = UpRecFunctions()
        assertEquals(9,recFuncs.maxDigitUp(8976))
    }
    @Test
    fun upRecMaxDigitNegative(){
        val recFuncs = UpRecFunctions()
        assertEquals(9,recFuncs.maxDigitUp(-8976))
    }
    @Test
    fun upRecMaxDigitZero(){
        val recFuncs = UpRecFunctions()
        assertEquals(0,recFuncs.maxDigitUp(0))
    }


    //Макс цифра хвостовая рекурсия
    @Test
    fun tailRecMaxDigitPositive(){
        val tailRecFuncs = TailRecFunctions()
        assertEquals(9,tailRecFuncs.maxDigitTailCall(8976))
    }
    @Test
    fun tailRecMaxDigitNegative(){
        val tailRecFuncs = TailRecFunctions()
        assertEquals(9,tailRecFuncs.maxDigitTailCall(-8976))
    }
    @Test
    fun tailRecMaxDigitZero(){
        val tailRecFuncs = TailRecFunctions()
        assertEquals(0,tailRecFuncs.maxDigitTailCall(0))
    }

    
}
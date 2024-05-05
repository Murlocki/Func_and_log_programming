import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DividersCountTests {
    //Количество делителей цикл
    @Test
    fun cycleDividerCountPositive(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(5, cycFuncs.findDividersCount(16))
    }
    @Test
    fun cycleDividerCountNegative(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(5, cycFuncs.findDividersCount(-16))
    }
    @Test
    fun cycleDividerCountZero(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(0, cycFuncs.findDividersCount(0))
    }
    @Test
    fun cycleDividerCountOne(){
        val cycFuncs = CycleFunctions()
        Assertions.assertEquals(1, cycFuncs.findDividersCount(1))
    }

    //Количество делителей рекурсия вверх
    @Test
    fun upRecDividerCountPositive(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(5, recFuncs.findDividersCountUpCall(16))
    }
    @Test
    fun upRecDividerCountNegative(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(5, recFuncs.findDividersCountUpCall(-16))
    }
    @Test
    fun upRecDividerCountOne(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(1, recFuncs.findDividersCountUpCall(1))
    }
    @Test
    fun upRecDividerCountZero(){
        val recFuncs = UpRecFunctions()
        Assertions.assertEquals(0, recFuncs.findDividersCountUpCall(0))
    }


    //Количество делителей хвостовая рекурсия
    @Test
    fun tailRecDividerCountPositive(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(5, tailRecFuncs.findDividersCountUpCall(16))
    }
    @Test
    fun tailRecDividerCountNegative(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(5, tailRecFuncs.findDividersCountUpCall(-16))
    }
    @Test
    fun tailRecDividerCountZero(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(0, tailRecFuncs.findDividersCountUpCall(0))
    }
    @Test
    fun tailRecDividerCountOne(){
        val tailRecFuncs = TailRecFunctions()
        Assertions.assertEquals(1, tailRecFuncs.findDividersCountUpCall(1))
    }
}
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class HighOrderFunctionTests {
        //Тест на корректность принятия и применения функции
        //Для циклических функций
        @Test
        fun callDigitFuncInputCycle(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,CycleFunctions()::sum3Digits))
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->CycleFunctions().maxDigit(k)})
        }
        //Для рекурсии вверх
        @Test
        fun callDigitFuncInputRec(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,UpRecFunctions()::sum3DigitsUp))
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->UpRecFunctions().maxDigitUp(k)})
        }
        //Для хвостовой рекурсии
        @Test
        fun callDigitFuncInputRecTail(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,TailRecFunctions()::sum3DigitsTailCall))
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->TailRecFunctions().maxDigitTailCall(k)})
        }

        //Тестируем верность возвращаемых значений
        //Для циклов
        @Test
        fun cycleHighOrderTests(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,CycleFunctions()::sum3Digits))
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(-8976,CycleFunctions()::sum3Digits))
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0,CycleFunctions()::sum3Digits))

            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->CycleFunctions().maxDigit(k)})
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(-8976){k->CycleFunctions().maxDigit(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->CycleFunctions().maxDigit(k)})

            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(16){k->CycleFunctions().findDividersCount(k)})
            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(-16){k->CycleFunctions().findDividersCount(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->CycleFunctions().findDividersCount(k)})
        }

        //Для рекурсии вверх
        @Test
        fun recHighOrderTests(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,UpRecFunctions()::sum3DigitsUp))
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(-8976,UpRecFunctions()::sum3DigitsUp))
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0,UpRecFunctions()::sum3DigitsUp))

            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->UpRecFunctions().maxDigitUp(k)})
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(-8976){k->UpRecFunctions().maxDigitUp(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->UpRecFunctions().maxDigitUp(k)})

            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(16){k->UpRecFunctions().findDividersCountUpCall(k)})
            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(-16){k->UpRecFunctions().findDividersCountUpCall(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->UpRecFunctions().findDividersCountUpCall(k)})
        }

        //Для хвостовой рекурсии
        @Test
        fun tailRecHighOrderTests(){
            val highOrderFuncs = HighOrder()
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(8976,TailRecFunctions()::sum3DigitsTailCall))
            Assertions.assertEquals(15, highOrderFuncs.callDigitFunc(-8976,TailRecFunctions()::sum3DigitsTailCall))
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0,TailRecFunctions()::sum3DigitsTailCall))

            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(8976){k->TailRecFunctions().maxDigitTailCall(k)})
            Assertions.assertEquals(9, highOrderFuncs.callDigitFunc(-8976){k->TailRecFunctions().maxDigitTailCall(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->TailRecFunctions().maxDigitTailCall(k)})

            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(16){k->TailRecFunctions().findDividersCountTailCall(k)})
            Assertions.assertEquals(5, highOrderFuncs.callDigitFunc(-16){k->TailRecFunctions().findDividersCountTailCall(k)})
            Assertions.assertEquals(0, highOrderFuncs.callDigitFunc(0){k->TailRecFunctions().findDividersCountTailCall(k)})
        }
}
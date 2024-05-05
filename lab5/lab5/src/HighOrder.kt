class HighOrder {
    //Функция высшего порядка принимающая другую функцию
    fun callDigitFunc(n:Int, f:(Int)->Int):Int = f(n);

    //Примеры передачи функций????хз из задания 4 понятно целое ничего
    fun maxDigTail(n:Int):Int = callDigitFunc(n,TailRecFunctions()::maxDigitTailCall)
    fun maxDigRec(n:Int):Int = callDigitFunc(n){ _: Int -> UpRecFunctions().maxDigitUp(n) }
    fun maxDigCyc(n:Int):Int = callDigitFunc(n){k->CycleFunctions().maxDigit(k)}

    fun sum3DigitCyc(n:Int):Int = callDigitFunc(n,CycleFunctions()::sum3Digits)
    fun sum3DigitTail(n:Int):Int = callDigitFunc(n,TailRecFunctions()::sum3DigitsUpCall)
    fun sum3DigitRec(n:Int):Int = callDigitFunc(n,UpRecFunctions()::sum3DigitsUp)

    fun divierCountCyc(n:Int):Int = callDigitFunc(n,CycleFunctions()::findDividersCount)
    fun divierCountTail(n:Int):Int = callDigitFunc(n,TailRecFunctions()::findDividersCountUpCall)
    fun divierCountRec(n:Int):Int = callDigitFunc(n,UpRecFunctions()::findDividersCountUpCall)
}
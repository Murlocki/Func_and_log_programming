import kotlin.math.abs
import kotlin.math.max

class UpRecFunctions {

    //Функция высшего порядка для обработки цифр числа
    fun digitsRec(n: Int,initResult:Int,condStop:(Int)->Boolean,processFunc:(Int,Int)->Int):Int =
        if(condStop(n)) initResult else processFunc(abs(n)%10,digitsRec(abs(n)/10,initResult,condStop,processFunc))

    //Рекурсия вверх для поиска макс цифры числа
    fun maxDigitUp(n:Int):Int = digitsRec(n,0,{k:Int->k==0},{digit:Int,prevResult:Int->max(digit,prevResult)})
    //Рекурсия вверх для поиска суммы цифр кратных 3
    fun sum3DigitsUp(n: Int):Int = digitsRec(n,0,{k:Int->k==0},{digit:Int,prevResult:Int->if(digit%3==0)prevResult+digit else prevResult})


    //Функция высшего порядка для обработки делителей
    fun dividerProcessRec(n:Int, initResult: Int,currentDiv: Int, condStop: (Int, Int) -> Boolean, processFunc: (Int, Int, Int) -> Int):Int =
        if(condStop(n,currentDiv))initResult else processFunc(n,currentDiv,dividerProcessRec(n,initResult,currentDiv+1,condStop,processFunc))

    //Рекурсия вверх для нахождения количества делителей целого числа
    fun findDividersCountUpCall(n: Int):Int = if(n==0) 0 else dividerProcessRec(abs(n),1,1,{k:Int,currentDiv:Int->!(currentDiv<=(k/2).toInt())},
        {k:Int,currentDiv:Int,prevResult:Int->if(k%currentDiv==0) prevResult+1 else prevResult})


}
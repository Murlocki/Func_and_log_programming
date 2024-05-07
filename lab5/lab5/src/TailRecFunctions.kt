import kotlin.math.abs
import kotlin.math.max

class TailRecFunctions {
    //Функция высшего порядка для обработки цифр с помощью хвостовой рекурсии
    tailrec fun digitProcessTailrec(n:Int,currentResult:Int,condStopFunc:(Int)->Boolean,processFunc:(Int,Int)->Int):Int =
        if(condStopFunc(n))currentResult else digitProcessTailrec(n/10,processFunc(currentResult,n%10),condStopFunc,processFunc)


    //Хвостовая рекурсия для поиска макс цифры числа
    fun maxDigitTailCall(n:Int):Int = digitProcessTailrec(abs(n),0,{k:Int->k==0},{result:Int,digit:Int->max(result,digit)})
    //Хвостовая рекурсия для поиска суммы цифр кратных 3
    fun sum3DigitsTailCall(n: Int):Int = digitProcessTailrec(abs(n),0,{k:Int->k==0},{prevResult:Int,digit:Int->if(digit%3==0) prevResult+digit else prevResult})


    //Функция высшего порядка для обработки делителей числа с помощью хвостовой рекурсии
    tailrec fun dividersProcessTailrec(n:Int,currentDiv: Int,currentResult: Int,condStopFunc: (Int,Int) -> Boolean,processFunc: (Int, Int,Int) -> Int):Int =
        if(condStopFunc(n,currentDiv))currentResult else dividersProcessTailrec(n,currentDiv+1,processFunc(n,currentDiv,currentResult),condStopFunc,processFunc)
    //Хвостовая рекурсия для нахождения количества делителей целого числа
    fun findDividersCountTailCall(n: Int):Int = if(n==0) 0 else dividersProcessTailrec(abs(n),1,1,{k:Int,currentDiv:Int->!(currentDiv<=(k/2).toInt())},
        {k:Int,currentDiv:Int,prevResult:Int->if(k%currentDiv==0) prevResult+1 else prevResult})
}
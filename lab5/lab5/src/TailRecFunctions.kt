import kotlin.math.abs
import kotlin.math.max

class TailRecFunctions {
    //Хвостовая рекурсия для поиска макс цифры числа
    fun maxDigitTailCall(n:Int):Int = maxDigitTail(abs(n),0)
    tailrec fun maxDigitTail(n: Int,currentMax:Int):Int = if(n==0) currentMax else maxDigitTail(n/10,max(currentMax,n%10))
    //Хвостовая рекурсия для поиска суммы цифр кратных 3
    fun sum3DigitsTailCall(n: Int):Int = sum3DigitsTail(abs(n),0)
    tailrec fun sum3DigitsTail(n: Int,currentSum:Int):Int = if(n==0) currentSum else
        sum3DigitsTail(n/10,if(n%10%3==0) currentSum+n%10 else currentSum)

    //Хвостовая рекурсия для нахождения количества делителей целого числа
    fun findDividersCountTailCall(n: Int):Int = if(n==0) 0 else findDividersCountTail(abs(n),1,1)
    tailrec fun findDividersCountTail(n: Int,currentDiv:Int,countDivs:Int):Int = if(!(currentDiv<=(n/2).toInt())) countDivs
        else findDividersCountTail(n,currentDiv+1,if(n%currentDiv==0) countDivs+1 else countDivs);
}
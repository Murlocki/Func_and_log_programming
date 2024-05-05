import kotlin.math.abs
import kotlin.math.max

class TailRecFunctions {
    //Хвостовая рекурсия для поиска макс цифры числа
    fun maxDigitTailCall(n:Int):Int = maxDigitTail(abs(n),0)
    tailrec fun maxDigitTail(n: Int,currentMax:Int):Int = if(n==0) currentMax else maxDigitTail(n/10,max(currentMax,n%10))
    //Хвостовая рекурсия для поиска суммы цифр кратных 3
    fun sum3DigitsUpCall(n: Int):Int = sum3DigitsUp(abs(n),0)
    tailrec fun sum3DigitsUp(n: Int,currentSum:Int):Int = if(n==0) currentSum else
        sum3DigitsUp(n/10,if(n%10%3==0) currentSum+n%10 else currentSum)

    //Хвостовая рекурсия для нахождения количества делителей целого числа
    fun findDividersCountUpCall(n: Int):Int? = if(n==0) null else findDividersCountUp(abs(n),1,1)
    tailrec fun findDividersCountUp(n: Int,currentDiv:Int,countDivs:Int):Int = if(!(currentDiv<=(n/2).toInt())) countDivs
        else findDividersCountUp(n,currentDiv+1,if(n%currentDiv==0) countDivs+1 else countDivs);
}
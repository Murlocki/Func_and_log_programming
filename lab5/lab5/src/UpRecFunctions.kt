import kotlin.math.abs
import kotlin.math.max

class UpRecFunctions {
    //Рекурсия вверх для поиска макс цифры числа
    fun maxDigitUp(n:Int):Int = if(n==0) 0 else max(abs(n)%10,maxDigitUp(abs(n)/10))
    //Рекурсия вверх для поиска суммы цифр кратных 3
    fun sum3DigitsUp(n: Int):Int = if(n==0) 0 else when{abs(n)%10%3==0->abs(n)%10;else->0}+sum3DigitsUp(abs(n)/10)

    //Рекурсия вверх для нахождения количества делителей целого числа
    fun findDividersCountUpCall(n: Int):Int? = if(n==0) null else findDividersCountUp(abs(n),1)
    fun findDividersCountUp(n: Int,currentDiv:Int):Int = if(!(currentDiv<=(n/2).toInt())) 1 else
        when{abs(n)%currentDiv==0->1;else->0}+findDividersCountUp(n,currentDiv+1);

}
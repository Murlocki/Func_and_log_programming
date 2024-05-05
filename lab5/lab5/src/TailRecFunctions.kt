import kotlin.math.abs
import kotlin.math.max

class TailRecFunctions {
    //Рекурсия вверх для поиска макс цифры числа
    fun maxDigitTailCall(n:Int):Int = maxDigitTail(abs(n),0)
    tailrec fun maxDigitTail(n: Int,currentMax:Int):Int = if(n==0) currentMax else maxDigitTail(n/10,max(currentMax,n%10))
    
}
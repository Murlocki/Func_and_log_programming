import kotlin.math.abs
import kotlin.math.max

class UpRecFunctions {
    //Рекурсия вверх для поиска макс цифры числа
    fun maxDigitUp(n:Int):Int = if(n==0) 0 else max(abs(n)%10,maxDigitUp(abs(n)/10))

}
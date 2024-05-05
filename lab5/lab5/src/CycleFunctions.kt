import kotlin.math.max

class CycleFunctions {
    //Циклическая функция для подсчета макс цифры числа
    fun maxdigit(n:Int): Int {
        var currentMax:Int=n%10;
        var currentN:Int = n/10;
        while(currentN>0){
            currentMax = max(currentMax,currentN%10);
            currentN = currentN/10;
        }
        return currentMax;
    }
}
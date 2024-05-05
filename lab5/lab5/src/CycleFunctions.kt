import kotlin.math.abs
import kotlin.math.max
import kotlin.math.round

class CycleFunctions {

    //Циклическая функция для превращение дробного числа в целое
    fun shiftToInteger(n:Double): Int{
        var result = n;
        while(round(result)!=result){
            result = result * 10;
        }
        return result.toInt()
    }

    //Циклическая функция для подсчета макс цифры числа
    fun maxdigit(n:Double): Int {
        var currentN:Int = abs(shiftToInteger(n));
        var currentMax:Int=currentN%10;
        while(currentN>0){
            currentMax = max(currentMax,currentN%10);
            currentN /= 10;
        }
        return currentMax;
    }
    //Циклическая функция для нахождения суммы цифр кратных 3
    fun sum3Digits(n:Double):Int{
        var currentN:Int = abs(shiftToInteger(n));
        var digitSum=0;
        while(currentN>0){
            var currentDigit = currentN%10;
            digitSum += if(currentDigit%3==0) currentDigit else 0;
            currentN /= 10;
        }
        return digitSum;
    }
    //Циклическая функция для нахождения делителей целого числа
    fun findDividersCount(n:Int):Int?{
        if (n==0) return null
        var currentDivider = 1;
        var dividerCount = 1;
        while(currentDivider<=(abs(n)/2).toInt()){
            dividerCount+= if(n%currentDivider==0) 1 else 0;
            currentDivider+=1;
        }
        return dividerCount;
    }
}
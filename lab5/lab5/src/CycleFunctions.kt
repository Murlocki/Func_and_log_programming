import kotlin.math.abs
import kotlin.math.max
import kotlin.math.round

class CycleFunctions {

    //Функция высшего порядка для обработки цифр числа
    fun cycleDigitHighOrder(n:Int,initResult: Int,stopCondFunc:(Int)->Boolean,processFunc:(Int,Int)->Int):Int {
        var currentN = abs(n)
        var result = initResult
        while(stopCondFunc(currentN)){
            result = processFunc(result,currentN%10)
            currentN=currentN/10
        }
        return result
    }

    //Циклическая функция для подсчета макс цифры числа
    fun maxDigit(n:Int):Int = cycleDigitHighOrder(n,0,{k:Int->k>0},{k:Int,m:Int->max(k,m)})

    //Циклическая функция для нахождения суммы цифр кратных 3
    fun sum3Digits(n: Int) = cycleDigitHighOrder(n,0,{k:Int->k>0},{k:Int,m:Int->if(m%3==0)m+k else k})


    //Функция высшего порядка для обработки делителей
    fun cycleDividers(n:Int,initResult:Int,stopCondFunc:(Int,Int)->Boolean,processFunc:(Int,Int,Int)->Int):Int {
        if (n==0) return 0
        val currentN = abs(n)
        var currentDivider = 1;
        var result = initResult;
        while(stopCondFunc(currentN,currentDivider)){
            result = processFunc(currentN,currentDivider,result)
            currentDivider+=1;
        }
        return result;
    }

    //Циклическая функция для нахождения делителей целого числа
    fun findDividersCount(n:Int):Int = cycleDividers(n,1,{m:Int,divider:Int->divider<=(m/2).toInt()}){n:Int,divider:Int,prevResult:Int->if(n%divider==0)prevResult+1 else prevResult}
}
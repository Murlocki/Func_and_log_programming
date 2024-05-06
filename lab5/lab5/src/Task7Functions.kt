import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Task7Functions {
    fun nod(first:Int,second:Int):Int = if(first % second == 0) second else nod(second,first%second)
    fun twoSimple(first: Int,second: Int):Boolean = if(first==0||second==0) false else
        if(nod(abs(max(first,second)),abs(min(first,second)))==1) true else false

    fun digitsEnd(n:Int):Boolean = if(n==0) true else false
    fun deleteLastDigit(n:Int):Int = n/10

    fun nextDel(n:Int):Int = n-1
    fun endOfDel(n: Int):Boolean = if(n>1) false else true

    fun countSimpleDigits(currentDel:Int,inputNumber:Int):Int =
        if(digitsEnd(inputNumber)) 0 else countSimpleDigits(currentDel,deleteLastDigit(inputNumber))+ if(twoSimple(currentDel,inputNumber%10)) 1 else 0
    fun maxDel(inputNumber:Int,currentDel: Int,currentMaxDel:Int,currentMaxCount:Int):Int =
        if(endOfDel(currentDel)) currentMaxDel else
            if(inputNumber%currentDel != 0) maxDel(inputNumber,nextDel(currentDel),currentMaxDel,currentMaxCount)
            else
                if(countSimpleDigits(currentDel,inputNumber)>currentMaxCount)
                    maxDel(inputNumber,nextDel(currentDel),currentDel,countSimpleDigits(currentDel,inputNumber))
                else maxDel(inputNumber,nextDel(currentDel),currentMaxDel,currentMaxCount)
    fun maxDelCall(inputNumber: Int) = maxDel(inputNumber,inputNumber,1,0)
    fun highOrderProcess(input:Int,stepFunc:(Int)->Int,conditionFunc:(Int)->(Boolean),processFunc:(Int)->Int,result:Int):Int=
        if(conditionFunc(input))highOrderProcess(stepFunc(input),stepFunc,conditionFunc,processFunc,processFunc(input))
        else result
}
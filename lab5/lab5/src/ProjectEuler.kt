import java.lang.Math.pow
import kotlin.math.log
import kotlin.math.truncate

class ProjectEuler {
    //Обрез слева
    fun cutLeft(n:Int):Int = n/10

    //Обрез справа
    fun cutRight(n:Int):Int = (n%(pow(10.0,calcPowForCut(n).toDouble())).toInt())
    //Вычисление нужной степени
    fun calcPowForCut(n: Int):Int = truncate(log(n.toDouble(),10.0)).toInt()

    //Проверка на простоту
    fun checkIfSimpleCall(n:Int):Boolean = if(n==1||n==0) false else checkIfSimple(n,2,true)
    tailrec fun checkIfSimple(n:Int,currentDel:Int,simple:Boolean):Boolean =
        if((currentDel>(n/2).toInt()) || !simple) simple
        else checkIfSimple(n,currentDel+1,if(n%currentDel==0) false else true )
    //Проверка на отсутствие чисел,сообщающих о не простоте
    fun checkIfNoSpecNumbers(n:Int):Boolean = when{
        n<10&&n in intArrayOf(2,3,5,7)->true;
        (n%10 in intArrayOf(0,2,4,5,6,8))->false;
        else-> checkIfNoSpecNumbers(n/10)
    }

    //Функция высшего порядка для осуществления срезов
    tailrec fun cutAndCheckSimple(n:Int,simple: Boolean,cutFunc:(Int)->(Int)):Boolean =
        if (!simple) false
        else if (n<10) if(checkIfSimpleCall(n)) true else false
            else cutAndCheckSimple(cutFunc(n),if (checkIfSimpleCall(n)) true else false,cutFunc);

    //Основная функция
    fun sumOfSimpleFromLeftAndRightCall():Int = sumOfSimpleFromLeftAndRight(23,0)
    tailrec fun sumOfSimpleFromLeftAndRight(currentNumb:Int,currentSum:Int):Int {
        if (currentNumb > 1000000) return currentSum
        else return sumOfSimpleFromLeftAndRight(
            currentNumb + 2,
            if (checkIfNoSpecNumbers(currentNumb)&&cutAndCheckSimple(currentNumb,true, ::cutLeft) && cutAndCheckSimple(currentNumb,true, ::cutRight)) currentSum + currentNumb
            else currentSum
        )
    }
}

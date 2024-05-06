import java.lang.Math.pow
import kotlin.math.log
import kotlin.math.truncate

class ProjectEuler {
    //Обрез слева
    fun cutLeft(n:Int):Int = n/10


    //Обрез справа
    fun cutRight(n:Int):Int = (n%(tenPower(calcPowForCut(n))).toInt())
    //Вычисление значения нужной степени
    fun calcPowForCut(n: Int):Int = truncate(log(n.toDouble(),10.0)).toInt()
    //Возведение 10 в нужную степень
    fun tenPower(degree:Int):Int = pow(10.0,degree.toDouble()).toInt()


    //Проверка на простоту
    fun checkIfSimpleCall(n:Int):Boolean = if(n==1||n==0) false else checkIfSimple(n,2,true)
    tailrec fun checkIfSimple(n:Int,currentDel:Int,simple:Boolean):Boolean =
        if(checkIfSimpleStopCond(currentDel,n,simple)) simple
        else checkIfSimple(n,getNextDiv(currentDel),checkIfDiv(n,currentDel) )
    //Условие остановка проверки простоты
    fun checkIfSimpleStopCond(currentDel: Int,n:Int,simple: Boolean):Boolean = if((currentDel>(n/2).toInt()) || !simple) true else false
    //Обновление значение простоты на основании деления
    fun checkIfDiv(n: Int,currentDel: Int) = if(n%currentDel==0) false else true
    //Получение нового делителя
    fun getNextDiv(currentDel: Int) = currentDel+1


    //Проверка на отсутствие чисел,сообщающих о не простоте
    tailrec fun checkIfNoSpecNumbers(n:Int):Boolean = when{
        n<10->checkIfSumpleDigit(n);
        checkMultMarker(n)->false;
        else-> checkIfNoSpecNumbers(n/10)
    }
    //Функция для проверки является ли цифра маркером кратности
    fun checkMultMarker(n: Int) = if(n%10 in intArrayOf(0,2,4,5,6,8)) true else false


    //Функция высшего порядка для осуществления срезов
    tailrec fun cutAndCheckSimple(n:Int,simple: Boolean,cutFunc:(Int)->(Int)):Boolean =
        when{
            !simple->false;
            n<10->checkIfSumpleDigit(n);
            else->cutAndCheckSimple(cutFunc(n),getNewSimple(n),cutFunc)
        }

    //Обновляем условие останова на основании простоты n
    fun getNewSimple(n:Int):Boolean = if (checkIfSimpleCall(n)) true else false
    //Проверяем простая ли у нас цифра
    fun checkIfSumpleDigit(n:Int) = if(checkIfSimpleCall(n)) true else false


    //Функция шага основной функции
    fun stepFunc(n:Int):Int = n+2

    //Функция проверки условия остановки основной функции
    fun stopCond(n:Int):Boolean = if(n>1000000) true else false

    //Функция проверки условия на добавление числа к сумме
    fun addCond(currentNumb: Int):Boolean = (checkIfNoSpecNumbers(currentNumb) && cutAndCheckSimple(currentNumb,true, ::cutLeft) && cutAndCheckSimple(currentNumb,true, ::cutRight))
    //Функция получения новой суммы
    fun getNewSum(currentNumb: Int,currentSum: Int):Int = if(addCond(currentNumb)) currentSum+currentNumb else currentSum
    //Основная функция
    fun sumOfSimpleFromLeftAndRightCall():Int = sumOfSimpleFromLeftAndRight(11,0)
    tailrec fun sumOfSimpleFromLeftAndRight(currentNumb:Int,currentSum:Int):Int {
        if (stopCond(currentNumb)) return currentSum
        else return sumOfSimpleFromLeftAndRight(
            stepFunc(currentNumb),
            getNewSum(currentNumb,currentSum)
        )
    }
}

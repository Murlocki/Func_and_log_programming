import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Task7Functions {
    //Функция подсчета нода с помощью функции высшего порядка
    fun nod(first:Int,second:Int):Int =
        twoArgRecursion(::nodEndCond, endResultFunc = ::returnSecondIntArg, firstArg = first, secondArg = second,
            firstArgStepFunc = ::returnSecondIntArg,
            secondArgStepFunc = ::nodStepFunc )

    //Функция окончания подсчета нода
    fun nodEndCond(first: Int,second: Int) = twoArgCond(::equalsCustom,first % second,0)
    //Функция шага для нода
    fun nodStepFunc(first:Int,second:Int) = first%second

    //Функция вызова подсчета ножа
    fun nodCall(first: Int,second: Int) = nod(abs(max(first,second)),abs(min(first,second)))

    //Функция проверки на простоту
    fun twoSimple(first: Int,second: Int):Boolean = if(twoSimpleSpecialCond(first,second)) false else
        twoArgCond(::equalsCustom,nodCall(first,second),1)

    fun twoSimpleSpecialCond(first: Int,second: Int):Boolean = twoArgCond(::equalsCustom,first,0)||twoArgCond(::equalsCustom,second,0)

    //Функция проверки окончания цифр числа
    fun digitsEnd(n:Int):Boolean = twoArgCond(::equalsCustom,n,0)
    //Функция шага по цифрам
    fun deleteLastDigit(n:Int):Int = n/10


    //Функция получения след делителя
    fun nextDel(n:Int):Int = n-1
    //Функция проверки окончания делителей
    fun endOfDel(n: Int):Boolean = !twoArgCond(::greaterCustom,n,1)


    //Вызов подсчета взаимнопростых с делителем цифр числа
    fun countSimpleDigitsCall(currentDel: Int,inputNumber:Int):Int =
        countSimpleDigits(currentDel, inputNumber,0 )
    //Основная рекурсия для подсчета взаимнопростых с делителем цифр числа
   tailrec fun countSimpleDigits(currentDel:Int,inputNumber:Int,currentCount:Int):Int =
       threeArgRecursion(condStopFunc = {_:Int,inpNumb:Int,_:Int->digitsEnd(inpNumb)},
           endResultFunc = {_:Int,_:Int,curCount:Int->curCount},
           firstArgStepFunc = {f:Int,_:Int,_:Int->f},
           secondArgStepFunc = {_:Int,s:Int,_:Int->deleteLastDigit(s)},
           thirdArgStepFunc = {f:Int,s:Int,th:Int->getNextCount(th,f,s)},
           firstArg = currentDel, secondArg = inputNumber, thirdArg = currentCount)

    //Получение следующего числа цифр
    fun getNextCount(currentCount: Int,currentDel: Int,inputNumber: Int):Int =
        if(getNextCountCond(currentDel,inputNumber%10))
            currentCount+1
        else
            currentCount
    //Условие увеличения количества взаимнопростых с делителем цифр
    fun getNextCountCond(first: Int,second: Int) = twoArgCond({n:Any,m:Any->twoSimple(n.toString().toInt(),m.toString().toInt())},first,second)


    tailrec fun maxDel(inputNumber:Int,currentDel: Int,currentMaxDel:Int,currentMaxCount:Int):Int =
        if(endOfDel(currentDel)) currentMaxDel
        else
            if(!(twoArgCond(::equalsCustom,inputNumber%currentDel,0)))
                maxDel(inputNumber,nextDel(currentDel),currentMaxDel,currentMaxCount)
            else
                maxDel(inputNumber,nextDel(currentDel),
                    checkCount(currentDel,inputNumber,currentMaxCount,currentDel,currentMaxDel),
                    checkCount(currentDel,inputNumber,currentMaxCount,countSimpleDigitsCall(currentDel,inputNumber),currentMaxCount)
                )
    fun maxDelCall(n:Int) = maxDel(n,n,1,0)
    fun checkCount(currentDel: Int,inputNumber: Int,currentMaxCount: Int,result1:Int,result2:Int) =
        if(twoArgCond(::greaterCustom,countSimpleDigitsCall(currentDel,inputNumber),currentMaxCount))
            result1
        else
            result2


    //Функции для передачи как аргументы
    fun equalsCustom(firstArg: Any,secondArg: Any) = firstArg == secondArg
    fun greaterCustom(firstArg: Any,secondArg: Any):Boolean = firstArg.toString().toInt().compareTo(secondArg.toString().toInt()) == 1

    fun returnFirstIntArg(arg1:Int,arg2:Int) = arg1
    fun returnSecondIntArg(arg1:Int,arg2:Int) = arg2

    //Функции высших порядков
    fun twoArgCond(cond:(Any,Any)->Boolean,firstArg:Int,secondArg:Int) = cond(firstArg,secondArg)

    //Шаблон для функции рекурсии от 2-ух аргументов
    //Не бейте плиз
    tailrec fun twoArgRecursion(condStopFunc:(Int,Int)->Boolean,endResultFunc:(Int,Int)->Int,firstArgStepFunc:(Int,Int)->Int,secondArgStepFunc:(Int,Int)->Int,firstArg:Int,secondArg:Int):Int=
        if(condStopFunc(firstArg,secondArg)) endResultFunc(firstArg,secondArg)
        else
            twoArgRecursion(
                condStopFunc,
                endResultFunc,
                firstArgStepFunc,
                secondArgStepFunc,
                firstArgStepFunc(firstArg,secondArg),
                secondArgStepFunc(firstArg,secondArg))

    //Шаблон для функции рекурсии от 3-ух аргументов
    //Не бейте плиз
    tailrec fun threeArgRecursion(condStopFunc:(Int,Int,Int)->Boolean,endResultFunc:(Int,Int,Int)->Int,
                                  firstArgStepFunc:(Int,Int,Int)->Int,secondArgStepFunc:(Int,Int,Int)->Int, thirdArgStepFunc:(Int,Int,Int)->Int,
                                  firstArg:Int,secondArg:Int,thirdArg:Int):Int =
        if(condStopFunc(firstArg,secondArg,thirdArg)) endResultFunc(firstArg,secondArg,thirdArg)
        else
            threeArgRecursion(
                condStopFunc,
                endResultFunc,
                firstArgStepFunc,
                secondArgStepFunc,
                thirdArgStepFunc,
                firstArgStepFunc(firstArg,secondArg,thirdArg),
                secondArgStepFunc(firstArg,secondArg,thirdArg),
                thirdArgStepFunc(firstArg,secondArg,thirdArg))

}
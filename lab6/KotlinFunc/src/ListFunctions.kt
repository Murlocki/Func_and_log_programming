import javax.swing.plaf.basic.BasicSplitPaneDivider
import kotlin.math.abs
import kotlin.math.absoluteValue

class ListFunctions {

    //Функция вызова подсчета количества элементов являющихся квадратами какого-то из элементов списка
    fun findSquaresOfElementsCall(list: MutableList<Int>):Int =
        findSquaresOfElements(list,list.iterator(),0)

    //Функция проверки содержится ли квадрат элемента в списке
    fun checkIfSqaure(element: Int,list: MutableList<Int>):Boolean = if(list.contains((Math.pow(element.toDouble(),2.0)).toInt())) true else false

    //Функция увеличения количества элементов
    fun addCounter(element: Int, list: MutableList<Int>, counter: Int) = if(checkIfSqaure(element,list)) counter+1 else counter

    //Основная функция подсчета количества элементов в списке являющихся квадратами какого-то из элементов списка
    fun findSquaresOfElements(list: MutableList<Int>,iterator: MutableIterator<Int>,counter:Int):Int =
        if(!iterator.hasNext()) counter else
            findSquaresOfElements(list,iterator,addCounter(iterator.next(),list,counter))

    //2
    //Функция высшего порядка для сортировки списков
    fun getSortedList(list: MutableList<Int>, comparator: Comparator<Int>) =
        list.sortedWith(comparator)

    //Функция для получения сортировки списка A
    fun getSortA(list: MutableList<Int>) = getSortedList(list, compareBy({-it}))

    //Функция для получения сортировки списка B
    fun getSortB(list: MutableList<Int>) = getSortedList(list, compareBy({element->calcDigitSum(element)},{it.absoluteValue}))

    //Функция вызова подсчета суммы цифр числа
    fun calcDigitSum(element: Int):Int = calcDigitSumCount(abs(element),0)

    //Функция рекурсивного подсчета суммы цифр числа
    tailrec fun calcDigitSumCount(element: Int,currentSum:Int):Int = if(stopDigitSumCond(element)) currentSum
    else calcDigitSumCount(deleteLastDigit(element),getNextSum(element,currentSum))

    //Функция получения новой цифры
    fun getDigit(element: Int) = element % 10
    //Функция получения следующей суммы
    fun getNextSum(element: Int,currentSum: Int) = currentSum + getDigit(element)
    //Функция проверки условия остановки подсчета суммы цифр
    fun stopDigitSumCond(element: Int) = element == 0
    //Функция удаления последней цифры числа
    fun deleteLastDigit(element: Int) = element/10

    //Функция получения сортировки списка C
    fun getSortC(list: MutableList<Int>) = getSortedList(list, compareBy({element->-getDividerCounterCall(element)},{-it.absoluteValue}))

    //Функция подсчета количества делителей числа
    fun getDividerCounterCall(element: Int) = if(element==0) 0 else getDividerCounter(abs(element),1,1)

    //Условия проверки является ли число делителем
    fun checkIfDivider(element: Int,divider:Int) = element%divider==0

    //Функция получения следующего значения счетчика делителей
    fun getNextCounter(element: Int,divider: Int,counter: Int) = if(checkIfDivider(element,divider)) counter+1 else counter

    //Функция проверки условия остановки подсчета количества делителей
    fun checkDividerCountCond(currentDivider: Int,element: Int) = currentDivider>element/2

    //Функция получения следующего делителя
    fun getNextDIvider(currentDivider: Int) = currentDivider + 1

    //Функция подсчета количества делителей
    tailrec fun getDividerCounter(element: Int,currentDivider:Int,counter: Int):Int = if(checkDividerCountCond(currentDivider,element))counter
        else getDividerCounter(element,getNextDIvider(currentDivider),getNextCounter(element,currentDivider,counter))

    //Функция получения итератора для отсортированного списка
    fun getIteratorForList(list:MutableList<Int>,sortFunction:(MutableList<Int>)->(List<Int>)) =
            sortFunction(list).iterator()

    //Получения итератора А
    fun getAIterator(list:MutableList<Int>) = getIteratorForList(list,::getSortA)
    //Получения итератора В
    fun getBIterator(list:MutableList<Int>) = getIteratorForList(list,::getSortB)
    //Получения итератора С
    fun getCIterator(list:MutableList<Int>) = getIteratorForList(list,::getSortC)

    //Вызов функции составления списка кортеджей
    fun getTripleCall(listA:MutableList<Int>,listB:MutableList<Int>,listC:MutableList<Int>) =
            getTriple(getAIterator(listA),getBIterator(listB),getCIterator(listC), mutableListOf())

    //Функция проверки условия останова формирования кортерджей
    fun checkTripleStopCond(iteratorA:Iterator<Int>,iteratorB:Iterator<Int>,iteratorC:Iterator<Int>) =
            !(iteratorA.hasNext() && iteratorB.hasNext() && iteratorC.hasNext())

    //Функция добавления кортеджа в список
    fun addTripleToList(list:MutableList<Triple<Int,Int,Int>>,iteratorA:Iterator<Int>,iteratorB:Iterator<Int>,iteratorC:Iterator<Int>):
            MutableList<Triple<Int,Int,Int>>{
            list.add(Triple(iteratorA.next(),iteratorB.next(),iteratorC.next()))
            return list
    }

    //Основная рекурсивная функция формирования списка кортеджей
    tailrec fun getTriple(iteratorA:Iterator<Int>,iteratorB:Iterator<Int>,iteratorC:Iterator<Int>,result:MutableList<Triple<Int,Int,Int>>):MutableList<Triple<Int,Int,Int>> =
        if(checkTripleStopCond(iteratorA,iteratorB,iteratorC)) result
        else getTriple(iteratorA,iteratorB,iteratorC,addTripleToList(result,iteratorA,iteratorB,iteratorC))
}
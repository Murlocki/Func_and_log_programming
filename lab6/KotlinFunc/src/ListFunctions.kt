import javax.swing.plaf.basic.BasicSplitPaneDivider
import kotlin.math.abs
import kotlin.math.absoluteValue

class ListFunctions {

    //Функция вызова подсчета количества элементов являющихся квадратами какого-то из элементов списка
    fun findSquaresOfElementsCall(list: MutableList<Int>):Int = list.count { it->checkIfSqaure(it,list) }

    //Функция проверки содержится ли квадрат элемента в списке
    fun checkIfSqaure(element: Int,list: MutableList<Int>):Boolean = if(list.contains((Math.pow(element.toDouble(),2.0)).toInt())) true else false


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


    //Вызов функции составления списка кортеджей
    fun getTripleCall(listA:MutableList<Int>,listB:MutableList<Int>,listC:MutableList<Int>) =
            getSortA(listA).zip(getSortB(listB)).zip(getSortC(listC)){(a,b),c->Triple(a,b,c)}



    //6.50

    //Функция удаления дублирующихся элементов
    fun deleteAllDuplicatesFromList(list: List<Int>) = list.filter { element->list.count(){it == element} == 1 }

    //Функция получения симметрической разности для списков
    fun getSymmetricDifForLists(firstList: List<Int>,secondList:List<Int>) = getSymmetricDifference(firstList.toSet(),secondList.toSet()).toList()

    //Функция получения симметрической разности множеств
    fun getSymmetricDifference(firstSet: Set<Int>,secondSet:Set<Int>): Set<Int> {
        val setUnion = firstSet union secondSet
        val setIntersect = firstSet intersect secondSet
        return setUnion subtract setIntersect
    }

    //Функция получения объединения списков без дублирующихся элементов и элементов встречающихся в обоих списках
    fun noDuplicateUnionOfLists(L1:List<Int>,L2:List<Int>) = getSymmetricDifForLists(deleteAllDuplicatesFromList(L1),deleteAllDuplicatesFromList(L2)).toList()


    //6.56

    //Проверка на простоту элемента
    fun checkIfSimple(element:Int) = getDividerCounterCall(element) == 2

    //Среднее отфильтрованных элементов
    fun meanOfFilter(list:List<Int>,filterFunc:(Int)->Boolean) = list.filter(filterFunc).average()

    //Среднее простых элементов
    fun meanOfSimpleElementsOfList(list: List<Int>) = meanOfFilter(list,::checkIfSimple)

    //Среднее непростых элементов больших чем среднее простых
    fun meanOfNotSimpleElemGreaterThanSimpleElemMean(list: List<Int>) = meanOfFilter(list) { it -> !checkIfSimple(it) && (it>meanOfSimpleElementsOfList(list)) }


}
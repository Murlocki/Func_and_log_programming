import javax.swing.plaf.basic.BasicSplitPaneDivider
import kotlin.math.abs
import kotlin.math.absoluteValue

class ListFunctions {

    //Функция вызова подсчета количества элементов являющихся квадратами какого-то из элементов списка
    fun findCountOfSquaresOfElementsCall(list: List<Int>):Int = list.count { it->checkIfSqaure(it,list) }

    //Функция проверки содержится ли квадрат элемента в списке
    private fun checkIfSqaure(element: Int,list: List<Int>):Boolean = if(list.contains((Math.pow(element.toDouble(),0.5)).toInt())) true else false


    //2
    //Функция высшего порядка для сортировки списков
    private  fun getSortedList(list: List<Int>, comparator: Comparator<Int>) =
        list.sortedWith(comparator)

    //Функция для получения сортировки списка A
    private  fun getSortA(list: List<Int>) = getSortedList(list, compareBy({-it}))

    //Функция для получения сортировки списка B
    private fun getSortB(list: List<Int>) = getSortedList(list, compareBy({element->calcDigitSum(element)},{it.absoluteValue}))

    //Функция вызова подсчета суммы цифр числа
    private fun calcDigitSum(element: Int):Int = calcDigitSumCount(abs(element),0)

    //Функция рекурсивного подсчета суммы цифр числа
    private tailrec fun calcDigitSumCount(element: Int,currentSum:Int):Int = if(stopDigitSumCond(element)) currentSum
    else calcDigitSumCount(deleteLastDigit(element),getNextSum(element,currentSum))

    //Функция получения новой цифры
    private fun getDigit(element: Int) = element % 10
    //Функция получения следующей суммы
    private fun getNextSum(element: Int,currentSum: Int) = currentSum + getDigit(element)
    //Функция проверки условия остановки подсчета суммы цифр
    private fun stopDigitSumCond(element: Int) = element == 0
    //Функция удаления последней цифры числа
    private fun deleteLastDigit(element: Int) = element/10

    //Функция получения сортировки списка C
    private fun getSortC(list: List<Int>) = getSortedList(list, compareBy({element->-getDividerCounterCall(element)},{-it.absoluteValue}))

    //Функция подсчета количества делителей числа
    private fun getDividerCounterCall(element: Int) = if(element==0) 0 else getDividerCounter(abs(element),1,1)

    //Условия проверки является ли число делителем
    private fun checkIfDivider(element: Int,divider:Int) = element%divider==0

    //Функция получения следующего значения счетчика делителей
    private fun getNextCounter(element: Int,divider: Int,counter: Int) = if(checkIfDivider(element,divider)) counter+1 else counter

    //Функция проверки условия остановки подсчета количества делителей
    private fun checkDividerStopCond(currentDivider: Int,element: Int) = currentDivider>element/2

    //Функция получения следующего делителя
    private fun getNextDIvider(currentDivider: Int) = currentDivider + 1

    //Функция подсчета количества делителей
    private tailrec fun getDividerCounter(element: Int,currentDivider:Int,counter: Int):Int = if(checkDividerStopCond(currentDivider,element))counter
        else getDividerCounter(element,getNextDIvider(currentDivider),getNextCounter(element,currentDivider,counter))


    //Вызов функции составления списка кортеджей
    fun getTripleCall(listA:List<Int>,listB:List<Int>,listC:List<Int>) =
            getSortA(listA).zip(getSortB(listB)).zip(getSortC(listC)){(a,b),c->Triple(a,b,c)}



    //6.50

    //Функция удаления дублирующихся элементов
    private fun deleteAllDuplicatesFromList(list: List<Int>) = list.filter { element->list.count(){it == element} == 1 }

    //Функция получения симметрической разности для списков
    private fun getSymmetricDifForLists(firstList: List<Int>,secondList:List<Int>) = getSymmetricDifference(firstList.toSet(),secondList.toSet()).toList()

    //Функция получения симметрической разности множеств
    private fun getSymmetricDifference(firstSet: Set<Int>,secondSet:Set<Int>): Set<Int> {
        val setUnion = firstSet union secondSet
        val setIntersect = firstSet intersect secondSet
        return setUnion subtract setIntersect
    }

    //Функция получения объединения списков без дублирующихся элементов и элементов встречающихся в обоих списках
    fun noDuplicateUnionOfLists(L1:List<Int>,L2:List<Int>) = getSymmetricDifForLists(deleteAllDuplicatesFromList(L1),deleteAllDuplicatesFromList(L2)).toList()


    //6.56

    //Проверка на простоту элемента
    private fun checkIfSimple(element:Int) = getDividerCounterCall(element) == 2

    //Среднее отфильтрованных элементов
    private fun meanOfFilter(list:List<Int>,filterFunc:(Int)->Boolean) = list.filter(filterFunc).average()

    //Среднее простых элементов
    private fun meanOfSimpleElementsOfList(list: List<Int>) = meanOfFilter(list,::checkIfSimple)

    //Среднее непростых элементов больших чем среднее простых
    fun meanOfNotSimpleElemGreaterThanSimpleElemMean(list: List<Int>) = meanOfFilter(list) { it -> !checkIfSimple(it) && (it>meanOfSimpleElementsOfList(list)) }


    //7.3.

    //Основная функция получения списка кортеджей
    fun getCortegesAB(N: Int) =
        when{
            N==0-> mutableListOf();
            abs(N)==1-> mutableListOf(Pair(1,1))
            else-> getCortegesABProcess(abs(N),1, mutableListOf())
        }

    //Функция поиска нода
    private  fun findNod(x:Int,y:Int):Int = if(checkIfDivider(x,y)) y else findNod(y,x%y)

    //Функция формирования 1 пары на добавление в список
    private  fun formPairABToAdd(N: Int,currentX: Int,currentNod:Int) = Pair((N/currentX/currentNod).toDouble(),(currentX/currentNod).toDouble())

    //Функция обработки для составления пары
    private  fun processOnePair(N: Int,currentX: Int,resultList: MutableList<Pair<Double, Double>>): MutableList<Pair<Double, Double>> {

        if(checkIfDivider(N,currentX)){
            val currentNod = findNod(N/currentX,currentX)
            val pairToAdd = formPairABToAdd(N,currentX,currentNod)
            if(!resultList.contains(pairToAdd)){
                resultList.addLast(pairToAdd)
                resultList.addLast(Pair(pairToAdd.second,pairToAdd.first))
            }
        }
        return resultList
    }

    //Основная функция формирования списка кортежей
    private fun getCortegesABProcess(N:Int,currentX: Int, resultList: MutableList<Pair<Double, Double>>): MutableList<Pair<Double, Double>> =
        if(checkDividerStopCond(currentX,N)) resultList
        else getCortegesABProcess(N,getNextDIvider(currentX),processOnePair(N,currentX,resultList))


    //7.5.

    //Функция проверки простоты делителя
    private fun checkIfSimpleDivider(N: Int,currentDivider: Int) = checkIfSimple(currentDivider)&&checkIfDivider(N,currentDivider)
    //Функция получения списка всех простых делителей
    private fun getListOfSimpleDividers(N: Int) = (1..N).toList().filter { checkIfSimpleDivider(N,it) }
    //Функция проверки наличия всех простых делителей числа в списке
    private fun checkIfAllSimpleDivInList(list: List<Int>, element: Int) =
        list.filter { it->checkIfSimpleDivider(element,it) }.size == getListOfSimpleDividers(element).size
    //Функция создания нового списка из элементов для которых все их простые делители есть в оригинальном списке
    fun createListOfElemsWithAllSimpleDiv(list: List<Int>):List<Int> = list.filter {element-> checkIfAllSimpleDivInList(list,element)}

    //7.9.
    //Функция получения списка всех элементов до указанного индекса
    private fun getAllPrevious(list: List<Int>, index: Int) = list.slice(0..index-1)
    //Функция находящая сумму всех элементов до текущего индекса
    private fun calcSumOfAllPrevious(list: List<Int>, index:Int) = getAllPrevious(list,index).sum()
    //Функция проверяющая делится ли элемент по индексу на все предыдущие элементы
    private fun multipleOfAllPrevious(list: List<Int>, index: Int) = getAllPrevious(list,index).all{ checkIfDivider(list[index],it)}
    //Функция подсчета количества элементов больших указанного
    private fun countOfElementsGreaterThanInput(list: List<Int>, element: Int) = list.count{it>element}
    //Функция проверки сложного условия задачи 7.9
    private fun checkComplexCond(element: Int,elementIndex: Int,list: List<Int>) =
        element>calcSumOfAllPrevious(list,elementIndex)
                && checkIfSqaure(element,list) && multipleOfAllPrevious(list,elementIndex)
    //Функция создания триплета значений
    private fun createTriple(element: Int,elementIndex: Int,list: List<Int>) =
        Triple(element,calcSumOfAllPrevious(list,elementIndex),countOfElementsGreaterThanInput(list,element))
    //Функция создания списка из кортеджей
    fun createListOfCortegesElSumPrevCountGreaterElem(list: List<Int>) = list.withIndex().filter {checkComplexCond(it.value,it.index,list)}.map {
        it->createTriple(it.value,it.index,list)}

}
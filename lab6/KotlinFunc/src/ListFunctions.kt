import javax.swing.plaf.basic.BasicSplitPaneDivider
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

    //
    fun getSortedList(list: MutableList<Int>, comparator: Comparator<Int>) =
        list.sortedWith(comparator)

    fun getSortA(list: MutableList<Int>) = getSortedList(list, compareBy({-it}))

    fun getSortB(list: MutableList<Int>) = getSortedList(list, compareBy({element->calcDigitSum(element)},{it.absoluteValue}))

    fun calcDigitSum(element: Int):Int = if(element==0) 0 else element%10 + calcDigitSum(element/10)

    fun getSortC(list: MutableList<Int>) = getSortedList(list, compareBy({element->-getDividerCounterCall(element)},{-it.absoluteValue}))

    fun getDividerCounterCall(element: Int) = if(element==0) 0 else getDividerCounter(element,1,1)


    fun checkIfDivider(element: Int,divider:Int) = if(element%divider==0) true else false

    fun getNextCounter(element: Int,divider: Int,counter: Int) = if(checkIfDivider(element,divider)) counter+1 else counter

    fun getDividerCounter(element: Int,currentDivider:Int,counter: Int):Int = if(currentDivider>element/2)counter
        else getDividerCounter(element,currentDivider+1,getNextCounter(element,currentDivider,counter))




}
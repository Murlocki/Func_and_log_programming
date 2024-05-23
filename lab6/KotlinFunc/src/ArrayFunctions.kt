import kotlin.math.min

class ArrayFunctions {

    //Функция получения массива до индекса
    fun getArrayBeforeIndex(originalArray: Array<Int>, index: Int) = originalArray.sliceArray(0..index-1)
    //Функция получения массива после индеса
    fun getArrayAfterIndex(originalArray: Array<Int>, index: Int) = originalArray.sliceArray(index+1..originalArray.size-1)
    //Функция удаления элемента по индексу
    fun deleteElementViaIndexFromArray(originalArray: Array<Int>, elementIndex: Int) =
        getArrayBeforeIndex(originalArray,elementIndex).plus(
            getArrayAfterIndex(originalArray,elementIndex))
    //Функция нахождения индекса первого минимума
    fun findFirstMinIndex(array: Array<Int>) = array.indexOf(array.min())
    //Функция второго минимума массива
    fun findSecondMinIndex(originalArray: Array<Int>, firstMinIndex:Int):Int{
        val arrayWithoutFirstMin = deleteElementViaIndexFromArray(originalArray,firstMinIndex)
        return originalArray.indexOfLast { it==arrayWithoutFirstMin.min() }
    }

    //Функция вызова поиска индексов 2 наименьших элементов
    fun getTwoMinIndexesCall(array: Array<Int>): Pair<Int, Int> {
        if(array.size<2) return Pair(0,0)
        else{
            val firstMinIndex = findFirstMinIndex(array)
            val secondMinIndex = findSecondMinIndex(array,firstMinIndex)
            return Pair(firstMinIndex,secondMinIndex)
        }
    }

    //3.5.
    //Ну как бы вот...
    //Проверка на глобальный минимум через встроенные функции
    fun checkGlobalMinWithBuildIn(array: Array<Int>, index:Int) = array[index] == array.min()
    //Функция проверки окончания итератора
    fun checkArrayEndCond(iterator: Iterator<Int>):Boolean = !(iterator.hasNext())
    //Функция получения обычного итератора массива
    fun getArrayIterator(array: Array<Int>) = array.iterator()
    //Функция проверки элемента на соответствие глобальность минимума
    fun checkGlobalMin(array: Array<Int>, index: Int) = array[index] == findGlobalMinimum(getArrayIterator(array),array[0])
    //Получение новоного минимума
    fun getNewMin(element: Int,currentMin: Int) = min(element,currentMin)
    //Функция нахождения глобального минимума
    fun findGlobalMinimum(iterator: Iterator<Int>,currentMin:Int): Int {
        if(checkArrayEndCond(iterator)) return currentMin
        else{
            val newMin = iterator.next()
            return findGlobalMinimum(iterator,getNewMin(currentMin,newMin))
        }

    }

    //3.17

    //Это легально
    //Функция получения пары максимального и минимального элементов
    fun getMaxMinValue(array: Array<Int>) = Pair(array.min(),array.max())
    //Функции получения индексов максимального и минимального элементов
    fun getMaxMinIndexes(array: Array<Int>, minEl:Int, maxEl:Int) = Pair(array.indexOf(minEl),array.indexOf(maxEl))

    //Функция создания массива с поменямыми местами макс и мин элементами
    fun createSwitchedArray(originalArray: Array<Int>, minMaxIndexes:Pair<Int,Int>,minMaxValues:Pair<Int,Int>): Array<Int> {
        val newArray = arrayOf(*originalArray)
        newArray[minMaxIndexes.first] = minMaxValues.second
        newArray[minMaxIndexes.second] = minMaxValues.first
        return newArray
    }

    //Основная функция создания массива с поменянемыми местами макс и мин элементами
    fun switchMaxAndMin(array: Array<Int>): Array<Int> {
        val (minValue,maxValue) = getMaxMinValue(array)
        val minMaxIndexes = getMaxMinIndexes(array,minValue,maxValue)
        return createSwitchedArray(array,minMaxIndexes,Pair(minValue,maxValue))
    }


    //3.23

    //Функция вывода массива
    fun printArray(array: Array<Int>) = println(array.contentToString())
}
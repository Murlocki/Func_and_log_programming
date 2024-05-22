class ArrayFunctions {

    //Функция получения индексированного итератора массива
    fun getArrayIndexIterator(array:Array<Int>):Iterator<IndexedValue<Int>> = array.iterator().withIndex()

    //Проверка условия остановки пропуска элементов
    fun checkIteratorSkipStopCond(arrayIterator: Iterator<IndexedValue<Int>>,k:Int) = !(checkArrayEndCond(arrayIterator)) or (k==0)
    //Функция пропуска элементов итератора
    fun skipKIterElements(arrayIterator: Iterator<IndexedValue<Int>>,k:Int): Iterator<IndexedValue<Int>> {
        if(checkIteratorSkipStopCond(arrayIterator,k)) return arrayIterator
        else {
            arrayIterator.next()
            return skipKIterElements(arrayIterator,k-1)
        }
    }

    //Функция проверки окончания итератора
    fun checkArrayEndCond(iterator: Iterator<IndexedValue<Int>>) = iterator.hasNext()

    //Функция вызова поиска индексов 2 наименьших элементов
    fun getTwoMinIndexesCall(array: Array<Int>) = if(array.size<2) Pair(0,0)
        else getTwoMinIndexes(skipKIterElements(getArrayIndexIterator(array),2),
        getSmallerValueResult(array[0],array[1],0,1) as Int,
        getBiggerValueResult(array[0],array[1],0,1) as Int ,
        getSmallerValueResult(array[0],array[1],array[0],array[1]) as Int,
        getBiggerValueResult(array[0],array[1],array[0],array[1]) as Int)

    //Функция получения первого результата в случае выполнения условия
    fun getFuncCondResult(compareCondFunc:(Int,Int)->Boolean,firstComparableValue:Int,secondComparableValue:Int,firstResult:Any,secondResult:Any) =
        if(compareCondFunc(firstComparableValue,secondComparableValue)) firstResult else secondResult

    //Функция получения результата по меньшему элементу
    fun getSmallerValueResult(firstComparableValue:Int,secondComparableValue:Int,firstResult:Any,secondResult:Any) =
        getFuncCondResult({first:Int,second:Int->first<second},firstComparableValue,secondComparableValue,firstResult,secondResult)

    //Функция получения результата по большему элементу
    fun getBiggerValueResult(firstComparableValue:Int,secondComparableValue:Int,firstResult:Any,secondResult:Any) =
        getFuncCondResult({first:Int,second:Int->first>second},firstComparableValue,secondComparableValue,firstResult,secondResult)

    //Функция получения следующих 2 мин элементов и их индексов
    fun getNextTwoMins(currentElement: Int,currentIndex:Int,firstMinIndex: Int, secondMinIndex: Int, firstMin: Int, secondMin: Int):List<Int> =
        when{
            currentElement<firstMin->listOf(currentElement,currentIndex,firstMin,firstMinIndex);
            currentElement<secondMin->listOf(firstMin,firstMinIndex,currentElement,currentIndex);
            else->listOf(firstMin,firstMinIndex,secondMin,secondMinIndex)
        }

    //Основная функция получения индексов 2 наим элементов
    fun getTwoMinIndexes(arrayIterator: Iterator<IndexedValue<Int>>, firstMinIndex: Int, secondMinIndex: Int, firstMin: Int, secondMin: Int): Pair<Int, Int> {
        if (!checkArrayEndCond(arrayIterator)) return Pair(firstMinIndex, secondMinIndex)
        else {
            val (currentIndex,currentElement) = arrayIterator.next()
            val (nextFirstMin,nextFirstMinIndex,nextSecondMin,nextSecondMinIndex) = getNextTwoMins(currentElement,currentIndex,firstMinIndex,secondMinIndex,firstMin,secondMin)
            return getTwoMinIndexes(
                arrayIterator,
                nextFirstMinIndex,
                nextSecondMinIndex,
                nextFirstMin,
                nextSecondMin
            )
        }
    }
}
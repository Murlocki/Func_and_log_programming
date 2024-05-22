fun main() {
    val f = ListFunctions()
    println(f.findSquaresOfElementsCall(mutableListOf(1,36,6,25,5)))
    //println(f.getSortC(mutableListOf(1,1,2,8,4,36,6,25,5)))

    //println(f.getTripleCall(mutableListOf(4,7,1,-10,4),mutableListOf(159,1,10,-14,59), mutableListOf(36,8,9,5,100)))
    val ar = ArrayFunctions()
    //println(ar.getTwoMinIndexesCall(arrayOf(-100,3,4,5,1,2,-10,4,-100)))
    //println(ar.checkGlobalMin(arrayOf(-100,10,3),1))
    //println(ar.checkGlobalMinWithBuildIn(arrayOf(-100,10,3),1))
    ar.printArray(ar.switchMaxAndMin(arrayOf(10,10,-10)))
}
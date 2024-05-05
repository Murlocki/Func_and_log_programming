fun main() {
    val c = CycleFunctions();
    println(c.maxdigit(897))
    println(c.sum3Digits(8976));
    println(c.findDividersCount(0))

    val upRec = UpRecFunctions()
    println(upRec.maxDigitUp(897))
    println(upRec.sum3DigitsUp(8976))
    println(upRec.findDividersCountUpCall(8))

    val tailRec = TailRecFunctions()
    println(tailRec.maxDigitTailCall(8979))
}
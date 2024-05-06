import java.io.File

class Task6Funcs {
    //Функция создания хешмапа функций
    fun createFuncMap():Map<String,(Int)->Int> = hashMapOf("maxDigitCycle" to CycleFunctions()::maxDigit,
        "sum3DigitsTail" to TailRecFunctions()::sum3DigitsTailCall,
        "findDividersCountUp" to UpRecFunctions()::findDividersCountUpCall,)
    //Функция извления функции по имени
    fun getFuncByName(funName:String,funcMap: Map<String, (Int) -> Int>):((Int)->Int)? =
        try {
            funcMap.getValue(funName)
        }
        catch(e: NoSuchElementException){
            null
        }

    //Функция чтения содержимого файла и возврата этого в виде списка
    fun readFileLineByLine(filePath:String) = File(filePath).useLines { it.toList() }

    //Обработки содержимого файла
    fun checkLines(linesList: List<String>,funcMap:Map<String,(Int)->Int>) = checkLinesRec(linesList,funcMap,0,"")
    tailrec fun checkLinesRec(linesList: List<String>, funcMap: Map<String, (Int) -> Int>, currentIndex:Int, currentResult:String):String? = when{
            currentIndex==linesList.size->currentResult;
            foundErros(linesList[currentIndex],funcMap)->null;
            else->checkLinesRec(linesList,funcMap,currentIndex+1,getOutputString(getArgs(linesList[currentIndex]),funcMap,currentResult))
    }
    //Функция получения аргумента названия и аргумента функции
    fun getArgs(line: String)= line.split(" ")

    //Функция формирования очередной строки выходного файла
    fun getOutputString(args: List<String>, funcMap: Map<String, (Int) -> Int>, currentResult: String) = currentResult+args[0]+args[1]+" "+
            invokeSelectedFunction(getFuncByName(args[1],funcMap),args[0])+"\n"
    //Функция вызова полученной функции
    fun invokeSelectedFunction(func:((Int)->Int)?,arg:String) =  func?.invoke(arg.toInt())
    //Функция проверки содержимого строки на ошибки
    fun foundErros(line:String,funcMap: Map<String, (Int) -> Int>) = checkLineSize(line) || checkfirstArg(line) || checkFuncExist(line,funcMap)
    //Проверка на количество элементов строки
    fun checkLineSize(line: String) = line.split(" ").size != 2
    //Проверка первого аргумента на представимость в виде целого числа
    fun checkfirstArg(line: String) = line.split(" ")[0].toIntOrNull()==null
    //Проверка на существование введенной функции
    fun checkFuncExist(line: String,funcMap: Map<String, (Int) -> Int>) = getFuncByName(line.split(" ")[1],funcMap)==null


    //Функция вывода результата
    fun printResult(linesResult:String?) =
        if (linesResult == null) println("Ошибка структуры файла")
        else printToFile(linesResult)

    //Функция вывода ответа в файл
    fun printToFile(linesResult: String?) = linesResult?.let { File("result.txt").writeText(it) }
    

    //Основная функция работы
    fun mainFunc(args:Array<String>){
        if(args.size>0) {
            val linesList = readFileLineByLine(args[0])
            val funcMap = createFuncMap()
            val linesResult = checkLines(linesList, funcMap)
            printResult(linesResult)
        }
        else{
            println("Отсутствует указанный путь к файлу")
        }
    }
}
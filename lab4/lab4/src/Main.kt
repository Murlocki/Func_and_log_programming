
fun form_quan():String{
    val q='"';
    val s = "fun form_quan():String{\n\tval q='';\n\tval s=;\n\treturn s.subSequence(0,32).toString() + s.subSequence(32,42).toString()+q+s+q+s.subSequence(43,s.length);\n}\n" +
            "fun print_quan(inputQuan:String){\n\tprintln(inputQuan);\n}\nfun main() {\n\tprint_quan(form_quan())\n}"
    return s.subSequence(0,32).toString() + q + s.subSequence(32,42).toString()+q+s+q+s.subSequence(43,s.length);
}
fun print_quan(inputQuan:String){
    println(inputQuan);
}
fun main() {
    print_quan(form_quan())
}

data class TreeNode(var value:String, var leftNode: TreeNode? = null, var rightNode: TreeNode?=null){
    override fun toString(): String {
        return "$value"
    }
}

fun defaultComparator(s1:String,s2:String) = s1.length.compareTo(s2.length)


fun compareCoundOfWords(o1: String,o2: String):Int{
    val splitO1 = o1.split(' ')
    val splitO2 = o2.split(' ')
    val sizeO1 = splitO1.size
    val sizeO2 = splitO2.size

    if (sizeO1 > sizeO2) return 1
    else if (sizeO1 < sizeO2) return -1
    else return 0
}
fun  wordCountComparator(o1:String?,o2:String?):Int=
    when{
        (o1 == null && o2 == null)->0;
        (o1 == null)-> -1;
        (o2 == null)-> 1;
        else-> compareCoundOfWords(o1,o2)
    }



class BinaryStringTree(var rootNode: TreeNode? = null, var stringComparator: Comparator<String> = Comparator<String>(::defaultComparator)){
    fun insert(value: String) = insertRec(value,rootNode!!)

    private fun addLeftNode(currentNode: TreeNode,value: String){
        if(currentNode.leftNode!=null) insertRec(value, currentNode.leftNode!!)
        else currentNode.leftNode = TreeNode(value)
    }
    private fun addRightNode(currentNode: TreeNode,value: String){
        if(currentNode.rightNode!=null) insertRec(value, currentNode.rightNode!!)
        else currentNode.rightNode = TreeNode(value)
    }
    private fun insertRec(value: String, currentNode: TreeNode){
        if(stringComparator.compare(value,currentNode.value)<=0){
            addLeftNode(currentNode,value)
        }
        else{
            addRightNode(currentNode,value)
        }
    }

    fun toList() = toListNode(this.rootNode).toList()
    private fun toListNode(node: TreeNode?): List<String> {
        val list = mutableListOf<String>()
        if (node != null) {
            list.addAll(toListNode(node.leftNode))
            list.add(node.value)
            list.addAll(toListNode(node.rightNode))
        }
        return list
    }



    fun fromList(list: List<String>):Boolean{
        if(list.size==0) return false
        this.rootNode = TreeNode(list[0])
        from(list, list.size, 1)
        return true
    }

    private tailrec fun from(list: List<String>, maxInd: Int, index: Int) {
        if (index < maxInd) {
            this.insert(list[index])
            this.from(list, maxInd,index + 1)
        }
    }

}
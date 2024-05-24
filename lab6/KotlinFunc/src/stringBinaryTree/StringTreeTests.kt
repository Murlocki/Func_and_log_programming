
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class StringTreeTests {
    @Test
    fun addNodeTest(){
        val binaryStringTree = BinaryStringTree(TreeNode("a"))
        binaryStringTree.insert("cc")
        binaryStringTree.insert("b")
        binaryStringTree.insert("ee")
        binaryStringTree.insert("dd")
        Assertions.assertEquals(null,binaryStringTree.rootNode!!.leftNode!!.leftNode)
        Assertions.assertEquals("cc",binaryStringTree.rootNode!!.rightNode!!.value)
        Assertions.assertEquals("b",binaryStringTree.rootNode!!.leftNode!!.value)
    }

    @Test
    fun convertToLiST(){
        val binaryStringTree = BinaryStringTree(TreeNode("a"))
        binaryStringTree.insert("cc")
        binaryStringTree.insert("b")
        binaryStringTree.insert("ee")
        binaryStringTree.insert("dd")
        Assertions.assertEquals(listOf("b","a","dd","ee","cc"),binaryStringTree.toList())
    }

    @Test
    fun convertToTree(){
        val binaryStringTree = BinaryStringTree()
        val list = listOf("a","cc","b","ee","dd")
        binaryStringTree.fromList(list)
        Assertions.assertEquals(listOf("b","a","dd","ee","cc"),binaryStringTree.toList())
    }


    @Test
    fun compareCountOfWordsTest(){
        val o1 = "This is a sentence"
        val o2 = "Hello world"
        Assertions.assertEquals(1,wordCountComparator(o1,o2))
        Assertions.assertEquals(-1,wordCountComparator(o2,o1))
        Assertions.assertEquals(0,wordCountComparator(o1,o1))
    }
    @Test
    fun compareCountOfWordsTestNull(){
        val o1 = "This is a sentence"
        val o2 = null
        Assertions.assertEquals(1,wordCountComparator(o1,o2))
        Assertions.assertEquals(-1,wordCountComparator(o2,o1))
        Assertions.assertEquals(0,wordCountComparator(o2,o2))
    }
    @Test
    fun countOfWordsSOrt(){
        val list = listOf("This is a sentence", "Helloworld", "Kotlin language", "java java", "IOIO")
        val binaryStringTree = BinaryStringTree(){el1,el2->wordCountComparator(el1,el2)}
        binaryStringTree.fromList(list)
        Assertions.assertEquals(listOf("IOIO","Helloworld","java java","Kotlin language", "This is a sentence"),binaryStringTree.toList())
    }
}
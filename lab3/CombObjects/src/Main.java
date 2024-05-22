import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        String[] alp = new String[]{"a", "b","c","d","e"};
        PlacementsWithRepeats pl = new PlacementsWithRepeats(alp,3);
        pl.printAllObjectsRecCall();
        System.out.println();
        pl.printAllObjectsNonRec();
        pl.printAllObjectsRecCall("a.txt");
        System.out.println();
        pl.printAllObjectsNonRec("b.txt");

//        CombinationsWithoutRep comb = new CombinationsWithoutRep(alp,2);
//        comb.printAllObjectsNonRec("a.txt");
//        System.out.println();
//        comb.printAllObjectsRecCall("br.txt");
//
//        wordWith3a r = new wordWith3a(alp,5);
//        r.printAllObjectsNonRec();
//        r.printAllObjectsNonRec("a.txt");
//
//        Subsets s = new Subsets(alp);
//        s.printAllObjectsNonRec("a.txt");
//
//        PlacementsWithoutRep p = new PlacementsWithoutRep(alp,3);
//        p.printAllObjectsRecCall();
//        System.out.println();
//        p.printAllObjectsNonRec();
//        p.printAllObjectsRecCall("C:\\Users\\kiril\\Desktop\\Новая папка\\a.txt");
//        System.out.println();
//        p.printAllObjectsNonRec("b.txt");
//
//        Permitations per = new Permitations(alp);
//        per.printAllObjectsRecCall();
//        System.out.println();
//        per.printAllObjectsNonRec();
//        per.printAllObjectsRecCall("a.txt");
//        System.out.println();
//        per.printAllObjectsNonRec("b.txt");
//
//        CombWithReps c = new CombWithReps(alp,4);
//        c.printAllObjectsRecCall();
//        System.out.println();
//        c.printAllObjectsNonRec();
//        c.printAllObjectsRecCall("a.txt");
//        System.out.println();
//        c.printAllObjectsNonRec("b.txt");
//
//        Word2 words = new Word2(alp);
//        words.printAllObjectsNonRec();
//        words.printAllObjectsNonRec("a.txt");
//
//        WordOne2One3 word = new WordOne2One3(alp);
//        word.printAllObjectsNonRec();
//        word.printAllObjectsNonRec("a.txt");
    }
}
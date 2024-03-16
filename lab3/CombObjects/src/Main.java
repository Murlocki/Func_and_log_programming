//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] alp = new String[]{"a", "b","c","d"};
        //PlacementsWithRepeats pl = new PlacementsWithRepeats(alp);
        //pl.printAllPlacementsWithRep(3);
        //System.out.println();
        //pl.printAllPlacementWithRepNonRec(2);

//        CombinationsWithoutRep comb = new CombinationsWithoutRep(alp);
//        comb.printAllComb(3);
//
//        comb.printAllCombNonRec(2);
//
//        wordWith3a r = new wordWith3a(alp);
//        r.printWords3a(5);

//        Subsets s = new Subsets(alp);
//        s.PrintAllSubsetsRecCall();

//        PlacementsWithoutRep p = new PlacementsWithoutRep(alp);
//        //p.printAllPlacementsRecCall(3);
//          p.printAllPlacementWithoutRepNonRec(3);
//
//        CombWithReps c = new CombWithReps(alp);
//        c.printCombWithRepNonRec(4);
//        c.printCombWithRepRecCall(4);

//        Word2 words = new Word2(alp);
//        words.printAllWords();

        WordOne2One3 word = new WordOne2One3(alp);
        word.printAllWords();

    }
}
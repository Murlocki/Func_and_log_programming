//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[] alp = new char[]{'a', 'b','c','d'};
//        PlacementsWithRepeats pl = new PlacementsWithRepeats(alp);
//        pl.printAllPlacementsWithRep(1);
//        System.out.println();
//        pl.printAllPlacementWithRepNonRec(1);

        //CombinationsWithoutRep comb = new CombinationsWithoutRep(alp);
        //comb.printAllComb(2);

        //comb.printAllCombNonRec(2);

//        wordWith3a r = new wordWith3a(alp);
//        r.printWords3a(5);

//        Subsets s = new Subsets(alp);
//        s.PrintAllSubsetsRecCall();

        PlacementsWithoutRep p = new PlacementsWithoutRep(alp);
//        //p.printAllPlacementsRecCall(3);
       p.printAllPlacementWithoutRepNonRec(4);

//        CombWithReps c = new CombWithReps(alp);
//        //c.printCombWithRepNonRec(4);
//        c.printCombWithRepRecCall(4);

    }
}
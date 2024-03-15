//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[] alp = new char[]{'a', 'b'};
        PlacementsWithRepeats pl = new PlacementsWithRepeats(alp);
        pl.printAllPlacementsWithRep(1);
        System.out.println();
        pl.printAllPlacementWithRepNonRec(1);
    }
}
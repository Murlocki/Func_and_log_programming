public class Permitations extends PlacementsWithoutRep{
    public Permitations(String[] alp){
        super(alp);
    }
    public void printAllPermNonRec(){
        super.printAllPlacementWithoutRepNonRec(getN());
    }

    public void printAllPermRec(){
        super.printAllPlacementsRecCall(getN());
    }
}

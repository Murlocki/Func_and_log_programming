public class PlacementsWithRepeats {
    private char[] alphabet;
    private int n;

    public PlacementsWithRepeats(char[] inputAlphabet){
        this.alphabet = inputAlphabet;
        this.n = inputAlphabet.length;
    }
    public void printAllPlacementsWithRep(int k){
        this.printAllPlacementsWithRepRec(0,k,new char[k]);
    }
    private void printAllPlacementsWithRepRec(int curPos,int k,char[]placement){
        if(curPos==k){
            for(int i=0;i<k;i++){
                System.out.print(placement[i]);
            }
            System.out.println();
        }
        else {
            for(int i=0;i<this.n;i++){
                placement[curPos]=this.alphabet[i];
                printAllPlacementsWithRepRec(curPos+1,k,placement);
            }
        }
    }
}

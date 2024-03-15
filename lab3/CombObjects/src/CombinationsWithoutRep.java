import java.util.Arrays;

public class CombinationsWithoutRep {

    private char[] alphabet;
    private int n;
    public CombinationsWithoutRep(char[] alphabet){
        this.alphabet = alphabet;
        this.n = alphabet.length;
    }

    private char getNextSymbol(char curSymbol){
        int i=0;
        while((i<this.n)&&(this.alphabet[i]!=curSymbol)){
            i=i+1;
        }
        return this.alphabet[i+1];
    }

    public void printAllComb(int k){
        printAllCombRec(new char[k],0,k);
    }
    private void printAllCombRec(char[] curComb,int curPos,int k){
        if(curPos==k){
            System.out.println(curComb);
        }
        else{
            char startChar = (curPos==0)? this.alphabet[0]: getNextSymbol(curComb[curPos-1]);

            int startNumber = Arrays.binarySearch(this.alphabet,startChar);
            int finishNumber = this.n - (k - curPos);
            for(int i=startNumber;i<=finishNumber;i++){
                curComb[curPos]=this.alphabet[i];
                printAllCombRec(curComb,curPos+1,k);
            }
        }
    }
}

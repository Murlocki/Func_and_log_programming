import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.System.arraycopy;

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

    private boolean nextCombination(char[] currentComb,int k){
        int j=k-1;
        int i=1;
        while((j>=0) && (currentComb[j]==this.alphabet[this.n-i])){
            j=j-1;
            i=i+1;
        }
        if(j<0){
            return false;
        }
        else{
            currentComb[j]=getNextSymbol(currentComb[j]);
            for(i=j+1;i<k;i++){
                currentComb[i]= getNextSymbol(currentComb[i-1]);
            }
            return true;
        }
    }
    public void printAllCombNonRec(int k){
        char[] comb = new char[k];
        arraycopy(this.alphabet,0,comb,0,k);
        for(int i=0;i<k;i++){
            comb[i]=this.alphabet[i];
        }
        System.out.println(comb);

        while (nextCombination(comb,k)){
            System.out.println(comb);
        }
    }
}

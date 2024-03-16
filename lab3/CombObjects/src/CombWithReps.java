import java.util.Arrays;

import static java.lang.System.arraycopy;

public class CombWithReps {
    private int n;
    private char[] alphabet;
    public CombWithReps(char[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
    }
    public char getNextSymbol(char symbol){
        int i=0;
        while(i<this.n&&this.alphabet[i]!=symbol)i=i+1;
        return this.alphabet[i+1];
    }

    public boolean getNextComb(char[] currentComb,int k){
        int j=k-1;
        while(j>=0&&currentComb[j]==this.alphabet[n-1])j=j-1;
        if(j<0){
            return false;
        }
        else{
            currentComb[j]=getNextSymbol(currentComb[j]);
            for(int i=j+1;i<k;i++){
                currentComb[i]=currentComb[j];
            }
            return true;
        }
    }
    public void printCombWithRepNonRec(int k){
        char[] comb = new char[k];
        Arrays.fill(comb,this.alphabet[0]);
        System.out.println(comb);

        while (getNextComb(comb,k)){
            System.out.println(comb);
        }
    }

    public void printCombWithRepRec(char[] currentComb,int currentK,int k){
        if(currentK==k){
            System.out.println(currentComb);
        }
        else{
            for(int i=0;i<this.n;i++){
                String al=new String(this.alphabet);
                if(currentK==0){
                    currentComb[currentK]=this.alphabet[i];
                    printCombWithRepRec(currentComb,currentK+1,k);
                }
                else if(al.indexOf(currentComb[currentK-1])<=i){
                    currentComb[currentK]=this.alphabet[i];
                    printCombWithRepRec(currentComb,currentK+1,k);
                }

            }
        }
    }
    public void printCombWithRepRecCall(int k){
            printCombWithRepRec(new char[k],0,k);
    }
}

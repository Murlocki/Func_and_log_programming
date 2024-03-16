import java.util.Arrays;
import java.util.Objects;

public class CombWithReps {
    private int n;
    private String[] alphabet;
    public CombWithReps(String[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
    }
    public String getNextSymbol(String symbol){
        int i=0;
        while(i<this.n&& !Objects.equals(this.alphabet[i], symbol))i=i+1;
        return this.alphabet[i+1];
    }

    public boolean getNextComb(String[] currentComb,int k){
        int j=k-1;
        while(j>=0&& Objects.equals(currentComb[j], this.alphabet[n - 1]))j=j-1;
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
        String[] comb = new String[k];
        Arrays.fill(comb,this.alphabet[0]);
        System.out.println(Arrays.toString(comb));

        while (getNextComb(comb,k)){
            System.out.println(Arrays.toString(comb));
        }
    }

    public void printCombWithRepRec(String[] currentComb,int currentK,int k){
        if(currentK==k){
            System.out.println(Arrays.toString(currentComb));
        }
        else{
            for(int i=0;i<this.n;i++){

                if(currentK==0){
                    currentComb[currentK]=this.alphabet[i];
                    printCombWithRepRec(currentComb,currentK+1,k);
                }
                else if(Arrays.asList(this.alphabet).indexOf(currentComb[currentK-1])<=i){
                    currentComb[currentK]=this.alphabet[i];
                    printCombWithRepRec(currentComb,currentK+1,k);
                }

            }
        }
    }
    public void printCombWithRepRecCall(int k){
            printCombWithRepRec(new String[k],0,k);
    }
}

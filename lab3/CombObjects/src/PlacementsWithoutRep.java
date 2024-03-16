import java.lang.constant.DynamicCallSiteDesc;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlacementsWithoutRep {
    private final char[] alphabet;
    private final int n;

    private char[] currentPlacement;

    public int getN() {
        return n;
    }

    public PlacementsWithoutRep(char[] alp){
        this.alphabet=alp;
        this.n = alp.length;
        this.currentPlacement=null;
    }

    public char getNextSymbol(char curSymbol){
        int i=0;
        while((i<this.n)&&(this.alphabet[i]!=curSymbol)){
            i=i+1;

        }
        int j=i+1;
        while(j<this.n){
            if(new String(this.currentPlacement).indexOf(this.alphabet[j])<0){
                break;
            }
            else{
                j++;
            }
        }
        return this.alphabet[j];
    }

    public char findMaxSymbol(int posInPl){
        char[]open = new char[this.n-posInPl];
        int m=0;
        for(int i=0;i<this.n;i++){
            if(new String(this.currentPlacement).indexOf(this.alphabet[i])<0||this.alphabet[i]==this.currentPlacement[posInPl]){

                open[m]=this.alphabet[i];
                m++;
            }
        }
        return open[open.length-1];
    }

    private boolean getNextPlacement(int k){
        int j=k-1;
        int i=0;
        while(j>=0&&(this.currentPlacement[i]==this.alphabet[n-1-i])){
            j=j-1;
            i=i+1;
        }
        if(j<0){
            return false;
        }
        else{
            j=k-1;
            while(j>=0&&this.currentPlacement[j]==findMaxSymbol(j)){
                this.currentPlacement[j]=' ';
                j=j-1;
            }
            this.currentPlacement[j]=getNextSymbol(this.currentPlacement[j]);

            int p=j+1;
            for(int m=0;m<this.n&&p<k;m++){
                if(new String(this.currentPlacement).indexOf(this.alphabet[m])<0){
                    this.currentPlacement[p]=this.alphabet[m];
                    p=p+1;
                }
            }
            return true;
        }
    }

    public void printAllPlacementWithoutRepNonRec(int k){
        this.currentPlacement=new char[k];
        for(int i=0;i<k;i++){
            this.currentPlacement[i]=this.alphabet[i];
        }
        System.out.println(this.currentPlacement);

        while(getNextPlacement(k)){
            System.out.println(this.currentPlacement);
        }
    }

    public void printAllPlacementsRecCall(int k){
        printAllPlacementsRec(new char[k],0,k);
    }
    public void printAllPlacementsRec(char[] currentPl,int currentK,int K){
        if(currentK==K){
            System.out.println(currentPl);
        }
        else{
            for(int i=0;i<this.n;i++){
                boolean flag = true;
                for(int p=0;p<currentK;p++){
                    if(currentPl[p]==this.alphabet[i]){
                        flag=false;
                    }
                }
                if(flag){
                    currentPl[currentK]=this.alphabet[i];
                    printAllPlacementsRec(currentPl,currentK+1,K);
                }

            }
        }
    }
}

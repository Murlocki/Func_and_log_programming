import java.util.Arrays;
import java.util.Objects;

public class PlacementsWithoutRep {
    private final String[] alphabet;
    private final int n;

    private String[] currentPlacement;

    public int getN() {
        return n;
    }
    public void setCurrentPlacement(String[] pl){
        this.currentPlacement=pl;
    }
    public String[] getCurrentPlacement() {
        return currentPlacement;
    }

    public PlacementsWithoutRep(String[] alp){
        this.alphabet=alp;
        this.n = alp.length;
        this.currentPlacement=null;
    }

    public String getNextSymbol(String curSymbol){
        int i=0;
        while((i<this.n)&&(!Objects.equals(this.alphabet[i], curSymbol))){
            i=i+1;

        }
        int j=i+1;
        while(j<this.n){
            if(!Arrays.asList(this.currentPlacement).contains(this.alphabet[j])){
                break;
            }
            else{
                j++;
            }
        }
        return this.alphabet[j];
    }

    public String findMaxSymbol(int posInPl){
        String[] open = new String[this.n-posInPl];
        int m=0;
        for(int i=0;i<this.n;i++){
            if(!Arrays.asList(this.currentPlacement).contains(this.alphabet[i]) || Objects.equals(this.alphabet[i], this.currentPlacement[posInPl])){

                open[m]=this.alphabet[i];
                m++;
            }
        }
        return open[open.length-1];
    }

    public boolean getNextPlacement(int k){
        int j=k-1;
        int i=0;
        while(j>=0&&(Objects.equals(this.currentPlacement[i], this.alphabet[n - 1 - i]))){
            j=j-1;
            i=i+1;
        }
        if(j<0){
            return false;
        }
        else{
            j=k-1;
            while(j>=0&& Objects.equals(this.currentPlacement[j], findMaxSymbol(j))){
                this.currentPlacement[j]=" ";
                j=j-1;
            }
            this.currentPlacement[j]=getNextSymbol(this.currentPlacement[j]);

            int p=j+1;
            for(int m=0;m<this.n&&p<k;m++){
                if(!Arrays.asList(this.currentPlacement).contains(this.alphabet[m])){
                    this.currentPlacement[p]=this.alphabet[m];
                    p=p+1;
                }
            }

            return true;

        }
    }

    public void printAllPlacementWithoutRepNonRec(int k){
        this.currentPlacement=new String[k];
        System.arraycopy(this.alphabet, 0, this.currentPlacement, 0, k);
        System.out.println(Arrays.toString(this.currentPlacement));

        while(getNextPlacement(k)){
            System.out.println(Arrays.toString(this.currentPlacement));
        }
    }

    public void printAllPlacementsRecCall(int k){
        printAllPlacementsRec(new String[k],0,k);
    }
    public void printAllPlacementsRec(String[] currentPl,int currentK,int K){
        if(currentK==K){
            System.out.println(Arrays.toString(currentPl));
        }
        else{
            for(int i=0;i<this.n;i++){
                boolean flag = true;
                for(int p=0;p<currentK;p++){
                    if (Objects.equals(currentPl[p], this.alphabet[i])) {
                        flag = false;
                        break;
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

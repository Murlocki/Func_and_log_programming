import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.arraycopy;

public class CombinationsWithoutRep {

    private String[] alphabet;
    private int n;

    //Конструктор
    // Принимает на вход алфавит для построения сочетаний
    public CombinationsWithoutRep(String[] alphabet){
        this.alphabet = alphabet;
        this.n = alphabet.length;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    // Возвращает следующий по порядку символ алфавита
    // Принимает на вход текущий символ алфавита
    private String getNextSymbol(String curSymbol){
        int i=0;
        while((i<this.n)&&(!Objects.equals(this.alphabet[i], curSymbol))){
            i=i+1;
        }
        return this.alphabet[i+1];
    }

    // Метод для вызова рекурсивной процедуры построения сочетаний
    // Принимает на вход длину сочетания
    public void printAllComb(int k){
        printAllCombRec(new String[k],0,k);
    }

    // Рекурсивный метод для вывода всех сочетаний длины k
    // Принимает уже построенную часть комбинации, позицию в комбинации, длину комбинации
    private void printAllCombRec(String[] curComb,int curPos,int k){
        if(curPos==k){
            System.out.println(Arrays.toString(curComb));
        }
        else{
            String startChar = (curPos==0)? this.alphabet[0]: getNextSymbol(curComb[curPos-1]);

            int startNumber = Arrays.binarySearch(this.alphabet,startChar);
            int finishNumber = this.n - (k - curPos);
            for(int i=startNumber;i<=finishNumber;i++){
                curComb[curPos]=this.alphabet[i];
                printAllCombRec(curComb,curPos+1,k);
            }
        }
    }

    // Метод для получения следующей по порядку комбинации
    // Принимает текущую комбинацию и длину комбинации
    public boolean nextCombination(String[] currentComb,int k){
        int j=k-1;
        int i=1;
        while((j>=0) && (Objects.equals(currentComb[j], this.alphabet[this.n - i]))){
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

    // Метод для вывода всех сочетаний длины k\
    // Принимает длину комбинации
    public void printAllCombNonRec(int k){
        String[] comb = new String[k];
        arraycopy(this.alphabet,0,comb,0,k);
        for(int i=0;i<k;i++){
            comb[i]=this.alphabet[i];
        }
        System.out.println(Arrays.toString(comb));

        while (nextCombination(comb,k)){
            System.out.println(Arrays.toString(comb));
        }
    }
}

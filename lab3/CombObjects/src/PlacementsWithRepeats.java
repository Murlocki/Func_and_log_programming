import java.lang.reflect.Array;
import java.util.Arrays;

//Класс для вывода всех размещений с повторений для алфавита длиной n
public class PlacementsWithRepeats {
    private char[] alphabet;
    private int n;

    // Конструктор класс
    // Принимает на вход массив символов - алфавит
    public PlacementsWithRepeats(char[] inputAlphabet){
        this.alphabet = inputAlphabet;
        this.n = inputAlphabet.length;
    }
    // Метод для вызоыва рекурсивной функции вывода всех размещений с повторениями длины k
    // Принимает длину размещения k
    public void printAllPlacementsWithRep(int k){
        this.printAllPlacementsWithRepRec(0,k,new char[k]);
    }

    // Рекурсивный метод для вывода всех размещений с повторениями длины k
    // Принимает текущую позицию в размещении, длину размещения, уже построенную часть размещения
    private void printAllPlacementsWithRepRec(int curPos,int k,char[]placement){
        if(curPos==k){
            System.out.println(placement);
        }
        else {
            for(int i=0;i<this.n;i++){
                placement[curPos]=this.alphabet[i];
                printAllPlacementsWithRepRec(curPos+1,k,placement);
            }
        }
    }
    // Метод для получения следующего символа из алфавита
    // Принимает текущий символ алфавита
    private char getNextSymbol(char curSymbol){
        int i=0;
        while((i<this.n)&&(this.alphabet[i]!=curSymbol)){
            i=i+1;
        }
        return this.alphabet[i+1];
    }

    // Метод для получения следующего по порядку размещения с повторениями
    // Принимает текущее размещение и длину размещения
    public boolean getNextPlacement(char[] currentPlacement,int k){
        int j = k - 1;
        while((j >= 0) && (currentPlacement[j] == this.alphabet[this.n-1])){
            j = j - 1;
        }
        if (j<0){
            return false;
        }
        else{
            currentPlacement[j] = getNextSymbol(currentPlacement[j]);
            for(int i=j+1;i<k;i++){
                currentPlacement[i]=this.alphabet[0];
            }
            return true;
        }
    }
    // Не рекурсивный метод для вывода всех размещений с повторениями длины k
    // Принимает длину размещения
    public void printAllPlacementWithRepNonRec(int k){
        char[] curPlacement=new char[k];
        Arrays.fill(curPlacement,this.alphabet[0]);
        System.out.println(curPlacement);

        while(getNextPlacement(curPlacement,k)){
            System.out.println(curPlacement);
        }
    }
}

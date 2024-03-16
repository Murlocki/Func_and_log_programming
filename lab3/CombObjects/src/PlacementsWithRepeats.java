import java.util.Arrays;
import java.util.Objects;

//Класс для вывода всех размещений с повторений для алфавита длиной n
public class PlacementsWithRepeats {
    private String[] alphabet;
    private int n;

    // Конструктор класс
    // Принимает на вход массив символов - алфавит
    public PlacementsWithRepeats(String[] inputAlphabet){
        this.alphabet = inputAlphabet;
        this.n = inputAlphabet.length;
    }
    // Метод для вызоыва рекурсивной функции вывода всех размещений с повторениями длины k
    // Принимает длину размещения k
    public void printAllPlacementsWithRep(int k){
        this.printAllPlacementsWithRepRec(0,k,new String[k]);
    }

    // Рекурсивный метод для вывода всех размещений с повторениями длины k
    // Принимает текущую позицию в размещении, длину размещения, уже построенную часть размещения
    private void printAllPlacementsWithRepRec(int curPos,int k,String[]placement){
        if(curPos==k){
            System.out.println(Arrays.toString(placement));
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
    private String getNextSymbol(String curSymbol){
        int i=0;
        while((i<this.n)&&(!Objects.equals(this.alphabet[i], curSymbol))){
            i=i+1;
        }
        return this.alphabet[i+1];
    }

    // Метод для получения следующего по порядку размещения с повторениями
    // Принимает текущее размещение и длину размещения
    public boolean getNextPlacement(String[] currentPlacement, int k){
        int j = k - 1;
        while((j >= 0) && (Objects.equals(currentPlacement[j], this.alphabet[this.n - 1]))){
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
        String[] curPlacement=new String[k];
        Arrays.fill(curPlacement,this.alphabet[0]);
        System.out.println(Arrays.toString(curPlacement));

        while(getNextPlacement(curPlacement,k)){
            System.out.println(Arrays.toString(curPlacement));
        }
    }
}

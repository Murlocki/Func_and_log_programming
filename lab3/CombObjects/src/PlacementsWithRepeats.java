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

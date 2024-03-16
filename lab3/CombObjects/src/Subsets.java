public class Subsets extends CombinationsWithoutRep {

    //Конструктор
    //Принимает алфавит элементов множества
    public Subsets(char[] alp){
        super(alp);
    }

    //Не рекурсивный метод вывода всех подмножеств
    public void printAllSubsetsNonRec(){
        int k = getAlphabet().length;
        System.out.println("{}");
        for(int i=1;i<=k;i++){
            super.printAllCombNonRec(i);
        }
    }
    //Рекурсивный метод вызоыва построения всех подмножеств
    public void PrintAllSubsetsRecCall(){
        int k = getAlphabet().length;
        this.printAllSubsets(k,0);
    }
    //Построение всех подмножеств от 0 до длины множества
    private void printAllSubsets(int k,int currentk){
        if(currentk==0) {System.out.println("{}");
            currentk=currentk+1;
            printAllSubsets(k,currentk);
        }
        else if(currentk<=k){
            super.printAllComb(currentk);
            currentk=currentk+1;
            printAllSubsets(k,currentk);
        }
    }
}

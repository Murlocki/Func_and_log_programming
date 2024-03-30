import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class PlacementsWithoutRep extends SimpleCombObject implements  PrintAllObjectNonRec,PrintAllObjectRec{
    // Конструктор
    // Принимает алфавит и размер размещения
    public PlacementsWithoutRep(String[] alp,int k){
        super(alp,k);
    }

    // Метод получения следующего символа
    // Принимает на вход текущий символ
    @Override
    public String getNextSymbol(String curSymbol){
        int i=0;
        int n=getN();
        String[] currentPlacement = getCurrentObject();
        String[] alphabet=getAlphabet();
        while((i<n)&&(!Objects.equals(alphabet[i], curSymbol))){
            i=i+1;

        }
        int j=i+1;
        while(j<n){
            if(!Arrays.asList(currentPlacement).contains(alphabet[j])){
                break;
            }
            else{
                j++;
            }
        }
        return alphabet[j];
    }

    // Метод поиска макисмального символа для текущего свободного алфавита
    // Принимает позицию в объекте
    public String findMaxSymbol(int posInPl){
        int n = getN();
        String[] currentPlacement = getCurrentObject();
        String[] alphabet = getAlphabet();


        String[] open = new String[n-posInPl];
        int m=0;
        for(int i=0;i<n;i++){
            if(!Arrays.asList(currentPlacement).contains(alphabet[i]) || Objects.equals(alphabet[i], currentPlacement[posInPl])){

                open[m]=alphabet[i];
                m++;
            }
        }
        return open[open.length-1];
    }

    // Метод получения следующего объекта
    @Override
    public boolean getNextObject(){
        int k = getK();
        int n = getN();
        String[] alphabet = this.getAlphabet();
        String[] currentPlacement = this.getCurrentObject();


        int j=k-1;
        int i=0;
        while(j>=0&&(Objects.equals(currentPlacement[i], alphabet[n - 1 - i]))){
            j=j-1;
            i=i+1;
        }
        if(j<0){
            return false;
        }
        else{
            j=k-1;
            while(j>=0&& Objects.equals(currentPlacement[j], findMaxSymbol(j))){
                currentPlacement[j]=" ";
                j=j-1;
            }
            currentPlacement[j]=getNextSymbol(currentPlacement[j]);

            int p=j+1;
            for(int m=0;m<n&&p<k;m++){
                if(!Arrays.asList(currentPlacement).contains(alphabet[m])){
                    currentPlacement[p]=alphabet[m];
                    p=p+1;
                }
            }
            return true;
        }
    }

    // Метод для вызова рекурсивного вывода объектов
    @Override
    public void printAllObjectsRecCall() {
        printAllObjectRec(0);
    }

    // Метод для вызова рекурсивного вывода объектов в файл
    // Принимает путь до файла
    @Override
    public void printAllObjectsRecCall(String filePath) {
        setFilePath(filePath);
        try{
            setWriter(new FileWriter(getFilePath()));
            printAllObjectsRecCall();
            getWriter().close();
            setFilePath("");
        }catch (IOException e){
            System.out.println("File not found");
            setFilePath("-1");
        }
    }

    // Не рекурсивный метод вывода объектов
    @Override
    public void printAllObjectsNonRec() {
        int k = getK();
        String[] alphabet = getAlphabet();
        setCurrentObject(new String[k]);
        String[] currentPlacement = getCurrentObject();


        System.arraycopy(alphabet, 0, currentPlacement, 0, k);
        setCurrentObject(currentPlacement);

        printIfFileSet();
        while(getNextObject()){
            printIfFileSet();
        }
    }

    //Не рекурсивный метод вывода объектов в файл
    // Принимает путь до файла
    @Override
    public void printAllObjectsNonRec(String filePath) {
        setFilePath(filePath);
        try{
            setWriter(new FileWriter(getFilePath()));
            printAllObjectsNonRec();
            getWriter().close();
            setFilePath("");
        }catch (IOException e){
            System.out.println("File not found");
            setFilePath("-1");
        }
    }

    // Рекурсивный метод вывода объектов
    // Принимает текущую позицию в объекте
    @Override
    public void printAllObjectRec(int currentK) {
        //Получили все нужные поля
        int K = getK();
        String[] currentPl = getCurrentObject();
        int n = getN();
        String[]alphabet = getAlphabet();


        if(currentK==K){
            printIfFileSet();
        }
        else{
            for(int i=0;i<n;i++){
                if(!Arrays.asList(currentPl).contains(alphabet[i])){
                    currentPl[currentK]=alphabet[i];
                    printAllObjectRec(currentK+1);
                    currentPl[currentK]="";
                }

            }
        }
    }
}

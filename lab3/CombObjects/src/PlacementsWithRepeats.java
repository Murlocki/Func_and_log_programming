import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

//Класс для вывода всех размещений с повторений для алфавита длиной n
public class PlacementsWithRepeats extends SimpleCombObject implements PrintAllObjectNonRec,PrintAllObjectRec{


    // Конструктор класс
    // Принимает на вход массив символов - алфавит и размер размещения
    public PlacementsWithRepeats(String[] inputAlphabet,int k){
        super(inputAlphabet,k);
    }

    // Метод для вызоыва рекурсивного формирования объектов
    // Принимает путь до файла
    @Override
    public void printAllObjectsRecCall(String filePath){
        setFilePath(filePath);
        try{
            setWriter(new FileWriter(getFilePath()));
            printAllObjectRec(0);
            getWriter().close();
        }catch (IOException e){
            System.out.println("File not found");
            setFilePath("-1");
        }

    }


    // Метод для вызова рекурсивной функции вывода всех размещений с повторениями длины k
    @Override
    public void  printAllObjectsRecCall(){
        this.setCurrentObject(new String[getK()]);
        this.printAllObjectRec(0);
    }

    // Рекурсивный метод для вывода всех размещений с повторениями длины k
    // Принимает текущую позицию в размещении
    @Override
    public void printAllObjectRec(int currentK){
        if(currentK==getK()){
            printIfFileSet();
        }
        else {
            for(int i=0;i<getN();i++){
                setCurrentObject(getAlphabet()[i],currentK);
                printAllObjectRec(currentK+1);
            }
        }
    }

    // Метод для получения следующего по порядку размещения с повторениями
    @Override
    public boolean getNextObject(){
        int k = getK();
        int n = getN();
        String[] alphabet = getAlphabet();
        String[] currentPlacement = getCurrentObject();

        int j = k - 1;
        while((j >= 0) && (Objects.equals(currentPlacement[j], alphabet[n - 1]))){
            j = j - 1;
        }
        if (j<0){
            return false;
        }
        else{
            currentPlacement[j] = getNextSymbol(currentPlacement[j]);
            for(int i=j+1;i<k;i++){
                currentPlacement[i]=alphabet[0];
            }
            return true;
        }
    }

    // Не рекурсивный метод для вывода всех размещений с повторениями длины k
    @Override
    public void printAllObjectsNonRec(){
        String[] curPlacement=new String[getK()];
        Arrays.fill(curPlacement,getAlphabet()[0]);
        setCurrentObject(curPlacement);

        printIfFileSet();
        while(getNextObject()){
            printIfFileSet();
        }
    }

    //Не рекурсивный метод вывода объектов в файл принимает путь до файла
    @Override
    public void printAllObjectsNonRec(String filePath){
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
}

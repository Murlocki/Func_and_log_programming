import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class CombWithReps extends SimpleCombObject implements PrintAllObjectNonRec,PrintAllObjectRec{

    // Конструктор
    // Принимает алфафит и размер сочетания
    public CombWithReps(String[] alphabet, int k){
        super(alphabet,k);
    }

    //Метод получения следующего объекта
    @Override
    public boolean getNextObject(){
        int k = getK();
        int n = getN();
        String[] currentComb = getCurrentObject();
        String[] alphabet = getAlphabet();

        int j=k-1;
        while(j>=0&& Objects.equals(currentComb[j], alphabet[n - 1]))j=j-1;
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

    //Не рекурсивный метод вывода всех объектов
    @Override
    public void printAllObjectsNonRec(){
        int k = getK();
        String[] alphabet = getAlphabet();
        String[] comb = new String[k];
        Arrays.fill(comb,alphabet[0]);
        setCurrentObject(comb);

        printIfFileSet();
        while (getNextObject()){
            printIfFileSet();
        }
    }

    // Не рекурсивный метод вывода всех объектов в файл
    // Принимает путь до файла
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

    //Рекурсивный метод вывода всех объектов
    // Принимает текущую позицию в объекте
    @Override
    public void printAllObjectRec(int currentK){
        if(currentK==getK()){
            printIfFileSet();
        }
        else{
            String[] currentComb = getCurrentObject();
            String[] alphabet = getAlphabet();
            for(int i=0;i<getN();i++){

                if(currentK==0){
                    currentComb[currentK]=alphabet[i];
                    printAllObjectRec(currentK+1);
                }
                else if(Arrays.asList(alphabet).indexOf(currentComb[currentK-1])<=i){
                    currentComb[currentK]=alphabet[i];
                    printAllObjectRec(currentK+1);
                }

            }
        }
    }

    //Метод вызова рекурсивного вывода всех объектов
    @Override
    public void printAllObjectsRecCall(){
        printAllObjectRec(0);
    }

    // Рекурсивный Метод вызова рекурсивного вывода всех объектовв в файл
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
}

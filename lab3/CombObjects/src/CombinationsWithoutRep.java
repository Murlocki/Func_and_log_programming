import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.arraycopy;

public class CombinationsWithoutRep extends SimpleCombObject implements PrintAllObjectNonRec, PrintAllObjectRec{


    // Конструктор
    // Принимает на вход алфавит для построения сочетаний
    public CombinationsWithoutRep(String[] alphabet,int k){
        super(alphabet,k);
    }

    // Метод для вызова рекурсивной процедуры построения сочетаний
    @Override
    public void printAllObjectsRecCall(){
        printAllObjectRec(0);
    }

    // Метод для вызова рекурсивной процедуры вывода объектов в файл
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

    // Рекурсивный метод для вывода всех сочетаний длины k
    // Принимает позицию в комбинации
    @Override
    public void printAllObjectRec(int curPos){
        String[] curComb = getCurrentObject();
        String[] alphabet = getAlphabet();
        int n = getN();
        int k = getK();

        if(curPos==getK()){
            printIfFileSet();
        }
        else{
            String startChar = (curPos==0)? alphabet[0]: getNextSymbol(curComb[curPos-1]);

            int startNumber = Arrays.binarySearch(alphabet,startChar);
            int finishNumber = n - (k - curPos);
            for(int i=startNumber;i<=finishNumber;i++){
                curComb[curPos]=alphabet[i];
                printAllObjectRec(curPos+1);
            }
        }
    }

    // Метод для получения следующей по порядку комбинации
    @Override
    public boolean getNextObject(){
        int k = getK();
        int n = getN();
        String[] currentComb = getCurrentObject();
        String[] alphabet = getAlphabet();

        int j=k-1;
        int i=1;
        while((j>=0) && (Objects.equals(currentComb[j], alphabet[n - i]))){
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

    // Не рекурсивный метод для вывода всех сочетаний длины k
    @Override
    public void printAllObjectsNonRec(){
        int k = getK();
        String[]alphabet = getAlphabet();

        String[] comb = new String[k];
        arraycopy(alphabet,0,comb,0,k);
        for(int i=0;i<k;i++){
            comb[i]=alphabet[i];
        }
        setCurrentObject(comb);
        printIfFileSet();
        while (getNextObject()) {
            if(!Objects.equals(getFilePath(), "-1")) printIfFileSet();
            else{
                break;
            }
        }
    }

    // Не рекурсивный метод вывода объектов в файл
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

}

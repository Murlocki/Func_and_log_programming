import java.io.FileWriter;
import java.io.IOException;

public class Subsets extends CombinationsWithoutRep implements PrintAllObjectNonRec, PrintAllObjectRec{

    // Конструктор
    // Принимает алфавит элементов множества
    public Subsets(String[] alp){
        super(alp,alp.length);
    }

    // Не рекурсивный метод вывода всех подмножеств
    @Override
    public void printAllObjectsNonRec(){
        int k = getAlphabet().length;
        System.out.println("{}");
        for(int i=1;i<=k;i++){
            setK(i);
            super.printAllObjectsNonRec();
        }
    }

    // Не рекурсивный метод вывода всех подмножеств в файл
    // Принимает путь до файла
    @Override
    public void printAllObjectsNonRec(String filePath){
        int k = getAlphabet().length;
        setFilePath(filePath);
        try{
            setWriter(new FileWriter(getFilePath()));
            getWriter().write("{}");
            getWriter().write("\n");
            for(int i=1;i<=k;i++){
                setK(i);
                super.printAllObjectsNonRec();
            }
            getWriter().close();
        }catch (IOException e){
            System.out.println("File not found");
            setFilePath("-1");
        }

    }

    // Метод для вызоыва рекурсивного построения всех подмножеств
    @Override
    public void printAllObjectsRecCall(){
        int k = getAlphabet().length;
        this.printAllObjectRec(0);
    }

    // Метод для вызоыва рекурсивного построения всех подмножеств в файл
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

    // Рекурсивный метод выова всех подмножеств от 0 до длины множества
    // Принимает длину подмножества
    @Override
    public void printAllObjectRec(int currentk){
        if(currentk==0) {
            System.out.println("{}");
            currentk=currentk+1;
            printAllObjectRec(currentk);
        }
        else if(currentk<=getK()){
            setK(currentk);
            super.printAllObjectsRecCall();
            currentk=currentk+1;
            printAllObjectRec(currentk);
        }
    }
}

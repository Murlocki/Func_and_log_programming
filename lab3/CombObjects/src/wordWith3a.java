import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.arraycopy;

public class wordWith3a extends ComplexCombObject implements PrintAllObjectNonRec{
    private int k;

    // Конструтор
    // Принимает алфавит и длину объекта
    public wordWith3a(String[] alphabet,int k){
        super(alphabet,new String[]{" "});
        String[] st = new String[k];
        Arrays.fill(st, " ");
        this.setCurrentObject(st);
        this.k=k;
    }

    //Не рекурсивный метод вывода объектов в файл
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

    //Не рекурсивный метод вывода всех объектов
    @Override
    public void printAllObjectsNonRec(){
        int n = getN();
        String[] alphabet = getAlphabet();

        String[] positionsA = new String[3];
        String[] positionsAlp = new String[k];

        for (int i = 0; i < k; i++) {
            positionsAlp[i] = Integer.toString(i);
        }
        for (int i = 0; i < 3; i++) {
            positionsA[i] = Integer.toString(i);
        }

        String[] newAlp = new String[n-1];
        System.arraycopy(alphabet, 1, newAlp, 0, n - 1);

        CombinationsWithoutRep combsA = new CombinationsWithoutRep(positionsAlp,3);
        combsA.setCurrentObject(positionsA);


        String[]word= new String[k];
        setCurrentObject(word);
        do{
            word = getCurrentObject();
            positionsA = combsA.getCurrentObject();

            putOnPositions(positionsA,"a");
            String[] restOfWord = new String[k - 3];
            int lengthRestOfWord = k - 3;
            for (int i = 0; i < lengthRestOfWord; i++) {
                restOfWord[i] = newAlp[0];
            }

            PlacementsWithRepeats pl = new PlacementsWithRepeats(newAlp,k-3);
            pl.setCurrentObject(restOfWord);
            do{
                restOfWord = pl.getCurrentObject();
                int posRestWord = 0;
                for (int i = 0; i < k; i++) {
                    if (!Objects.equals(word[i], "a")) {
                        word[i] = restOfWord[posRestWord];
                        posRestWord++;
                    }
                }
                printIfFileSet();

            }while(pl.getNextObject());
            Arrays.fill(word, " ");
        }while(combsA.getNextObject());
    }
}

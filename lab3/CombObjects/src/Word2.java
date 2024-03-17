import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Word2 extends ComplexCombObject implements PrintAllObjectNonRec{

    // Конструктор
    // Принимает алфавит
    public Word2(String[] alphabet){
        super(alphabet,new String[]{" "," "," "," "," "});
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
        String[]alphabet = getAlphabet();
        int n = getN();
        String[] CurrentWord = getCurrentObject();

        //Получили символ
        CombinationsWithoutRep comb = new CombinationsWithoutRep(alphabet,1);
        String[] symbol = new String[1];
        symbol[0]=alphabet[0];
        comb.setCurrentObject(symbol);

        //Получили все позиции символов
        String[] allPositions = createPositions(5);

        //Создали стартовые позиции для повторяющейся буквы
        CombinationsWithoutRep symbolPositions = new CombinationsWithoutRep(allPositions,2);
        String[] positions=new String[]{"0","1"};
        symbolPositions.setCurrentObject(positions);


        //Основной цикл вывода
        do{
            symbol = comb.getCurrentObject();
            symbolPositions.setCurrentObject(new String[]{"0","1"});
            do{
                //Получили позиции повторяющегося символа
                positions = symbolPositions.getCurrentObject();
                putOnPositions(positions,symbol[0]);

                //Получили новый алфавит
                String[] newAlp = new String[n-1];
                int k=0;
                for(int i=0;i<n;i++)
                    if(!Objects.equals(alphabet[i], symbol[0])){
                        newAlp[k]=alphabet[i];
                        k=k+1;
                    }

                //Формируем размещения остальных символов
                PlacementsWithoutRep restWord = new PlacementsWithoutRep(newAlp,3);

                String[] FirstPl=new String[3];
                System.arraycopy(newAlp, 0, FirstPl, 0, 3);
                restWord.setCurrentObject(FirstPl);


                do{
                    putOnPositions(positions,symbol[0]);
                    String[] CurrentPl = restWord.getCurrentObject();
                    int m=0;
                    for(int i=0;i<5;i++){
                        if(!Objects.equals(CurrentWord[i], symbol[0])){
                            CurrentWord[i]=CurrentPl[m];
                            m++;
                        }
                    }

                    //Выводим слово
                    printIfFileSet();

                    //Очищаем слово
                    clearWord();

                }while(restWord.getNextObject());
            }while(symbolPositions.getNextObject());
        }while(comb.getNextObject());
    }
}

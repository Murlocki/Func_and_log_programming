import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class WordOne2One3 extends ComplexCombObject implements PrintAllObjectNonRec {

    // Конструктор
    // Принимает алфавит
    public WordOne2One3(String[] alphabet){
        super(alphabet,new String[]{" "," "," "," "," "," "," ",});
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

        //Получили символы
        PlacementsWithoutRep comb = new PlacementsWithoutRep(alphabet,2);
        String[] symbol = new String[2];
        symbol[0]=alphabet[0];
        symbol[1]=alphabet[1];
        comb.setCurrentObject(symbol);


        //Получили позиции символа на 2
        String[] allPositions = new String[7];
        for(int i=0;i<allPositions.length;i++){
            allPositions[i]= String.valueOf(i);
        }
        CombinationsWithoutRep symbolPositions2 = new CombinationsWithoutRep(allPositions,2);
        String[] positions_2=new String[]{"0","1"};




        //Позиции для буквы на 3
        String[] positions_after_2 = new String[5];
        int k=0;
        for(int i=0;i<7;i++)
            if(!Arrays.asList(positions_2).contains(allPositions[i])){
                positions_after_2[k]=allPositions[i];
                k=k+1;
            }
        CombinationsWithoutRep symbolPositions3 = new CombinationsWithoutRep(positions_after_2,3);
        String[] positions_3=new String[]{"2","3","4"};




        do{
            symbol = comb.getCurrentObject();
            symbolPositions2.setCurrentObject(new String[]{"0","1"});
            do{

                positions_2 = symbolPositions2.getCurrentObject();
                //Заполняем позиции второй буквы
                positions_after_2 = new String[5];
                k=0;
                for(int i=0;i<7;i++)
                    if(!Arrays.asList(positions_2).contains(allPositions[i])){
                        positions_after_2[k]=allPositions[i];
                        k=k+1;
                    }
                symbolPositions3 = new CombinationsWithoutRep(positions_after_2,3);
                positions_3=new String[3];
                System.arraycopy(positions_after_2, 0, positions_3, 0, 3);
                symbolPositions3.setCurrentObject(positions_3);

                do {

                    positions_3 = symbolPositions3.getCurrentObject();

                    putOnPositions(positions_2, symbol[0]);
                    putOnPositions(positions_3, symbol[1]);

                    String[] newAlp = new String[n - 2];
                    k = 0;
                    for (int i = 0; i < n; i++)
                        if (!Objects.equals(alphabet[i], symbol[0]) && !Objects.equals(alphabet[i], symbol[1])) {
                            newAlp[k] = alphabet[i];
                            k = k + 1;
                        }
                    PlacementsWithoutRep restWord = new PlacementsWithoutRep(newAlp,2);

                    //Создаем первое размещение не повт букв
                    String[] FirstPl = new String[2];
                    System.arraycopy(newAlp, 0, FirstPl, 0, 2);
                    restWord.setCurrentObject(FirstPl);

                    do {
                        putOnPositions(positions_2, symbol[0]);
                        putOnPositions(positions_3, symbol[1]);
                        String[] CurrentPl = restWord.getCurrentObject();

                        String[] currentWord = getCurrentObject();

                        int m = 0;
                        for (int i = 0; i < 7; i++) {
                            if (!Objects.equals(currentWord[i], symbol[0]) && !Objects.equals(currentWord[i], symbol[1])) {
                                currentWord[i] = CurrentPl[m];
                                m++;
                            }
                        }


                        printIfFileSet();

                        for (int i = 0; i < 7; i++) {
                            currentWord[i] = " ";
                        }
                    } while (restWord.getNextObject());
                }while(symbolPositions3.getNextObject());
            }while(symbolPositions2.getNextObject());
        }while(comb.getNextObject());
    }

}

import java.util.Arrays;
import java.util.Objects;

public class WordOne2One3 {

    private String[] alphabet;
    private int n;
    private String[]CurrentWord;
    public WordOne2One3(String[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
        this.CurrentWord=new String[7];
        Arrays.fill(this.CurrentWord," ");
    }

    public void putOnPositions(String[]Positions,String symbol){
        for (String position : Positions) {
            this.CurrentWord[Integer.parseInt(position)] = symbol;
        }
    }

    public void printAllWords(){
        //Получили символы
        PlacementsWithoutRep comb = new PlacementsWithoutRep(this.alphabet);
        String[] symbol = new String[2];

        //Получили позиции символа на 2
        String[] allPositions = new String[7];
        for(int i=0;i<allPositions.length;i++){
            allPositions[i]= String.valueOf(i);
        }
        CombinationsWithoutRep symbolPositions2 = new CombinationsWithoutRep(allPositions);
        String[] positions_2=new String[]{"0","1"};
        symbol[0]=this.alphabet[0];


        //Позиции для буквы на 3
        String[] positions_after_2 = new String[5];
        int k=0;
        for(int i=0;i<7;i++)
            if(!Arrays.asList(positions_2).contains(allPositions[i])){
                positions_after_2[k]=allPositions[i];
                k=k+1;
            }
        CombinationsWithoutRep symbolPositions3 = new CombinationsWithoutRep(positions_after_2);
        String[] positions_3=new String[]{"2","3","4"};
        symbol[1]=this.alphabet[1];

        comb.setCurrentPlacement(symbol);
        do{
            symbol = comb.getCurrentPlacement();
            do{
                //Заполняем позиции второй буквы
                positions_after_2 = new String[5];
                k=0;
                for(int i=0;i<7;i++)
                    if(!Arrays.asList(positions_2).contains(allPositions[i])){
                        positions_after_2[k]=allPositions[i];
                        k=k+1;
                    }
                symbolPositions3 = new CombinationsWithoutRep(positions_after_2);
                positions_3=new String[3];
                System.arraycopy(positions_after_2, 0, positions_3, 0, 3);
                do {

                    putOnPositions(positions_2, symbol[0]);
                    putOnPositions(positions_3, symbol[1]);

                    String[] newAlp = new String[this.n - 2];
                    k = 0;
                    for (int i = 0; i < this.n; i++)
                        if (!Objects.equals(this.alphabet[i], symbol[0]) && !Objects.equals(this.alphabet[i], symbol[1])) {
                            newAlp[k] = this.alphabet[i];
                            k = k + 1;
                        }
                    PlacementsWithoutRep restWord = new PlacementsWithoutRep(newAlp);

                    String[] FirstPl = new String[2];
                    System.arraycopy(newAlp, 0, FirstPl, 0, 2);
                    restWord.setCurrentPlacement(FirstPl);
                    do {
                        putOnPositions(positions_2, symbol[0]);
                        putOnPositions(positions_3, symbol[1]);
                        String[] CurrentPl = restWord.getCurrentPlacement();
                        System.out.println("------------");
                        int m = 0;
                        for (int i = 0; i < 7; i++) {
                            if (!Objects.equals(this.CurrentWord[i], symbol[0]) && !Objects.equals(this.CurrentWord[i], symbol[1])) {
                                this.CurrentWord[i] = CurrentPl[m];
                                m++;
                            }
                        }


                        System.out.println(Arrays.toString(this.CurrentWord));

                        for (int i = 0; i < 7; i++) {
                            this.CurrentWord[i] = " ";
                        }
                    } while (restWord.getNextPlacement(2));
                }while(symbolPositions3.nextCombination(positions_3,3));
            }while(symbolPositions2.nextCombination(positions_2,2));
        }while(comb.getNextPlacement(2));
    }


}

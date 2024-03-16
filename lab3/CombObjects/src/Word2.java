import java.util.Arrays;
import java.util.Objects;

public class Word2 {
    private String[] alphabet;
    private int n;
    private String[]CurrentWord;
    public Word2(String[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
        this.CurrentWord=new String[]{" "," "," "," "," "};
    }

    public void putOnPositions(String[]Positions,String symbol){
        for(int i=0;i<2;i++){
            this.CurrentWord[Integer.parseInt(Positions[i])] = symbol;
        }
    }

    public void printAllWords(){
        //Получили символ
        CombinationsWithoutRep comb = new CombinationsWithoutRep(this.alphabet);
        String[] symbol = new String[1];

        //Получили позиции символов
        String[] allPositions = new String[5];
        for(int i=0;i<allPositions.length;i++){
            allPositions[i]= String.valueOf(i);
        }
        CombinationsWithoutRep symbolPositions = new CombinationsWithoutRep(allPositions);

        String[] positions=new String[]{"0","1"};

        symbol[0]=this.alphabet[0];

        do{
            do{
                putOnPositions(positions,symbol[0]);

                String[] newAlp = new String[this.n-1];
                int k=0;
                for(int i=0;i<this.n;i++)
                    if(!Objects.equals(this.alphabet[i], symbol[0])){
                        newAlp[k]=this.alphabet[i];
                        k=k+1;
                    }

                PlacementsWithoutRep restWord = new PlacementsWithoutRep(newAlp);

                String[] FirstPl=new String[3];
                System.arraycopy(newAlp, 0, FirstPl, 0, 3);
                restWord.setCurrentPlacement(FirstPl);
                do{
                    putOnPositions(positions,symbol[0]);
                    String[] CurrentPl = restWord.getCurrentPlacement();
                    System.out.println("------------");
                    int m=0;
                    for(int i=0;i<5;i++){
                        if(!Objects.equals(this.CurrentWord[i], symbol[0])){
                            this.CurrentWord[i]=CurrentPl[m];
                            m++;
                        }
                    }

                    System.out.println(Arrays.toString(this.CurrentWord));

                    for(int i=0;i<5;i++){
                        this.CurrentWord[i]=" ";
                    }
                }while(restWord.getNextPlacement(3));
            }while(symbolPositions.nextCombination(positions,2));
        }while(comb.nextCombination(symbol,1));
    }
}

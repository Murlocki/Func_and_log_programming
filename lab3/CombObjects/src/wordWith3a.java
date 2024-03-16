import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.arraycopy;

public class wordWith3a {
    private String[] alphabet;
    private int n;

    public wordWith3a(String[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
    }

    public void putAinPos(String[] word, String[] posA){
        for(int i=0;i<3;i++){
            word[Integer.parseInt(posA[i])] = "a";
        }
    }
    public void printWords3a(int k){
        String[] positionsA = new String[3];
        String[] positionsAlp = new String[k];

        for (int i = 0; i < k; i++) {
            positionsAlp[i] = Integer.toString(i);
        }
        for (int i = 0; i < 3; i++) {
            positionsA[i] = Integer.toString(i);
        }

        String[] newAlp = new String[this.n-1];
        for(int i=1;i<this.n;i++){
            newAlp[i-1]=this.alphabet[i];
        }

        CombinationsWithoutRep combsA = new CombinationsWithoutRep(positionsAlp);

        String[]word= new String[k];
        do{
            putAinPos(word,positionsA);
            String[] restOfWord = new String[k - 3];
            int lengthRestOfWord = k - 3;
            for (int i = 0; i < lengthRestOfWord; i++) {
                restOfWord[i] = newAlp[0];
            }

            PlacementsWithRepeats pl = new PlacementsWithRepeats(newAlp);
            do{
                int posRestWord = 0;
                for (int i = 0; i < k; i++) {
                    if (!Objects.equals(word[i], "a")) {
                        word[i] = restOfWord[posRestWord];
                        posRestWord++;
                    }
                }
                for (int i = 0; i < k; i++) {
                    System.out.print(word[i]);
                }
                System.out.println();
            }while(pl.getNextPlacement(restOfWord,k-3));
            for (int i = 0; i < k; i++) {
                word[i] = " ";
            };
        }while(combsA.nextCombination(positionsA,3));
    }
}

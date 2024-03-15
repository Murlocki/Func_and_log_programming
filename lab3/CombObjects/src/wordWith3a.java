import static java.lang.System.arraycopy;

public class wordWith3a {
    private char[] alphabet;
    private int n;

    public wordWith3a(char[] alphabet){
        this.alphabet=alphabet;
        this.n=alphabet.length;
    }

    public void putAinPos(char[] word, char[] posA){
        for(int i=0;i<3;i++){
            word[posA[i]] = 'a';
        }
    }
    public void printWords3a(int k){
        char[] positionsA = new char[3];
        char[] positionsAlp = new char[k];

        for (int i = 0; i < k; i++) {
            positionsAlp[i] = (char)i;
        }
        for (int i = 0; i < 3; i++) {
            positionsA[i] = (char)i;
        }

        char[] newAlp = new char[this.n-1];
        for(int i=1;i<this.n;i++){
            newAlp[i-1]=this.alphabet[i];
        }

        CombinationsWithoutRep combsA = new CombinationsWithoutRep(positionsAlp);

        char[]word= new char[k];
        do{
            putAinPos(word,positionsA);
            char[] restOfWord = new char[k - 3];
            int lengthRestOfWord = k - 3;
            for (int i = 0; i < lengthRestOfWord; i++) {
                restOfWord[i] = newAlp[0];
            }

            PlacementsWithRepeats pl = new PlacementsWithRepeats(newAlp);
            do{
                int posRestWord = 0;
                for (int i = 0; i < k; i++) {
                    if (word[i] != 'a') {
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
                word[i] = ' ';
            };
        }while(combsA.nextCombination(positionsA,3));
    }
}

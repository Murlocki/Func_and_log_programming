import java.util.Arrays;

public class ComplexCombObject extends CombinatorObject{

    //Конструктор
    // Принимает алфавит и стартовую комбинацию
    public ComplexCombObject(String[] alp,String[] startObject){
        super(alp,startObject);
    }

    // Метод для постановки символа на позиции П
    // Принимает массив позиций и символ
    public void putOnPositions(String[]positions,String symbol){
        for (String position : positions) {
            setCurrentObject(symbol, Integer.parseInt(position));
        }
    }

    //Метод очистки символов слова
    public void clearWord(){
        Arrays.fill(getCurrentObject(), " ");
    }

    //Метод формирования массива номеров всех позиций в слове
    public String[] createPositions(int k){
        String[] allPositions = new String[k];
        for(int i=0;i<allPositions.length;i++){
            allPositions[i]= String.valueOf(i);
        }
        return allPositions;
    }
}

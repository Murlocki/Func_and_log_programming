public class SimpleCombObject extends CombinatorObject {
    private int k;

    // Конструктор
    // Принимает алфавит и длину объекта
    public SimpleCombObject(String[] alp,int k){
        super(alp,new String[k]);
        this.k=k;
    }

    // Геттеры
    public int getK() {
        return k;
    }

    // Сеттеры
    public void setK(int k) {
        this.k = k;
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

abstract public class CombinatorObject {
    private int n;
    private String[] alphabet;
    private String[] CurrentObject;
    private String filePath;
    private FileWriter writer;


    //Геттеры
    public FileWriter getWriter() {return writer;}
    public int getN() {
        return n;
    }
    public String[] getAlphabet() {return alphabet;}
    public String[] getCurrentObject() {
        return CurrentObject;
    }
    public String getFilePath() {
        return filePath;
    }

    //Сеттеры
    public void setWriter(FileWriter writer) {this.writer = writer;}
    public void setCurrentObject(String[] currentObject) {
        CurrentObject = currentObject;
    }
    public void setCurrentObject(String objToPut,int pos){
        this.CurrentObject[pos]=objToPut;
    }
    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }
    public void setN(int n) {
        this.n = n;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }



    // Конструктор
    // Принимает алфавит и стартовый объект
    public CombinatorObject(String[] alp, String[] startObject){
        this.alphabet = alp;
        this.n = alp.length;
        this.CurrentObject=startObject;
        this.filePath="";
    }

    // Метод для получения следующего символа из алфавита
    // Принимает текущий символ алфавита
    public String getNextSymbol(String curSymbol){
        int i=0;
        while((i<this.n)&&(!Objects.equals(this.alphabet[i], curSymbol))){
            i=i+1;
        }
        return this.alphabet[i+1];
    }

    //Метод для получения следующего объекта
    public boolean getNextObject(){
        return false;
    };

    //Метод для вывода объекта в консоль или в файл
    public void printIfFileSet(){

        if(Objects.equals(this.getFilePath(), "")) {
            System.out.println(Arrays.toString(getCurrentObject()));
        }
        else{
            try {
                this.writer.write(Arrays.toString(getCurrentObject()));
                this.writer.write('\n');
                this.writer.flush();
            }catch (IOException e){
                System.out.println("File not found");
                this.filePath = "-1";
            }
        }
    }
}

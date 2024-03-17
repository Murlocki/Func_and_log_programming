public class Permitations extends PlacementsWithoutRep{

    // Конструктор
    // Принимает на вход алфавит
    public Permitations(String[] alp){

        super(alp,alp.length);
    }

    // Метод для нерекурсивного вывода всех объектов
    @Override
    public void printAllObjectsNonRec(){

        super.printAllObjectsNonRec();
    }
    // Метод для вызова рекурсивного вывода всех объектов
    @Override
    public void printAllObjectsRecCall(){
        super.printAllObjectsRecCall();
    }

    // Метод для нерекурсивного вывода всех объектов в файл
    // Принимает путь до файла
    @Override
    public void printAllObjectsNonRec(String filePath){

        super.printAllObjectsNonRec(filePath);
    }

    // Метод для вызова рекурсивного вывода всех объектов в файл
    // Принимает путь до файла
    @Override
    public void printAllObjectsRecCall(String filePath){
        super.printAllObjectsRecCall(filePath);
    }
}

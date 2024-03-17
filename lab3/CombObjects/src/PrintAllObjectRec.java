public interface PrintAllObjectRec {

    // Метод для вызова рекурсивного вывода объектов
    public void printAllObjectsRecCall();

    // Метод для вызова рекурсивного вывода объектов в файл
    // Принимает путь до файла
    public void printAllObjectsRecCall(String filePath);

    // Метод рекурсивного вывода объектов
    // Принимает текущую позицию
    public void printAllObjectRec(int currentK);
}
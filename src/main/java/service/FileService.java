package service;

import java.io.*;
import java.util.List;

public class FileService {

    //  Сохраняет список объектов в файл.
    public static <T> void saveToFile(List<T> list, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        }
    }

    //  Загружает список объектов из файла.
    public static <T> List<T> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) ois.readObject();
        }
    }
}
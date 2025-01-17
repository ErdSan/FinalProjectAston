package org.example.data;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.File;
import org.example.validate.Validator;

//класс для работы с файлами
public class FilesHandler {
  // для валидации обьекта
  private Validator validator;

  // метод находит расширение файла
  private String getExtension(String filePath) {
    int dotIndex = filePath.lastIndexOf('.');
    return filePath.substring(dotIndex);
  }

  // метод выбирает куда записывать в бинарный или текстовый

  /**
   * Метод записывает массив объектов в файл
   * @param filePath - путь к файлу неважно бинарный или txt
   * @param objects - массив объектов
   * @param <T> - класс записанного объекта
   */
  public <T> void writeToFile(String filePath, T[] objects) {
    var fileExtension = getExtension(filePath);// расширение файла
    if (fileExtension.equals(".txt")) // текстовый
      writeToTextFile(filePath, objects);
    else // бинарный
      writeToBinaryFile(filePath, objects);
  }

  //запись в файл бинарный
  private <T> void writeToBinaryFile(String filePath, T[] objects) {
    boolean fileExists = new File(filePath).exists();

    try (var objectOutputStream = fileExists
        ? new AppendableObjectOutputStream(new FileOutputStream(filePath, true))
        : new ObjectOutputStream(new FileOutputStream(filePath, true))) {

      for (T object : objects) {
        if (object != null)
          objectOutputStream.writeObject(object);
      }
    } catch (IOException e) {
      System.err.println("Ошибка записи в файл: " + e.getMessage());
    }
  }

  //запись в файл текстовый
  private <T> void writeToTextFile(String filePath, T[] objects) {
    try (var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath, true))) {
      for (T object : objects) {
        bufferedOutputStream.write(object.toString().getBytes());
        bufferedOutputStream.write('\n');
      }
    } catch (IOException e) {
      System.err.println("Ошибка записи в файл: " + e.getMessage());
    }
  }
  //класс для корректирования записи в файл, пропуск заголовка
  private static class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
      super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
      // Пропускаем запись заголовка
      reset();
    }
  }
  /**
   * Метод считывает массив объектов из файла
   * @param filePath - путь к файлу неважно бинарный или txt
   * @param size - требуемый размер
   * @param clazz - .class требуемого класса
   * @param <T> - класс объекта
   * @return возвращает массив обьектов
   */
  public <T> T[] readFromFile(String filePath, Class<T> clazz, int size) {
    var fileExtension = getExtension(filePath);// расширение файла
    if (fileExtension.equals("txt")) // текстовый
      return readFromTextFile(filePath, clazz, size);
    else // бинарный
      return readFromBinaryFile(filePath, clazz, size);
  }
  private <T> T[] readFromTextFile(String filePath, Class<T> clazz, int size) {
    return null;
  }
  //чтение из файла обьекта + его валидация
  private <T> T[] readFromBinaryFile(String filePath, Class<T> clazz, int size) {
    @SuppressWarnings("unchecked")
    var resultArray = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
    try (var objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
      int count = 0;
      while (count < size) {
        var obj = objectInputStream.readObject();
        if (clazz.isInstance(obj)) { // проверяем тип прочитанного обьекта
            resultArray[count] = clazz.cast(obj);
            count++;
        }
      }
    } catch (EOFException e) {
      System.err.println("Недостаточно данных в файле.");
      return null;
    } catch (FileNotFoundException e) {
      System.err.println("Файл " + filePath + " не существует");
      return null;
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Ошибка чтения с файла: " + e.getMessage());
      return null;
    }
    return resultArray;
  }
}


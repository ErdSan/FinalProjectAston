package org.example.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.File;

import org.example.dto.Bus;
import org.example.dto.Student;
import org.example.dto.User;
import org.example.exception.ValidateException;
import org.example.validate.Validator;

//класс для работы с файлами
public class FilesHandler {
    // для валидации обьекта
    private final Validator validator = new Validator();

    // метод находит расширение файла
    private String getExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return filePath.substring(dotIndex);
    }

    // метод выбирает куда записывать в бинарный или текстовый

    /**
     * Метод записывает массив объектов в файл
     *
     * @param filePath - путь к файлу неважно бинарный или txt
     * @param objects  - массив объектов
     * @param <T>      - класс записанного объекта
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
        try (var outputStream = new BufferedWriter(new FileWriter(filePath, true))) {
            var file = new File(filePath);
            System.out.println(file.getAbsolutePath());
            for (T object : objects) {
                outputStream.append(object.toString());
                outputStream.write('\n');
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
     *
     * @param filePath - путь к файлу неважно бинарный или txt
     * @param amount   - требуемый размер
     * @param clazz    - .class требуемого класса
     * @param <T>      - класс объекта
     * @return возвращает массив обьектов или null в случае ошибки
     */
    public <T> T[] readFromFile(String filePath, Class<T> clazz, int amount) {
        var fileExtension = getExtension(filePath);// расширение файла
        if (fileExtension.equals(".txt")) // текстовый
            return readFromTextFile(filePath, clazz, amount);
        else // бинарный
            return readFromBinaryFile(filePath, clazz, amount);
    }

    private String[] getFieldsData(String str) {
        if (str.contains("{") && str.contains("}")) {
            String content = str.substring(str.indexOf("{") + 1, str.lastIndexOf("}"));
            return content.split(", ");
        }
        return null;
    }

    private String extractValue(String[] values, String key) {
        String prefix = key + "='";
        for (var part : values) {
            if (part.startsWith(prefix) && part.endsWith("'")) {
                return part.substring(prefix.length(), part.length() - 1);
            }
        }
        return "";
    }

    private <T> T[] readFromTextFile(String filePath, Class<T> clazz, int amount) {
        @SuppressWarnings("unchecked")
        var resultArray = (T[]) java.lang.reflect.Array.newInstance(clazz, amount);
        try (var inputStream = new BufferedReader(new FileReader(filePath))) {
            int count = 0;
            var line = "";
            while (count < amount) {
                line = inputStream.readLine();
                if (line == null)
                    break;
                var values = getFieldsData(line);
                if (values != null) {
                    try {
                        if (clazz.getSimpleName().equals("Bus")) {
                            var model = extractValue(values, "model");
                            var numberS = extractValue(values, "number");
                            var mileageS = extractValue(values, "mileage");
                            if (!numberS.isEmpty() && !mileageS.isEmpty()) {
                                var number = Integer.parseInt(numberS);
                                var mileage = Double.parseDouble(mileageS);
                                validator.validateBus(number, model, mileage);
                                resultArray[count] = (T) new Bus.Builder()
                                        .setMileage(mileage)
                                        .setModel(model)
                                        .setNumber(number)
                                        .build();
                                count++;

                            }
                        }
                        if (clazz.getSimpleName().equals("User")) {
                            var name = extractValue(values, "name");
                            var password = extractValue(values, "password");
                            var email = extractValue(values, "email");
                            validator.validateUser(name, password, email);
                            resultArray[count] = (T) new User.Builder()
                                    .setEmail(email)
                                    .setName(name)
                                    .setPassword(password)
                                    .build();
                            count++;

                        }
                        if (clazz.getSimpleName().equals("Student")) {
                            var groupNumberS = extractValue(values, "numberGroup");
                            var averageS = extractValue(values, "averageScore");
                            var numberS = extractValue(values, "bookNumber");
                            if (!averageS.isEmpty() && !numberS.isEmpty() && !groupNumberS.isEmpty()) {
                                var average = Double.parseDouble(averageS);
                                var number = Long.parseLong(numberS);
                                var groupNumber = Integer.parseInt(groupNumberS);
                                validator.validateStudent(groupNumberS, average, numberS);
                                resultArray[count] = (T) new Student.Builder()
                                        .setNumberGroup(groupNumber)
                                        .setBookNumber(number)
                                        .setAverageScore(average)
                                        .build();
                                count++;
                            }
                        }
                    } catch (ValidateException e) {
                        if (!e.getMessage().equals("E")) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
            if (count != amount) {
                System.err.println("Недостаточно данных в файле");
                return null;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл " + filePath + " не существует");
        } catch (IOException e) {
            System.err.println("Ошибка чтения с файла: " + e.getMessage());
        }
        return resultArray;
    }

    //чтение из файла обьекта + его валидация
    private <T> T[] readFromBinaryFile(String filePath, Class<T> clazz, int amount) {
        @SuppressWarnings("unchecked")
        var resultArray = (T[]) java.lang.reflect.Array.newInstance(clazz, amount);
        try (var objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            int count = 0;
            while (count < amount) {
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


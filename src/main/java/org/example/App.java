package org.example;

import org.example.BinarySearch.BinarySearch;
import org.example.data.ConsoleInputHandler;
import org.example.data.FilesHandler;
import org.example.data.RandomInputHandler;
import org.example.dto.Bus;
import org.example.dto.Student;
import org.example.dto.User;
import org.example.sort.SelectionSort;
import org.example.sort.SortStrategy;

import java.util.Scanner;

public class App {
    static Student[] students;
    static Bus[] buses;
    static User[] users;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Ввести данные (вручную, из файла или случайно)");
            System.out.println("2. Сортировать данные");
            System.out.println("3. Найти элемент (бинарный поиск)");
            System.out.println("4. Записать данные в файл");
            System.out.println("5. Выход");
            System.out.println("*************************************************");
            System.out.println("Введите номер пункта:");
            int choice = scanner.nextInt();
            int choiceInput;
            int amount;
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // Ввод данных
                    System.out.println("Выберите вариант получения данных:");
                    System.out.println("1. Вручную");
                    System.out.println("2. Из файла");
                    System.out.println("3. Случайно");
                    choiceInput = scanner.nextInt();
                    switch (choiceInput) {
                        case 1:
                            //Вручную
                            ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
                            System.out.println("Выберите данные:");
                            System.out.println("1. Студенты");
                            System.out.println("2. Автобусы");
                            System.out.println("3. Пользователи");
                            choiceInput = scanner.nextInt();
                            System.out.println("Введите количество вводимых данных:");
                            amount = scanner.nextInt();
                            switch (choiceInput) {
                                case 1:
                                    students = consoleInputHandler.createStudents(amount);
                                    break;
                                case 2:
                                    buses = consoleInputHandler.createBuses(amount);
                                    break;
                                case 3:
                                    users = consoleInputHandler.createUsers(amount);
                                    break;
                                default:
                                    System.out.println("Неверный выбор! Начните с начала!");
                            }
                            break;
                        case 2:
                            //Из файла
                            FilesHandler filesHandler = new FilesHandler();
                            System.out.println("Выберите данные:");
                            System.out.println("1. Студенты");
                            System.out.println("2. Автобусы");
                            System.out.println("3. Пользователи");
                            choiceInput = scanner.nextInt();
                            System.out.println("Введите количество данных:");
                            amount = scanner.nextInt();
                            switch (choiceInput) {
                                case 1:
                                    students = filesHandler.readFromFile("src/SourceData.txt", Student.class, amount);
                                    break;
                                case 2:
                                    buses = filesHandler.readFromFile("src/SourceData.txt", Bus.class, amount);
                                    break;
                                case 3:
                                    users = filesHandler.readFromFile("src/SourceData.txt", User.class, amount);
                                    break;
                                default:
                                    System.out.println("Неверный выбор! Начните с начала!");
                            }
                            break;
                        case 3:
                            //Случайно
                            RandomInputHandler randomInputHandler = new RandomInputHandler();
                            System.out.println("Выберите данные:");
                            System.out.println("1. Студенты");
                            System.out.println("2. Автобусы");
                            System.out.println("3. Пользователи");
                            choiceInput = scanner.nextInt();
                            System.out.println("Введите количество данных:");
                            amount = scanner.nextInt();
                            switch (choiceInput) {
                                case 1:
                                    students = randomInputHandler.createRandomStudents(amount);
                                    break;
                                case 2:
                                    buses = randomInputHandler.createRandomBuses(amount);
                                    break;
                                case 3:
                                    users = randomInputHandler.createRandomUsers(amount);
                                    break;
                                default:
                                    System.out.println("Неверный выбор! Начните с начала!");
                            }
                            break;
                        default:
                            System.out.println("Неверный выбор! Начните с начала!");
                    }
                    break;
                case 2:
                    //Сортировка данных
                    System.out.println("Выберите какие данные нужно отсортировать:");
                    System.out.println("1. Студенты");
                    System.out.println("2. Автобусы");
                    System.out.println("3. Пользователи");
                    choiceInput = scanner.nextInt();
                    switch (choiceInput) {
                        case 1:
                            SortStrategy<Student> sortStrategyStudents = new SelectionSort<>();
                            sortStrategyStudents.sort(students);
                            break;
                        case 2:
                            SortStrategy<Bus> sortStrategyBuses = new SelectionSort<>();
                            sortStrategyBuses.sort(buses);
                            break;
                        case 3:
                            SortStrategy<User> sortStrategyUsers = new SelectionSort<>();
                            sortStrategyUsers.sort(users);
                            break;
                        default:
                            System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                    break;
                case 3:
                    //Поиск элемента
                    System.out.println("Выберите в каком списке нужно искать:");
                    System.out.println("1. Студенты");
                    System.out.println("2. Автобусы");
                    System.out.println("3. Пользователи");
                    choiceInput = scanner.nextInt();
                    switch (choiceInput) {
                        case 1:
                            System.out.println("Введите данные по которым искать студента:");
                            System.out.println("Введите номер группы:");
                            int numberGroup = scanner.nextInt();
                            System.out.println("Введите среднюю оценку:");
                            double averageScope = scanner.nextDouble();
                            System.out.println("Введите номер зачетной книги:");
                            int bookNumber = scanner.nextInt();
                            BinarySearch<Student> studentsBinarySearch = new BinarySearch<>();
                            studentsBinarySearch.search(students, new Student.Builder().setNumberGroup(numberGroup).setAverageScore(averageScope).setBookNumber(bookNumber).build());
                            break;
                        case 2:
                            System.out.println("Введите данные по которым искать автобус:");
                            System.out.println("Введите модель автобуса:");
                            String modelBus = scanner.nextLine();
                            System.out.println("Введите номер автобуса:");
                            int numberBus = scanner.nextInt();
                            System.out.println("Введите пробег автобуса:");
                            double mileage = scanner.nextDouble();
                            BinarySearch<Bus> busBinarySearch = new BinarySearch<>();
                            busBinarySearch.search(buses, new Bus.Builder().setModel(modelBus).setNumber(numberBus).setMileage(mileage).build());
                            break;
                        case 3:
                            System.out.println("Введите данные по которым искать пользователя:");
                            System.out.println("Введите имя пользователя:");
                            String name = scanner.nextLine();
                            System.out.println("Введите пароль пользователя:");
                            String password = scanner.nextLine();
                            System.out.println("Введите email пользователя:");
                            String email = scanner.nextLine();
                            BinarySearch<User> userBinarySearch = new BinarySearch<>();
                            userBinarySearch.search(users, new User.Builder().setName(name).setPassword(password).setEmail(email).build());
                            break;
                        default:
                            System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                    break;
                case 4:
                    //Запись в файл
                    System.out.println("Выберите какой список нужно записать в файл:");
                    System.out.println("1. Студенты");
                    System.out.println("2. Автобусы");
                    System.out.println("3. Пользователи");
                    choiceInput = scanner.nextInt();
                    FilesHandler filesHandler = new FilesHandler();
                    switch (choiceInput) {
                        case 1:
                            filesHandler.writeToFile("src/Result.txt", students);
                            break;
                        case 2:
                            filesHandler.writeToFile("src/Result.txt", buses);
                            break;
                        case 3:
                            filesHandler.writeToFile("src/Result.txt", users);
                            break;
                        default:
                            System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                    break;
                case 5:
                    //Выход из цикла
                    running = false;
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        System.out.println("Работа программы завершена!");
        scanner.close();
    }
}
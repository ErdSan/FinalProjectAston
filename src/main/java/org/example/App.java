package org.example;


import java.util.Scanner;

public class App {
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
            scanner.nextLine(); // Очистка буфера
            switch (choice) {
                case 1:
                    System.out.println("Выберите вариант получения данных:");
                    System.out.println("1. Вручную");
                    System.out.println("2. Из файла");
                    System.out.println("3. Случайно");
                    choiceInput = scanner.nextInt();
                    switch (choiceInput) {
                        case 1:
                            //Вручную
                            break;
                        case 2:
                            //Из файла
                            break;
                        case 3:
                            //Случайно
                            break;
                        default:
                            System.out.println("Неверный выбор! Начните с начала!");
                    }
                    // Ввод данных (вызов метода Участника 4)
                    break;
                case 2:
                    // Сортировка данных (вызов метода Участника 2)
                    break;
                case 3:
                    // Поиск элемента (вызов метода Участника 3)
                    break;
                case 4:
                    // Запись в файл (вызов метода Участника 4)
                    break;
                case 5:
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
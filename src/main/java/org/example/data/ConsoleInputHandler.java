package org.example.data;

import java.util.Scanner;

import org.example.dto.Bus;
import org.example.dto.Student;
import org.example.dto.User;
import org.example.exception.ValidateException;
import org.example.validate.Validator;

public class ConsoleInputHandler {
    private final Validator validator = new Validator();

    /**
     * @return массив автобусов заданной размерности
     */
    public Bus[] createBuses(int amount) {
        var scanner = new Scanner(System.in);
        var buses = new Bus[amount];
        for (int i = 0; i < amount; i++) {
            try {
                System.out.println("Введите " + (i + 1) + "-й автобус:");
                System.out.println("Введите номер автобуса:");
                var number = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите модель автобуса:");
                var model = scanner.nextLine();
                System.out.println("Введите пробег автобуса:");
                var mileage = scanner.nextDouble();
                scanner.nextLine();
                validator.validateBus(number, model, mileage);
                buses[i] = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();
            } catch (ValidateException exception) {
                System.err.println(exception.getMessage());
                i--;
            }
        }
        return buses;
    }

    /**
     * @return массив студентов заданной размерности
     */
    public Student[] createStudents(int amount) {
        var scanner = new Scanner(System.in);
        var students = new Student[amount];
        for (int i = 0; i < amount; i++) {
            try {
                System.out.println("Введите " + (i + 1) + "-го студента:");
                System.out.println("Введите номер группы студента:");
                var groupNumber = scanner.nextLine();
                System.out.println("Введите номер зачетки студента:");
                var number = scanner.nextLine();
                System.out.println("Введите средний балл студента:");
                var average = scanner.nextDouble();
                scanner.nextLine();
                validator.validateStudent(groupNumber, average, number);
                students[i] = new Student.Builder()
                        .setBookNumber(Long.parseLong(number))
                        .setAverageScore(average)
                        .setNumberGroup(Integer.parseInt(groupNumber))
                        .build();
            } catch (ValidateException exception) {
                System.err.println(exception.getMessage());
                i--;
            }
        }
        return students;
    }

    /**
     * @return массив пользователей заданной размерности
     */
    public User[] createUsers(int amount) {
        var scanner = new Scanner(System.in);
        var users = new User[amount];
        for (int i = 0; i < amount; i++) {
            try {
                System.out.println("Введите " + (i + 1) + "-го пользователя:");
                System.out.println("Введите имя пользователя:");
                var name = scanner.nextLine();
                System.out.println("Введите пароль пользователя:");
                var password = scanner.nextLine();
                System.out.println("Введите почту пользователя:");
                var email = scanner.nextLine();
                validator.validateUser(name, password, email);
                users[i] = new User.Builder()
                        .setName(name)
                        .setPassword(password)
                        .setEmail(email)
                        .build();
            } catch (ValidateException exception) {
                System.err.println(exception.getMessage());
                i--;
            }
        }
        return users;
    }
}


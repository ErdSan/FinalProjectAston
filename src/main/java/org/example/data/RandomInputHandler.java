package org.example.data;

import java.util.Random;

import org.example.dto.Bus;
import org.example.dto.Student;
import org.example.dto.User;

public class RandomInputHandler {
    private static final Random random = new Random();

    private Character randomCharacter() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int randomIndex = random.nextInt(characters.length());
        return characters.charAt(randomIndex);
    }

    private String randomLengthString(int length) {
        var finalString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            finalString.append(randomCharacter());
        }
        return finalString.toString();
    }

    private String randomLengthLetterString(int length) {
        var finalString = new StringBuilder();
        finalString.append((char) (random.nextInt(91 - 65) + 65));
        for (int i = 0; i < length - 1; i++) {
            finalString.append((char) (random.nextInt(123 - 97) + 97));
        }
        return finalString.toString();
    }

    public Bus[] createRandomBuses(int amount) {
        var buses = new Bus[amount];
        for (int i = 0; i < amount; i++) {
            var number = random.nextInt(1000);
            var model = "Model" + randomCharacter();
            var mileage = random.nextDouble() * 150000;
            buses[i] = new Bus.Builder()
                    .setMileage(mileage)
                    .setModel(model)
                    .setNumber(number)
                    .build();
        }
        return buses;
    }

    public Student[] createRandomStudents(int amount) {
        var students = new Student[amount];
        for (int i = 0; i < amount; i++) {
            var groupNumber = random.nextInt(1000) + 1;
            var number = random.nextInt(50000) + 1;
            var average = random.nextDouble() * 5;
            students[i] = new Student.Builder()
                    .setNumberGroup(groupNumber)
                    .setBookNumber(number)
                    .setAverageScore(average)
                    .build();
        }
        return students;
    }

    public User[] createRandomUsers(int amount) {
        var users = new User[amount];
        for (int i = 0; i < amount; i++) {
            var name = randomLengthLetterString(random.nextInt(10 - 4) + 4);
            var password = randomLengthString(random.nextInt(12 - 6) + 6);
            var email = randomLengthLetterString(random.nextInt(10 - 5) + 5) + "@random.com";
            users[i] = new User.Builder()
                    .setEmail(email)
                    .setPassword(password)
                    .setName(name)
                    .build();
        }
        return users;
    }
}

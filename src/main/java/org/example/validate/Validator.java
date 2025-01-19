package org.example.validate;

import org.example.exception.ValidateException;

//Класс для валидации данных
public class Validator {
  /**
   * Валидация обьекта автобус
   *
   * @param number  - номер
   * @param model   - модель
   * @param mileage - пробег
   * @throws ValidateException с собственным сообщением
   */
  public void validateBus(Integer number, String model, Double mileage)
      throws ValidateException {
    if (model.isEmpty() && mileage == null)
      throw new ValidateException("E");
    if (number <= 0)
      throw new ValidateException("Номер автобуса должен быть больше нуля");
    if (model.isEmpty())
      throw new ValidateException("Модель автобуса не должна быть пустой");
    if (mileage < 0)
      throw new ValidateException("Пробег не может быть отрицательным");
  }

  /**
   * Валидация обьекта студент
   *
   * @param recordBookNumber - номер зачетки
   * @param averageGrade     - средний балл
   * @param groupNumber      - номер группы
   * @throws ValidateException с собственным сообщением
   */
  public void validateStudent(String groupNumber, Double averageGrade, String recordBookNumber)
      throws ValidateException {
    if (groupNumber.isEmpty() && averageGrade == null && recordBookNumber.isEmpty())
      throw new ValidateException("E");
    if (groupNumber.isEmpty() || !groupNumber.matches("\\d+"))
      throw new ValidateException("Номер группы должен быть численный");
    if (averageGrade < 0 || averageGrade > 5)
      throw new ValidateException("Средний балл должен быть в диапазоне от 0 до 10");
    if (recordBookNumber.isEmpty() || !recordBookNumber.matches("\\d+"))
      throw new ValidateException("Номер зачетной книжки должен быть численный");
  }

  /**
   * Валидация обьекта пользователь
   *
   * @param password - пароль
   * @param email    - почта
   * @param name     - имя
   * @throws ValidateException с собственным сообщением
   */
  public void validateUser(String name, String password, String email)
      throws ValidateException {
    if (name.isEmpty() && email.isEmpty() && password.isEmpty())
      throw new ValidateException("E");
    if (name.isEmpty()) {
      throw new ValidateException("Имя пользователя не может быть пустым.");
    }
    if (password.length() < 6) {
      throw new ValidateException("Пароль должен быть не менее 6 символов.");
    }
    if (!password.matches("(?=.+[A-Za-z])(?=.+\\d).+"))
      throw new ValidateException("Пароль должен иметь и буквы и цифры.");
    if (!email.matches("\\w*@[a-zA-Z]+\\.[a-zA-Z]{2,}")) {
      throw new ValidateException("Некорректный формат электронной почты.");
    }
  }
}

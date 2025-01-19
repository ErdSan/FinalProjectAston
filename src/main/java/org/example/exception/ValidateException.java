package org.example.exception;

//Исключение при валидации
public class ValidateException extends IllegalArgumentException{
  public ValidateException(String message){
    super(message);
  }
}

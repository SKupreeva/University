package client.Exceptions;

public class PhoneException extends Exception{

    public String getMessage(){
        return "Ошибка! Вы ввели номер телефона неправильно!";
    }
}

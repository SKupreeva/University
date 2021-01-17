package client.Exceptions;

public class NumberException extends Exception{

    public String getMessage(){
        return "Ошибка! Вы ввели число неправильно!";
    }
}

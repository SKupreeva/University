package client.Exceptions;

public class StringException extends Exception{

    public String getMessage(){
        return "Ошибка! Вы ввели данные неправильно!";
    }
}

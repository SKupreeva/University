package client.Exceptions;

public class NullException extends Exception{

    public String getMessage(){
        return "Ошибка! Вы не ввели данные!";
    }
}

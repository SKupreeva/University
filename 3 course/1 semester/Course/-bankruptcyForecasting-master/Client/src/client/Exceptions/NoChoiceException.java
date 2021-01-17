package client.Exceptions;

public class NoChoiceException extends Exception{

    public String getMessage(){
        return "Ошибка! Вы не выбрали данные!";
    }
}

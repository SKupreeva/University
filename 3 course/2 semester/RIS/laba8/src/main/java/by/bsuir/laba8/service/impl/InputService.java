package by.bsuir.laba8.service.impl;

import by.bsuir.laba8.service.IInputService;
import java.util.Scanner;

public class InputService implements IInputService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String ReadString() {
        StringBuilder str = new StringBuilder();
        if(scanner.hasNextLine()) {
            str.append(scanner.nextLine());
        }

        return str.toString();
    }
}

package com.company;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws ServiceException, IOException {

        String endpoint = "http://localhost:8080/axis/CarService.jws";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endpoint));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isActive = true;
        while (isActive) {
            String choice = readChoices(reader);
            switch (choice) {
                case "1":
                    System.out.println("Введите вид ремонта");
                    String repairType = reader.readLine();
                    Object[] param = new Object[]{repairType};
                    String crash = (String) call.invoke("findCrashByRepairType", param);
                    System.out.println("Тип поломки для " + repairType + ": " + crash + "\n");
                    break;
                case "2":
                    System.out.println("Введите тип поломки");
                    crash = reader.readLine();
                    param = new Object[]{crash};
                    repairType = (String) call.invoke("findRepairTypeByCrash", param);
                    System.out.println("Вид ремонта для " + crash + ": " + repairType + "\n");
                    break;
                case "0":
                    isActive = false;
                    break;
                default:
                    System.out.println("Ошибка. Введите правильные данные");
            }
        }
    }

    private static String readChoices(BufferedReader reader) throws IOException {
        System.out.println("0 - Выход из приложения");
        System.out.println("1 - Поиск по виду ремонта");
        System.out.println("2 - Поиск по типу поломки");
        System.out.println("Введите номер списка:");
        return reader.readLine();
    }
}

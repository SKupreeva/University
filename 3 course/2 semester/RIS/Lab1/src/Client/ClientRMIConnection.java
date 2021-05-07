package Client;

import Constants.Const;
import Logic.TwoVowelsInterface;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import static Printer.Printer.println;

public class ClientRMIConnection {
    void connectServer(){
        try{
            var input = new Scanner(System.in);
            final var registry = LocateRegistry.getRegistry(Const.REGISTRY_PORT_NUMBER);
            var service = (TwoVowelsInterface) registry.lookup(Const.UNIC_BINDING_NAME);
            println("Connected!\nPlease enter your string:");
            println("Result: " + service.changeString(input.nextLine()));
        } catch (Exception e) {
            println("Error! Client exception!\n" + e.toString());
        }
    }
}

package Server;

import Constants.Const;
import Logic.TwoVowelsChanging;
import OptionsProvider.OptionsProvider;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import static Printer.Printer.println;

public class ServerRMIConnection {
    void startServer(){
        var provider = new OptionsProvider();
        provider.getOptions();
        try {
            final var service = new TwoVowelsChanging();
            final var registry = LocateRegistry.createRegistry(Const.REGISTRY_PORT_NUMBER);
            var stub = UnicastRemoteObject.exportObject(service, Const.STUB_PORT_NUMBER);
            registry.bind(Const.UNIC_BINDING_NAME, stub);
            println("Server is ready!\nListening...");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            println("Error! Server exception!\n" + e.toString());
        }
    }
}

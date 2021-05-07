package Client;

public class Client {

    public static void main(String[] args)
    {
        var rmi = new ClientRMIConnection();
        rmi.connectServer();
    }
}

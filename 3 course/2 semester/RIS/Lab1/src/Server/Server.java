package Server;

public class Server {

    public static void main(String[] args)
    {
        var rmi = new ServerRMIConnection();
        rmi.startServer();
    }
}

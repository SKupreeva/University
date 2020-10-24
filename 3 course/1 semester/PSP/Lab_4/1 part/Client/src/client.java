import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] arg) {
        try {
            System.out.println("Server is connecting. Please wait.");
            Socket clientSocket = new Socket("127.0.0.1",2624);
            System.out.println("Connection established!");

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream outp = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream  inp = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Enter dimension of the matrix \n\tEnter '0' to exit the programm.");
            String clientMess = stdin.readLine();
            System.out.println("You've entered: "+clientMess);

            while(!clientMess.equals("0")) {
                outp.writeObject(clientMess);
                System.out.println("Ratio =  "+inp.readObject()+".");
                System.out.println("---------------------------");
                clientMess = stdin.readLine();
                System.out.println("You've entered: "+clientMess);
            }

            outp.close();
            inp.close();
            clientSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

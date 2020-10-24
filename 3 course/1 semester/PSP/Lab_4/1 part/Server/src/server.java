import java.io.*;
import java.net.*;

public class server
{
    public static void main(String[] arg) {
    ServerSocket serverSocket = null;
    Socket clientAccepted = null;
    ObjectInputStream  inp = null;
    ObjectOutputStream outp = null;

    try {
        System.out.println("Server is starting. Please wait.");
        serverSocket = new ServerSocket(2624);
        clientAccepted = serverSocket.accept();
        System.out.println("Connection established!");

        inp = new ObjectInputStream(clientAccepted.getInputStream());
        outp = new ObjectOutputStream(clientAccepted.getOutputStream());

        String clientMess = (String)inp.readObject();
        while(!clientMess.equals("0"))
        {
            System.out.println("Dimension of the matrix: '"+ clientMess +"'");
            int n = Integer.parseInt(clientMess);
            int sumMainD = 0, sumSideD = 0;
            int[][] mas = new int[n][n];
            System.out.println("    Matrix:");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    mas[i][j] = (int)(Math.random() * n);
                    System.out.print(mas[i][j]+"  ");
                    if(j == n-1)
                        System.out.print("\n");
                }
            }
            System.out.print("\n\n");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == j)
                        sumMainD+=mas[i][j];
                    if(j == n - i -1) {
                        sumSideD+= mas[i][j];
                    }
                }
            }
            System.out.println("Main diagonal sum = "+sumMainD+".");
            System.out.println("Side diagonal sum = "+sumSideD+".\n");
            int k = sumMainD/sumSideD;
            int d = sumMainD%sumSideD;
            String s = k +"." + d;
            System.out.println("Ratio = "+s+".");
            clientMess = s;
            outp.writeObject(clientMess);
            clientMess = (String)inp.readObject();
        }

    }catch(Exception e)	{
        } finally {
            try {
                inp.close();
                outp.close();
                clientAccepted.close();
                serverSocket.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

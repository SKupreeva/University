import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client {
    public void runClient() throws IOException {
        System.out.println("Session started!");
        DatagramSocket s = null;
        try {
            byte[] buf = new byte[512];
            s = new DatagramSocket();
            Scanner scan = new Scanner(System.in);
            int[] mas = new int[3];
            System.out.println("Please enter X:");
            mas[0] = scan.nextInt();
            System.out.println("Please enter Y:");
            mas[1] = scan.nextInt();
            System.out.println("Please enter Z:");
            mas[2] = scan.nextInt();
            byte[] arr = new byte[] {(byte)mas[0], (byte)mas[1], (byte)mas[2]};
            DatagramPacket sendPack = new DatagramPacket(arr, arr.length, InetAddress.getByName("127.0.0.1"), 8001);
            s.send(sendPack);
            DatagramPacket recvPack = new DatagramPacket(buf, buf.length);
            s.receive(recvPack);
            String version = new String(recvPack.getData()).trim();
            System.out.println("Function value = " + version + ".");
            System.out.println("\nSession ended!");
        }
        finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            client clnt = new client();
            clnt.runClient();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

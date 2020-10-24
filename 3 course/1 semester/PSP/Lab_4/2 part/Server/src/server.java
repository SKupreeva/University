import java.net.*;
import java.io.*;

public class server {
    public void runServer() throws IOException {
        DatagramSocket s = null;
        try {
            byte[] bf = new byte[512];
            s = new DatagramSocket(8001);
            System.out.println("Server started!");
            DatagramPacket recvPacket = new DatagramPacket(bf, bf.length);
            s.receive(recvPacket);
            FileWriter fw = new FileWriter("D:\\универ\\3 курс\\1 семестр\\ПСП\\Lab 4\\2 part\\Server\\data.txt");
            byte[] cmd = recvPacket.getData();
            float x = (int)cmd[0];
            float y = (int)cmd[1];
            float z = (int)cmd[2];
            System.out.println("Values: " + x + " " + y + " " + z);
            double k = 6+(Math.pow(Math.E, (x-Math.sin(y)))/(y+(Math.tan(Math.pow(x, 2))/(y+(Math.pow(x, 7))/z))))*
                    Math.pow(1+Math.pow((1/Math.tan(z/100)), 7), Math.sqrt(Math.abs(y)+3));
            System.out.println("Function value = " + k);
            String str = Double.toString(k);
            byte[] buf = str.getBytes();
            fw.write(x + " " + y + " " + z +"\n");
            fw.write(k + "\n");
            fw.flush();
            DatagramPacket sendPacket = new DatagramPacket(buf, 0, recvPacket.getAddress(), recvPacket.getPort());
            sendPacket.setData(buf);
            sendPacket.setLength(buf.length);
            s.send(sendPacket);
            fw.close();
            System.out.println("Server stopped");
        }
        finally {
            if (s != null)
                s.close();
        }
    }
    public static void main(String[] args) {
        try {
            server svr = new server();
            svr.runServer();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

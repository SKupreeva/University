package ClientWork;
import MyForms.SigningInFrame;
import java.net.*;
import java.io.*;

public class Client extends SocketStream{
     public static void main(String[] args) throws IOException,
            InterruptedException {
        InetAddress address = InetAddress.getByName(null);
        socket = new Socket(address, 8020);
        
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        
        SigningInFrame signingIn = new SigningInFrame();
        signingIn.setVisible(true);
        signingIn.setLocationRelativeTo(null);
    }
    
}

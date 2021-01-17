package ClientWork;
import java.net.*;
import java.io.*;

public class SocketStream {
    protected static Socket socket;
    protected static BufferedReader in;
    protected static PrintWriter out;

    public void sendString (String str){
        out.println(str);
    }
    
    public void sendInt (int i){
        out.println(Integer.toString(i));
    }
    
    public String getString () throws IOException{
        return in.readLine();
    }
    
    public int getInt () throws IOException{
        return Integer.parseInt(in.readLine());
    }
}

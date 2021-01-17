package client;

import java.io.*;
import java.net.Socket;

public final class Client {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    private static Client instance;

    public static synchronized Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }

    private Client() {
        try {
            clientSocket = new Socket("localhost", 9000);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            InputStream input  = clientSocket.getInputStream();
            System.out.println("client.Client connected to socket.");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void send(String word) {
        try {
            out.write(word+'\n');
            out.flush();
            System.out.println("я отправил: "+ word);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String get() throws IOException {
        String word = in.readLine();
        System.out.println("я принял: "+word);
        return word;
    }

    public void close() {
        try {
            clientSocket = new Socket("localhost", 9001);
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

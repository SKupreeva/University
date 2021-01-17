package ServerWork;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.MyDatabase;

public class ThreadServer extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private MyDatabase database;
    
    public ThreadServer(Socket s) throws IOException, ClassNotFoundException{
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
        try {
            database = new MyDatabase("org.gjt.mm.mysql.Driver",
            "jdbc:mysql://localhost/database?useUnicode=true&characterEncoding=utf8",
            "root", "root");
            System.out.println("Сервер соединен с базой данных");
        } catch (SQLException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        start();
    }

    public void run() {
        Integer idOperation;
        ServerWork obj = new ServerWork(in, out, database);
        try {
            while (true) {
                String bufString = in.readLine();
                if (bufString.equals("END")) {
                    database.close();
                    System.out.println("Сервер отсоединен от базы данных");
                    break;
                }
                idOperation = Integer.parseInt(bufString);
                obj.getId(idOperation);
            }
            System.out.println("Клиент был отсоединен");
        }
        catch (IOException ex) {
            System.err.println("IO Exception");
        } catch (SQLException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }
}

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class Worker implements Runnable {
    protected Socket clientSocket = null;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static InputStream input;
    private static OutputStream output;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
            out = new BufferedWriter( new OutputStreamWriter( clientSocket.getOutputStream() ) );

            String exit = "ok";
            while (!exit.equals( "exit" )) {
                String str = in.readLine();
                System.out.println( "я получил: " + str );
                String who = null;

                switch (str) {
                    case "authorization": {
                        who = authorization();
                        break;
                    }
                    case "registration": {
                        registration();
                        who = "user";
                        break;
                    }
                    case "exit": {
                        exit = "exit";
                        output.close();
                        input.close();
                        break;
                    }
                    default:break;
                }
                switch (who) {
                    case "admin": {
                        exit = menuAdmin();
                        break;
                    }
                    case "user": {
                        exit = menuUser();
                        break;
                    }
                    case "nobody":{
                        break;
                    }
                    default:break;
                }
            }


        }
        catch ( ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String menuAdmin() throws SQLException, ClassNotFoundException, FileNotFoundException {
        DatabaseHandler handler = new DatabaseHandler();
        handler.getDbConnection(true);
        String users = handler.getUsers();
        String menu = "work";
        try {
            out.write( users + '\n' );
            out.flush();
            System.out.println( "я отправил: " + users );


            while (!menu.equals( "exit" )) {
                menu = in.readLine();
                System.out.println( "я получил: " + menu );
                switch (menu) {
                    case "addUSD": {
                        String newRate = in.readLine();
                        System.out.println( "я получил: " + newRate);
                        handler.addUSD(newRate);
                        break;
                    }
                    case "getReports": {
                        String userId = in.readLine();
                        System.out.println( "я получил: " + userId );
                        String reports = handler.getReportsUser( userId );
                        out.write( reports + '\n' );
                        out.flush();
                        break;
                    }
                    case "getCompanies": {
                        String userId = in.readLine();
                        System.out.println( "я получил: " + userId );
                        String companies = handler.getCompanies( userId );
                        out.write( companies + '\n' );
                        out.flush();
                        break;
                    }
                    case "deleteUser":{
                        String userId = in.readLine();
                        System.out.println( "я получил: " + userId );
                        handler.deleteUser(userId);
                        break;
                    }
                    case "statistics":{
                        handler.saveStatistics();
                        break;
                    }
                    case "exit": {
                        menu = "exit";
                        break;
                    }
                    default:break;
                }
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return menu;

    }

    protected void registration() {
        try {
            String user;
            user = in.readLine();
            if(!user.equals( "exit" )) {
                JSONObject userJson = new JSONObject( user );
                int id = IdGenerator.getInstance( "user" ).getNextId();

                String surname = userJson.getString( "surname" );
                String name = userJson.getString( "name" );
                String phone = userJson.getString( "phone" );
                String email = userJson.getString( "email" );
                String login = userJson.getString( "login" );
                String password = userJson.getString( "password" );

                DatabaseHandler handler = new DatabaseHandler();
                String sign = handler.signUpUser( id, surname, name, phone, email, login, password );
                out.write( sign + '\n' );
                out.flush();
                System.out.println( "я отправил: " + sign );
            }else {return;}
        }
        catch (IOException | JSONException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected String authorization() {
        String who = null;
        try {
            String user = in.readLine();
            JSONObject userJson = new JSONObject( user );

            String login = userJson.getString( "login" );
            String password = userJson.getString( "password" );

            if( login.equals( "admin" ) && password.equals( "admin" ) ) {
                output.write( "admin\n".getBytes() );
                who = "admin";
            }
            else {
                DatabaseHandler handler = new DatabaseHandler();
                String sign = handler.signInUser( login, password );
                if(sign.equals( "nobody" )){
                    output.write( "nobody\n".getBytes() );
                    out.flush();
                    who = "nobody";
                }else {
                    who = "user";
                    output.write( "user\n".getBytes() );
                    out.flush();
                    System.out.println( "я отправил: " + "user" );
                    out.write( sign + '\n' );
                    out.flush();
                    System.out.println( "я отправил: " + sign );
                }
            }

        }
        catch (IOException | JSONException | SQLException e) {
            e.printStackTrace();
        }
        return who;
    }

    public String menuUser() throws IOException, SQLException, ClassNotFoundException {
        DatabaseHandler handler = new DatabaseHandler();
        handler.getDbConnection(true);
        String idUser = in.readLine();
        String companies = handler.getCompanies(idUser);
        String menu2 = "work";
        try {
            out.write( companies + '\n' );

            out.flush();
            System.out.println( "я отправил: " + companies );


            while (!menu2.equals( "exit" )) {
                menu2 = in.readLine();
                System.out.println( "я получил: " + menu2 );
                switch (menu2) {
                    case "calculate": {
                        String Id = in.readLine();
                        int IDUser = Integer.parseInt( Id );
                        String type = in.readLine();
                        System.out.println( "я получил: " + type );

                        String reportValue = in.readLine();
                        System.out.println( "я получил: " + reportValue );
                        JSONObject res = new JSONObject( reportValue );
                        Report report = new Report( res.getString( "date" ), type, res.getDouble( "x1" ), res.getDouble( "x2" ), res.getDouble( "x3" ),
                                res.getDouble( "x4" ), res.getDouble( "x5" ), res.getDouble( "x6" ),
                                res.getDouble( "x7" ), res.getDouble( "x8" ), res.getDouble( "x9" ), res.getInt( "IDCompany" ) );

                        out.write( report.getResult() + '\n' );
                        out.flush();
                        System.out.println( "я отправил:" + report.getResult() );
                        handler.addReport( report ,IDUser );
                        break;
                    }
                    case "getReports": {
                        String companyId = in.readLine();
                        System.out.println( "я получил: " + companyId );
                        String reports = handler.getReportsCompany( companyId );
                        out.write( reports + '\n' );
                        out.flush();
                        break;
                    }
                    case "addCompany": {
                        String Id = in.readLine();
                        int IDUser = Integer.parseInt( Id );
                        String company = in.readLine();
                        System.out.println( "я получил" + company );
                        handler.addCompany( company , IDUser);
                        break;
                    }
                    case "txt":{
                        Report report = handler.getLastReport();
                        report.saveTXT();
                        break;
                    }
                    case "pdf":{
                        Report report = handler.getLastReport();
                        report.savePDF();
                        break;
                    }
                    case "exit": {
                        menu2 = "exit";
                        break;
                    }
                    default:break;
                }
            }
        }
        catch (IOException | JSONException | SQLException | ClassNotFoundException | COSVisitorException e) {
            e.printStackTrace();
        }
        return menu2;
    }
}

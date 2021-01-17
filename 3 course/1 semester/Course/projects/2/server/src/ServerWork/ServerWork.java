package ServerWork;

import Database.MyDatabase;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerWork {
    private BufferedReader in;
    private PrintWriter out;
    private MyDatabase database;
    
    public ServerWork (BufferedReader in, PrintWriter out, MyDatabase database){
        this.in = in;
        this.out = out;
        this.database = database;
    }
    
    public void getId (int idOperation) throws IOException, SQLException{
        switch(idOperation){
            case 1:
                signingIn();
                break;
            case 2:
                registrationUser();
                break;
            case 3:
                outputAssetTable();
                break;
            case 4:
                database.insert(in.readLine());
                break;
            case 5:
                database.delete(in.readLine());
                break;
            case 6:
                database.update(in.readLine());
                break;
            case 7:
                outputUserTable();
                break;
            case 8:
                addCapitalConsumption();
                break;
            case 9:
                outputCapitalConsumptionTable();
                break;
            case 10:
                deleteCapitalConsumption();
                break;
            case 11:
                viewCapitalConsumption();
                break;
            case 12:
                viewAsset();
                break;
        }
    }

    private void signingIn() throws IOException, SQLException{
        String login = in.readLine();
        String password = in.readLine();
        
        ResultSet result;
        
        result = database.select("select * from users where login = '"+login+"' and password = '"+password+"'");
        if(result.next()){
            out.println(result.getString("status"));
        }else{
            out.println("error");
        }
    }
    
    private void registrationUser() throws IOException, SQLException{
        String login = in.readLine();
        String password = in.readLine();
        int status = Integer.parseInt(in.readLine());
        String sqlString = "SELECT * FROM users where login = '"+login+"'";
        ResultSet rs = database.select(sqlString);
        if(rs.next()){
            out.println("error");
        }else{
            out.println("ok");
        }
        sqlString = "INSERT INTO users (login, password, status) VALUES ('"+login+"','"+password+"','"+status+"')";
        database.insert(sqlString);
    }
    
    private void outputAssetTable() throws SQLException{
        ResultSet result = database.select("SELECT * FROM fixed_assets");
        int count = 0;
        if(result.first()){
            do {
               count++; 
            } while(result.next());
        }
        out.println(Integer.toString(count));
        result.first();
        for(int i = 0; i < count; i++){
            out.println(result.getString("name"));
            out.println(Integer.toString(result.getInt("price")));
            out.println(Integer.toString(result.getInt("term_of_use")));
            result.next();
        }
    }
    
    private void outputUserTable() throws SQLException{
        ResultSet result = database.select("SELECT * FROM users");
        int count = 0;
        if(result.first()){
            do{
               count++; 
            }while(result.next());
        }
        out.println(Integer.toString(count));
        result.first();
        for(int i = 0; i < count; i++){
            out.println(result.getString("login"));
            out.println(result.getString("password"));
            out.println(Integer.toString(result.getInt("status")));
            result.next();
        }
    }
    
    private void addCapitalConsumption() throws IOException, SQLException{
        String name = in.readLine();
        int price = Integer.parseInt(in.readLine());
        int term = Integer.parseInt(in.readLine());
        String sqlString = "SELECT id FROM fixed_assets WHERE `name`='"+name+"'"
                + " AND `price`='"+price+"' AND `term_of_use`='"+term+"'";
        ResultSet result = database.select(sqlString);
        int id = 0;
        if(result.next()){
            id = result.getInt("id");
            float year_percent = ((float)100)/term;
            year_percent = ((float)Math.round(year_percent * 1000)  ) / 1000;
            float year_price = price/100*year_percent;
            year_price = ((float)Math.round(year_price * 1000)  ) / 1000;
            float month_percent = year_percent/12;
            month_percent = ((float)Math.round(month_percent * 1000)  ) / 1000;
            float month_price = price/100*month_percent;
            month_price = ((float)Math.round(month_price * 1000)  ) / 1000;
            sqlString = "INSERT INTO depreciation (name, year_proz, year_price, month_proz,"
                    + " month_price, id_fixed_assets) VALUES ('"+name+"','"+year_percent+"',"
                    + "'"+year_price+"','"+month_percent+"','"+month_price+"','"+id+"')";
            database.insert(sqlString);
        }
    }
    
    private void outputCapitalConsumptionTable() throws SQLException, IOException{
        ResultSet result = database.select(in.readLine());
        int count = 0;
        if(result.first()){
            do{
               count++; 
            }while(result.next());
        }
        out.println(Integer.toString(count));
        result.first();
        for(int i = 0; i < count; i++){
            out.println(result.getString("name"));
            out.println(Double.toString((double)result.getFloat("year_proz")));
            out.println(Double.toString((double)result.getFloat("year_price")));
            out.println(Double.toString((double)result.getFloat("month_proz")));
            out.println(Double.toString((double)result.getFloat("month_price")));
            result.next();
        }
    }
    
    private void deleteCapitalConsumption() throws IOException, SQLException{
        ResultSet result = database.select(in.readLine());
        if(result.next()){
            int id = result.getInt("id");
            String sqlString = "DELETE FROM depreciation WHERE `id_fixed_assets`='"+id+"'";
            database.delete(sqlString);
        }
        return;
    }
    
    private void viewCapitalConsumption() throws IOException, SQLException{
       ResultSet result = database.select(in.readLine());
       if(result.next()){
            int id = result.getInt("id");
            String sqlString = "SELECT * FROM depreciation WHERE `id_fixed_assets`='"+id+"'";
            result = database.select(sqlString);
            if(result.next()){
                out.println(result.getString("name"));
                out.println(Double.toString((double)result.getFloat("year_proz")));
                out.println(Double.toString((double)result.getFloat("year_price")));
                out.println(Double.toString((double)result.getFloat("month_proz")));
                out.println(Double.toString((double)result.getFloat("month_price")));
            }
        }
    }
    
    private void viewAsset() throws IOException, SQLException{
        ResultSet result = database.select(in.readLine());
        if(result.next()){
            out.println(result.getString("name"));
            out.println(Integer.toString((int)result.getInt("price")));
            out.println(Integer.toString((int)result.getInt("term_of_use")));
        }
    }
}

package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyDatabase implements DatabaseInterface{
    private String drivName = "org.gjt.mm.mysql.Driver";
    private Connection connect;
    private Statement statemnt;
    
    public MyDatabase(String drivName, String url, String name, String pass)
            throws ClassNotFoundException, SQLException {
        this.drivName = drivName;
        Class.forName(this.drivName);
        this.connect = DriverManager.getConnection
        (url,name,pass);
        this.statemnt=this.connect.createStatement();
        this.statemnt.execute("set character set utf8");
        this.statemnt.execute("set names utf8");
    }
    
    @Override
    public void insert (String sqlString) {
        try {
            statemnt.executeUpdate(sqlString);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete (String sqlString) {
        try {
            statemnt.executeUpdate(sqlString);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(String sqlString) {
        try {
            statemnt.executeUpdate(sqlString);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ResultSet select(String sqlString) {
        ResultSet rs = null;
        try {
            rs = statemnt.executeQuery(sqlString);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public void close(){
        try {
            connect.close();
            statemnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

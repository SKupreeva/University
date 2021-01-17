import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {
    private int lastId;
    private static IdGenerator instance;

    public static synchronized IdGenerator getInstance( String type){
        if(instance == null){
            instance = new IdGenerator(type);
        }
        return instance;
    }

    private IdGenerator(String type){
        String last = null;

        switch (type){
            case "user":last = "SELECT MAX("+Const.USERS_ID+") FROM "+ Const.USER_TABLE; break;
            case "report":last = "SELECT MAX("+Const.REPORTS_ID+") FROM "+ Const.REPORT_TABLE; break;
            case "company": last = "SELECT MAX("+Const.COMPANY_ID+") FROM "+ Const.COMPANY_TABLE;break;
            case "usd": last ="SELECT MAX("+Const.USD_ID+") FROM "+ Const.USD_TABLE; break;
        }

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prep = handler.getDbConnection(false).prepareStatement(last);
            ResultSet rs = prep.executeQuery();
            if(rs == null){
                lastId = 0;
            }
            while (rs.next()){
                lastId =  rs.getInt(1);}
            if(type.equals( "usd" )){
                lastId--;
            }
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return ++lastId;
    }
}

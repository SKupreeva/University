package database;

import model.Date;
import model.Rout;

import java.util.ArrayList;

public class SQLDate implements IDate{
    private static SQLDate instance;
    private ConnectionDB dbConnection;

    private SQLDate() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLDate getInstance() {
        if (instance == null) {
            instance = new SQLDate();
        }
        return instance;
    }

    @Override
    public boolean isFind(Date obj, int idDate) {
        String str = "SELECT * FROM date WHERE idDate = '" + idDate +
                "' and day = '" + obj.getDay() + "' and month = '" + obj.getMonth() +
                "' and year = '" + obj.getYear() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public int selectId(Date obj) {
        String str = "SELECT idDate FROM date WHERE day = '" + obj.getDay() +
                "' and month = '" + obj.getMonth() +
                "' and year = '" + obj.getYear() +
                "' and hours = '" + obj.getHours() +
                "' and minutes = '" + obj.getMinutes() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void insert(Date obj) {
        String str = "INSERT INTO date (day, month, year, hours, minutes) VALUES(" + obj.getDay()
                + ", " + obj.getMonth() + ", " + obj.getYear() +
                ", " + obj.getHours() + ", " + obj.getMinutes() + ")";
        dbConnection.execute(str);
    }

    @Override
    public Date selectDate(int idDate) {
        String str = "SELECT * From date WHERE idDate = " + idDate;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Date date = new Date();
        for (String[] items: result){
            date.setDay(Integer.parseInt(items[1]));
            date.setMonth(Integer.parseInt(items[2]));
            date.setYear(Integer.parseInt(items[3]));
            date.setHours(Integer.parseInt(items[4]));
            date.setMinutes(Integer.parseInt(items[5]));
        }
        return date;
    }

    @Override
    public void update(Date obj, int idDate) {
        String str = "UPDATE date SET date.day="
                + obj.getDay()
                + ", date.month="
                + obj.getMonth()
                + ", date.year="
                + obj.getYear()
                + ", date.hours="
                + obj.getHours()
                + ", date.minutes="
                + obj.getMinutes()
                + "  WHERE date.idDate=" + idDate;
        dbConnection.execute(str);
    }

    @Override
    public void delete(int idDate) {
        String str = "DELETE FROM date WHERE idDate = " + idDate;
        dbConnection.execute(str);
    }
}

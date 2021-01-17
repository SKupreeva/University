package database;

import model.Passengers;
import model.Users;

import java.util.ArrayList;

public class SQLUsers implements IUsers{
    private static SQLUsers instance;
    private ConnectionDB dbConnection;

    private SQLUsers() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLUsers getInstance() {
        if (instance == null) {
            instance = new SQLUsers();
        }
        return instance;
    }

    @Override
    public String findUser(Users obj) {
        String str = "SELECT * From users Where login = '" + obj.getLogin() +
                "' and password = '" + obj.getPassword() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        String status = "";
        for (String[] item: result)
            status = item[3];
        return status;
    }

    @Override
    public void insert(Users obj) {
        String str = "INSERT INTO users (login, password, status, name, secondName, patronymic, passportSeries, passportNumber) VALUES('" + obj.getLogin()
                + "', '" + obj.getPassword() + "', '" + obj.getStatus() +  "', '" + obj.getName() +  "', '" +
                obj.getSecondName() + "', '" + obj.getPatronymic() + "', '" + obj.getPassportSeries() + "', " + obj.getPassportNumber() + ")";
        dbConnection.execute(str);
    }

    @Override
    public Users selectUsers(Users obj) {
        String str = "SELECT * From users Where login = '" + obj.getLogin() +
                "' and password = '" + obj.getPassword() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Users user = new Users();
        for (String[] items: result){
            user.setIdUser(Integer.parseInt(items[0]));
            user.setName(items[4]);
            user.setSecondName(items[5]);
            user.setPatronymic(items[6]);
            user.setPassportSeries(items[7]);
            user.setPassportNumber(Integer.parseInt(items[8]));
        }
        return user;
    }

    @Override
    public Users selectIdUsers(Passengers obj) {
        String str = "SELECT * From users Where name = '" + obj.getName() +
                "' and secondName = '" + obj.getSecondName() +
                "' and patronymic = '" + obj.getPatronymic() +
                "' and passportSeries = '" + obj.getPassportSeries() +
                "' and passportNumber = " + obj.getPassportNumber();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Users user = new Users();
        for (String[] items: result){
            user.setIdUser(Integer.parseInt(items[0]));
        }
        return user;
    }
}

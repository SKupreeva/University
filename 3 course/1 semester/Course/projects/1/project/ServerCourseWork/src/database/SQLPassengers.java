package database;


import model.Passengers;

import java.util.ArrayList;

public class SQLPassengers implements IPassengers{
    private static SQLPassengers instance;
    private ConnectionDB dbConnection;

    private SQLPassengers() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLPassengers getInstance() {
        if (instance == null) {
            instance = new SQLPassengers();
        }
        return instance;
    }

    @Override
    public void insert(Passengers obj) {
        String str = "INSERT INTO passengers (name, secondName, patronymic, passportSeries, passportNumber) VALUES('"
                + obj.getName() + "', '" + obj.getSecondName() + "', '" + obj.getPatronymic()
                + "', '" + obj.getPassportSeries() + "', " + obj.getPassportNumber() + ")";
        dbConnection.execute(str);
    }

    @Override
    public Passengers selectPassenger(Passengers obj) {
        String str = "SELECT * FROM passengers WHERE name = '" + obj.getName() +
                "' AND secondName = '" + obj.getSecondName() +
                "' AND patronymic = '" + obj.getPatronymic() +
                "' AND passportSeries = '" + obj.getPassportSeries() +
                "' AND passportNumber = " + obj.getPassportNumber();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Passengers passenger = new Passengers();
        for (String[] items: result) {
            passenger.setIdPassenger(Integer.parseInt(items[0]));
        }
        return passenger;
    }
    @Override
    public Passengers selectPassenger(int idPassenger) {
        String str = "SELECT * FROM passengers WHERE idPassenger = " + idPassenger;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Passengers passenger = new Passengers();
        for (String[] items: result) {
            passenger.setName(items[1]);
            passenger.setSecondName(items[2]);
            passenger.setPatronymic(items[3]);
            passenger.setPassportSeries(items[4]);
            passenger.setPassportNumber(Integer.parseInt(items[5]));
        }
        return passenger;
    }

    @Override
    public int findId(Passengers obj) {
        String str = "SELECT * FROM passengers WHERE name = '" + obj.getName() +
                "' and secondName = '" + obj.getSecondName() +
                "' and patronymic = '"  + obj.getPatronymic() +
                "' and passportSeries = '"  + obj.getPassportSeries() +
                "' and passportNumber = "  + obj.getPassportNumber();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return 0;
        return Integer.parseInt(result.get(0)[0]);
    }
}

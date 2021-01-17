package database;

import model.Plane;

import java.util.ArrayList;

public class SQLPlane implements IPlane{
    private static SQLPlane instance;
    private ConnectionDB dbConnection;

    private SQLPlane() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLPlane getInstance() {
        if (instance == null) {
            instance = new SQLPlane();
        }
        return instance;
    }

    @Override
    public ArrayList<Plane> selectBoardNumber() {
        String str = "SELECT * From plane";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Plane> listPlane = new ArrayList<>();

        for (String[] items: result){
            Plane plane = new Plane();
            plane.setModel(items[1]);
            plane.setYearOfMade(Integer.parseInt(items[2]));
            plane.setCountOfSeats(Integer.parseInt(items[3]));
            plane.setBoardNumber(items[4]);
            listPlane.add(plane);
        }
        return listPlane;
    }

    @Override
    public boolean isFind(Plane obj, int idPlane) {
        String str = "SELECT * FROM plane WHERE idPlane = '" + idPlane +
                "' and boardNumber = '" + obj.getBoardNumber() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public boolean isFind(Plane obj) {
//        String str = "SELECT * FROM plane WHERE model = '" + obj.getModel() +
//                "' AND yearOfMade = " + obj.getYearOfMade() + " AND countOfSeats = " + obj.getCountOfSeats() +
//                " AND boardNumber = '" + obj.getBoardNumber() + "'";
        String str = "SELECT * FROM plane WHERE boardNumber = '" + obj.getBoardNumber() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public int selectId(Plane obj) {
        String str = "SELECT * FROM plane WHERE boardNumber = '" + obj.getBoardNumber() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Plane plane = new Plane();
        for (String[] items: result){
            plane.setIdPlane(Integer.parseInt(items[0]));
        }
        return plane.getIdPlane();
    }

    @Override
    public Plane selectPlane(int idPlane) {
        String str = "SELECT * FROM plane WHERE idPlane = " + idPlane;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Plane plane = new Plane();
        for (String[] items: result){
            plane.setCountOfSeats(Integer.parseInt(items[3]));
            plane.setBoardNumber(items[4]);
        }
        return plane;
    }

    @Override
    public void insert(Plane obj) {
        String str = "INSERT INTO plane (model, yearOfMade, countOfSeats, boardNumber) VALUES('" + obj.getModel()
                + "', " + obj.getYearOfMade() + ", " + obj.getCountOfSeats() +
                ", '" + obj.getBoardNumber() + "')";
        dbConnection.execute(str);
    }

    @Override
    public void delete(String boardNumber) {
        String str = "DELETE FROM plane WHERE boardNumber = '" + boardNumber + "'";
        dbConnection.execute(str);
    }
}

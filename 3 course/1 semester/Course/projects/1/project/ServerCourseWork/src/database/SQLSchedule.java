package database;

import model.Plane;
import model.Schedule;

import java.util.ArrayList;

public class SQLSchedule implements ISchedule{
    private static SQLSchedule instance;
    private ConnectionDB dbConnection;

    private SQLSchedule() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLSchedule getInstance() {
        if (instance == null) {
            instance = new SQLSchedule();
        }
        return instance;
    }

    @Override
    public boolean isFind(Schedule obj) {
        String str = "SELECT * FROM schedule WHERE flight = '" + obj.getFlight() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public ArrayList<Schedule> selectAllSchedule() {
        String str = "SELECT * From schedule";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Schedule> listSchedule = new ArrayList<>();

        for (String[] items: result){
            Schedule schedule = new Schedule();
            schedule.setIdSchedule(Integer.parseInt(items[0]));
            schedule.setFlight(items[1]);
            schedule.setTypeClass(items[2]);
            schedule.setIdPlane(Integer.parseInt(items[3]));
            schedule.setIdRout(Integer.parseInt(items[4]));
            schedule.setIdDate(Integer.parseInt(items[6]));
            listSchedule.add(schedule);
        }
        return listSchedule;
    }

    @Override
    public void insert(Schedule obj, int idPlane, int idRout, int idIndexOfPrice, int idDate) {
        String str = "INSERT INTO schedule (flight, typeClass, idPlane, idRout, idPriceIndex, idDate) VALUES('" +
                obj.getFlight() + "', '" + obj.getTypeClass() + "', " + idPlane +
                ", " + idRout + ", " + idIndexOfPrice + ", " + idDate +")";
        dbConnection.execute(str);
    }

    @Override
    public Schedule selectSchedule(String flight) {
        String str = "SELECT * FROM schedule WHERE flight = '" + flight + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Schedule schedule = new Schedule();
        for (String[] items: result){
            schedule.setIdSchedule(Integer.parseInt(items[0]));
            schedule.setFlight(items[1]);
            schedule.setTypeClass(items[2]);
            schedule.setIdPlane(Integer.parseInt(items[3]));
            schedule.setIdRout(Integer.parseInt(items[4]));
            schedule.setIdPriceIndex(Integer.parseInt(items[5]));
            schedule.setIdDate(Integer.parseInt(items[6]));
        }
        return schedule;
    }

    @Override
    public Schedule selectScheduleId(int idSchedule) {
        String str = "SELECT * FROM schedule WHERE idSchedule = " + idSchedule;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Schedule schedule = new Schedule();
        for (String[] items: result){
            schedule.setFlight(items[1]);
            schedule.setTypeClass(items[2]);
            schedule.setIdPlane(Integer.parseInt(items[3]));
            schedule.setIdRout(Integer.parseInt(items[4]));
            schedule.setIdPriceIndex(Integer.parseInt(items[5]));
            schedule.setIdDate(Integer.parseInt(items[6]));
        }
        return schedule;
    }

    @Override
    public void update(Schedule obj, int idPlane, int idIndexOfPrice) {
        String str = "UPDATE schedule SET schedule.typeClass='"
                + obj.getTypeClass()
                + "', schedule.idPlane="
                + idPlane
                + ", schedule.idPriceIndex="
                + idIndexOfPrice
                + "  WHERE schedule.flight='" + obj.getFlight() + "'";
        dbConnection.execute(str);
    }

    @Override
    public void delete(int idSchedule) {
        String str = "DELETE FROM schedule WHERE idSchedule = " + idSchedule;
        dbConnection.execute(str);
    }
}

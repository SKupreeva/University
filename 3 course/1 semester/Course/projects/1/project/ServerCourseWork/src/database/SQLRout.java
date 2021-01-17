package database;

import model.Rout;

import java.util.ArrayList;

public class SQLRout implements IRout{
    private static SQLRout instance;
    private ConnectionDB dbConnection;

    private SQLRout() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLRout getInstance() {
        if (instance == null) {
            instance = new SQLRout();
        }
        return instance;
    }

    @Override
    public boolean isFind(Rout obj, int idRout) {
        String str = "SELECT * FROM rout WHERE idRout = '" + idRout +
                "' and startPoint = '" + obj.getStartPoint() +
                "' and finalPoint = '" + obj.getFinalPoint() + "'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public int selectId(Rout obj) {
        String str = "SELECT idRout FROM rout WHERE startPoint = '" + obj.getStartPoint() +
                "' and finalPoint = '" + obj.getFinalPoint() +
                "' and cost = '" + obj.getCost() +
                "' and hoursOfFlight = '" + obj.getHoursOfFlight() +
                "' and minutesOfFlight = '" + obj.getMinutesOfFlight() + "'" ;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void insert(Rout obj) {
        String str = "INSERT INTO rout (startPoint, finalPoint, cost, hoursOfFlight, minutesOfFlight) VALUES('" + obj.getStartPoint()
                + "', '" + obj.getFinalPoint() + "', " + obj.getCost() +
                ", " + obj.getHoursOfFlight() + ", " + obj.getMinutesOfFlight() + ")";
        dbConnection.execute(str);
    }

    @Override
    public Rout selectRout(int idRout) {
        String str = "SELECT * From rout WHERE idRout = " + idRout;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Rout rout = new Rout();
        for (String[] items: result){
            rout.setStartPoint(items[1]);
            rout.setFinalPoint(items[2]);
            rout.setCost(Integer.parseInt(items[3]));
            rout.setHoursOfFlight(Integer.parseInt(items[4]));
            rout.setMinutesOfFlight(Integer.parseInt(items[5]));
        }
        return rout;
    }

    @Override
    public void update(Rout obj, int idRout) {
        String str = "UPDATE rout SET rout.startPoint='"
                + obj.getStartPoint()
                + "', rout.finalPoint='"
                + obj.getFinalPoint()
                + "', rout.cost="
                + obj.getCost()
                + ", rout.hoursOfFlight="
                + obj.getHoursOfFlight()
                + ", rout.minutesOfFlight="
                + obj.getMinutesOfFlight()
                + "  WHERE rout.idRout=" + idRout;
        dbConnection.execute(str);
    }

    @Override
    public void delete(int idRout) {
        String str = "DELETE FROM rout WHERE idRout = " + idRout;
        dbConnection.execute(str);
    }
}

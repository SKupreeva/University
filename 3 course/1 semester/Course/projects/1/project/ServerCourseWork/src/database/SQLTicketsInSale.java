package database;


import model.TicketsInSale;

import java.util.ArrayList;

public class SQLTicketsInSale implements ITicketsInSale{
    private static SQLTicketsInSale instance;
    private ConnectionDB dbConnection;

    private SQLTicketsInSale() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLTicketsInSale getInstance() {
        if (instance == null) {
            instance = new SQLTicketsInSale();
        }
        return instance;
    }

    @Override
    public void insert(int idSchedule, int count, int currentCount, double cost) {
        String str = "INSERT INTO ticketsinsale (idSchedule, count, currentCount, cost) VALUES(" + idSchedule
                + ", " + count + ", " + currentCount +
                ", " + cost + ")";
        dbConnection.execute(str);
    }

    @Override
    public TicketsInSale selectTicketsInSale(int idSchedule) {
        String str = "SELECT * From ticketsinsale WHERE idSchedule = " + idSchedule;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        TicketsInSale ticketsInSale = new TicketsInSale();
        for (String[] items: result){
            ticketsInSale.setIdTicketInSale(Integer.parseInt(items[0]));
            ticketsInSale.setCurrentCount(Integer.parseInt(items[3]));
            ticketsInSale.setCost(Integer.parseInt(items[4]));
        }
        return ticketsInSale;
    }

    @Override
    public TicketsInSale selectTicketsInSaleId(int idSale) {
        String str = "SELECT * From ticketsinsale WHERE idTicketsInSale = " + idSale;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        TicketsInSale ticketsInSale = new TicketsInSale();
        for (String[] items: result){
            ticketsInSale.setIdSchedule(Integer.parseInt(items[1]));
            ticketsInSale.setCurrentCount(Integer.parseInt(items[3]));
            ticketsInSale.setCost(Integer.parseInt(items[4]));
        }
        return ticketsInSale;
    }

    @Override
    public void update(int idSchedule, int count, int currentCount, double cost) {
        String str = "UPDATE ticketsinsale SET ticketsinsale.count="
                + count
                + ", ticketsinsale.currentCount="
                + currentCount
                + ", ticketsinsale.cost="
                + cost
                + "  WHERE ticketsinsale.idSchedule=" + idSchedule;
        dbConnection.execute(str);
    }

    @Override
    public void update(int idSchedule, int currentCount) {
        String str = "UPDATE ticketsinsale SET ticketsinsale.currentCount="
                + currentCount
                + "  WHERE ticketsinsale.idSchedule=" + idSchedule;
        dbConnection.execute(str);
    }

    @Override
    public void delete(int idSchedule) {
        String str = "DELETE FROM ticketsinsale WHERE idSchedule = " + idSchedule;
        dbConnection.execute(str);
    }

    @Override
    public boolean checkSale(int idSchedule) {
        String str = "SELECT * From ticketsinsale WHERE idSchedule = " + idSchedule;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        for (String[] items: result){
            if (Integer.parseInt(items[3]) == Integer.parseInt(items[2]))
                return false;
        }
        return true;
    }
}

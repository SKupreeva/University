package database;


import model.TicketsInSale;

public interface ITicketsInSale {
    public void insert(int idSchedule, int count, int currentCount, double cost);
    public TicketsInSale selectTicketsInSale(int idSchedule);
    public TicketsInSale selectTicketsInSaleId(int idSale);
    public void update(int idSchedule, int count, int currentCount, double cost);
    public void update(int idSchedule, int currentCount);
    public void delete(int idSchedule);
    public boolean checkSale(int idSchedule);
}

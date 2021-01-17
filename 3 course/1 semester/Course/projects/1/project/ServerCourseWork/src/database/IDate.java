package database;

import model.Date;

public interface IDate {
    public boolean isFind(Date obj, int idDate);
    public int selectId(Date obj);
    public void insert(Date obj);
    public Date selectDate(int idDate);
    public void update(Date obj, int idDate);
    public void delete(int idDate);
}

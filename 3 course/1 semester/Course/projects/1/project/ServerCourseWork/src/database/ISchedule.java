package database;

import model.Schedule;

import java.util.ArrayList;

public interface ISchedule {
    public boolean isFind(Schedule obj);
    public ArrayList<Schedule> selectAllSchedule();
    public Schedule selectSchedule(String flight);
    public Schedule selectScheduleId(int idSchedule);
    public void insert(Schedule obj, int idPlane, int idRout, int idIndexOfPrice, int idDate);
    public void update(Schedule obj, int idPlane, int idIndexOfPrice);
    public void delete(int idSchedule);
}

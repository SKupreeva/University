package database;

import model.Rout;

import java.util.ArrayList;

public interface IRout {
    public boolean isFind(Rout obj, int idRout);
    public int selectId(Rout obj);
    public void insert(Rout obj);
    public Rout selectRout(int idRout);
    public void update(Rout obj, int idRout);
    public void delete(int idRout);
}

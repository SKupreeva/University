package database;

import model.Plane;

import java.util.ArrayList;

public interface IPlane {
    public ArrayList<Plane> selectBoardNumber();
    public boolean isFind(Plane obj, int idPlane);
    public boolean isFind(Plane obj);
    public int selectId(Plane obj);
    public Plane selectPlane(int idPlane);
    public void insert(Plane obj);
    public void delete(String idPlane);
}

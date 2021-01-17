package database;

import model.Passengers;

public interface IPassengers {
    public void insert(Passengers obj);
    public Passengers selectPassenger(Passengers obj);
    public Passengers selectPassenger(int idPassenger);
    public int findId(Passengers obj);
}

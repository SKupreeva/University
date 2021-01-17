package database;


import model.Date;
import model.IndexOfPrice;

public interface IIndexOfPrice {
    public IndexOfPrice selectIndexOfPrice(Date obj);
}

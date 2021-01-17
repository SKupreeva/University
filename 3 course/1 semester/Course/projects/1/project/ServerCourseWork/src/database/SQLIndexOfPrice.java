package database;


import model.Date;
import model.IndexOfPrice;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

public class SQLIndexOfPrice implements IIndexOfPrice{
    private static SQLIndexOfPrice instance;
    private ConnectionDB dbConnection;

    private SQLIndexOfPrice() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLIndexOfPrice getInstance() {
        if (instance == null) {
            instance = new SQLIndexOfPrice();
        }
        return instance;
    }

    @Override
    public IndexOfPrice selectIndexOfPrice(Date obj) {
       String str = "SELECT * FROM indexofprice WHERE timeOfFlight = " + obj.getMonth();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        IndexOfPrice indexOfPrice = new IndexOfPrice();
        for (String[] items: result) {
            indexOfPrice.setIdIndexOfPrice(Integer.parseInt(items[0]));
            indexOfPrice.setIndexOfPrice(Double.parseDouble(items[2]));
        }
        return indexOfPrice;
    }
}

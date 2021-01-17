package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public final class CollectionReports{

    private ObservableList<Report> reports = FXCollections.observableArrayList();

    public CollectionReports(){}

    public ObservableList<Report> getReports() {
        return reports;
    }

    public void setReports(){
        try {
            String array = Client.getInstance().get();
            if(!array.equals( "none" )){
            JSONArray newArray = new JSONArray( array );
            int count = newArray.length();
            for(int i = 0; i<count; i++) {
                JSONObject object = newArray.getJSONObject( i );
                int id = object.getInt( "id");
                String date = object.getString( "date" );
                double x1 = object.getDouble( "x1" );
                double x2 = object.getDouble( "x2" );
                double x3 = object.getDouble( "x3" );
                double x4 = object.getDouble( "x4" );
                double x5 = object.getDouble( "x5" );
                double x6 = object.getDouble( "x6" );
                double x7 = object.getDouble( "x7" );
                double x8 = object.getDouble( "x8" );
                double x9 = object.getDouble( "x9" );
                double result = object.getDouble( "result" );
                Report report = new Report(date, id, x1, x2, x3, x4, x5, x6, x7, x8, x9, result);
                reports.add( report );
            }}
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public Iterator<Report> iterator(){
        return new MenuIterator();
    }

    class MenuIterator implements Iterator<Report>{
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex >= reports.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Report next() {
            return reports.get(currentIndex++);
        }

        @Override
        public void remove() {
            reports.remove(--currentIndex);
        }
    }

    public Report getReport(int i){
        Report report = reports.get(i);
        return report;
    }
}

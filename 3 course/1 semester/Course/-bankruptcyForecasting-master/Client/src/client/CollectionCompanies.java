package client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
public final class CollectionCompanies {

    private ObservableList<Company> companies = FXCollections.observableArrayList();

    private static CollectionCompanies instance;

    public static synchronized CollectionCompanies getInstance(){
        if(instance == null){
            instance = new CollectionCompanies();
        }
        return instance;
    }

    public ObservableList getCompanies(){
        return companies;
    }

    public void fillData(){
        try {
           // Client.getInstance().send( "getCompanies" );
            String array = Client.getInstance().get();
            JSONArray newArray = new JSONArray( array );
            int count = newArray.length();
            for(int i = 0; i<count; i++) {
                JSONObject object = newArray.getJSONObject( i );
                int id = object.getInt( "id");
                String name = object.getString( "name" );
                String address = object.getString( "address" );
                Company company = new Company(id, name, address);
                companies.add( company );
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public void addCompany(Company company){
        companies.add( company );
    }
}

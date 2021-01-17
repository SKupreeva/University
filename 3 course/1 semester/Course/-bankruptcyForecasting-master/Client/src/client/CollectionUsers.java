package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public final class CollectionUsers{

    private ObservableList<UserS> users = FXCollections.observableArrayList();

    private static CollectionUsers instance;

    public static synchronized CollectionUsers getInstance(){
        if(instance == null){
            instance = new CollectionUsers();
        }
        return instance;
    }

    public ObservableList<UserS> getUsers() {
        return users;
    }

    public void delete(UserS user){
        users.remove(user);
    }

    public void fillData(){
        try {
            String array = Client.getInstance().get();
            JSONArray newArray = new JSONArray( array );
            int count = newArray.length();
            for(int i = 0; i<count; i++) {
                JSONObject object = newArray.getJSONObject( i );
                int ids = object.getInt( "id");
                String id = String.valueOf( ids );
                String name = object.getString( "name" );
                String surname = object.getString( "surname" );
                String login = object.getString( "login" );
                String email = object.getString( "email" );
                String phone = object.getString( "phone" );
                UserS user = new UserS(id, name, surname, phone, email, login );
                users.add( user );
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

}

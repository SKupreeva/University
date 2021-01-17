package client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Company {
    private int ID;
    private String name;
    private String address;

    public Company(int id, String name, String address){

            ID = id;
            this.address = address;
            this.name = name;
    }

    public Company(String name, String address){
        ID = 0;
        this.name = name;
        this.address = address;
        JSONObject companyJson = new JSONObject();
        try {
            companyJson.put("name", this.name);
            companyJson.put("address", this.address);
            Client.getInstance().send("company" );
            Client.getInstance().send( companyJson.toString() );
        } catch (JSONException e) {
        e.printStackTrace();
    }
    }

    public Company(){}
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

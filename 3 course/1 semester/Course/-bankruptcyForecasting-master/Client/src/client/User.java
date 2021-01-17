package client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public final class User {
    private int ID;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;

    private static User instance;

    public static synchronized User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    private User() {
        try{
        String str = client.Client.getInstance().get();
            JSONObject json = new JSONObject(str);
            ID = json.getInt("id");
            surname = json.getString("surname");
            name = json.getString("name");
            phone = json.getString("phone");
            email =json.getString("email");
            login = json.getString("login");
            password = json.getString("password");

    } catch (IOException| JSONException e) {
        System.err.println(e);
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

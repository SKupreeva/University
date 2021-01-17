package client;

public class UserS {
    private String ID;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;

    public UserS(String ID, String surname, String name, String phone, String email, String login) {
        this.ID = ID;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.login = login;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

public class User {
    private int ID;
    private String name;
    private String login;
    private String password;
    private String surname;
    private String email;
    private String phone;

    public User(int ID, String name, String login, String password, String surname, String email, String phone) {
        this.ID = ID;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.surname =surname;
    }

    public User(){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package AllData;

public class User {
    private String login;
    private String password;
    private int status;
    
    public User (String login, String password, int status){
        this.setStatus(status);
        this.setLogin(login);
        this.setPassword(password);
    }
    
    public void setStatus (int status) {
        this.status = status;
    }
    
    public void setLogin (String login) {
        this.login = login;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
    
    public int getStatus () {
        return this.status;
    }
    
    public String getLogin () {
        return this.login;
    }
    
    public String getPassword () {
        return this.password;
    }
}

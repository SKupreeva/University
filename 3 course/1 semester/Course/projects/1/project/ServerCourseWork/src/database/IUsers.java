package database;

import model.Passengers;
import model.Users;

public interface IUsers {
    public String findUser(Users obj);
    public void insert(Users obj);
    public Users selectUsers(Users obj);
    public Users selectIdUsers(Passengers obj);
}

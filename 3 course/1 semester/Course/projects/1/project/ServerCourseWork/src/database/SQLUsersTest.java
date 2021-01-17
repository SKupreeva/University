package database;

import model.Users;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SQLUsersTest {
    private static SQLUsers sqlUsers;
    private static Users objUsers;

    @BeforeClass
    public static void init(){
        sqlUsers = SQLUsers.getInstance();
        objUsers = new Users();
        objUsers.setLogin("anna");
        objUsers.setPassword("123");
    }

    @Test
    public void testFindUser() {
        assertTrue(sqlUsers.findUser(objUsers).equals("user"));
    }

    @Test
    public void testSelectUsers() throws Exception {
        assertTrue(sqlUsers.selectUsers(objUsers).getIdUser() == 4);
    }
}
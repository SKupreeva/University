package database;

import model.Plane;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SQLPlaneTest {
    private static SQLPlane sqlPlane;
    private static Plane objPlane;

    @BeforeClass
    public static void init(){
        sqlPlane = SQLPlane.getInstance();
        objPlane = new Plane();
    }

    @Test
    public void testIsFind() {
        objPlane.setBoardNumber("111111111111");
        assertFalse(sqlPlane.isFind(objPlane));
    }

    @Test
    public void testSelectPlane(){
        assertTrue(sqlPlane.selectPlane(7).getBoardNumber().equals("123-232-215"));
    }
}
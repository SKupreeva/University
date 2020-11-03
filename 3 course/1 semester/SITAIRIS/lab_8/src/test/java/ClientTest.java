import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {
    private Client client0;
    private Client client1;
    private Client client2;
    private Client client3;

    @Before
    public void setClients() {
        client0 = new Client("Jason", 12);
        client1 = new Client("Ann", 19);
        client2 = new Client("Mary", 32);
        client3 = new Client("Arny", 21);
    }

    @Test
    public void getAllClients() {
        List<Client> expected = Client.getAllClients();

        List<Client> actual = new ArrayList<>();
        actual.add(client0);
        actual.add(client1);
        actual.add(client2);
        actual.add(client3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllClients_NO_NULL() {
        List<Client> expected = Client.getAllClients();
        Assert.assertNotNull(expected);
    }

    @Test
    public void getHowManyClients() {
        int expected = Client.getHowManyClients();

        int actual = 4;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeClients() {
        int expected = Client.getAllAgeClients();

        int actual = 12 + 19 + 32 + 21;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAverageAgeOfAllClients() {
        int expected = Client.getAverageAgeOfAllClients();

        int actual = (12 + 19 + 32 + 21)/4;

        Assert.assertEquals(expected, actual);
    }
}
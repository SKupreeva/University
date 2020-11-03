import java.util.*;

public class Client {
    private int id;
    private String name;
    private int age;

    private static Map<Integer, Client> Clients = new HashMap<>();
    private static int countId = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Client(String name, int age) {
        this.name = name;
        this.age = age;

        if (!hasClient()){
            countId++;
            this.id = countId;
            Clients.put(id, this);
        }
    }

    private boolean hasClient(){
        for (Client client : Clients.values()){
            if (client.equals(this) && client.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    public static List<Client> getAllClients(){
        return new ArrayList<>(Clients.values());
    }

    public static int getHowManyClients(){
        return Clients.size();
    }

    public static int getAllAgeClients(){
        int countAge = 0;
        for (Client client : Clients.values()){
            countAge += client.age;
        }
        return countAge;
    }

    public static int getAverageAgeOfAllClients(){
        return getAllAgeClients() / getHowManyClients();
    }


}

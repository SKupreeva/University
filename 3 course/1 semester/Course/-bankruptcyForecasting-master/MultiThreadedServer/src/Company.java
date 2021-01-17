public class Company {
    int ID;
    String name;
    String address;

    public Company(String name, String address) {
        this.ID = IdGenerator.getInstance( "company" ).getNextId();
        this.name = name;
        this.address = address;
    }

    public Company(){
        name = "none";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

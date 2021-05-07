package DataAccess.Model.Data;

public class Ticket implements Comparable{
    private int number;
    private String depart;
    private String arrive;
    private int price;
    private String classType;


    public Ticket(int number, String depart, String arrive, int price, String classType) {
        this.number = number;
        this.depart = depart;
        this.arrive = arrive;
        this.price = price;
        this.classType = classType;
    }

    public int  getNumber(){return number;}

    public String getDepart() {
        return depart;
    }

    public String getArrive() {
        return arrive;
    }

    public int getPrice() {
        return price;
    }

    public String getClassType() {
        return classType;
    }

    @Override
    public int compareTo(Object o) {
        int compareage=((Ticket)o).getPrice();
        return this.price - compareage;
    }
}

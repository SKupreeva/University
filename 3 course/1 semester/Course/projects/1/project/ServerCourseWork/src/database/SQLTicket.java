package database;

import model.Ticket;

import java.util.ArrayList;

public class SQLTicket implements ITicket{
    private static SQLTicket instance;
    private ConnectionDB dbConnection;

    private SQLTicket() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLTicket getInstance() {
        if (instance == null) {
            instance = new SQLTicket();
        }
        return instance;
    }

    @Override
    public void insert(Ticket obj, boolean isUser) {
        String str = "";
        if (isUser) {
            str = "INSERT INTO ticket (numberOfTicket, idSale, idUser, idPassenger) VALUES(" + obj.getNumberOfTicket()
                    + ", " + obj.getIdSale() + ", " + obj.getIdUser() + ", " + obj.getIdPassenger() + ")";
        }
        else{
            str = "INSERT INTO ticket (numberOfTicket, idSale, idPassenger) VALUES(" + obj.getNumberOfTicket()
                    + ", " + obj.getIdSale() + ", " + obj.getIdPassenger() + ")";
        }
        dbConnection.execute(str);
    }

    @Override
    public boolean isEmpty() {
        String str = "SELECT * From ticket";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        if (result.size() == 0)
            return true;
        return false;
    }

    @Override
    public Ticket last() {
        String str = "SELECT * From ticket";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Ticket ticket = new Ticket();
        ticket.setNumberOfTicket(Integer.parseInt(result.get(result.size() - 1)[1]));
        return ticket;
    }

    @Override
    public ArrayList<Ticket> selectAll() {
        String str = "SELECT * From ticket";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Ticket> listTictet = new ArrayList<>();
        for (String[] items: result){
            Ticket ticket = new Ticket();
            ticket.setIdSale(Integer.parseInt(items[3]));
            ticket.setIdPassenger(Integer.parseInt(items[6]));
            listTictet.add(ticket);
        }
        return listTictet;
    }

    @Override
         public ArrayList<Ticket> selectTicket(int idUser) {
        String str = "SELECT * From ticket where idUser = " + idUser;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Ticket> listTicket = new ArrayList<>();

        for (String[] items: result){
            Ticket ticket = new Ticket();
            ticket.setNumberOfTicket(Integer.parseInt(items[1]));
            ticket.setIdSale(Integer.parseInt(items[3]));
            ticket.setIdPassenger(Integer.parseInt(items[6]));
            listTicket.add(ticket);
        }
        return listTicket;
    }

    @Override
    public Ticket find(int numberOfTicket) {
        String str = "SELECT * From ticket where numberOfTicket = " + numberOfTicket;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Ticket ticket = new Ticket();
        for (String[] items: result){
            ticket.setNumberOfTicket(Integer.parseInt(items[1]));
            ticket.setIdSale(Integer.parseInt(items[3]));
            ticket.setIdPassenger(Integer.parseInt(items[6]));
        }
        return ticket;
    }

    @Override
    public ArrayList<Ticket> selectTicketIdSale(int idSale) {
        String str = "SELECT * From ticket where idSale = " + idSale;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Ticket> listTicket = new ArrayList<>();

        for (String[] items: result){
            Ticket ticket = new Ticket();
            ticket.setNumberOfTicket(Integer.parseInt(items[1]));
            ticket.setIdPassenger(Integer.parseInt(items[6]));
            listTicket.add(ticket);
        }
        return listTicket;
    }

    @Override
    public void delete(int numberOfTicket) {
        String str = "DELETE FROM ticket WHERE numberOfTicket = " + numberOfTicket;
        dbConnection.execute(str);
    }
}

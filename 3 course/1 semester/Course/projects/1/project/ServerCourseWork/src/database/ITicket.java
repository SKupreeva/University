package database;


import model.Ticket;

import java.util.ArrayList;

public interface ITicket {
    public boolean isEmpty();
    public void insert(Ticket obj, boolean isUser);
    public Ticket last();
    public ArrayList<Ticket> selectTicket(int idUser);
    public ArrayList<Ticket> selectAll();
    public Ticket find(int numberOfTicket);
    public void delete(int numberOfTicket);
    public ArrayList<Ticket> selectTicketIdSale(int idSale);
}

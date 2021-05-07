package BusinessLogic.Model;

import DataAccess.Model.Data.Ticket;

import java.nio.file.Path;
import java.util.ArrayList;

public interface BusinessLogicControllerInterface {
    ArrayList<Integer> deleteTicket(String number);
    ArrayList<Ticket> setTickets(Path path);
    ArrayList<Ticket> showTicketByClass(String position, Path path);
}

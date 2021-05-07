package BusinessLogic;

import BusinessLogic.Model.BusinessLogicControllerInterface;
import DataAccess.Model.Data.Ticket;
import DataAccess.Model.RepositoryInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class BusinessLogicController implements BusinessLogicControllerInterface {

    private RepositoryInterface repository;

    public BusinessLogicController(RepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public ArrayList<Integer> deleteTicket(String number) {
        ArrayList<Integer> deletedTickets = repository.getDeleted();
        deletedTickets.add(Integer.parseInt(number));
        repository.setDeleted(deletedTickets);
        return deletedTickets;
    }

    @Override
    public ArrayList<Ticket> setTickets(Path path) {
        return repository.getTickets(path);
    }

    @Override
    public ArrayList<Ticket> showTicketByClass(String classType, Path path) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : repository.getTickets(path)) {
            if (ticket.getClassType().equals(classType)) tickets.add(ticket);
        }
        Collections.sort(tickets);
        if(tickets.size() > 2){
            for(int i = 2; i < tickets.size(); i++){
                tickets.remove(i);
            }
        }
        return tickets;
    }
}

package DataAccess.Model;

import DataAccess.Model.Data.Ticket;

import java.nio.file.Path;
import java.util.ArrayList;

public interface RepositoryInterface {
    ArrayList<Ticket> getTickets(Path path);
    ArrayList<Integer> setDeleted(ArrayList<Integer> deleted);
    ArrayList<Integer> getDeleted();
}

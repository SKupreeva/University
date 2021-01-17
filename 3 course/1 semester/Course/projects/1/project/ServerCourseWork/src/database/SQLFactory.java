package database;

public class SQLFactory extends AbstractFactory{
    @Override
    public SQLUsers getUsers() {
        return SQLUsers.getInstance();
    }

    @Override
    public SQLPlane getPlane() {
        return SQLPlane.getInstance();
    }

    @Override
    public SQLSchedule getSchedule() {
        return SQLSchedule.getInstance();
    }

    @Override
    public SQLRout getRout() {
        return SQLRout.getInstance();
    }

    @Override
    public SQLDate getDate() {
        return SQLDate.getInstance();
    }

    @Override
    public SQLIndexOfPrice getIndexOfPrice() {
        return SQLIndexOfPrice.getInstance();
    }

    @Override
    public SQLTicketsInSale getTicketsInSale() {
        return SQLTicketsInSale.getInstance();
    }

    @Override
    public SQLPassengers getPassengers() {
        return SQLPassengers.getInstance();
    }
    @Override
    public SQLTicket getTicket() {
        return SQLTicket.getInstance();
    }
}

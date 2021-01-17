package model;


import java.io.Serializable;
import java.util.Objects;

public class TicketsInSale implements Serializable{
    private int idTicketInSale;
    private int idSchedule;
    private int count;
    private int currentCount;
    private int cost;

    public int getIdTicketInSale() {
        return idTicketInSale;
    }

    public void setIdTicketInSale(int idTicketInSale) {
        this.idTicketInSale = idTicketInSale;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsInSale that = (TicketsInSale) o;

        return Objects.equals(this.idTicketInSale, that.idTicketInSale) &&
                Objects.equals(this.idSchedule, that.idSchedule) &&
                Objects.equals(this.count, that.count) &&
                Objects.equals(this.currentCount, that.currentCount) &&
                Objects.equals(this.cost, that.cost);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idTicketInSale, this.idSchedule, this.count, this.currentCount, this.cost);
    }

    @Override
    public String toString() {
        return "TicketsInSale{" +
                "idTicketInSale=" + idTicketInSale +
                ", idSchedule=" + idSchedule +
                ", count=" + count +
                ", currentCount=" + currentCount +
                ", cost=" + cost +
                '}';
    }
}

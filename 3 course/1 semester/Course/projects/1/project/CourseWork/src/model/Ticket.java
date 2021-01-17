package model;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {
    private int idTicket;
    private int numberOfTicket;
    private String numberOfSeat;
    private int idUser;
    private int idSale;
    private int idPassenger;

    public Ticket() {
        this.idTicket = 0;
        this.idUser = 0;
        this.idSale = 0;
        this.idPassenger = 0;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public String getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(String numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket that = (Ticket) o;

        return Objects.equals(this.idTicket, that.idTicket) &&
                Objects.equals(this.numberOfSeat, that.numberOfSeat) &&
                Objects.equals(this.numberOfTicket, that.numberOfTicket) &&
                Objects.equals(this.idUser, that.idUser) &&
                Objects.equals(this.idPassenger, that.idPassenger) &&
                Objects.equals(this.idSale, that.idSale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idTicket, this.numberOfSeat, this.numberOfTicket, this.idUser, this.idPassenger, this.idSale);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", numberOfTicket=" + numberOfTicket +
                ", numberOfSeat='" + numberOfSeat + '\'' +
                ", idUser=" + idUser +
                ", idSale=" + idSale +
                ", idPassenger=" + idPassenger +
                '}';
    }
}

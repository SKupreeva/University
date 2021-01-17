package model;

import java.io.Serializable;
import java.util.Objects;

public class Plane implements Serializable {
    private int idPlane;
    private String model;
    private int yearOfMade;
    private int countOfSeats;
    private String boardNumber;

    public Plane() {
        this.idPlane = 0;
        this.yearOfMade = 0;
        this.countOfSeats = 0;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfMade() {
        return yearOfMade;
    }

    public void setYearOfMade(int yearOfMade) {
        this.yearOfMade = yearOfMade;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public void setCountOfSeats(int countOfSeats) {
        this.countOfSeats = countOfSeats;
    }

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(String boardNumber) {
        this.boardNumber = boardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane that = (Plane) o;

        return  Objects.equals(this.idPlane, that.idPlane) &&
                Objects.equals(this.model, that.model) &&
                Objects.equals(this.yearOfMade, that.yearOfMade) &&
                Objects.equals(this.countOfSeats, that.countOfSeats) &&
                Objects.equals(this.boardNumber, that.boardNumber);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idPlane, this.model, this.yearOfMade, this.countOfSeats, this.boardNumber);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "idPlane=" + idPlane +
                ", model='" + model + '\'' +
                ", yearOfMade=" + yearOfMade +
                ", countOfSeats=" + countOfSeats +
                ", boardNumber='" + boardNumber + '\'' +
                '}';
    }
}

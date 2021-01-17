package model;

import java.io.Serializable;
import java.util.Objects;

public class Passengers implements Serializable {
    private int idPassenger;
    private String name;
    private String secondName;
    private String patronymic;
    private String passportSeries;
    private int passportNumber;

    public Passengers() {
        this.idPassenger = 0;
        this.passportNumber = 0;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passengers that = (Passengers) o;

        return Objects.equals(this.idPassenger, that.idPassenger) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.secondName, that.secondName) &&
                Objects.equals(this.patronymic, that.patronymic) &&
                Objects.equals(this.passportSeries, that.passportSeries) &&
                Objects.equals(this.passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idPassenger, this.name, this.secondName, this.patronymic, this.passportSeries,  this.passportNumber);
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "idPassenger=" + idPassenger +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }
}

package model;

import java.io.Serializable;
import java.util.Objects;

public class Schedule implements Serializable {
    private int idSchedule;
    private String flight;
    private String typeClass;
    private int idPlane;
    private int idRout;
    private int idPriceIndex;
    private int idDate;

    public Schedule(){
        this.idSchedule = 0;
        this.idPlane = 0;
        this.idRout = 0;
        this.idPriceIndex = 0;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public int getIdRout() {
        return idRout;
    }

    public void setIdRout(int idRout) {
        this.idRout = idRout;
    }

    public int getIdPriceIndex() {
        return idPriceIndex;
    }

    public void setIdPriceIndex(int idPriceIndex) {
        this.idPriceIndex = idPriceIndex;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule that = (Schedule) o;

        return Objects.equals(this.idSchedule, that.idSchedule) &&
                Objects.equals(this.flight, that.flight) &&
                Objects.equals(this.typeClass, that.typeClass) &&
                Objects.equals(this.idPlane, that.idPlane) &&
                Objects.equals(this.idRout, that.idRout) &&
                Objects.equals(this.idPriceIndex, that.idPriceIndex) &&
                Objects.equals(this.idDate, that.idDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idSchedule, this.flight, this.typeClass, this.idPlane, this.idRout, this.idPriceIndex, this.idDate);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", flight='" + flight + '\'' +
                ", typeClass='" + typeClass + '\'' +
                ", idPlane=" + idPlane +
                ", idRout=" + idRout +
                ", idPriceIndex=" + idPriceIndex +
                ", idDate=" + idDate +
                '}';
    }
}

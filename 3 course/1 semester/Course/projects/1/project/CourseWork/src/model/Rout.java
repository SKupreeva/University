package model;


import java.io.Serializable;
import java.util.Objects;

public class Rout implements Serializable {
    private int idRout;
    private String startPoint;
    private String finalPoint;
    private int cost;
    private int hoursOfFlight;
    private int minutesOfFlight;

    public Rout() {
        this.idRout = 0;
        this.cost = 0;
        this.hoursOfFlight = 0;
        this.minutesOfFlight = 0;
    }

    public int getIdRout() {
        return idRout;
    }

    public void setIdRout(int idRout) {
        this.idRout = idRout;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(String finalPoint) {
        this.finalPoint = finalPoint;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHoursOfFlight() {
        return hoursOfFlight;
    }

    public void setHoursOfFlight(int hoursOfFlight) {
        this.hoursOfFlight = hoursOfFlight;
    }

    public int getMinutesOfFlight() {
        return minutesOfFlight;
    }

    public void setMinutesOfFlight(int minutesOfFlight) {
        this.minutesOfFlight = minutesOfFlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rout that = (Rout) o;

        return Objects.equals(this.idRout, that.idRout) &&
                Objects.equals(this.startPoint, that.startPoint) &&
                Objects.equals(this.finalPoint, that.finalPoint) &&
                Objects.equals(this.cost, that.cost) &&
                Objects.equals(this.hoursOfFlight, that.hoursOfFlight) &&
                Objects.equals(this.minutesOfFlight, that.minutesOfFlight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idRout, this.startPoint, this.finalPoint, this.cost, this.hoursOfFlight, this.minutesOfFlight);
    }

    @Override
    public String toString() {
        return "Rout{" +
                "idRout=" + idRout +
                ", startPoint='" + startPoint + '\'' +
                ", finalPoint='" + finalPoint + '\'' +
                ", cost=" + cost +
                ", hoursOfFlight=" + hoursOfFlight +
                ", minutesOfFlight=" + minutesOfFlight +
                '}';
    }
}

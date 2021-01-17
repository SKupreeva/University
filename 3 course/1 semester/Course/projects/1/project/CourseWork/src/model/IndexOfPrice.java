package model;

import java.io.Serializable;
import java.util.Objects;

public class IndexOfPrice implements Serializable {
    private int idIndexOfPrice;
    private int timeOfFlight;
    private double indexOfPrice;

    public IndexOfPrice() {
        this.idIndexOfPrice = 0;
        this.timeOfFlight = 0;
        this.indexOfPrice = 0.0;
    }

    public int getIdIndexOfPrice() {
        return idIndexOfPrice;
    }

    public void setIdIndexOfPrice(int idIndexOfPrice) {
        this.idIndexOfPrice = idIndexOfPrice;
    }

    public int getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(int timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }

    public double getIndexOfPrice() {
        return indexOfPrice;
    }

    public void setIndexOfPrice(double indexOfPrice) {
        this.indexOfPrice = indexOfPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexOfPrice that = (IndexOfPrice) o;

        return Objects.equals(this.idIndexOfPrice, that.idIndexOfPrice) &&
                Objects.equals(this.timeOfFlight, that.timeOfFlight) &&
                Objects.equals(this.indexOfPrice, that.indexOfPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idIndexOfPrice, this.timeOfFlight, this.indexOfPrice);
    }

    @Override
    public String toString() {
        return "IndexOfPrice{" +
                "idIndexOfPrice=" + idIndexOfPrice +
                ", timeOfFlight='" + timeOfFlight + '\'' +
                ", indexOfPrice=" + indexOfPrice +
                '}';
    }
}

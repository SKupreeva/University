package model;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {
    private int idDate;
    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;

    public Date() {
        this.idDate = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
        this.hours = 0;
        this.minutes = 0;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date that = (Date) o;

        return Objects.equals(this.idDate, that.idDate) &&
                Objects.equals(this.day, that.day) &&
                Objects.equals(this.month, that.month) &&
                Objects.equals(this.year, that.year) &&
                Objects.equals(this.hours, that.hours) &&
                Objects.equals(this.minutes, that.minutes);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idDate, this.day, this.month, this.year, this.hours, this.minutes);
    }

    @Override
    public String toString() {
        return "Date{" +
                "idDate=" + idDate +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}

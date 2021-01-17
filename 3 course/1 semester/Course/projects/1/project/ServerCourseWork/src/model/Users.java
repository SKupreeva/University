package model;

import java.io.Serializable;
import java.util.Objects;

public class Users implements Serializable {
    private int idUser;
    private String login;
    private String password;
    private String status;
    private String name;
    private String secondName;
    private String patronymic;
    private String passportSeries;
    private int passportNumber;

    public Users() {
        this.idUser = 0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        Users that = (Users) o;

        return Objects.equals(this.idUser, that.idUser) &&
                Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.secondName, that.secondName) &&
                Objects.equals(this.patronymic, that.patronymic) &&
                Objects.equals(this.passportSeries, that.passportSeries) &&
                Objects.equals(this.passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idUser, this.login, this.password, this.status, this.name, this.secondName, this.patronymic, this.passportSeries,  this.passportNumber);
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }
}

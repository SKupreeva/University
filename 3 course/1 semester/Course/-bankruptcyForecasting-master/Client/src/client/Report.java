package client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private String date;
    private int IdUSD;
    private int IDCompany;
    private int ID;
    private double x1, x2, x3, x4, x5, x6, x7, x8, x9;
    private double result;

    public Report() {
    }

    public Report(String date, int ID, double x1, double x2, double x3, double x4, double x5, double x6, double x7, double x8, double x9, double result) {
        this.date = date;
        this.ID = ID;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.x7 = x7;
        this.x8 = x8;
        this.x9 = x9;
        this.result = result;
    }


    public String getDate() {
        return date;
    }

    public void setDate() {
        Date dateNow = new Date(  );
        SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yyyy" );
        date = format.format( dateNow );
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getX5() {
        return x5;
    }

    public void setX5(double x5) {
        this.x5 = x5;
    }

    public double getX6() {
        return x6;
    }

    public void setX6(double x6) {
        this.x6 = x6;
    }

    public double getX7() {
        return x7;
    }

    public void setX7(double x7) {
        this.x7 = x7;
    }

    public double getX8() {
        return x8;
    }

    public void setX8(double x8) {
        this.x8 = x8;
    }

    public double getX9() {
        return x9;
    }

    public void setX9(double x9) {
        this.x9 = x9;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getIDCompany() {
        return IDCompany;
    }

    public void setIDCompany(int IDCompany) {
        this.IDCompany = IDCompany;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdUSD() {
        return IdUSD;
    }

    public void setIdUSD(int idUSD) {
        IdUSD = idUSD;
    }
}

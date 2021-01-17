import org.apache.pdfbox.exceptions.COSVisitorException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
    private String date;
    private int ID;
    private double x1, x2, x3, x4, x5, x6, x7, x8, x9;
    private double result;
    private int IdUSD;
    private int IDCompany;

    public Report(String date,String type, double x1, double x2, double x3, double x4, double x5,
                  double x6, double x7, double x8, double x9, int IDCompany ) {
        this.date = date;
        this.ID = IdGenerator.getInstance( "report" ).getNextId();
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.x7 = x7;
        this.x8 = x8;
        this.x9 = x9;
        this.result = calculateResult(type, x1, x2, x3, x4, x5, x6, x7, x8, x9);
        this.IdUSD = IdGenerator.getInstance( "usd" ).getNextId();
        this.IDCompany = IDCompany;
    }

    public Report(){};

    public double calculateResult(String type, double x1, double x2, double x3, double x4, double x5, double x6, double x7, double x8, double x9) {
        DatabaseHandler handler = new DatabaseHandler();
        double coef = 1;
        if(type.equals( "byn" )) {
            coef = 2.8;
        } else if(type.equals( "eur" )) {
            coef = 2.1;
        } else if(type.equals( "rub" )) {
            coef = 2.4;
        } else coef = 1;
        double result = 5.529*( x1*coef ) + 0.212*( coef*x2 ) + 0.073*(coef* x3 )+
                1.27*(coef * x4 ) + 0.12*(coef* x5 ) +2.235*(coef* x6  )+ 0.575*(coef*x7 )+
                1.083*( coef* x8 ) + 0.984*(coef*x9 ) - 3.075;
        return result;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getResult() {
        return Double.toString( result );
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getIdUSD() {
        return (IdUSD-2);
    }

    public void setIdUSD(int idUSD) {
        IdUSD = idUSD;
    }

    public int getIDCompany() {
        return IDCompany;
    }

    public void setIDCompany(int IDCompany) {
        this.IDCompany = IDCompany;
    }

    public void saveTXT() {
        File file = new File("Отчет "+ date +" " +ID+".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
                FileWriter add = new FileWriter(file, true);
                add.write("Сгенерированный отчет №"+ID+'\n');
                add.write( "X1 = "+ x1+'\n');
                add.write( "X1 = "+ x2+'\n');
                add.write( "X1 = "+ x3+'\n');
                add.write( "X1 = "+ x4+'\n');
                add.write( "X1 = "+ x5+'\n');
                add.write( "X1 = "+ x6+'\n');
                add.write( "X1 = "+ x7+'\n');
                add.write( "X1 = "+ x8+'\n');
                add.write( "X1 = "+ x9+'\n');
                add.write( "Результат = "+ result + '\n' );
                add.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Сохранил отчет " );
    }

    public void savePDF() throws IOException, COSVisitorException {
    }
}

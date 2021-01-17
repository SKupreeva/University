import java.text.SimpleDateFormat;
import java.util.Date;

public final class USD {
    private int id;
    private String date;
    private double USDBYN;
    private double USDEUR;
    private double USDRUB;


    public USD(double byn, double eur, double rub){
        USDBYN = byn;
        USDEUR = eur;
        USDRUB = rub;
        id = IdGenerator.getInstance( "usd" ).getNextId()+1;
        Date dateNow = new Date(  );
        SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yyyy" );
        date = format.format( dateNow );

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUSDBYN() {
        return USDBYN;
    }

    public void setUSDBYN(double USDBYN) {
        this.USDBYN = USDBYN;
    }

    public double getUSDEUR() {
        return USDEUR;
    }

    public void setUSDEUR(double USDEUR) {
        this.USDEUR = USDEUR;
    }

    public double getUSDRUB() {
        return USDRUB;
    }

    public void setUSDRUB(double USDRUB) {
        this.USDRUB = USDRUB;
    }
}

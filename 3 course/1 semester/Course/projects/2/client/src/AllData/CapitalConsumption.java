package AllData;

public class CapitalConsumption {
    private String name;
    private float year_percent;
    private float year_price;
    private float month_percent;
    private float month_price;
    
    public CapitalConsumption (String name,float year_percent,
            float year_price,float month_percent,float month_price){
        this.setName(name);
        this.setYearPercent(year_percent);
        this.setYearPrice(year_price);
        this.setMonthPercent(month_percent);
        this.setMonthPrice(month_price);
    }
    
    public void setName (String name){
        this.name = name;
    }
    
    public void setYearPercent(float year_percent){
        this.year_percent = year_percent;
    }
    
    public void setYearPrice(float year_price){
        this.year_price = year_price;
    }
    
    public void setMonthPercent(float month_percent){
        this.month_percent = month_percent;
    }
    
    public void setMonthPrice(float month_price){
        this.month_price = month_price;
    }
    
    public String getNmae (){
        return this.name;
    }
    
    public float getYearPercent (){
        return this.year_percent;
    }
    
    public float getYearPrice (){
        return this.year_price;
    }
    
    public float getMonthPercent (){
        return this.month_percent;
    }
    
    public float getMonthPrice (){
        return this.month_price;
    }
}

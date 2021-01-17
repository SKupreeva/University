package AllData;

public class Asset {
    private String name;
    private int price;
    private int termUse;
    
    public Asset (String name, int price, int termUse) {
        this.setName(name);
        this.setPrice(price);
        this.setTermUse(termUse);
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setPrice (int price) {
        this.price = price;
    }
    
    public void setTermUse (int termUse) {
        this.termUse = termUse;
    }
    
    public String getName () {
        return this.name;
    }
    
    public int getPrice () {
        return this.price;
    }
    
    public int getTermUse () {
        return this.termUse;
    }
}

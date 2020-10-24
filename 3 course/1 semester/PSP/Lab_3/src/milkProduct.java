public interface milkProduct {
    String shelfLife = "8 days";
    double proteinNumber = 12.5;
    double fatContent = 9;

    public int getShelfLife();
    public double getProteinNumber();
    public double getFatContent();

    abstract void showInfo();
}

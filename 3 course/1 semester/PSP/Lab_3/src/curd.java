public class curd implements milkProduct, Object{
    int weight;
    int grain = 56;

    curd() {
        getProteinNumber();
        getFatContent();
        getShelfLife();
        print();
    }

    curd(int w) {
        this.weight = w;
        showInfo();
    }

    curd(int w, int g) {
        this.grain = g;
        this.weight = w;
        showInfo();
    }

    @Override
    public void print() {
        System.out.println("\n    Info about class Curd:");
        System.out.println("Class fields: weight, grain.");
        System.out.println("Constructors: curd(), curd(int w).");
        System.out.println("Overrided methods: getShelfLife(), getProteinNumber(), getFatContent(), showInfo(), print().\n");
    }

    @Override
    public int getShelfLife() {
        return 0;
    }

    @Override
    public double getProteinNumber() {
        return 0;
    }

    @Override
    public double getFatContent() {
        return 0;
    }

    @Override
    public void showInfo() {
        System.out.println("\n    Info(class Curd):");
        System.out.println("Shelf Life - " + shelfLife + ".");
        System.out.println("Protein number - " + proteinNumber + "%.");
        System.out.println("Fat content - " + fatContent + "%.");
        System.out.println("Weight - " + weight + "gr.");
        System.out.println("Grain - " + grain + "%.");
    }
}

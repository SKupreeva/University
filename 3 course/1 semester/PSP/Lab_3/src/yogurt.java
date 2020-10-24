abstract class yogurt implements milkProduct, Object{

    abstract void showFields();

    @Override
    public void print() {
        System.out.println("\n    Info about class Yogurt:");
        System.out.println("Class fields: none.");
        System.out.println("Constructors: yogurt(), yogurt(String s, int v, int t).");
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
        System.out.println("\n    Info(class Yogurt):");
        System.out.println("Shelf Life - " + shelfLife + ".");
        System.out.println("Protein number - " + proteinNumber + "%.");
        System.out.println("Fat content - " + fatContent + "%.");
    }
}

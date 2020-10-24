public class kefir implements milkProduct, Object{
    int volume;
    int bifidobacteriumsNumber = 54;

    kefir() {
        getShelfLife();
        getFatContent();
        getProteinNumber();
        print();
    }

    kefir(int v) {
        this.volume = v;
        showInfo();
    }

    kefir(int v, int bn) {
        this.bifidobacteriumsNumber = bn;
        this.volume = v;
        showInfo();
    }

    @Override
    public void print() {
        System.out.println("\n    Info about class Kefir:");
        System.out.println("Class fields: volume, bifidobacteriumsNumber.");
        System.out.println("Constructors: kefir(), kefir(int v).");
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
        System.out.println("\n    Info(class Kefir):");
        System.out.println("Shelf Life - " + shelfLife + ".");
        System.out.println("Protein number - " + proteinNumber + "%.");
        System.out.println("Fat content - " + fatContent + "%.");
        System.out.println("Volume - " + volume + "ml.");
        System.out.println("Bifidobacteriums number - " + bifidobacteriumsNumber + "%.");
    }
}

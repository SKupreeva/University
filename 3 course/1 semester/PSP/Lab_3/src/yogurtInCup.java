public class yogurtInCup extends yogurt{
    String flavorAdditive;
    int volume;
    int termination;

    yogurtInCup() {
        print();
        System.out.println("\n    Info about class yogurtInCup:");
        System.out.println("Class fields: flavourAdditive, volume, termination.");
        System.out.println("Constructors: yogurtInCup(), yogurtInCup(String s, int v, int t).");
        System.out.println("Overrided methods: showFields().\n");
    }

    yogurtInCup(String s, int v, int t) {
        this.flavorAdditive = s;
        this.volume = v;
        this.termination = t;
        showInfo();
        showFields();
    }

    yogurtInCup(String s) {
        this.flavorAdditive = s;
        this.volume = 78;
        this.termination = 12;
        showInfo();
        showFields();
    }

    @Override
    void showFields() {
        System.out.println("Flavor additive - " + flavorAdditive + ".");
        System.out.println("Volume - " + volume + "ml.");
        System.out.println("Termination - " + termination + "%.");
    }
}

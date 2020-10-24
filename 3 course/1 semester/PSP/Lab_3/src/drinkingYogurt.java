public class drinkingYogurt extends yogurt{
    String flavorAdditive;
    int volume;
    int termination;

    drinkingYogurt() {
        print();
        System.out.println("\n    Info about class drinkingYogurt:");
        System.out.println("Class fields: flavourAdditive, volume, termination.");
        System.out.println("Constructors: drinkingYogurt(), drinkingYogurt(String s, int v, int t).");
        System.out.println("Overrided methods: showFields().\n");
    }

    drinkingYogurt(String s, int v, int t) {
        this.flavorAdditive = s;
        this.volume = v;
        this.termination = t;
        showInfo();
        showFields();
    }

    drinkingYogurt(String s, int t) {
        this.flavorAdditive = s;
        this.volume = 390;
        this.termination = t;
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

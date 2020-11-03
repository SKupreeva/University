package hieracy;

import hieracy.animal;

public class fish implements animal {

    public void showInfoFish() {
        System.out.println("Рыбы (лат. Pisces) — парафилетическая группа\n" +
                "(по современной кладистической классификации)\n" +
                "водных позвоночных животных.\n\n");
    }

    @Override
    public int numberDiff(int a, int b) {
        int k = a - b;
        System.out.println("Общее количество известных видов рыб = " + a + ".");
        System.out.println("Количество изученных видов рыб = " + b + ".");
        System.out.println("Количество не изученных видов рыб = " + k + ".\n\n");
        return k;
    }

    @Override
    public int numberSum(int a, int b) {
        int k = a + b;
        System.out.println("Количество известных пресноводных рыб = " + a + ".");
        System.out.println("Количество известных морских рыб = " + b + ".");
        System.out.println("Общее количество особей рыб = " + k + ".\n\n");
        return k;
    }
}

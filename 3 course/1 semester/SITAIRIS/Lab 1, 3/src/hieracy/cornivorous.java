package hieracy;

public class cornivorous extends mammal {
    public void showInfoCornivorous() {
        System.out.println("Хищные (Хищники) – это отряд млекопитающих,\n" +
                "в который входят такие подотряды как псообразные\n" +
                "и котообразные животные.\n\n");
    }

    @Override
    public int numberFeMale(int a, int b) {
        int k = a/b;
        System.out.println("Количество самцов хищных млекопитающих = " + a + ".");
        System.out.println("Количество самок хищных млекопитающих = " + b + ".");
        System.out.println("Отношение количества самок к количеству самцов = " + k + "(округление).\n\n");
        return k;
    }

    @Override
    public int numberDiff(int a, int b) {
        int k = a - b;
        System.out.println("Общее количество известных видов хищных млекопитающих = " + a + ".");
        System.out.println("Количество изученных видов хищных млекопитающих = " + b + ".");
        System.out.println("Количество не изученных видов хищных млекопитающих = " + k + ".\n\n");
        return k;
    }

    @Override
    public int numberSum(int a, int b) {
        int k = a + b;
        System.out.println("Количество самцов хищных млекопитающих = " + a + ".");
        System.out.println("Количество самок хищных млекопитающих = " + b + ".");
        System.out.println("Общее количество особей хищных млекопитающих = " + k + ".\n\n");
        return k;
    }
}

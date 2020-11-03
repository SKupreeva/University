package hieracy;

public class herbivorous extends mammal {

    public void showInfoHerbivorous() {
        System.out.println("Растительноядные или травоядные - это животные,\n" +
                "которые питаются пищей растительного происхождения, включая траву,\n" +
                "фрукты, листья, корни, луковицы, овощи и т.д.\n\n");
    }

    @Override
    public int numberFeMale(int a, int b) {
        int k = a/b;
        System.out.println("Количество самцов травоядных млекопитающих = " + a + ".");
        System.out.println("Количество самок травоядных млекопитающих = " + b + ".");
        System.out.println("Отношение количества самок к количеству самцов = " + k + "(округление).\n\n");
        return k;
    }

    @Override
    public int numberDiff(int a, int b) {
        int k = a - b;
        System.out.println("Общее количество известных видов травоядных млекопитающих = " + a + ".");
        System.out.println("Количество изученных видов травоядных млекопитающих = " + b + ".");
        System.out.println("Количество не изученных видов травоядных млекопитающих = " + k + ".\n\n");
        return k;
    }

    @Override
    public int numberSum(int a, int b) {
        int k = a + b;
        System.out.println("Количество самцов травоядных млекопитающих = " + a + ".");
        System.out.println("Количество самок травоядных млекопитающих = " + b + ".");
        System.out.println("Общее количество особей травоядных млекопитающих = " + k + ".\n\n");
        return k;
    }
}

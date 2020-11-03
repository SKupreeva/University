package hieracy;

public class symbiotrop extends moss implements cornivorousPlant, seaweed {

    public void showTableSymbiotrop() {
        System.out.println("    К симбиотропам относятся:");
        System.out.println("_____________________________");
        System.out.println("| № |   Название царства    |");
        System.out.println("_____________________________");
        System.out.println("| 1 |                  Грибы|");
        System.out.println("_____________________________");
        System.out.println("| 2 |              Лишайники|");
        System.out.println("_____________________________");
        System.out.println("| 3 |               Бактерии|");
        System.out.println("_____________________________\n\n");
    }

    @Override
    public void showStat() {
        System.out.println("Статистика количества видов вступающих в симбиотические отношения:");
        System.out.println("На 2017 год количество симбиотоических видов = 753 342 983.");
        System.out.println("На 2018 год количество симбиотоических видов = 1 407 539 623.");
        System.out.println("На 2019 год количество симбиотоических видов = 3 543 314 663.");
    }

    @Override
    public int numberDiff(int a, int b) {
        int k = a - b;
        System.out.println("Общее количество симбиотропов = " + a + ".");
        System.out.println("Количество изученных симбиотропов = " + b + ".");
        System.out.println("Количество не изученных симбиотропов = " + k + ".\n\n");
        return k;
    }

    @Override
    public void average(int a, int b) {
        float k = a/b + a%b;
        System.out.println("Количетсво особей вида Cypripedium calceolus на 2017 год = " + a + ".");
        System.out.println("Количетсво особей вида Cypripedium calceolus на 2019 год = " + b + ".");
        System.out.println("Среднее значние = " + k + ".\n\n");
    }

    @Override
    public void showString(String s) {
        System.out.println(s);
    }

    @Override
    public int ratioLess(int a, int b) {
        int k = a/b;
        System.out.println("Количество известных симбиотропов среди животных = " + a + ".");
        System.out.println("Количество известных симбиотропов среди бактерий = " + b + ".");
        System.out.println("Отношение количества симбиотропов 2 царств = " + k + "(округление).\n\n");
        return k;
    }

    @Override
    public String redactString(String a) {
        String s = a + " - symbiotrop(англ.).\n";
        System.out.println(s);
        return s;
    }

    @Override
    public int numberSum(int a, int b) {
        int k = a + b;
        System.out.println("Количество известных симбиотропов среди растений = " + a + ".");
        System.out.println("Количество известных симбиотропов среди грибов = " + b + ".");
        System.out.println("Общее количество симбиотропов в 2 царствах = " + k + ".\n\n");
        return k;
    }

    @Override
    public void showInfo() {
        System.out.println("Симбиотроп - организм, питающийся посредством\n" +
                "симбиоза с другим организмом.\n\n");
    }
}


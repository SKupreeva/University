package hieracy;

public class gidrovit extends wheat implements seaweed {

    public void showTableGidrovit() {
        System.out.println("    К гидрофитам относятся:");
        System.out.println("_____________________________");
        System.out.println("| № |   Название семейства  |");
        System.out.println("_____________________________");
        System.out.println("| 1 |               Наядовые|");
        System.out.println("_____________________________");
        System.out.println("| 2 |           Взморниковые|");
        System.out.println("_____________________________");
        System.out.println("| 3 |              Рдестовые|");
        System.out.println("_____________________________");
        System.out.println("| 4 |          Пузырчатковые|");
        System.out.println("_____________________________\n\n");
    }

    @Override
    public void average(int a, int b) {
        float k = a/b + a%b;
        System.out.println("Количетсво особей вида Selaginélla на 2017 год = " + a + ".");
        System.out.println("Количетсво особей вида Selaginélla на 2019 год = " + b + ".");
        System.out.println("Среднее значние = " + k + ".\n\n");
    }

    @Override
    public void showInfo() {
        System.out.println("Гидрофиты (от гидро... и греч. phýton — растение),\n" +
                "водные растения, прикрепленные к почве и погруженные\n" +
                "в воду только нижними своими частями.\n\n");
    }

    @Override
    public void showString(String s) {
        System.out.println(s);
    }

    @Override
    public int ratioLess(int a, int b) {
        int k = a/b;
        System.out.println("Количество известных гидрофитов среди грибов = " + a + ".");
        System.out.println("Количество известных гидрофитов среди бактерий = " + b + ".");
        System.out.println("Отношение количества гидрофитов 2 царств = " + k + "(округление).\n\n");
        return k;
    }

    @Override
    public String redactString(String a) {
        String s = a + " - gidrovit(англ.).\n";
        System.out.println(s);
        return s;
    }

    @Override
    public int numberSum(int a, int b) {
        int k = a + b;
        System.out.println("Количество известных гидрофитов среди грибов = " + a + ".");
        System.out.println("Количество известных гидрофитов среди водорослей = " + b + ".");
        System.out.println("Общее количество гидрофитов в 2 царствах = " + k + ".\n\n");
        return k;
    }
}


import java.util.Scanner;

import static java.lang.System.exit;

public class producer {
    public static void main(String[] args) {
        int k = 0;
        Scanner scan = new Scanner(System.in);
        while(k != 6) {
            System.out.println("\n\n    Menu:");
            System.out.println("1. Info about classes.");
            System.out.println("2. Info about object - class Curd.");
            System.out.println("3. Info about object - class Kefir.");
            System.out.println("4. Info about object - class Drinking yogurt.");
            System.out.println("5. Info about object - class Yogurt in Cup.");
            System.out.println("6. Exit.");
            System.out.println("Select:");
            k = scan.nextInt();
            switch (k) {
                case 1: writerInfo wr = new writerInfo();
                    break;
                case 2: curd c = new curd(200);
                    break;
                case 3: kefir kf = new kefir(300);
                    break;
                case 4: drinkingYogurt dy = new drinkingYogurt("1 week", 120, 21);
                    break;
                case 5: yogurtInCup cy = new yogurtInCup("5 days", 75, 18);
                    break;
                case 6: exit(0);
                    break;
                default: System.out.println("   Error!\n\n");
                    break;
            }
        }
    }
}

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    static String file1 = "Books.txt";
    static String file2 = "Authors.txt";

    public static void main(String[] args) throws IOException {
        FileReader fr1 = new FileReader(file1);
        Scanner scan1 = new Scanner(fr1);

        while(scan1.hasNextLine()){
            findWindow.c1.add(scan1.nextLine());
        }

        FileReader fr2 = new FileReader(file2);
        Scanner scan2 = new Scanner(fr2);

        while(scan2.hasNextLine()){
            findWindow.c2.add(scan2.nextLine());
        }

        JFrame menu_window = new menu();
        menu_window.setVisible(true);

        fr1.close();
        fr2.close();
    }
}

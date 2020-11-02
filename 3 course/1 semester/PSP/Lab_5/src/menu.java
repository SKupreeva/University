import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.FindException;

public class menu extends JFrame {
    JRadioButton rb1, rb2, rb3, rb4;
    JButton btn;
    JLabel lb;
    ButtonGroup bg;

    public menu() throws IOException {
        super("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(160, 210);

        FileWriter fw1 = new FileWriter(main.file1);
        FileWriter fw2 = new FileWriter(main.file2);

        lb = new JLabel("Select activity:");

        rb1 = new JRadioButton("Find string");
        rb2 = new JRadioButton("Add string");
        rb3 = new JRadioButton("Change string");
        rb4 = new JRadioButton("Delete string");

        btn = new JButton("Submit");

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);

        setLayout(null);
        lb.setBounds(5, 10, 150, 25);
        rb1.setBounds(10, 35, 150, 25);
        rb2.setBounds(10, 60, 150, 25);
        rb3.setBounds(10, 85, 150, 25);
        rb4.setBounds(10, 110, 150, 25);
        btn.setBounds(20, 140, 90, 20);
        add(lb);
        add(rb1);
        add(rb2);
        add(rb3);
        add(rb4);
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb1.isSelected()) {
                    JFrame find_window = new findWindow();
                    find_window.pack();
                    find_window.setVisible(true);
                    find_window.setSize(390, 460);
                } else if(rb2.isSelected()){
                    JFrame add_window = new addWindow();
                    add_window.pack();
                    add_window.setVisible(true);
                    add_window.setSize(180, 220);
                }else if(rb3.isSelected()){
                    JFrame change_window = new changeWindow();
                    change_window.pack();
                    change_window.setVisible(true);
                    change_window.setSize(390, 460);
                }else if(rb4.isSelected()){
                    JFrame delete_window = new deleteWindow();
                    delete_window.pack();
                    delete_window.setVisible(true);
                    delete_window.setSize(390, 460);
                } else {
                    showErrorWindow();
                }
            }
        });

        for(int i = 0; i < findWindow.c1.getItemCount(); i++) {
            fw1.write(findWindow.c1.getItem(i) + "\n");
        }

        for(int i = 0; i < findWindow.c2.getItemCount(); i++) {
            fw2.write(findWindow.c2.getItem(i) + "\n");
        }

        fw1.close();
        fw2.close();
    }

    void showErrorWindow() {
        JFrame error_window = new errorWindow();
        error_window.pack();
        error_window.setVisible(true);
        error_window.setSize(170, 100);
    }

}

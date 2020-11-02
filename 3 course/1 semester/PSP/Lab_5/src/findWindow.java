import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class findWindow extends JFrame {
    JCheckBox cb1, cb2;
    ButtonGroup cbg;
    JButton btn;
    JLabel lb1, lb2, lb3, lb4;
    JTextField tf;
    public static List c1 =  new List();
    public static List c2 = new List();

    public findWindow() {
        super("Find String");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        lb1 = new JLabel("Select list:");
        lb2 = new JLabel("Enter string to find:");
        lb3 = new JLabel("Books");
        lb4 = new JLabel("Authors");

        cb1 = new JCheckBox("Books");
        cb2 = new JCheckBox("Authors");

        tf = new JTextField();

        cbg = new ButtonGroup();
        cbg.add(cb1);
        cbg.add(cb2);

        btn = new JButton("OK");

        setLayout(null);
        lb1.setBounds(10, 10, 150, 25);
        cb1.setBounds(10, 35, 150, 25);
        cb2.setBounds(10, 60, 150, 25);
        lb2.setBounds(10, 85, 150, 25);
        tf.setBounds(10, 110, 150, 25);
        btn.setBounds(165, 110, 70, 25);
        lb3.setBounds(10, 135, 150, 25);
        lb4.setBounds(200, 135, 150, 25);
        c1.setBounds(10, 160, 160, 180);
        c2.setBounds(200, 160, 160, 180);

        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);
        add(cb1);
        add(cb2);
        add(c1);
        add(c2);
        add(tf);
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = tf.getText();
                c1.setMultipleSelections(true);
                c2.setMultipleSelections(true);
                if(cb1.isSelected()) {
                    int flag = 0;
                    for(int i = 0; i < c1.getItemCount(); i++){
                        if(c1.getItem(i).contains(str)){
                            c1.select(i);
                            flag = 1;
                        }
                    }
                    if(flag == 0) {
                        showErrorWindow();
                    }
                }else if(cb2.isSelected()) {
                    int flag = 0;
                    for(int i = 0; i < c2.getItemCount(); i++){
                        if(c2.getItem(i).contains(str)){
                            c2.select(i);
                            flag = 1;
                        }
                    }
                    if(flag == 0) {
                        showErrorWindow();
                    }
                } else {
                    showErrorWindow();
                }
            }
        });

    }

    void showErrorWindow() {
        JFrame error_window = new errorWindow();
        error_window.pack();
        error_window.setVisible(true);
        error_window.setSize(170, 100);
    }
}
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changeWindow extends JFrame {
    JLabel lb1, lb2, lb3;
    JRadioButton rb1, rb2;
    ButtonGroup bg;
    JTextField tf1, tf2;
    JComboBox c1, c2;
    JButton btn;

    public changeWindow(){
        super("Change String");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        lb1 = new JLabel("Select list:");
        lb2 = new JLabel("Enter old string:");
        lb3 = new JLabel("Enter new string:");

        rb1 = new JRadioButton("Books");
        rb2 = new JRadioButton("Authors");

        c1 = new JComboBox();
        c2 = new JComboBox();

        c1.addItem("Cinderella");
        c1.addItem("Red Hood");

        c2.addItem("Anderson");
        c2.addItem("Hamlet");

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        tf1 = new JTextField();
        tf2 = new JTextField();

        btn = new JButton("OK");

        setLayout(null);
        lb1.setBounds(10, 10, 150, 25);
        rb1.setBounds(10, 35, 150, 25);
        rb2.setBounds(10, 60, 150, 25);
        lb2.setBounds(10, 85, 150, 25);
        tf1.setBounds(10, 110, 150, 25);
        lb3.setBounds(10, 300, 150, 25);
        tf2.setBounds(10, 320, 150, 25);
        btn.setBounds(100, 350, 70, 25);
        c1.setBounds(10, 120, 160, 25);
        c2.setBounds(200, 120, 160, 25);

        add(lb1);
        add(lb2);
        add(lb3);
        add(rb1);
        add(rb2);
        add(tf2);
        add(c1);
        add(c2);
        add(btn);
        c1.setVisible(false);
        c2.setVisible(false);

        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.setVisible(true);
            }
        });

        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c2.setVisible(true);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int f = 0;
                String str = tf2.getText();
                if(rb1.isSelected()) {
                    Object o = c1.getSelectedItem();
                    c1.removeItem(o);
                    c1.addItem(str);
                    f++;
                }else if(rb2.isSelected()) {
                    Object o = c2.getSelectedItem();
                    c2.removeItem(o);
                    c2.addItem(str);
                    f++;
                }else{
                    showErrorWindow();
                }
                if(f != 0)
                    showActionWindow();
            }
        });
    }

    void showErrorWindow() {
        JFrame error_window = new errorWindow();
        error_window.pack();
        error_window.setVisible(true);
        error_window.setSize(170, 100);
    }
    void showActionWindow() {
        JFrame action_window = new actionWindow();
        action_window.pack();
        action_window.setVisible(true);
        action_window.setSize(170, 100);
    }
}
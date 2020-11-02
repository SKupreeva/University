import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteWindow extends JFrame {
    JLabel lb1, lb2;
    JRadioButton rb1, rb2;
    ButtonGroup bg;
    JTextField tf;
    JButton btn;

    public deleteWindow(){
        super("Delete String");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        lb1 = new JLabel("Select list:");
        lb2 = new JLabel("Enter string to delete:");

        rb1 = new JRadioButton("Books");
        rb2 = new JRadioButton("Authors");

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        tf = new JTextField();

        btn = new JButton("OK");

        setLayout(null);
        lb1.setBounds(10, 10, 150, 25);
        rb1.setBounds(10, 35, 150, 25);
        rb2.setBounds(10, 60, 150, 25);
        lb2.setBounds(10, 85, 150, 25);
        tf.setBounds(10, 110, 150, 25);
        btn.setBounds(100, 350, 70, 25);
        findWindow.c1.setBounds(10, 120, 160, 180);
        findWindow.c2.setBounds(200, 120, 160, 180);

        add(lb1);
        add(lb2);
        add(rb1);
        add(rb2);
        add(findWindow.c1);
        add(findWindow.c2);
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int f =  0;
                if(rb1.isSelected()) {
                    findWindow.c1.remove(findWindow.c1.getSelectedItem());
                    f  = 1;
                }else if(rb2.isSelected()) {
                    findWindow.c2.remove(findWindow.c2.getSelectedItem());
                    f = 1;
                }else{
                    showErrorWindow();
                }
                if(f == 1)
                    showActionWindow();
                else showErrorWindow();
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
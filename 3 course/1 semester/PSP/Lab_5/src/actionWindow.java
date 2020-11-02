import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actionWindow extends JFrame {
    JLabel lb;
    JButton btn;

    public actionWindow(){
        super("Action window");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        lb = new JLabel("Action is complete!");
        btn = new JButton("OK");

        setLayout(null);
        lb.setBounds(10, 2, 150, 25);
        btn.setBounds(45, 28, 70, 25);

        add(lb);
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

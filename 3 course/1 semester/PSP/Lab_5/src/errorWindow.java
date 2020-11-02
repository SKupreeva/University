import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class errorWindow extends JFrame {
    JLabel lb;
    JButton btn;

    public errorWindow(){
        super("Error window");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        lb = new JLabel("Error!");
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

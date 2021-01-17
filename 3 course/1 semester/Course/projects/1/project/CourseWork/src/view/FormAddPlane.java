package view;

import controller.Controller;

import javax.swing.*;

public class FormAddPlane extends JFrame{
    private JPanel contentPane;
    private JTextField model_tf;
    private JTextField boardNumber_tf;
    private JTextField yearOfMade_tf;
    private JTextField countOFSeats_tf;
    private JButton buttonBackPlane;
    private JButton buttonSavePlane;

    public FormAddPlane(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        Controller.getInstance().initialize(this);
        buttonBackPlane.setActionCommand("backFromAddPlane");
        buttonBackPlane.addActionListener(Controller.getInstance());
        buttonSavePlane.setActionCommand("savePlane");
        buttonSavePlane.addActionListener(Controller.getInstance());
    }

    public JTextField getModel_tf() {
        return model_tf;
    }

    public JTextField getBoardNumber_tf() {
        return boardNumber_tf;
    }

    public JTextField getYearOfMade_tf() {
        return yearOfMade_tf;
    }

    public JTextField getCountOFSeats_tf() {
        return countOFSeats_tf;
    }
}

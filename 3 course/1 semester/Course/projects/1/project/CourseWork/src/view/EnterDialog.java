package view;

import controller.Controller;
import model.client.Client;

import javax.swing.*;

public class EnterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonEnter;
    private JButton buttonRegistration;
    private JTextField textLogin;
    private JPasswordField passwordField1;

    public JTextField getTextLogin(){
        return textLogin;
    }
    public JPasswordField getPasswordField1(){
        return passwordField1;
    }

    public EnterDialog() {
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        setModal(true);
        getRootPane().setDefaultButton(buttonEnter);

        Controller.getInstance().initialize(this);
        buttonEnter.addActionListener(Controller.getInstance());
        buttonRegistration.setActionCommand("showRegistrationFrame");
        buttonRegistration.addActionListener(Controller.getInstance());
    }
    public void exit(){
        setDefaultCloseOperation(EnterDialog.HIDE_ON_CLOSE);
    }


}

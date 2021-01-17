package view;

import controller.Controller;

import javax.swing.*;

public class RegistrationUsers extends JFrame{
    private JPanel contentPane;
    private JTextField name_tf;
    private JTextField secondName_tf;
    private JTextField patronymic_tf;
    private JTextField login_tf;
    private JTextField password_tf;
    private JTextField passportSeries_tf;
    private JTextField passportNumver_tf;
    private JButton buttonRegistration;
    private JButton buttonCanselRegistration;

    public RegistrationUsers(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        Controller.getInstance().initialize(this);
        buttonRegistration.setActionCommand("registrationUsers");
        buttonRegistration.addActionListener(Controller.getInstance());
        buttonCanselRegistration.setActionCommand("backToAutorization");
        buttonCanselRegistration.addActionListener(Controller.getInstance());
    }

    public JTextField getName_tf() {
        return name_tf;
    }

    public JTextField getSecondName_tf() {
        return secondName_tf;
    }

    public JTextField getPatronymic_tf() {
        return patronymic_tf;
    }

    public JTextField getLogin_tf() {
        return login_tf;
    }

    public JTextField getPassportSeries_tf() {
        return passportSeries_tf;
    }

    public JTextField getPassword_tf() {
        return password_tf;
    }

    public JTextField getPassportNumver_tf() {
        return passportNumver_tf;
    }
}

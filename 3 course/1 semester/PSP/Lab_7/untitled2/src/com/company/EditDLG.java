package com.company;

import javax.swing.*;
import java.awt.event.*;

public class EditDLG extends JDialog {
    private JPanel contentPane;
    private JButton OKButton;
    private JTextArea StudentIDTextArea;
    private JTextArea StudentNameTextArea;
    private JTextArea StudentSurnameTextArea;
    private JTextArea StudentGroupNumberTextArea;
    private JTextField StudentIDTextField;
    private JTextField StudentNameTextField;
    private JTextField StudentSurnameTextField;
    private JTextField StudentGroupNumberTextField;

    public EditDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setContentPane(contentPane);
        OKButton.addActionListener(e -> onOK());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        pack();
        setResizable(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 416) / 2, (screenSize.height - 636) / 2, 416, 636);
    }

    private void onOK() {
        this.setVisible(false);
    }

    public String getStudentID() {
        return StudentIDTextField.getText();
    }

    public String getStudentName() {
        return StudentNameTextField.getText();
    }

    public String getStudentSurname() {
        return StudentSurnameTextField.getText();
    }

    public String getStudentGroupNumber() {
        return StudentGroupNumberTextField.getText();
    }

    public void setStudentID(String StudentID) {
        StudentIDTextField.setText(StudentID);
    }

    public void setStudentName(String StudentName) {
        StudentNameTextField.setText(StudentName);
    }

    public void setStudentSurname(String StudentSurname) {
        StudentSurnameTextField.setText(StudentSurname);
    }

    public void setStudentGroupNumber(String StudentGN) {
        StudentGroupNumberTextField.setText(StudentGN);
    }
}

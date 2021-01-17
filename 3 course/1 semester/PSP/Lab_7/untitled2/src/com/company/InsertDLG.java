package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertDLG extends JFrame {
    private DBConnection mdbc;
    private java.sql.Statement stmt;
    private final EditDLG dlg;

    private JPanel contentPane;
    private JButton SubmitButton;
    private JTextArea StudentIDTextArea;
    private JTextArea studentNameTextArea;
    private JTextArea studentSurnameTextArea;
    private JTextArea studentGroupNumberTextArea;
    private JTextField StudentIDTextField;
    private JTextField StudentNameTextField;
    private JTextField StudentSurnameTextField;
    private JTextField StudentGroupNumberTextField;
    private JTable table1;
    private JButton EditButton;
    private JButton DeleteButton;
    private JTextArea CommentLabel;

    public InsertDLG() {
        dlg =  new EditDLG(this, true);

        try {
            mdbc = new DBConnection();
            mdbc.init();
            Connection conn = mdbc.getMyConnection();
            stmt = conn.createStatement();
        } catch (Exception e) {System.out.println(e.getLocalizedMessage());}

        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        setContentPane(contentPane);

        ResultSet rs = getResultFromStudent();

        table1.setModel(new StudentModel(rs));
        table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mdbc.close(rs);

        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });

        SubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-416)/2, (screenSize.height-636)/2, 416, 636);
    }

    public ResultSet getResultFromStudent() {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery("SELECT * FROM lab7_part1.student");
        }
        catch(SQLException e){System.out.println(e.getLocalizedMessage());}
        return rs;
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try
        {
            mdbc.close(stmt.getResultSet());
            mdbc.destroy();
        }
        catch (SQLException ex){System.out.println("Close exception");}
    }

    public String quotate(String content)
    {
        return "'" + content + "'";
    }

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt){
        String studId = StudentIDTextField.getText();
        String studName = StudentNameTextField.getText();
        String studSurname = StudentSurnameTextField.getText();
        String studGroupNumber = StudentGroupNumberTextField.getText();
        String insertStr = "";
        try {
            insertStr = "INSERT INTO lab7_part1.student VALUES ("
                    + quotate(studId) + ","
                    + quotate(studName) + ","
                    + quotate(studSurname) + ","
                    + quotate(studGroupNumber) + ")";
            System.out.println(insertStr);
            int done = stmt.executeUpdate(insertStr);
            CommentLabel.setText("1 row inserted");
            initComponents();
        } catch (Exception e) {
            CommentLabel.setText(e.getLocalizedMessage());
        }
    }

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(table1.getSelectedRow());
        if (table1.getSelectedRowCount() > 0) {
            String profId = (String) table1.getValueAt(table1.getSelectedRow(), 0);

            String insertStr = "";
            try {
                insertStr = "DELETE FROM lab7_part1.student WHERE F_ID=" + profId;
                int done = stmt.executeUpdate(insertStr);
                CommentLabel.setText("1 row deleted");
                initComponents();
            } catch (Exception e) {
                CommentLabel.setText(e.getLocalizedMessage());
            }
        }
    }

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (table1.getSelectedRowCount() > 0) {
            dlg.setStudentID((String) table1.getValueAt(table1.getSelectedRow(), 0));
            dlg.setStudentName((String) table1.getValueAt(table1.getSelectedRow(), 1));
            dlg.setStudentSurname((String) table1.getValueAt(table1.getSelectedRow(), 2));
            dlg.setStudentGroupNumber((String) table1.getValueAt(table1.getSelectedRow(), 3));
            dlg.setVisible(true);
            try {
                String insertStr = "UPDATE lab7_part1.student SET F_ID="
                        + quotate(dlg.getStudentID()) + "WHERE F_ID =" + (String) table1.getValueAt(table1.getSelectedRow(), 0);
                int done = stmt.executeUpdate(insertStr);
                insertStr = "UPDATE lab7_part1.student SET SName="
                        + quotate(dlg.getStudentName()) + "WHERE SName =" + (String) table1.getValueAt(table1.getSelectedRow(), 0);
                done = stmt.executeUpdate(insertStr);
                insertStr = "UPDATE lab7_part1.student SET SurName="
                        + quotate(dlg.getStudentSurname()) + "WHERE SurName =" + (String) table1.getValueAt(table1.getSelectedRow(), 0);
                done = stmt.executeUpdate(insertStr);
                insertStr = "UPDATE lab7_part1.student SET GroupNumber="
                        + quotate(dlg.getStudentGroupNumber()) + "WHERE GroupNumber =" + (String) table1.getValueAt(table1.getSelectedRow(), 0);
                done = stmt.executeUpdate(insertStr);
                initComponents();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {
        if(table1.getSelectedRowCount() > 0)
        {
            EditButton.setEnabled(true);
            DeleteButton.setEnabled(true);
        }
        else
        {
            EditButton.setEnabled(false);
            DeleteButton.setEnabled(false);
        }
    }
}

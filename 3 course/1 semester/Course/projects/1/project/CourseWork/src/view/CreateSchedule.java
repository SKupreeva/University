package view;

import controller.Controller;
import model.client.Client;

import javax.swing.*;

public class CreateSchedule extends JFrame{
    private JPanel contentPane;
    private JTextField StartPoint_tf;
    private JTextField FinishPoint_tf;
    private JTextField Hours_tf;
    private JTextField Minutes_tf;
    private JComboBox boardNumber_cb;
    private JTextField Rout_tf;
    private JTextField Cost_tf;
    private JTextField MinutesS_tf;
    private JTextField HoursS_tf;
    private JButton ButtonSave;

    private JComboBox typeOfClass_cb;
    private JTextField day_tf;
    private JTextField month_tf;
    private JTextField year_tf;
    private JButton buttonBack;

    public CreateSchedule(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        Controller.getInstance().initialize(this);
        ButtonSave.setActionCommand("saveSchedule");
        ButtonSave.addActionListener(Controller.getInstance());
        buttonBack.setActionCommand("backForAdminMenu");
        buttonBack.addActionListener(Controller.getInstance());
    }

    public JButton getButtonSave() {
        return ButtonSave;
    }

    public JTextField getDay_tf() {
        return day_tf;
    }

    public JTextField getMonth_tf() {
        return month_tf;
    }

    public JTextField getYear_tf() {
        return year_tf;
    }

    public JTextField getStartPoint_tf() {
        return StartPoint_tf;
    }

    public JTextField getFinishPoint_tf() {
        return FinishPoint_tf;
    }

    public JTextField getHours_tf() {
        return Hours_tf;
    }

    public JTextField getMinutes_tf() {
        return Minutes_tf;
    }

    public JTextField getRout_tf() {
        return Rout_tf;
    }

    public JTextField getCost_tf() {
        return Cost_tf;
    }

    public JTextField getMinutesS_tf() {
        return MinutesS_tf;
    }

    public JTextField getHoursS_tf() {
        return HoursS_tf;
    }

    public JComboBox getTypeOfClass_cb() {
        return typeOfClass_cb;
    }

    public JComboBox getBoardNumber_cb() {
        return boardNumber_cb;
    }



    private void createUIComponents() {
        typeOfClass_cb = new JComboBox();
        boardNumber_cb  = new JComboBox();

        typeOfClass_cb.addItem("эконом");
        typeOfClass_cb.addItem("бизнес");
        typeOfClass_cb.addItem("первый");
    }
}

package view;

import controller.Controller;

import javax.swing.*;

public class Form extends JFrame{
    private Form dialog;
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JButton buttonShowSchedule;
    private JButton buttonEdit;
    private JButton buttonCreate;
    private JPanel panelTable;
    private JPanel panelChange;//экспериментальная
    private JScrollPane scrollPane;
    private JButton buttonDelete;
    private JScrollPane scrollPanePassenger;
    private JButton buttonAdd;
    private JButton buttonDeletePlane;
    private JScrollPane scrollPanePlane;
    private JTable tableSchedule;
    private JTable tablePassenger;
    private JTable tablePlane;
    private JPanel graficPane;
    private JComboBox from_cb;
    private JComboBox to_cb;
    private JComboBox boardNumber_cb;
    private JComboBox flight_cb;
    private JButton buttonDoText;


    public Form(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        Controller.getInstance().initialize(this);
        buttonCreate.setActionCommand("createSchedule");
        buttonCreate.addActionListener(Controller.getInstance());
        buttonShowSchedule.setActionCommand("showSchedule");
        buttonShowSchedule.addActionListener(Controller.getInstance());
        buttonDelete.setActionCommand("deleteSchedule");
        buttonDelete.addActionListener(Controller.getInstance());
        buttonEdit.setActionCommand("editSchedule");
        buttonEdit.addActionListener(Controller.getInstance());
        buttonAdd.setActionCommand("addPlaneShowPanel");
        buttonAdd.addActionListener(Controller.getInstance());
        buttonDeletePlane.setActionCommand("deletePlane");
        buttonDeletePlane.addActionListener(Controller.getInstance());
        buttonDoText.setActionCommand("doText");
        buttonDoText.addActionListener(Controller.getInstance());
    }

    public JTable getTableSchedule() {
        return tableSchedule;
    }

    public JTable getTablePassenger() {
        return tablePassenger;
    }

    public JTable getTablePlane() {
        return tablePlane;
    }

    public JPanel getGraficPane() {
        return graficPane;
    }

    public JComboBox getTo_cb() {
        return to_cb;
    }

    public JComboBox getFrom_cb() {
        return from_cb;
    }

    public JComboBox getBoardNumber_cb() {
        return boardNumber_cb;
    }

    public JComboBox getFlight_cb() {
        return flight_cb;
    }
}

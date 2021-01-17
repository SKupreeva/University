package view;

import controller.Controller;

import javax.swing.*;

public class FormCasher extends JFrame{
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JTextField secondName_tf;
    private JTextField name_tf;
    private JTextField patronymic_tf;
    private JTextField series_tf;
    private JTextField number_tf;
    private JComboBox flight_cb;
    private JTextField numberTicket_tf;
    private JButton buttonOrder;
    private JScrollPane scrollPane;
    private JTextField numTic_tf;
    private JButton buttonSearch;
    private JButton buttonShow;
    private JScrollPane scrollPaneReturnTicket;
    private JButton buttonReturnTicket;
    private JTable table;
    private JTable tableSchedule;
    private JTextField newFlight_tf;
    private JButton buttonAddFutureFlight;
    private JComboBox from_cb;
    private JComboBox to_cb;

    public FormCasher(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        Controller.getInstance().initialize(this);
        buttonShow.setActionCommand("showCasherSchedule");
        buttonShow.addActionListener(Controller.getInstance());
        buttonOrder.setActionCommand("orderTicketCasher");
        buttonOrder.addActionListener(Controller.getInstance());
        buttonSearch.setActionCommand("searchTicket");
        buttonSearch.addActionListener(Controller.getInstance());
        buttonReturnTicket.setActionCommand("returnTicket");
        buttonReturnTicket.addActionListener(Controller.getInstance());
        buttonReturnTicket.setEnabled(false);
        buttonAddFutureFlight.setActionCommand("addFutureFlight");
        buttonAddFutureFlight.addActionListener(Controller.getInstance());
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

    public JTextField getSeries_tf() {
        return series_tf;
    }

    public JTextField getNumber_tf() {
        return number_tf;
    }

    public JComboBox getFlight_cb() {
        return flight_cb;
    }

    public JTextField getNumberTicket_tf() {
        return numberTicket_tf;
    }

    public JTextField getNumTic_tf() {
        return numTic_tf;
    }

    public JButton getButtonReturnTicket() {
        return buttonReturnTicket;
    }

    public JTable getTable() {
        return table;
    }

    public JTable getTableSchedule() {
        return tableSchedule;
    }

    public JTextField getNewFlight_tf() {
        return newFlight_tf;
    }

    public JComboBox getFrom_cb() {
        return from_cb;
    }

    public JComboBox getTo_cb() {
        return to_cb;
    }
}

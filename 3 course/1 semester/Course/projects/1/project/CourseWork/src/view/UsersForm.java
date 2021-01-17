package view;

import controller.Controller;

import javax.swing.*;

public class UsersForm extends JFrame{
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JButton buttonShow;
    private JScrollPane scrollPane;
    private JButton buttonOrder;
    private JTextField secondName_tf;
    private JTextField name_tf;
    private JTextField patronymic_tf;
    private JTextField series_tf;
    private JTextField number_tf;
    private JComboBox flight_cb;
    private JButton buttonExit;
    private JScrollPane scrollPaneTicket;
    private JComboBox to_cb;
    private JComboBox from_cb;
    private JTable tableTicket;
    private JTable tableSchedule;
//    private static String[][] data;
//    private static String[][] dataTicket;

    public UsersForm() {
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        Controller.getInstance().initialize(this);
        buttonShow.setActionCommand("showUserSchedule");
        buttonShow.addActionListener(Controller.getInstance());
        buttonOrder.setActionCommand("orderTicket");
        buttonOrder.addActionListener(Controller.getInstance());
        buttonExit.setActionCommand("exitUser");
        buttonExit.addActionListener(Controller.getInstance());
    }

//    public static void setData(String[][] data1) {
//        data = data1;
//    }
//
//    public static void setDataTicket(String[][] dataTicket) {
//        UsersForm.dataTicket = dataTicket;
//    }

    public JTextField getSecondName_tf() {
        return secondName_tf;
    }

    public JTextField getName_tf() {
        return name_tf;
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

    public JComboBox getFrom_cb() {
        return from_cb;
    }

    public JComboBox getTo_cb() {
        return to_cb;
    }

    public JTable getTableTicket() {
        return tableTicket;
    }

    public JTable getTableSchedule() {
        return tableSchedule;
    }

    //    private void createUIComponents() {
//        String[] columnNames = {
//                "Рейс",
//                "Откуда",
//                "Куда",
//                "Самолет",
//                "Отправление",
//                "Прибытие",
//                "В самолете(часы)",
//                "Дата",
//                "Свободных мест",
//                "Класс",
//                "Стоимость"
//        };
//
//        JTable table = new JTable(data, columnNames);
//        scrollPane = new JScrollPane(table);
//
//        String[] columnNamesTicket = {
//                "Рейс",
//                "Номер билета",
//                "Откуда",
//                "Куда",
//                "Время",
//                "Дата",
//                "Пассажир",
//                "Класс",
//                "Стоимость"
//        };
//
//        JTable tableTicket = new JTable(dataTicket, columnNamesTicket);
//        scrollPaneTicket = new JScrollPane(tableTicket);
//    }
}

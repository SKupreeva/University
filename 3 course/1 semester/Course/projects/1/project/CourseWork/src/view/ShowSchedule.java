package view;

import controller.Controller;

import javax.swing.*;

public class ShowSchedule extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private static String[][] data;


    public ShowSchedule(){
        setContentPane(contentPane);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        buttonBack.setActionCommand("showSchedule_back");
        Controller.getInstance().initialize(this);
        buttonBack.addActionListener(Controller.getInstance());
    }

    public static void setData(String[][] data1) {
        data = data1;
    }

    private void createUIComponents() {
        String[] columnNames = {
                "Рейс",
                "Откуда",
                "Куда",
                "Самолет",
                "Отправление",
                "Прибытие",
                "В самолете(часы)",
                "Дата",
                "Свободных мест",
                "Класс",
                "Стоимость"
        };

        JTable table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
    }
}

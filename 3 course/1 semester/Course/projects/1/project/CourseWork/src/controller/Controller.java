package controller;

import model.*;
import model.client.Client;
import model.grafic.PieChart;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private static Controller instance;
    private EnterDialog objEnterDialog;
    private Form objForm;
    private CreateSchedule objCreateSchedule;
    private Message objMessage;
    private ShowSchedule objShowSchedule;
    private RegistrationUsers objRegistrarionUsers;
    private UsersForm objUsersForm;
    private FormCasher objFormCasher;
    private FormAddPlane objFormAddPlane;
    private Client client;

    private Controller(){
        client = new Client("127.0.0.2", "9006");
    }

    public static Controller getInstance(){
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void initialize(EnterDialog obj){
        objEnterDialog = obj;
    }
    public void initialize(Form obj){
        objForm = obj;
    }
    public void initialize(CreateSchedule obj){
        objCreateSchedule = obj;
    }
    public void initialize(Message obj){
        objMessage = obj;
    }
    public void initialize(ShowSchedule obj){
        objShowSchedule = obj;
    }
    public void initialize(RegistrationUsers obj){
        objRegistrarionUsers = obj;
    }
    public void initialize(UsersForm obj){
        objUsersForm = obj;
    }
    public void initialize(FormCasher obj){
        objFormCasher = obj;
    }
    public void initialize(FormAddPlane obj){
        objFormAddPlane = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Вход")) {
            autorization();
        }
        if (e.getActionCommand().equals("Регистрация")){

        }

//        if (e.getActionCommand().equals("buttonOK_delete")) {
//            String rout = objMessage.getRout_tf().getText();
//            if (rout == "")
//                JOptionPane.showMessageDialog(objEnterDialog,
//                        "Заполните полe!",
//                        "Ошибка ввода",
//                        JOptionPane.ERROR_MESSAGE);
//        }

        if (e.getActionCommand().equals("backForAdminMenu")) {
            objCreateSchedule.dispose();
        }

        if (e.getActionCommand().equals("showSchedule")) {
            Rout rout = new Rout();

            rout.setStartPoint(objForm.getFrom_cb().getSelectedItem().toString());
            rout.setFinalPoint(objForm.getTo_cb().getSelectedItem().toString());
            client.sendMessage("showSchedule");
            client.sendObject(rout);

            try {
                if (client.readMessage().equals("This schedule doesn't exist"))
                    JOptionPane.showMessageDialog(objMessage, "Такого расписания нет!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else{
                    String[][] data = (String[][])client.readObject();
                    ShowSchedule.setData(data);
                    ShowSchedule showSchedule = new ShowSchedule();
                    showSchedule.setTitle("Просмтотр расписания");
                    showSchedule.pack();
                    showSchedule.setLocationRelativeTo(null);
                    showSchedule.setVisible(true);

                }
            }
            catch (IOException exIO){
                exIO.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("showUserSchedule")) {
            Rout rout = new Rout();
            rout.setStartPoint(objUsersForm.getFrom_cb().getSelectedItem().toString());
            rout.setFinalPoint(objUsersForm.getTo_cb().getSelectedItem().toString());
            client.sendMessage("showSchedule");
            client.sendObject(rout);

            try {
                if (client.readMessage().equals("This schedule doesn't exist"))
                    JOptionPane.showMessageDialog(objUsersForm, "Такого расписания нет!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else{
                    String[][] data = (String[][])client.readObject();
                    ShowSchedule.setData(data);
                    ShowSchedule showSchedule = new ShowSchedule();
                    showSchedule.setTitle("Просмтотр расписания");
                    showSchedule.pack();
                    showSchedule.setLocationRelativeTo(null);
                    showSchedule.setVisible(true);
                }
            }
            catch (IOException exIO){
                exIO.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("showCasherSchedule")) {
            Rout rout = new Rout();
            rout.setStartPoint(objFormCasher.getFrom_cb().getSelectedItem().toString());
            rout.setFinalPoint(objFormCasher.getTo_cb().getSelectedItem().toString());
            client.sendMessage("showSchedule");
            client.sendObject(rout);

            try {
                if (client.readMessage().equals("This schedule doesn't exist"))
                    JOptionPane.showMessageDialog(objFormCasher, "Такого расписания нет!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else{
                    String[][] data = (String[][])client.readObject();
                    ShowSchedule.setData(data);
                    ShowSchedule showSchedule = new ShowSchedule();
                    showSchedule.setTitle("Просмтотр расписания");
                    showSchedule.pack();
                    showSchedule.setLocationRelativeTo(null);
                    showSchedule.setVisible(true);
                }
            }
            catch (IOException exIO){
                exIO.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("showSchedule_back")) {
            objShowSchedule.dispose();
        }

        if (e.getActionCommand().equals("deleteSchedule")) {
            Message message = new Message();
            message.getButtonOK().setActionCommand("buttonOK_delete");
            client.sendMessage("createFlightScheduleMessage");
            ArrayList<Schedule> list = (ArrayList<Schedule>) client.readObject();
            for (Schedule schedule : list)
                message.getRout_cb().addItem(schedule.getFlight());
            message.pack();
            message.setLocationRelativeTo(null);
            message.setVisible(true);
        }

        if (e.getActionCommand().equals("buttonOK_delete")) {
            Schedule schedule = new Schedule();
            schedule.setFlight(objMessage.getRout_cb().getSelectedItem().toString());
            client.sendMessage("deleteSchedule");
            client.sendObject(schedule);

            String mes = "";
            String mes2 = "";
            try {
                mes = client.readMessage();
                mes2 = client.readMessage();
            }
            catch (IOException exeption){
                System.out.println("Error in reading");
            }
            if (mes.equals("This schedule doesn't exist"))
                JOptionPane.showMessageDialog(objMessage, "Такого рейса не существует!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else {
                if (mes2.equals("NO"))
                    JOptionPane.showMessageDialog(objMessage, "Нельзя удалить расписание! Продажа билетов уже началась", "Удаление расписания", JOptionPane.ERROR_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(objMessage, "Расписание успешно удалено!", "Удаление расписания", JOptionPane.PLAIN_MESSAGE);
                    objMessage.dispose();
                    repaintSchedule();
                    createFormCB();
                }
            }
        }

        if (e.getActionCommand().equals("createSchedule")) {
            client.sendMessage("createSchedule");
            ArrayList<Plane> list = (ArrayList<Plane>)client.readObject();
            CreateSchedule formCreate = new CreateSchedule();
            for (Plane plane: list)
                formCreate.getBoardNumber_cb().addItem(plane.getBoardNumber());
            formCreate.setTitle("Создание расписания");
            formCreate.pack();
            formCreate.setLocationRelativeTo(null);
            formCreate.setVisible(true);
        }
        if (e.getActionCommand().equals("editSchedule")) {
            Message message = new Message();
            message.getButtonOK().setActionCommand("buttonOK_edit");
            client.sendMessage("createFlightScheduleMessage");
            ArrayList<Schedule> list = (ArrayList<Schedule>) client.readObject();
            for (Schedule schedule : list)
                message.getRout_cb().addItem(schedule.getFlight());
            message.pack();
            message.setLocationRelativeTo(null);
            message.setVisible(true);
        }

        if (e.getActionCommand().equals("buttonOK_edit")) {
            Schedule schedule = new Schedule();
            schedule.setFlight(objMessage.getRout_cb().getSelectedItem().toString());
            client.sendMessage("editSchedule");
            client.sendObject(schedule);
            String mes = "";
            try {
                mes = client.readMessage();
            }
            catch (IOException exeption){
                System.out.println("Error in reading");
            }
            if (mes.equals("NO"))
                JOptionPane.showMessageDialog(objMessage, "Невозможно изменить расписание! Продажа билетов уже началась!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            else {
                Schedule newSchedule = (Schedule) client.readObject();
                Rout rout = (Rout) client.readObject();
                Plane plane = (Plane) client.readObject();
                Date date = (Date) client.readObject();

                objMessage.dispose();
                CreateSchedule formCreate = new CreateSchedule();
                formCreate.setTitle("Корректирование расписания");
                formCreate.pack();
                formCreate.setLocationRelativeTo(null);
                formCreate.setVisible(true);
                formCreate.getButtonSave().setActionCommand("saveEdit");

                formCreate.getStartPoint_tf().setText(rout.getStartPoint());
                formCreate.getFinishPoint_tf().setText(rout.getFinalPoint());
                formCreate.getHours_tf().setText(rout.getHoursOfFlight() + "");
                formCreate.getMinutes_tf().setText(rout.getMinutesOfFlight() + "");

                client.sendMessage("createSchedule");
                ArrayList<Plane> list = (ArrayList<Plane>) client.readObject();
                for (Plane plane1 : list)
                    formCreate.getBoardNumber_cb().addItem(plane1.getBoardNumber());

                formCreate.getBoardNumber_cb().setSelectedItem(plane.getBoardNumber());
                formCreate.getTypeOfClass_cb().setSelectedItem(newSchedule.getTypeClass());
                formCreate.getRout_tf().setText(newSchedule.getFlight());
                formCreate.getRout_tf().setEnabled(false);
                formCreate.getCost_tf().setText(rout.getCost() + "");
                formCreate.getDay_tf().setText(date.getDay() + "");
                formCreate.getMonth_tf().setText(date.getMonth() + "");
                formCreate.getYear_tf().setText(date.getYear() + "");
                formCreate.getHoursS_tf().setText(date.getHours() + "");
                formCreate.getMinutesS_tf().setText(date.getMinutes() + "");
            }
        }
        if (e.getActionCommand().equals("saveEdit")) {
            Rout rout = new Rout();
            rout.setStartPoint(objCreateSchedule.getStartPoint_tf().getText());
            rout.setFinalPoint(objCreateSchedule.getFinishPoint_tf().getText());
            Plane plane = new Plane();
            plane.setBoardNumber(objCreateSchedule.getBoardNumber_cb().getSelectedItem().toString());

            Schedule schedule = new Schedule();
            schedule.setTypeClass(objCreateSchedule.getTypeOfClass_cb().getSelectedItem().toString());
            schedule.setFlight(objCreateSchedule.getRout_tf().getText());

            Date date = new Date();

            try {
                objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.gray));

                rout.setHoursOfFlight(Integer.parseInt(objCreateSchedule.getHours_tf().getText()));
                rout.setMinutesOfFlight(Integer.parseInt(objCreateSchedule.getMinutes_tf().getText()));
                rout.setCost(Integer.parseInt(objCreateSchedule.getCost_tf().getText()));
                date.setDay(Integer.parseInt(objCreateSchedule.getDay_tf().getText()));
                date.setMonth(Integer.parseInt(objCreateSchedule.getMonth_tf().getText()));
                date.setYear(Integer.parseInt(objCreateSchedule.getYear_tf().getText()));
                date.setHours(Integer.parseInt(objCreateSchedule.getHoursS_tf().getText()));
                date.setMinutes(Integer.parseInt(objCreateSchedule.getMinutesS_tf().getText()));

                int coutError = 0;
                if (rout.getHoursOfFlight() < 0 || rout.getHoursOfFlight() > 24) {
                    objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 24!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (rout.getMinutesOfFlight() < 0 || rout.getMinutesOfFlight() > 60) {
                    objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 60!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (rout.getCost() < 0) {
                    objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно Быть положительным числом", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getMonth() < 1 || date.getMonth() > 12) {
                    objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 12", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getYear() < 2015 || date.getYear() > 2016) {
                    objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 2015 до 2016", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 29 && date.getMonth() == 2 &&  date.getYear() == 2016) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 29", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 30 && (date.getMonth() == 3 || date.getMonth() == 4 || date.getMonth() == 6 || date.getMonth() == 9 || date.getMonth() == 11)) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 30", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 31 && (date.getMonth() == 1 || date.getMonth() == 5 || date.getMonth() == 7 || date.getMonth() == 8 || date.getMonth() == 10 || date.getMonth() == 12)) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 31", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getYear() == 2015 && date.getMonth() != 12) {
                    objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Создать расписание можно начинаяя с 1 декабря 2015 года", "Ошибка создания расписания", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getHours() < 0 || date.getHours() > 24) {
                    objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 24!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getMinutes() < 0 || date.getMinutes() > 60) {
                    objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 60!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (coutError == 0){
                    client.sendMessage("saveEdit");
                    client.sendObject(rout);
                    client.sendObject(plane);
                    client.sendObject(schedule);
                    client.sendObject(date);

                    try {
                        String answer = client.readMessage();
                        if (answer.equals("Error: schedule is already exist"))
                            JOptionPane.showMessageDialog(objEnterDialog, "Такое расписание уже существует!", "Ошибка корректирования расписание", JOptionPane.ERROR_MESSAGE);
                        if (answer.equals("Error: plane can't make this flight"))
                            JOptionPane.showMessageDialog(objEnterDialog, "Самолет не может осуществить данный рейс!", "Ошибка корректирования расписание", JOptionPane.ERROR_MESSAGE);
                        if (answer.equals("Schedule successfully edited")) {
                            JOptionPane.showMessageDialog(objEnterDialog, "Расписание успешно изменено!", "Корректирование расписания", JOptionPane.PLAIN_MESSAGE);
                            objCreateSchedule.dispose();
                            repaintSchedule();
                            createFormCB();
                        }
                    }
                    catch(IOException exe){
                        exe.printStackTrace();
                    }
                }
            }
            catch(NumberFormatException ex){
                objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.red));

                JOptionPane.showMessageDialog(objEnterDialog, "Поле должно быть целым числом", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("saveSchedule")) {
            Rout rout = new Rout();
            rout.setStartPoint(objCreateSchedule.getStartPoint_tf().getText());
            rout.setFinalPoint(objCreateSchedule.getFinishPoint_tf().getText());

            Plane plane = new Plane();
            plane.setBoardNumber(objCreateSchedule.getBoardNumber_cb().getSelectedItem().toString());

            Schedule schedule = new Schedule();
            schedule.setTypeClass(objCreateSchedule.getTypeOfClass_cb().getSelectedItem().toString());
            schedule.setFlight(objCreateSchedule.getRout_tf().getText());

            Date date = new Date();

            try {
                objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.gray));

                rout.setHoursOfFlight(Integer.parseInt(objCreateSchedule.getHours_tf().getText()));
                rout.setMinutesOfFlight(Integer.parseInt(objCreateSchedule.getMinutes_tf().getText()));
                rout.setCost(Integer.parseInt(objCreateSchedule.getCost_tf().getText()));
                date.setDay(Integer.parseInt(objCreateSchedule.getDay_tf().getText()));
                date.setMonth(Integer.parseInt(objCreateSchedule.getMonth_tf().getText()));
                date.setYear(Integer.parseInt(objCreateSchedule.getYear_tf().getText()));
                date.setHours(Integer.parseInt(objCreateSchedule.getHoursS_tf().getText()));
                date.setMinutes(Integer.parseInt(objCreateSchedule.getMinutesS_tf().getText()));

                int coutError = 0;
                if (rout.getHoursOfFlight() < 0 || rout.getHoursOfFlight() > 24) {
                    objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 24!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (rout.getMinutesOfFlight() < 0 || rout.getMinutesOfFlight() > 60) {
                    objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 60!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (rout.getCost() < 0) {
                    objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно Быть положительным числом", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getMonth() < 1 || date.getMonth() > 12) {
                    objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 12", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getYear() < 2015 || date.getYear() > 2016) {
                    objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 2015 до 2016", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 29 && date.getMonth() == 2 &&  date.getYear() == 2016) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 29", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 30 && (date.getMonth() == 3 || date.getMonth() == 4 || date.getMonth() == 6 || date.getMonth() == 9 || date.getMonth() == 11)) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 30", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getDay() < 1 || date.getDay() > 31 && (date.getMonth() == 1 || date.getMonth() == 5 || date.getMonth() == 7 || date.getMonth() == 8 || date.getMonth() == 10 || date.getMonth() == 12)) {
                    objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 1 до 31", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getYear() == 2015 && date.getMonth() != 12) {
                    objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Создать расписание можно начинаяя с 1 декабря 2015 года", "Ошибка создания расписания", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getHours() < 0 || date.getHours() > 24) {
                    objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 24!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (date.getMinutes() < 0 || date.getMinutes() > 60) {
                    objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objEnterDialog, "Значение поля должно попадать в диапазон от 0 до 60!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    coutError++;
                }
                if (coutError == 0){
                    client.sendMessage("saveSchedule");
                    client.sendObject(rout);
                    client.sendObject(plane);
                    client.sendObject(schedule);
                    client.sendObject(date);

                    try {
                        String answer = client.readMessage();
                        if (answer.equals("Error: flight is already exist"))
                            JOptionPane.showMessageDialog(objEnterDialog, "Такой рейс уже существует!", "Ошибка создания расписание", JOptionPane.ERROR_MESSAGE);
                        if (answer.equals("Error: schedule is already exist"))
                            JOptionPane.showMessageDialog(objEnterDialog, "Такое расписание уже существует!", "Ошибка создания расписание", JOptionPane.ERROR_MESSAGE);
                        if (answer.equals("Error: plane can't make this flight"))
                            JOptionPane.showMessageDialog(objEnterDialog, "Самолет не может осуществить данный рейс!", "Ошибка создания расписание", JOptionPane.ERROR_MESSAGE);
                        if (answer.equals("Schedule successfully saved")) {
                            JOptionPane.showMessageDialog(objEnterDialog, "Расписание успешно создано!", "Создание расписания", JOptionPane.PLAIN_MESSAGE);
                            objCreateSchedule.dispose();
                            repaintSchedule();
                            createFormCB();
                        }
                        }
                    catch(IOException exe){
                        exe.printStackTrace();
                    }
                }
            }
            catch(NumberFormatException ex){
                objCreateSchedule.getHours_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMinutes_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getCost_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getDay_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMonth_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getYear_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getHoursS_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                objCreateSchedule.getMinutesS_tf().setBorder(BorderFactory.createLineBorder(Color.red));

                JOptionPane.showMessageDialog(objEnterDialog, "Поле должно быть целым числом", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("showRegistrationFrame")) {
            objEnterDialog.dispose();
            RegistrationUsers formReg = new RegistrationUsers();
            formReg.setTitle("Регистрация пользователя");
            formReg.pack();
            formReg.setLocationRelativeTo(null);
            formReg.setVisible(true);
        }

        if (e.getActionCommand().equals("backToAutorization")) {
            objRegistrarionUsers.dispose();
        }

        if (e.getActionCommand().equals("registrationUsers")) {
            if (objRegistrarionUsers.getName_tf().getText().equals("") ||
                    objRegistrarionUsers.getSecondName_tf().getText().equals("") ||
                    objRegistrarionUsers.getPatronymic_tf().getText().equals("") ||
                    objRegistrarionUsers.getLogin_tf().getText().equals("") ||
                    objRegistrarionUsers.getPassword_tf().getText().equals("") ||
                    objRegistrarionUsers.getPassportSeries_tf().getText().equals("") ||
                    objRegistrarionUsers.getPassportNumver_tf().getText().equals(""))
                JOptionPane.showMessageDialog(objRegistrarionUsers, "Заполните поля!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
                Users user = new Users();
                user.setName(objRegistrarionUsers.getName_tf().getText());
                user.setSecondName(objRegistrarionUsers.getSecondName_tf().getText());
                user.setPatronymic(objRegistrarionUsers.getPatronymic_tf().getText());
                user.setPassportSeries(objRegistrarionUsers.getPassportSeries_tf().getText());
                objRegistrarionUsers.getPassportNumver_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                int error = 0;
                try {
                    user.setPassportNumber(Integer.parseInt(objRegistrarionUsers.getPassportNumver_tf().getText()));
                }catch(NumberFormatException ex){
                    objRegistrarionUsers.getPassportNumver_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objRegistrarionUsers, "Поле должно быть целым числом", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    error++;
                }
                user.setLogin(objRegistrarionUsers.getLogin_tf().getText());
                user.setPassword(objRegistrarionUsers.getPassword_tf().getText());
                user.setStatus("user");
                if (error == 0){
                    client.sendMessage("registrationUser");
                    client.sendObject(user);
                    String mes = "";
                    try {
                        mes = client.readMessage();
                    }catch(IOException ex){
                        System.out.println("Error in reading");
                    }
                    if (mes.equals("This user is already existed"))
                        JOptionPane.showMessageDialog(objRegistrarionUsers, "Такой пользователь уже существует!", "Ошибка регистрации", JOptionPane.ERROR_MESSAGE);
                    else {
                        JOptionPane.showMessageDialog(objRegistrarionUsers, "Пользователь успешно зарегистрирован", "Регистрация пользователя", JOptionPane.PLAIN_MESSAGE);
                        objRegistrarionUsers.dispose();
                        EnterDialog dialog = new EnterDialog();
                        dialog.setTitle("Авторизация");
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                }
            }
        }

        if (e.getActionCommand().equals("orderTicket")) {
            if (objUsersForm.getSecondName_tf().equals("") || objUsersForm.getName_tf().equals("") ||
                    objUsersForm.getPatronymic_tf().equals("") || objUsersForm.getSeries_tf().equals("") ||
                    objUsersForm.getNumber_tf().equals(""))
                JOptionPane.showMessageDialog(objUsersForm, "Заполните поля!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
                Users user = new Users();
                user.setLogin(objEnterDialog.getTextLogin().getText());
                user.setPassword(objEnterDialog.getPasswordField1().getText());
                Passengers passenger = new Passengers();
                passenger.setName(objUsersForm.getName_tf().getText());
                passenger.setSecondName(objUsersForm.getSecondName_tf().getText());
                passenger.setPatronymic(objUsersForm.getPatronymic_tf().getText());
                passenger.setPassportSeries(objUsersForm.getSeries_tf().getText());
                Schedule schedule = new Schedule();
                schedule.setFlight(objUsersForm.getFlight_cb().getSelectedItem().toString());
                int error = 0;
                try {
                    passenger.setPassportNumber(Integer.parseInt(objUsersForm.getNumber_tf().getText()));
                    objUsersForm.getNumber_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                }
                catch(NumberFormatException ex){
                    objUsersForm.getNumber_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objUsersForm, "Поле должно быть целым числом!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    error++;
                }
                if (error == 0){
                    client.sendMessage("orderTicket");
                    client.sendObject(passenger);
                    client.sendObject(schedule);
                    client.sendObject(user);

                    try {
                        if (client.readMessage().equals("OK")) {
                            JOptionPane.showMessageDialog(objUsersForm, "Билет заказан! Информация о билете находится в личном кабинете", "Заказ билета", JOptionPane.PLAIN_MESSAGE);
                            objUsersForm.dispose();
                            repaintTickets(user);
                        }
                    }
                    catch(IOException ex){
                        System.out.println("Error in reading");
                    }
                }
            }
        }

        if (e.getActionCommand().equals("orderTicketCasher")) {
            if (objFormCasher.getSecondName_tf().equals("") || objFormCasher.getName_tf().equals("") ||
                    objFormCasher.getPatronymic_tf().equals("") || objFormCasher.getSeries_tf().equals("") ||
                    objFormCasher.getNumber_tf().equals(""))
                JOptionPane.showMessageDialog(objFormCasher, "Заполните поля!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
                Passengers passenger = new Passengers();
                passenger.setName(objFormCasher.getName_tf().getText());
                passenger.setSecondName(objFormCasher.getSecondName_tf().getText());
                passenger.setPatronymic(objFormCasher.getPatronymic_tf().getText());
                passenger.setPassportSeries(objFormCasher.getSeries_tf().getText());
                Schedule schedule = new Schedule();
                schedule.setFlight(objFormCasher.getFlight_cb().getSelectedItem().toString());
                int error = 0;
                try {
                    passenger.setPassportNumber(Integer.parseInt(objFormCasher.getNumber_tf().getText()));
                    objFormCasher.getNumber_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                }
                catch(NumberFormatException ex){
                    objFormCasher.getNumber_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(objFormCasher, "Поле должно быть целым числом!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    error++;
                }
                if (error == 0){
                    client.sendMessage("orderTicketCasher");
                    client.sendObject(passenger);
                    client.sendObject(schedule);

                    try {
                        if (client.readMessage().equals("OK")) {
                            JOptionPane.showMessageDialog(objFormCasher, "Билет заказан!", "Заказ билета", JOptionPane.PLAIN_MESSAGE);
                            objFormCasher.dispose();
                            autorization();
                        }
                    }
                    catch(IOException ex){
                        System.out.println("Error in reading");
                    }
                }
            }

        }

        if (e.getActionCommand().equals("exitUser")) {
            client.sendMessage("exit");
            try {
                if (client.readMessage().equals("OK")) {
                    client.close();
                    objUsersForm.dispose();
                    System.exit(0);
                }
            }
            catch(IOException ex){
                System.out.println("Error in reading");
            }
        }

        if (e.getActionCommand().equals("searchTicket")) {
            if (objFormCasher.getNumTic_tf().equals(""))
                JOptionPane.showMessageDialog(objFormCasher, "Заполните поля!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
                Ticket ticket = new Ticket();
                int error = 0;
                try {
                    ticket.setNumberOfTicket(Integer.parseInt(objFormCasher.getNumTic_tf().getText()));
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(objFormCasher, "Поле должно быть целым числом!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    error++;
                }
                if (error == 0) {
                    client.sendMessage("getInfTicketByNum");
                    client.sendObject(ticket);
                    String mes = "";
                    try{
                        mes = client.readMessage();
                    }
                    catch(IOException ew){
                        System.out.println("Error in reading");
                    }
                    if (mes.equals("OK")) {
                        String[][] data = (String[][]) client.readObject();
                        String[] columnNamesTicket = {
                                "Рейс",
                                "Номер билета",
                                "Откуда",
                                "Куда",
                                "Время",
                                "Дата",
                                "Пассажир",
                                "Класс",
                                "Стоимость"
                        };

                        TableModel tableModel = new DefaultTableModel(data, columnNamesTicket);
                        JTable tableTicket = objFormCasher.getTable();
                        tableTicket.setModel(tableModel);
                        objFormCasher.getButtonReturnTicket().setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(objFormCasher, "Билета не найдено!", "Ошибка поиска", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        if (e.getActionCommand().equals("returnTicket")) {
            Ticket ticket = new Ticket();
            ticket.setNumberOfTicket(Integer.parseInt(objFormCasher.getNumTic_tf().getText()));
            client.sendMessage("returnTicket");
            client.sendObject(ticket);
            JOptionPane.showMessageDialog(objFormCasher, "Оформление возврата выполнено успешно!", "Возврат билетa", JOptionPane.PLAIN_MESSAGE);
        }

        if (e.getActionCommand().equals("backFromAddPlane")) {
            objFormAddPlane.dispose();
        }

        if (e.getActionCommand().equals("deletePlane")) {
            Plane plane = new Plane();
            plane.setBoardNumber(objForm.getBoardNumber_cb().getSelectedItem().toString());
            client.sendMessage("deletePlane");
            client.sendObject(plane);
            String mes = "";
            try{
                mes = client.readMessage();
            }
            catch(IOException ew){
                System.out.println("Error in reading");
            }
            if (mes.equals("OK")) {
                JOptionPane.showMessageDialog(objFormCasher, "Запись успешно удалена!", "Удаление записи", JOptionPane.PLAIN_MESSAGE);
                repaintPlane();
                objForm.getBoardNumber_cb().removeAllItems();
                client.sendMessage("createSchedule");
                ArrayList<Plane> list = (ArrayList<Plane>) client.readObject();
                for (Plane plane1 : list)
                    objForm.getBoardNumber_cb().addItem(plane1.getBoardNumber());
            }
            else
                JOptionPane.showMessageDialog(objForm, "Данный самолет учавствует в расписании!", "Ошибка удаления", JOptionPane.ERROR_MESSAGE);
        }

        if (e.getActionCommand().equals("addPlaneShowPanel")) {
            FormAddPlane form = new FormAddPlane();
            form.setTitle("Добавление записи");
            form.pack();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        }

        if (e.getActionCommand().equals("savePlane")) {
           if(objFormAddPlane.getModel_tf().getText().equals("") ||objFormAddPlane.getBoardNumber_tf().getText().equals("") ||
                   objFormAddPlane.getYearOfMade_tf().getText().equals("") || objFormAddPlane.getCountOFSeats_tf().getText().equals(""))
               JOptionPane.showMessageDialog(objFormAddPlane, "Заполните поле!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
               Plane plane = new Plane();
               plane.setModel(objFormAddPlane.getModel_tf().getText());
               plane.setBoardNumber(objFormAddPlane.getBoardNumber_tf().getText());

               int error = 0;
               try{
                   plane.setCountOfSeats(Integer.parseInt(objFormAddPlane.getCountOFSeats_tf().getText()));
                   objFormAddPlane.getCountOFSeats_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                   if (plane.getCountOfSeats() < 10 || plane.getCountOfSeats() > 200) {
                       JOptionPane.showMessageDialog(objFormAddPlane, "Значение должно попадать в диапазон от 10 до 200!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                       objFormAddPlane.getCountOFSeats_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                       error++;
                   }
               }
               catch(NumberFormatException ex){
                   JOptionPane.showMessageDialog(objFormAddPlane, "Значение должно быть целым числом!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                   objFormAddPlane.getCountOFSeats_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                   error++;
               }

               try{
                   plane.setYearOfMade(Integer.parseInt(objFormAddPlane.getYearOfMade_tf().getText()));
                   objFormAddPlane.getYearOfMade_tf().setBorder(BorderFactory.createLineBorder(Color.gray));
                   if (plane.getYearOfMade() < 1980 || plane.getYearOfMade() > 2015) {
                       JOptionPane.showMessageDialog(objFormAddPlane, "Значение должно попадать в диапазон от 1980 до 2015!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                       objFormAddPlane.getYearOfMade_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                       error++;
                   }
               }
               catch(NumberFormatException ex){
                   JOptionPane.showMessageDialog(objFormAddPlane, "Значение должно быть целым числом!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                   objFormAddPlane.getYearOfMade_tf().setBorder(BorderFactory.createLineBorder(Color.red));
                   error++;
               }

               if (error == 0){
                   client.sendMessage("addPlane");
                   client.sendObject(plane);
                   String str = "";
                   try{
                       str = client.readMessage();
                   }
                   catch (IOException ex){
                       System.out.println("Error in reading");
                   }

                   if (str.equals("OK")) {
                       JOptionPane.showMessageDialog(objFormAddPlane, "Запись успешно добавлена!", "Добавление записи", JOptionPane.PLAIN_MESSAGE);
                       objFormAddPlane.dispose();
                       repaintPlane();
                       objForm.getBoardNumber_cb().removeAllItems();
                       client.sendMessage("createSchedule");
                       ArrayList<Plane> list = (ArrayList<Plane>) client.readObject();
                       for (Plane plane1 : list)
                           objForm.getBoardNumber_cb().addItem(plane1.getBoardNumber());
                   }
                   else
                       JOptionPane.showMessageDialog(objFormAddPlane, "Самолет с таким бортовым номером уже существует!", "Ошибка добавления", JOptionPane.ERROR_MESSAGE);
               }
           }
        }

        if (e.getActionCommand().equals("addFutureFlight")) {
            if (objFormCasher.getNewFlight_tf().getText().equals(""))
                JOptionPane.showMessageDialog(objFormAddPlane, "Заполните поле!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            else{
                String futureFlight = objFormCasher.getNewFlight_tf().getText();
                client.sendMessage("addFutureFlight");
                client.sendObject(futureFlight);
                JOptionPane.showMessageDialog(objFormAddPlane, "Запись успешно добавлена!", "Добавление записи", JOptionPane.PLAIN_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("doText")) {
            String flight = objForm.getFlight_cb().getSelectedItem().toString();
            client.sendMessage("doText");
            client.sendObject(flight);
        }

        if (e.getActionCommand().equals("exit")) {
            System.out.println("SSSSSSSSSSSSSSSSSSS");
            client.sendMessage("exit");
            objForm.dispose();
        }
    }

    public void autorization(){
        try {
            String msgLogin = objEnterDialog.getTextLogin().getText();
            String msgPassword = objEnterDialog.getPasswordField1().getText();
            if (msgLogin.equals("") || msgPassword.equals(""))
                JOptionPane.showMessageDialog(objEnterDialog,
                        "Заполните все поля!",
                        "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);

            Users user = new Users();
            user.setLogin(msgLogin);
            user.setPassword(msgPassword);

            client.sendMessage("enter");
            client.sendObject(user);

            String servMsg = client.readMessage();
            switch(servMsg){
                case "error":{
                    JOptionPane.showMessageDialog(objEnterDialog,
                            "Такой пользователь не зарегистрирован",
                            "Ошибка авторизации",
                            JOptionPane.ERROR_MESSAGE);
                }break;
                case "errorInput":{
                    JOptionPane.showMessageDialog(objEnterDialog,
                            "Проверьте введенные данные",
                            "Ошибка ввода",
                            JOptionPane.ERROR_MESSAGE);
                }break;
                case "ok":{
                    String status = client.readMessage();
                    if (status.equals("admin")) {
                        objEnterDialog.setVisible(false);
                        Form form = new Form();
                        repaintSchedule();
                        repaintPassenger();
                        repaintPlane();
                        createFormCB();
                        client.sendMessage("grafic");
                        String[] keys = (String[])client.readObject();
                        Integer[] values = (Integer[])client.readObject();

                        objForm.getGraficPane().add(PieChart.createDemoPanel(keys, values));

                        client.sendMessage("createSchedule");
                        ArrayList<Plane> list = (ArrayList<Plane>) client.readObject();
                        for (Plane plane1 : list)
                            form.getBoardNumber_cb().addItem(plane1.getBoardNumber());

                        client.sendMessage("getFlights");
                        ArrayList<Schedule> listSchedule = (ArrayList<Schedule>)client.readObject();
                        for (Schedule schedule: listSchedule){
                            form.getFlight_cb().addItem(schedule.getFlight());
                        }

                        form.setTitle("Меню администратора");
                        form.pack();
                        form.setLocationRelativeTo(null);
                        form.setVisible(true);
                        JMenuBar menu = new JMenuBar();
                        JMenu item = new JMenu("Выход");
                        item.setActionCommand("exit");
                        item.addActionListener(Controller.getInstance());
                        menu.add(item);
                        form.setJMenuBar(menu);
                    }
                    if (status.equals("casher")) {
                        objEnterDialog.setVisible(false);
                        FormCasher formCasher = new FormCasher();
                        repaintScheduleCasher();
                        createCasherFormCB();

                        client.sendMessage("getFlights");
                        ArrayList<Schedule> listSchedule = (ArrayList<Schedule>)client.readObject();

                        for (Schedule schedule: listSchedule){
                            formCasher.getFlight_cb().addItem(schedule.getFlight());
                        }
                        client.sendMessage("getNumberOfTicket");
                        int numberOfTicket = (int)client.readObject();
                        formCasher.getNumberTicket_tf().setText(numberOfTicket + "");
                        formCasher.getNumberTicket_tf().setEnabled(false);

                        formCasher.setTitle("Меню кассира");
                        formCasher.pack();
                        formCasher.setLocationRelativeTo(null);
                        formCasher.setVisible(true);
                        JMenuBar menu = new JMenuBar();
                        JMenu item = new JMenu("Выход");
                        menu.add(item);
                        formCasher.setJMenuBar(menu);
                    }
                    if (status.equals("user")){
                        objEnterDialog.setVisible(false);

                        UsersForm usersForm = new UsersForm();
                        repaintScheduleUser();
                        createUserFormCB();
                        repaintTickets(user);
                        client.sendMessage("getFlights");
                        ArrayList<Schedule> listSchedule = (ArrayList<Schedule>)client.readObject();

                        for (Schedule schedule: listSchedule){
                            usersForm.getFlight_cb().addItem(schedule.getFlight());
                        }

                        client.sendMessage("getInfAboutPassenger");
                        client.sendObject(user);

                        Users infUser = (Users)client.readObject();
                        usersForm.getName_tf().setText(infUser.getName());
                        usersForm.getSecondName_tf().setText(infUser.getSecondName());
                        usersForm.getPatronymic_tf().setText(infUser.getPatronymic());
                        usersForm.getSeries_tf().setText(infUser.getPassportSeries());
                        usersForm.getNumber_tf().setText(infUser.getPassportNumber() + "");

                        usersForm.setTitle("Меню пользователя");
                        usersForm.pack();
                        usersForm.setLocationRelativeTo(null);
                        usersForm.setVisible(true);
                    }
                }break;
            }
        }catch(IOException error){
            error.printStackTrace();
        }
    }

    public void repaintSchedule(){
        client.sendMessage("createAdminMenu");
        String[][] data = (String[][])client.readObject();
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

        TableModel tableModel = new DefaultTableModel(data, columnNames);
        objForm.getTableSchedule().setModel(tableModel);
    }

    public void repaintScheduleCasher(){
        client.sendMessage("createAdminMenu");
        String[][] data = (String[][])client.readObject();
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

        TableModel tableModel = new DefaultTableModel(data, columnNames);
        objFormCasher.getTableSchedule().setModel(tableModel);
    }

    public void repaintScheduleUser(){
        client.sendMessage("createAdminMenu");
        String[][] data = (String[][])client.readObject();
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

        TableModel tableModel = new DefaultTableModel(data, columnNames);
        objUsersForm.getTableSchedule().setModel(tableModel);
    }

    public void repaintPassenger(){
        client.sendMessage("createListPassenger");
        String[][] data = (String[][])client.readObject();
        String[] columnNames = {
                "Фамилия",
                "Имя",
                "Отчество",
                "Откуда",
                "Куда",
                "Дата"
        };
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        objForm.getTablePassenger().setModel(tableModel);
    }

    public void repaintTickets(Users user){
        client.sendMessage("getListTicket");
        client.sendObject(user);
        String[][] dataTicket = (String[][])client.readObject();
        String[] columnNamesTicket = {
                "Рейс",
                "Номер билета",
                "Откуда",
                "Куда",
                "Время",
                "Дата",
                "Пассажир",
                "Класс",
                "Стоимость"
        };
        TableModel tableModel = new DefaultTableModel(dataTicket, columnNamesTicket);
        objUsersForm.getTableTicket().setModel(tableModel);
    }

    public void repaintPlane(){
        client.sendMessage("createListPlane");
        String[][] data = (String[][])client.readObject();
        String[] columnNames = {
                "Модель",
                "Бортовой номер",
                "Год выпуска",
                "Количество мест",
        };
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        objForm.getTablePlane().setModel(tableModel);
    }

    public void createFormCB(){
        client.sendMessage("createFormCB");
        String[] from = (String[])client.readObject();
        String[] to = (String[])client.readObject();

        objForm.getFrom_cb().removeAllItems();
        System.out.println(from.length);
        for (int i = 0; i < from.length; i++){
//            if (!from[i].equals(""))
                objForm.getFrom_cb().addItem(from[i]);
        }

        objForm.getTo_cb().removeAllItems();
        for (int i = 0; i < to.length; i++){
           // if (!to[i].equals(""))
            objForm.getTo_cb().addItem(to[i]);
        }
    }

    public void createCasherFormCB(){
        client.sendMessage("createFormCB");
        String[] from = (String[])client.readObject();
        String[] to = (String[])client.readObject();

        objFormCasher.getFrom_cb().removeAllItems();
        for (int i = 0; i < from.length; i++){
            objFormCasher.getFrom_cb().addItem(from[i]);
        }

        objFormCasher.getTo_cb().removeAllItems();
        for (int i = 0; i < to.length; i++){
            objFormCasher.getTo_cb().addItem(to[i]);
        }
    }

    public void createUserFormCB(){
        client.sendMessage("createFormCB");
        String[] from = (String[])client.readObject();
        String[] to = (String[])client.readObject();

        objUsersForm.getFrom_cb().removeAllItems();
        for (int i = 0; i < from.length; i++){
            objUsersForm.getFrom_cb().addItem(from[i]);
        }

        objUsersForm.getTo_cb().removeAllItems();
        for (int i = 0; i < to.length; i++){
            objUsersForm.getTo_cb().addItem(to[i]);
        }
    }
}

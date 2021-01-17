package server;

import database.SQLFactory;
import database.SQLTicket;
import database.SQLUsers;
import model.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Worker implements Runnable {
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true){
                String choice = sois.readObject().toString();
                switch(choice){
                    case "enter":{
                        Users user = (Users)sois.readObject();

                        SQLFactory sqlFactory = new SQLFactory();
                        String status = sqlFactory.getUsers().findUser(user);
                        if (status == "")
                            soos.writeObject("error");
                        else {
                            soos.writeObject("ok");
                            soos.writeObject(status);
                        }
                    }break;
                    case "createSchedule":{
                        System.out.println("Запрос к БД (таблица Plane), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Plane> list = sqlFactory.getPlane().selectBoardNumber();
                        soos.writeObject(list);
                    }break;
                    case "saveSchedule":{
                        System.out.println("Запрос к БД на сохранение данных (таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        Rout rout = (Rout)sois.readObject();
                        Plane plane = (Plane)sois.readObject();
                        Schedule schedule = (Schedule)sois.readObject();
                        Date date = (Date)sois.readObject();

                        SQLFactory sqlFactory = new SQLFactory();
                        if(sqlFactory.getSchedule().isFind(schedule))
                            soos.writeObject("Error: flight is already exist");
                        else{
                            ArrayList<Schedule> listSchedule = new ArrayList<>();
                            listSchedule = sqlFactory.getSchedule().selectAllSchedule();

                            int checkExist = 0;
                            for (Schedule item: listSchedule){
                                if (sqlFactory.getRout().isFind(rout, item.getIdRout()) && sqlFactory.getDate().isFind(date, item.getIdDate())) {
                                    soos.writeObject("Error: schedule is already exist");
                                    checkExist++;
                                    break;
                                }
                            }
                            if (checkExist == 0){
                                for (Schedule item: listSchedule){
                                    if (sqlFactory.getPlane().isFind(plane, item.getIdPlane()) && sqlFactory.getDate().isFind(date, item.getIdDate())) {
                                        soos.writeObject("Error: plane can't make this flight");
                                        checkExist++;
                                        break;
                                    }
                                }
                                if (checkExist == 0) {
                                    sqlFactory.getRout().insert(rout);
                                    int idRout = sqlFactory.getRout().selectId(rout);
                                    int idPlane = sqlFactory.getPlane().selectId(plane);
                                    int idIndexOfPrice = sqlFactory.getIndexOfPrice().selectIndexOfPrice(date).getIdIndexOfPrice();
                                    sqlFactory.getDate().insert(date);
                                    int idDate = sqlFactory.getDate().selectId(date);
                                    sqlFactory.getSchedule().insert(schedule, idPlane, idRout, idIndexOfPrice, idDate);
                                    ArrayList<Schedule> listNewSchedule = sqlFactory.getSchedule().selectAllSchedule();
                                    Schedule scheduleNew = listNewSchedule.get(listNewSchedule.size() - 1);
                                    int count = sqlFactory.getPlane().selectPlane(idPlane).getCountOfSeats();

                                    String line = "";
                                    try(BufferedReader fileReader = new BufferedReader(new FileReader("E:/indexPriceTypeOfClass.txt"))){
                                        line = fileReader.readLine();
                                    }catch(FileNotFoundException error){
                                        System.out.println("FileNotFoundException error: ReadFile error");
                                    }
                                    catch(IOException error){
                                        System.out.println("IOException error: ReadFile error");
                                    }
                                    String[] lineArray = line.split(" ");

                                    double indexClass = 0.0;
                                    if (scheduleNew.getTypeClass().equals(lineArray[0]))
                                        indexClass = Double.parseDouble(lineArray[1]);
                                    if (scheduleNew.getTypeClass().equals(lineArray[2]))
                                        indexClass = Double.parseDouble(lineArray[3]);
                                    if (scheduleNew.getTypeClass().equals(lineArray[4]))
                                        indexClass = Double.parseDouble(lineArray[5]);

                                    double cost = rout.getCost() + rout.getCost() * sqlFactory.getIndexOfPrice().selectIndexOfPrice(date).getIndexOfPrice() +
                                            rout.getCost() * indexClass;
                                    sqlFactory.getTicketsInSale().insert(scheduleNew.getIdSchedule(), count, count, cost);
                                    soos.writeObject("Schedule successfully saved");
                                }
                            }
                        }
                    }break;
                    case "createAdminMenu":{
                        System.out.println("Запрос к БД (таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Schedule> listSchedule = sqlFactory.getSchedule().selectAllSchedule();
                        String[][] data = new String[listSchedule.size()][11];
                        int count = 0;
                        for (Schedule schedule: listSchedule) {
                            Rout rout = sqlFactory.getRout().selectRout(schedule.getIdRout());
                            Date date = sqlFactory.getDate().selectDate(schedule.getIdDate());
                            Plane plane = sqlFactory.getPlane().selectPlane(schedule.getIdPlane());
                            TicketsInSale ticketsInSale = sqlFactory.getTicketsInSale().selectTicketsInSale(schedule.getIdSchedule());

                            data[count][0] = schedule.getFlight();
                            data[count][1] = rout.getStartPoint();
                            data[count][2] = rout.getFinalPoint();
                            data[count][3] = plane.getBoardNumber();
                            data[count][4] = date.getHours() + ":" + date.getMinutes();
                            int hours = date.getHours() + rout.getHoursOfFlight();
                            int minutes = date.getMinutes() + rout.getMinutesOfFlight();
                            if (minutes >= 60){
                                hours += 1;
                                minutes -= 60;
                            }
                            data[count][5] = hours + ":" + minutes;
                            data[count][6] = rout.getHoursOfFlight() + ":" + rout.getMinutesOfFlight();
                            data[count][7] = date.getDay() + "." + date.getMonth() + "." + date.getYear();
                            data[count][8] = "" + ticketsInSale.getCurrentCount();
                            data[count][9] = schedule.getTypeClass();
                            data[count][10] = "" + ticketsInSale.getCost();

                            count++;
                        }
                        soos.writeObject(data);
                    }break;
                    case "showSchedule":{
                        System.out.println("Запрос к БД на просмотр расписания (таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        Rout rout = (Rout)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> listSchedule = sqlFactory.getSchedule().selectAllSchedule();
                        String[][] data = new String[listSchedule.size()][11];
                        int count = 0;
                        int checkFind = 0;
                        for (Schedule schedule: listSchedule) {
                            if (sqlFactory.getRout().isFind(rout, schedule.getIdRout())){
                                Rout allRout = sqlFactory.getRout().selectRout(schedule.getIdRout());
                                Date date = sqlFactory.getDate().selectDate(schedule.getIdDate());
                                Plane plane = sqlFactory.getPlane().selectPlane(schedule.getIdPlane());
                                TicketsInSale ticketsInSale = sqlFactory.getTicketsInSale().selectTicketsInSale(schedule.getIdSchedule());

                                data[count][0] = schedule.getFlight();
                                data[count][1] = allRout.getStartPoint();
                                data[count][2] = allRout.getFinalPoint();
                                data[count][3] = plane.getBoardNumber();
                                data[count][4] = date.getHours() + ":" + date.getMinutes();
                                int hours = date.getHours() + allRout.getHoursOfFlight();
                                int minutes = date.getMinutes() + allRout.getMinutesOfFlight();
                                if (minutes >= 60){
                                    hours += 1;
                                    minutes -= 60;
                                }
                                data[count][5] = hours + ":" + minutes;
                                data[count][6] = allRout.getHoursOfFlight() + ":" + allRout.getMinutesOfFlight();
                                data[count][7] = date.getDay() + "." + date.getMonth() + "." + date.getYear();
                                data[count][8] = "" + ticketsInSale.getCurrentCount();
                                data[count][9] = schedule.getTypeClass();
                                data[count][10] = "" + ticketsInSale.getCost();

                                count++;
                                checkFind++;
                            }
                        }
                        if (checkFind == 0)
                            soos.writeObject("This schedule doesn't exist");
                        else {
                            soos.writeObject("OK");
                            soos.writeObject(data);
                        }
                    }break;
                    case "editSchedule":{
                        System.out.println("Запрос к БД на изменение расписания(таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        Schedule schedule = (Schedule)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
//                        if (sqlFactory.getSchedule().isFind(schedule)) {
//                            soos.writeObject("OK");
                            Schedule newSchedule = sqlFactory.getSchedule().selectSchedule(schedule.getFlight());
                            if (!sqlFactory.getTicketsInSale().checkSale(newSchedule.getIdSchedule())) {
                                soos.writeObject("OK");
                                Rout rout = sqlFactory.getRout().selectRout(newSchedule.getIdRout());
                                Plane plane = sqlFactory.getPlane().selectPlane(newSchedule.getIdPlane());
                                Date date = sqlFactory.getDate().selectDate(newSchedule.getIdDate());
                                soos.writeObject(newSchedule);
                                soos.writeObject(rout);
                                soos.writeObject(plane);
                                soos.writeObject(date);
                            }
                            else
                                soos.writeObject("NO");
//                        }
//                        else
//                            soos.writeObject("This schedule doesn't exist");
                    }break;
                    case "saveEdit":{
                        System.out.println("Запрос к БД на сохранение изменений(таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        Rout rout = (Rout)sois.readObject();
                        Plane plane = (Plane)sois.readObject();
                        Schedule schedule = (Schedule)sois.readObject();
                        Date date = (Date)sois.readObject();

                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> listSchedule = new ArrayList<>();
                        listSchedule = sqlFactory.getSchedule().selectAllSchedule();

                        int checkExist = 0;
                        for (Schedule item: listSchedule){
                            if (!schedule.getFlight().equals(item.getFlight())) {
                                if (sqlFactory.getRout().isFind(rout, item.getIdRout()) && sqlFactory.getDate().isFind(date, item.getIdDate())) {
                                    soos.writeObject("Error: schedule is already exist");
                                    checkExist++;
                                    break;
                                }
                            }
                        }
                        if (checkExist == 0){
                            for (Schedule item: listSchedule){
                                if (!schedule.getFlight().equals(item.getFlight())) {
                                    if (sqlFactory.getPlane().isFind(plane, item.getIdPlane()) && sqlFactory.getDate().isFind(date, item.getIdDate())) {
                                        soos.writeObject("Error: plane can't make this flight");
                                        checkExist++;
                                        break;
                                    }
                                }
                            }
                            if (checkExist == 0) {
                                Schedule newSchedule = sqlFactory.getSchedule().selectSchedule(schedule.getFlight());
                                sqlFactory.getDate().update(date, newSchedule.getIdDate());
                                sqlFactory.getRout().update(rout, newSchedule.getIdRout());
                                int idPlane = sqlFactory.getPlane().selectId(plane);
                                int idPriceIndex = sqlFactory.getIndexOfPrice().selectIndexOfPrice(date).getIdIndexOfPrice();
                                sqlFactory.getSchedule().update(schedule, idPlane, idPriceIndex);
                                int count = sqlFactory.getPlane().selectPlane(idPlane).getCountOfSeats();
                                double cost = rout.getCost() + rout.getCost() * sqlFactory.getIndexOfPrice().selectIndexOfPrice(date).getIndexOfPrice();
                                sqlFactory.getTicketsInSale().update(newSchedule.getIdSchedule(), count, count, cost);
                                soos.writeObject("Schedule successfully edited");
                            }
                        }
                    }break;
                    case "deleteSchedule":{
                        System.out.println("Запрос к БД на удаление(таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        Schedule schedule = (Schedule)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        if (sqlFactory.getSchedule().isFind(schedule)) {
                            soos.writeObject("OK");
                            Schedule newSchedule = sqlFactory.getSchedule().selectSchedule(schedule.getFlight());
                            if (!sqlFactory.getTicketsInSale().checkSale(newSchedule.getIdSchedule())) {
                                sqlFactory.getTicketsInSale().delete(newSchedule.getIdSchedule());
                                sqlFactory.getSchedule().delete(newSchedule.getIdSchedule());
                                sqlFactory.getRout().delete(newSchedule.getIdRout());
                                sqlFactory.getDate().delete(newSchedule.getIdDate());
                                soos.writeObject("OK");
                            }
                            else
                                soos.writeObject("NO");
                        }
                        else
                            soos.writeObject("This schedule doesn't exist");
                    }break;
                    case "registrationUser":{
                        System.out.println("Запрос к БД на проверку пользователя(таблица User), клиент: " + clientSocket.getInetAddress().toString());
                        Users user = (Users)sois.readObject();

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getUsers().findUser(user).equals("")) {
                            soos.writeObject("OK");
                            sqlFactory.getUsers().insert(user);
                        }
                        else{
                            soos.writeObject("This user is already existed");
                        }
                    }break;
                    case "getFlights":{
                        System.out.println("Запрос к БД на получение списка рейсов(таблица Schedule), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> listSchedule = sqlFactory.getSchedule().selectAllSchedule();
                        soos.writeObject(listSchedule);
                    }break;
                    case "getInfAboutPassenger":{
                        System.out.println("Запрос к БД на получение информации о пассажирах(таблица Passenger), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Users user = (Users)sois.readObject();
                        Users newUser = sqlFactory.getUsers().selectUsers(user);
                        soos.writeObject(newUser);
                    }break;
                    case "orderTicket":{
                        System.out.println("Запрос к БД (таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Passengers passenger = (Passengers)sois.readObject();
                        Schedule schedule = (Schedule)sois.readObject();
                        Users user = (Users)sois.readObject();
                        int idPassenger = sqlFactory.getPassengers().findId(passenger);

                        if (idPassenger == 0) {
                            sqlFactory.getPassengers().insert(passenger);
                            idPassenger = sqlFactory.getPassengers().findId(passenger);
                        }

                        int idUser = sqlFactory.getUsers().selectUsers(user).getIdUser();
                        Schedule newSchedule = sqlFactory.getSchedule().selectSchedule(schedule.getFlight());
                        int idSchedule = newSchedule.getIdSchedule();
                        int idSale = sqlFactory.getTicketsInSale().selectTicketsInSale(idSchedule).getIdTicketInSale();
                        int currentCount = sqlFactory.getTicketsInSale().selectTicketsInSale(idSchedule).getCurrentCount() - 1;
                        sqlFactory.getTicketsInSale().update(idSchedule, currentCount);
                        int numberOfTicket = 1246583952;
                        Ticket ticket;

                        if (!sqlFactory.getTicket().isEmpty()) {
                            ticket = sqlFactory.getTicket().last();
                            numberOfTicket = ticket.getNumberOfTicket() + 1;
                        }

                        Ticket newTicket = new Ticket();
                        newTicket.setNumberOfTicket(numberOfTicket);
                        newTicket.setIdUser(idUser);
                        newTicket.setIdPassenger(idPassenger);
                        newTicket.setIdSale(idSale);
                        sqlFactory.getTicket().insert(newTicket, true);
                        soos.writeObject("OK");
                    }break;
                    case "orderTicketCasher":{
                        System.out.println("Запрос к БД (таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Passengers passenger = (Passengers)sois.readObject();
                        Schedule schedule = (Schedule)sois.readObject();
                        int idPassenger = sqlFactory.getPassengers().findId(passenger);

                        if (idPassenger == 0) {
                            sqlFactory.getPassengers().insert(passenger);
                            idPassenger = sqlFactory.getPassengers().findId(passenger);
                        }

                        Schedule newSchedule = sqlFactory.getSchedule().selectSchedule(schedule.getFlight());
                        int idSchedule = newSchedule.getIdSchedule();
                        int idSale = sqlFactory.getTicketsInSale().selectTicketsInSale(idSchedule).getIdTicketInSale();
                        int currentCount = sqlFactory.getTicketsInSale().selectTicketsInSale(idSchedule).getCurrentCount() - 1;
                        sqlFactory.getTicketsInSale().update(idSchedule, currentCount);
                        int numberOfTicket = 1246583952;
                        Ticket ticket;

                        if (!sqlFactory.getTicket().isEmpty()) {
                            ticket = sqlFactory.getTicket().last();
                            numberOfTicket = ticket.getNumberOfTicket() + 1;
                        }

                        Ticket newTicket = new Ticket();
                        newTicket.setNumberOfTicket(numberOfTicket);
                        newTicket.setIdPassenger(idPassenger);
                        newTicket.setIdSale(idSale);
                        sqlFactory.getTicket().insert(newTicket, false);
                        soos.writeObject("OK");
                    }break;
                    case "getListTicket":{
                        System.out.println("Запрос к БД на получение информации о заказанных билета(таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Users user = (Users)sois.readObject();
                        int idUser = sqlFactory.getUsers().selectUsers(user).getIdUser();
                        ArrayList<Ticket> listTicket = sqlFactory.getTicket().selectTicket(idUser);
                        String[][] dataTicket = new String[listTicket.size()][9];
                        int count = 0;

                        for (Ticket ticket: listTicket){
                            dataTicket[count][1] = ticket.getNumberOfTicket() + "";
                            Passengers passenger = sqlFactory.getPassengers().selectPassenger(ticket.getIdPassenger());
                            dataTicket[count][6] =  passenger.getSecondName() + " " + passenger.getName() + " " + passenger.getPatronymic();

                            TicketsInSale ticketsInSale = sqlFactory.getTicketsInSale().selectTicketsInSaleId(ticket.getIdSale());
                            dataTicket[count][8] = ticketsInSale.getCost() + "";

                            Schedule schedule = sqlFactory.getSchedule().selectScheduleId(ticketsInSale.getIdSchedule());
                            dataTicket[count][0] = schedule.getFlight();
                            dataTicket[count][7] = schedule.getTypeClass();

                            Rout rout = sqlFactory.getRout().selectRout(schedule.getIdRout());
                            dataTicket[count][2] = rout.getStartPoint();
                            dataTicket[count][3] = rout.getFinalPoint();

                            Date date = sqlFactory.getDate().selectDate(schedule.getIdDate());
                            dataTicket[count][4] = date.getHours() + ":" + date.getMinutes();
                            dataTicket[count][5] = date.getDay() + "." + date.getMonth() + "." + date.getYear();
                            count++;
                        }

                        soos.writeObject(dataTicket);
                    }break;
                    case "getNumberOfTicket":{
                        System.out.println("Запрос к БД на поиск билета(таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        int numberOfTicket = 1246583952;
                        if (!sqlFactory.getTicket().isEmpty()) {
                            Ticket ticket = sqlFactory.getTicket().last();
                            numberOfTicket = ticket.getNumberOfTicket() + 1;
                        }
                        soos.writeObject(numberOfTicket);

                    }break;
                    case "getInfTicketByNum":{
                        System.out.println("Запрос к БД (таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        Ticket ticket = (Ticket)sois.readObject();

                        SQLFactory sqlFactory = new SQLFactory();
                        Ticket newTicket = sqlFactory.getTicket().find(ticket.getNumberOfTicket());
                        if (newTicket.getNumberOfTicket() != 0) {
                            soos.writeObject("OK");
                            String[][] dataTicket = new String[1][9];
                            int count = 0;

                            dataTicket[count][1] = newTicket.getNumberOfTicket() + "";
                            Passengers passenger = sqlFactory.getPassengers().selectPassenger(newTicket.getIdPassenger());
                            dataTicket[count][6] = passenger.getSecondName() + " " + passenger.getName() + " " + passenger.getPatronymic();

                            TicketsInSale ticketsInSale = sqlFactory.getTicketsInSale().selectTicketsInSaleId(newTicket.getIdSale());
                            dataTicket[count][8] = ticketsInSale.getCost() + "";

                            Schedule schedule = sqlFactory.getSchedule().selectScheduleId(ticketsInSale.getIdSchedule());
                            dataTicket[count][0] = schedule.getFlight();
                            dataTicket[count][7] = schedule.getTypeClass();

                            Rout rout = sqlFactory.getRout().selectRout(schedule.getIdRout());
                            dataTicket[count][2] = rout.getStartPoint();
                            dataTicket[count][3] = rout.getFinalPoint();

                            Date date = sqlFactory.getDate().selectDate(schedule.getIdDate());
                            dataTicket[count][4] = date.getHours() + ":" + date.getMinutes();
                            dataTicket[count][5] = date.getDay() + "." + date.getMonth() + "." + date.getYear();

                            soos.writeObject(dataTicket);
                        }
                        else{
                            soos.writeObject("NO");
                        }
                    }break;
                    case "returnTicket":{
                        System.out.println("Запрос к БД на удаление билета(таблица Ticket), клиент: " + clientSocket.getInetAddress().toString());
                        Ticket ticket = (Ticket)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        sqlFactory.getTicket().delete(ticket.getNumberOfTicket());
                        int idSale = sqlFactory.getTicket().find(ticket.getNumberOfTicket()).getIdSale();
                        TicketsInSale ticketsInSale = sqlFactory.getTicketsInSale().selectTicketsInSaleId(idSale);
                        int currentCount =  ticketsInSale.getCurrentCount() + 1;
                        sqlFactory.getTicketsInSale().update(ticketsInSale.getIdSchedule(), currentCount);
                    }break;
                    case "createListPassenger":{
                        System.out.println("Запрос к БД для получения списка пассажиров(таблица Passenger), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Ticket> listTicket = sqlFactory.getTicket().selectAll();
                        String[][] data = new String[listTicket.size()][6];
                        int count = 0;
                        for (Ticket ticket: listTicket){
                            Passengers passenger = sqlFactory.getPassengers().selectPassenger(ticket.getIdPassenger());
                            data[count][0] = passenger.getSecondName();
                            data[count][1] = passenger.getName();
                            data[count][2] = passenger.getPatronymic();
                            int idSchedule = sqlFactory.getTicketsInSale().selectTicketsInSaleId(ticket.getIdSale()).getIdSchedule();
                            Schedule schedule = sqlFactory.getSchedule().selectScheduleId(idSchedule);
                            Rout rout = sqlFactory.getRout().selectRout(schedule.getIdRout());
                            data[count][3] = rout.getStartPoint();
                            data[count][4] = rout.getFinalPoint();
                            Date date = sqlFactory.getDate().selectDate(schedule.getIdDate());
                            data[count][5] = date.getDay() + "." + date.getMonth() + "." + date.getYear();
                            count++;
                        }
                        soos.writeObject(data);
                    }break;
                    case "createListPlane":{
                        System.out.println("Запрос к БД на получение списка самолетов(таблица Plane), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Plane> listPlane = sqlFactory.getPlane().selectBoardNumber();
                        String[][] data = new String[listPlane.size()][4];
                        int count = 0;
                        for (Plane plane: listPlane){
                            data[count][0] = plane.getModel();
                            data[count][1] = plane.getBoardNumber();
                            data[count][2] = plane.getYearOfMade() + "";
                            data[count][3] = plane.getCountOfSeats() + "";

                            count++;
                        }
                        soos.writeObject(data);
                    }break;
                    case "addPlane":{
                        System.out.println("Запрос к БД на добавление данных(таблица Plane), клиент: " + clientSocket.getInetAddress().toString());
                        Plane plane = (Plane)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        if (sqlFactory.getPlane().isFind(plane)){
                            soos.writeObject("This plane is already existed!");
                        }
                        else{
                            soos.writeObject("OK");
                            sqlFactory.getPlane().insert(plane);
                        }
                    }break;
                    case "deletePlane":{
                        System.out.println("Запрос к БД на удаление данных(таблица Plane), клиент: " + clientSocket.getInetAddress().toString());
                        Plane plane = (Plane)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> listSchedule = sqlFactory.getSchedule().selectAllSchedule();
                        int isFind = 0;
                        for (Schedule schedule: listSchedule){
                            String boardNumber = sqlFactory.getPlane().selectPlane(schedule.getIdPlane()).getBoardNumber();
                            if (boardNumber.equals(plane.getBoardNumber())) {
                                isFind++;
                                System.out.println("** " + sqlFactory.getPlane().selectPlane(schedule.getIdPlane()).getBoardNumber());
                                System.out.println(plane.getBoardNumber());
                            }
                        }

                        if (isFind == 0) {
                            soos.writeObject("OK");
                            sqlFactory.getPlane().delete(plane.getBoardNumber());
                        } else {
                            soos.writeObject("NO");
                        }
                    }break;
                    case "addFutureFlight":{
                        String futureFlight = (String)sois.readObject();
                        String line = "";
                        try(BufferedReader fileReader = new BufferedReader(new FileReader("E:\\futureFlight.txt"))){
                            line = fileReader.readLine();
                        }catch(FileNotFoundException error){
                            try(BufferedWriter FirstFile = new BufferedWriter(new FileWriter("E:\\futureFlight.txt"))){
                                //FirstFile.write(str);
                            }
                            catch(IOException errora){
                                System.out.println("IOException error: Creation error");
                            }
                        }
                        catch(IOException error){
                            System.out.println("IOException error: ReadFile error");
                        }
                        String[] lineArray = line.split(" ");
                        Map<String, Integer> flightMap = new HashMap<>();
                        if (lineArray.length != 0) {
                            for (int i = 0; i < lineArray.length; i += 2) {
                                flightMap.put(lineArray[i], new Integer(lineArray[i + 1]));
                            }

                            if (flightMap.containsKey(futureFlight)){
                                Integer count = (Integer)flightMap.get(futureFlight);
                                count += 1;
                                flightMap.put(futureFlight, count);
                            }
                            else{
                                flightMap.put(futureFlight, 1);
                            }

                            try(BufferedWriter firstFile = new BufferedWriter(new FileWriter("E:\\futureFlight.txt"))){
                                Set<Map.Entry<String, Integer>> set = flightMap.entrySet();
                                for (Map.Entry<String, Integer> entity : set) {
                                    firstFile.write(entity.getKey() + " " + entity.getValue() + " ");
                                }
                            }
                            catch(IOException errora){
                                System.out.println("IOException error: Creation error");
                            }
                        }
                    }break;
                    case "grafic":{
                        String line = "";
                        try(BufferedReader fileReader = new BufferedReader(new FileReader("E:\\futureFlight.txt"))){
                            line = fileReader.readLine();
                        }catch(FileNotFoundException error){
                        }
                        catch(IOException error){
                            System.out.println("IOException error: ReadFile error");
                        }
                        String[] lineArray = line.split(" ");
                        String[] keys = new String[lineArray.length/2];
                        Integer[] values = new Integer[lineArray.length/2];
                        for (int i = 0, count = 0; i < lineArray.length; i += 2, count++) {
                            keys[count] = lineArray[i];
                            values[count] = Integer.parseInt(lineArray[i + 1]);
                        }
                        soos.writeObject(keys);
                        soos.writeObject(values);
                    }break;
                    case "createFormCB":{
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> listScedule = sqlFactory.getSchedule().selectAllSchedule();
                        String[] from = new String[listScedule.size()];
                        String[] to = new String[listScedule.size()];
                        int countFrom = 0;
                        int countTo = 0;
                        for (Schedule schedule: listScedule){
                            String startPoint = sqlFactory.getRout().selectRout(schedule.getIdRout()).getStartPoint();
                            String finalPoint = sqlFactory.getRout().selectRout(schedule.getIdRout()).getFinalPoint();

                            int errorFrom = 0;
                            int errorTo = 0;

                           if (countFrom != 0) {
                               for (int i = 0; i < countFrom; i++)
                                   if (from[i].equals(startPoint)) {
                                       errorFrom++;
                                       break;
                                   }
                           }
                            if (countTo != 0) {
                                for (int i = 0; i < countTo; i++)
                                    if (to[i].equals(finalPoint)) {
                                        errorTo++;
                                        break;
                                    }
                            }

                            if (errorFrom == 0) {
                                from[countFrom] = startPoint;
                                countFrom++;
                            }
                            if (errorTo == 0) {
                                to[countTo] = finalPoint;
                                countTo++;
                            }
                        }
                        soos.writeObject(from);
                        soos.writeObject(to);
                    }break;
                    case "createFlightScheduleMessage":{
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Schedule> list = sqlFactory.getSchedule().selectAllSchedule();
                        soos.writeObject(list);
                    }break;
                    case "doText":{
                        String flight = (String)sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        int idSchedule = sqlFactory.getSchedule().selectSchedule(flight).getIdSchedule();
                        int idRout = sqlFactory.getSchedule().selectSchedule(flight).getIdRout();
                        int idDate = sqlFactory.getSchedule().selectSchedule(flight).getIdDate();
                        Rout rout = sqlFactory.getRout().selectRout(idRout);
                        Date date = sqlFactory.getDate().selectDate(idDate);
                        int idSale = sqlFactory.getTicketsInSale().selectTicketsInSale(idSchedule).getIdTicketInSale();
                        ArrayList<Ticket> list = sqlFactory.getTicket().selectTicketIdSale(idSale);

                        try(BufferedWriter firstFile = new BufferedWriter(new FileWriter("E:\\passengerOnFlight.txt"))){
                            firstFile.write("Отчет по рейсу номер " + flight + " отправлением из " + rout.getStartPoint() + "\n" + " в " +
                                    rout.getFinalPoint() + " дата: " + date.getDay() + "." + date.getMonth() + "." +
                                    date.getYear() + " в " + date.getHours() + ":" + date.getMinutes() + "\n");
//                            firstFile.append("\n");
//                            firstFile.append("\n");

                            for (Ticket ticket: list){
                                int idPassenger = ticket.getIdPassenger();
                                Passengers passenger = sqlFactory.getPassengers().selectPassenger(idPassenger);
                                firstFile.write(passenger.getSecondName() + " " + passenger.getName() + " " + passenger.getPatronymic() + "\n");
//                                firstFile.append("\n");
                            }
                        }
                        catch(IOException errora){
                            System.out.println("IOException error: Creation error");
                        }

                    }break;
                    case "exit":{
                        soos.writeObject("OK");
                        soos.close();
                        sois.close();
                        System.out.println("Client " + clientSocket.getInetAddress().toString() + "disconnected.");
                    }break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
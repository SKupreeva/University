package DataAccess;

import DataAccess.Model.Data.Ticket;
import DataAccess.Model.RepositoryInterface;
import WebFace.Constants.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Repository implements RepositoryInterface {

    private static RepositoryInterface repository;

    public static RepositoryInterface getInstance() {
        if (repository == null) repository = new Repository();
        return repository;
    }

    public ArrayList<Ticket> getTickets(Path path) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(new FileReader(path.toString()));
            JSONObject jo = (JSONObject) obj;
            JSONArray ticketArray = (JSONArray) jo.get("tickets");
            for (int i = 0; i < ticketArray.size(); i++) {
                JSONObject object = (JSONObject) ticketArray.get(i);
                Ticket ticket = new Ticket(
                        Integer.parseInt(object.get("number").toString()),
                        object.get("depart").toString(),
                        object.get("arrive").toString(),
                        Integer.parseInt(object.get("price").toString()),
                        object.get("class").toString());
                tickets.add(ticket);
            }
        } catch (IOException e) {
            System.out.println("Файл " + path + " не найден!");

        } catch (ParseException e) {
            System.out.println("Ошибка парсинга!");
        }
        return tickets;
    }

    public ArrayList<Integer> setDeleted(ArrayList<Integer> deleted) {
        try {
            FileWriter writer = new FileWriter(URL.FILEPATH);
            for (Integer integer : deleted) {
                writer.append(integer + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public ArrayList<Integer> getDeleted() {
        ArrayList<Integer> deleted = new ArrayList<>();
        try {
            File myObj = new File(URL.FILEPATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                deleted.add(Integer.parseInt(data));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}

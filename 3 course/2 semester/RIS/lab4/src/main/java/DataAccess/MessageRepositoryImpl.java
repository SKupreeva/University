package DataAccess;

import DataAccess.models.MessageRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    private Path path;
    private  static MessageRepository repository;
    private MessageRepositoryImpl(){}
    public static MessageRepository getInstance(){
        if (repository == null) repository = new MessageRepositoryImpl();
        return repository;
    }
    @Override
    public List<String> getMessages() {
        StringBuilder filePath = new StringBuilder();
        filePath.append("D:/универ/University/3 course/2 semester/RIS/lab4/src/main/resources/Messages.txt");
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get(filePath.toString()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void saveMessages(List<String> messages) throws IOException {
        File myFile = new File("D:/универ/University/3 course/2 semester/RIS/lab4/src/main/resources/Messages.txt");
        FileWriter writer = new FileWriter(myFile, false);

        try {
            for (String message : messages) {
                writer.write(message + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}

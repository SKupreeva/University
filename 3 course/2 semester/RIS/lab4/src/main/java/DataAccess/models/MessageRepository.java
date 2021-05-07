package DataAccess.models;

import java.io.IOException;
import java.util.List;

public interface MessageRepository {
    List<String> getMessages();
    void saveMessages(List<String> messages) throws IOException;
}

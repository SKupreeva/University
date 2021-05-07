package BusinessLogic;

import DataAccess.MessageRepositoryImpl;
import DataAccess.models.MessageRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.*;

@MessageDriven(name = "MessageListenerImpl", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue")
})
public class MessageListeners implements MessageListener {
    private MessageRepository repository=MessageRepositoryImpl.getInstance();

    @Override
    public void onMessage(Message receivedMessage) {
        TextMessage receivedTextMessage;
        try {
            if (receivedMessage instanceof TextMessage) {
                receivedTextMessage = (TextMessage) receivedMessage;
                String[] receivedStringMessage = receivedTextMessage.getText().split("\n");

                ArrayList<String> modifiedMessages = new ArrayList<>(Arrays.asList(receivedStringMessage));
                modifiedMessages.addAll(repository.getMessages());
                try {
                    List<String> result = shifrList(modifiedMessages);
                    repository.saveMessages(result);
                } finally {
                    System.out.println("--------------------------------------------------");
                    for (String message : repository.getMessages())
                        System.out.println("Modified message: " + message);
                    System.out.println("--------------------------------------------------");
                }
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    private List<String> shifrList(List<String> unshifredMessages) {
        List<String> shifredMessages = new ArrayList<String>();
        for (String unshifredMessage : unshifredMessages) {
            shifredMessages.add(unshifredMessage.replaceAll("\\p{all}", "*"));
        }
        return shifredMessages;
    }
}

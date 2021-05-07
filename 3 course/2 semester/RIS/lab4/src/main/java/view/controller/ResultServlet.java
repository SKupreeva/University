package view.controller;

import view.utils.URL;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static view.utils.ViewAttributes.*;

@WebServlet(name = "ShowSortedMessages", urlPatterns = {"/ShowSortedMessages"})
public class ResultServlet extends HttpServlet {
    @Resource(name = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Destination destination;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp, URL.BASE_URL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String receivedMessages = req.getParameter(MESSAGES.toString().toLowerCase());
        if (receivedMessages == null || receivedMessages.equals(""))
            req.setAttribute(RESULT.toString().toLowerCase(), "EMPTY");
        else {
            try {
                this.sendMessageToListeners(receivedMessages);
            } catch (JMSException ex) {
                Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute(RESULT.toString().toLowerCase(), "OK");
        }
        setRequestDispatcher(req, resp, URL.SHOW_RESULT);
    }

    private void sendMessageToListeners(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.send(createJMSMessage(session, messageData));
        } finally {
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
    private Message createJMSMessage(Session session, Object messageData) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(messageData.toString());
        return textMessage;
    }

    private void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}

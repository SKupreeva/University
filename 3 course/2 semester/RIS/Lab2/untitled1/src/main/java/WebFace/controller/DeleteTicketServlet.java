package WebFace.controller;

import DataAccess.Model.Data.Ticket;
import WebFace.Constants.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeleteTicketServlet extends TicketServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp, URL.INDEX_URL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType(UTF_8_CONTENT_TYPE);
        resp.setCharacterEncoding("UTF-8");
        path = Paths.get(getServletContext().getRealPath("WEB-INF/classes/Tickets.json"));
        String number = req.getParameter("number");
        ArrayList<Ticket> tickets = controller.setTickets(path);
        ArrayList<Integer> deletedTickets = controller.deleteTicket(number);
        req.setAttribute("deletedTickets", deletedTickets);
        req.setAttribute("tickets", tickets);
        req.setAttribute("number", number);
        setRequestDispatcher(req, resp, URL.DELETE_TICKET);

    }
}

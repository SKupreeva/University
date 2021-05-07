package WebFace.controller;

import DataAccess.Model.Data.Ticket;
import WebFace.Constants.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ShowTicketByClassServlet  extends TicketServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setRequestDispatcher(req, resp, URL.INDEX_URL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setContentType(UTF_8_CONTENT_TYPE);
        resp.setCharacterEncoding("UTF-8");
        path = Paths.get(getServletContext().getRealPath("WEB-INF/classes/Tickets.json"));
        String classType = req.getParameter("class");
        ArrayList<Integer> deleted = controller.deleteTicket("0");
        ArrayList<Ticket> filteredTickets = controller.showTicketByClass(classType, path);
        req.setAttribute("filteredTickets", filteredTickets);
        req.setAttribute("classType", classType);
        req.setAttribute("deleted", deleted);
        setRequestDispatcher(req, resp, URL.SHOW_TICKET_BY_CLASS);

    }
}
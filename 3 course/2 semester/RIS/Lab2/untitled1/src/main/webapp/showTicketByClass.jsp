<%@ page import="DataAccess.Model.Data.Ticket" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Sveta
  Date: 25.03.2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab 2, 1 part</title>
    <h4>2 the cheapest tickets in selected class:
        <%
            String classType = (String) request.getAttribute("classType");
            out.println(classType);
        %>
    </h4>
</head>
<body>
<% ArrayList<Ticket> filteredTickets = (ArrayList<Ticket>) request.getAttribute("filteredTickets");
    ArrayList<Integer> deleted = (ArrayList<Integer>) request.getAttribute("deleted");
    if (filteredTickets.size() != 0) {

        out.println("<p> =================================================================");
        for (Ticket filteredTicket : filteredTickets) {
            int flag = 0;
            for (Integer integer : deleted) {
                if (filteredTicket.getNumber() == integer) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                out.println("<p>Ticket number - " + filteredTicket.getNumber() + "</p>");
                out.println("<p> .................................................................");
                out.println("<p> Depart : " + filteredTicket.getDepart() + "</p>");
                out.println("<p> Arrive : " + filteredTicket.getArrive() + "</p>");
                out.println("<p> Price : " + filteredTicket.getPrice() + "</p>");
                out.println("<p> Class : " + filteredTicket.getClassType() + "</p>");
                out.println("<p> =================================================================");
            }
        }

    } else {
        out.println("<p> \n List is empty! </p>");
    }
%>
</body>
</html>

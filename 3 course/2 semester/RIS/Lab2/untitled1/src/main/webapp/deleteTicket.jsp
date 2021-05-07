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
    <title>Lab 2, 2 part</title>
    <h4>List of tickets:</h4>
</head>
<body>
<% ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("tickets");
    ArrayList<Integer> deleted = (ArrayList<Integer>) request.getAttribute("deletedTickets");
    String number = (String) request.getAttribute("number");
     if (tickets.size() != 0) {
         int f = 0;
         for(int i = 0; i < deleted.size() - 1; i++){
             if(deleted.get(i).toString().equals(number)) f++;
         }
         if(f != 0) out.println("<p> \n Selected ticket is deleted. Try again. </p>");
         else {
             out.println("<p> =================================================================");
             for (Ticket ticket : tickets) {
                 int flag = 0;
                 for (Integer integer : deleted) {
                     if (ticket.getNumber() == integer) {
                         flag = 1;
                         break;
                     }
                 }
                 if (flag == 0) {
                     out.println("<p>Ticket number - " + ticket.getNumber() + "</p>");
                     out.println("<p> .................................................................");
                     out.println("<p> Depart : " + ticket.getDepart() + "</p>");
                     out.println("<p> Arrive : " + ticket.getArrive() + "</p>");
                     out.println("<p> Price : " + ticket.getPrice() + "</p>");
                     out.println("<p> Class : " + ticket.getClassType() + "</p>");
                     out.println("<p> =================================================================");
                 }
             }
         }

    } else {
        out.println("<p> \n List is empty! </p>");
    }
%>
</body>
</html>

<%@ page import="java.util.ArrayList" %>
<html>
<title>Lab 2</title>
<body>
<h4>Please select class of ticket: </h4>
<form action="ShowTicketByClassServlet " method="post">
    <select name="class">
        <option value="tourist">Tourist</option>
        <option value="business">Business</option>
        <option value="first">First</option>
    </select>
    <input name="go" type="submit" value="Submit">
</form>
<h4>Please select ticket you want to delete: </h4>
<form action="DeleteTicketServlet " method="post">
    <select name="number">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
    </select>
    <input name="go" type="submit" value="Delete">
</form>
</body>
</html>

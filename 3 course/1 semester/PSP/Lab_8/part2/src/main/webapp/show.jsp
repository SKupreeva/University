<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sveta
  Date: 09.11.2020
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<div>
    <div>
        <div>
            <div>
                <h2>Results</h2>
            </div>
            <%
                List<Double> resList = (List<Double>) request.getAttribute("funResults");

                if (resList != null && !resList.isEmpty()) {
                    out.println("<ui>");
                    for (double s : resList) {
                        out.println("<li>" + s + "</li>");
                    }
                    out.println("</ui>");
                } else out.println("<p>There are no results yet!</p>");
            %><br/>
        </div>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</div>
</body>
</html>

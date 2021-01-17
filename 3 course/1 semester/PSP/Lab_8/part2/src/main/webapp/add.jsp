<%--
  Created by IntelliJ IDEA.
  User: Sveta
  Date: 09.11.2020
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<div>
    <div>
        <div>
            <h2>Properties:</h2>
        </div>
        <div>
            <form method="post">
                <label>Select function:
                    <select name="funSelect">
                        <option>sin(x)</option>
                        <option>cos(x)</option>
                        <option>tg(x)</option>
                        <option>ctg(x)</option>
                    </select><br />
                </label>
                <label>Enter x value:
                    <input type="text" name="valueX"><br />
                </label>
                <label>Select:
                    <select name="waySelect">
                        <option>radians</option>
                        <option>degrees</option>
                    </select><br />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</div>
</body>
</html>
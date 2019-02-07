<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.03.2018
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/addUser" method="POST">
        <ul>
            <li>
                <label>Username: <input type="text" name="name"></label>
            </li>
            <li>
                <label>Password: <input type="text" name="password"></label>
            </li>
            <li><input type="submit" align="center" value="Submit"/></li>
        </ul>

    </form>
</body>
</html>

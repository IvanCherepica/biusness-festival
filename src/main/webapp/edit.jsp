<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.03.2018
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>
<body>
<div align="center">
    <form action="${pageContext.servletContext.contextPath}/edit" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <ul>
            <li>
                <label>Username: <input type="text" name="name" value="${user.name}"></label>
            </li>
            <li>
                <label>Password: <input type="text" name="password" value="${user.password}"></label>
            </li>
            <li><input type="submit" align="center" value="Submit"/></li>
        </ul>

    </form>
</div>

</body>
</html>

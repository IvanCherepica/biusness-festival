<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Users management</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/addUser">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/edit?id=${user.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.servletContext.contextPath}/delete?id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>FestivalEditor</title>
</head>
<body>

<h1>Радактирование Фестивалей</h1>
<div align="center">
    <table border="5" cellpadding="10">
        <caption><h2>Фестивали</h2></caption>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>возможности</th>
        </tr>
        <c:forEach items="${festival}" var="fest" varStatus="status">
            <tr>
                <td>${fest.id }</td>
                <td>${fest.name}</td>
                <td>
                    <a ><!--href="${pageContext.servletContext.contextPath}/admin/edit?id=${fest.id}"-->редакция</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a ><!--href="${pageContext.servletContext.contextPath}/admin/delete?id=${fest.id}"-->удаление</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<h2>
    <a href="${pageContext.request.contextPath}/admin/addFest">создайте новый фестиваль</a>
</h2>
</body>
</html>
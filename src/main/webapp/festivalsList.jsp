<%--
  Created by IntelliJ IDEA.
  User: NICK
  Date: 08.02.2019
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Festivals</title>
    <link type="text/css" rel="stylesheet" href="festivalsList.css" />
</head>
<body>

</body>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Geometry</th>
        <th>Color</th>
    </tr>
    <c:forEach var="festival" items="${FestivalsList}">
        <tr>
            <td>${festival.getId()}</td>
            <td>${festival.getName()}</td>
            <td>${festival.getDescription()}</td>
            <td>${festival.getGeometry()}</td>
            <td>${festival.getColor()}</td>
        </tr>
    </c:forEach>
</table>
</html>

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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    </head>
<body>

</body>


<div class="panel-body">
    <h4>All festivals</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Geometry</th>
            <th>Color</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="festival" items="${FestivalsList}">
            <tr>
                <th scope="row">${festival.getId()}</th>
                <td>${festival.getName()}</td>
                <td>${festival.getDescription()}</td>
                <td>${festival.getGeometry()}</td>
                <td>${festival.getColor()}</td>
                <td>
                    <a id="editButton${festival.getId()}" class="btn btn-primary">Edit</a>
                    <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/delete/${FestivalsList}">Delete</a>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</html>



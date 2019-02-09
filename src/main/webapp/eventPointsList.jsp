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
    <title>Event Points</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    </head>
<body>

</body>


<div class="panel-body">
    <h4>All Event Points</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Geometry</th>
            <th>Color</th>
            <th>FestivalId</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="eventPoint" items="${EventPointsList}">
            <tr>
                <th scope="row">${eventPoint.getId()}</th>
                <td>${eventPoint.getName()}</td>
                <td>${eventPoint.getDescription()}</td>
                <td>${eventPoint.getGeometry()}</td>
                <td>${eventPoint.getColor()}</td>
                <%--<td>${eventPoint.getFestival().getId()}</td>--%>
                <td>tempValue</td>
                <td>
                    <a id="editButton${eventPoint.getId()}" class="btn btn-primary">Edit</a>
                    <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/delete/${eventPoint}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</html>



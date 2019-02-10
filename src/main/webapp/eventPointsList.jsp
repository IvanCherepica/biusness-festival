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
                <form>
                <td>${eventPoint.getId()}</td>
                <td>${eventPoint.getName()}</td>
                <td>${eventPoint.getDescription()}</td>
                <td>${eventPoint.getGeometry()}</td>
                <td>${eventPoint.getColor()}</td>
                <td>${eventPoint.getFestival().getId()}</td>
                <td>
                    <%-- HELP
                    button
                    name
                    value
                    formmethod
                    formaction --%>
                    <button name="deleteId" value="${eventPoint.getId()}" formmethod="post" formaction="" class="btn btn-primary">Delete</button>
                    <button name="editId" value="${eventPoint.getId()}" formmethod="get" formaction="\eventpointedit" class="btn btn-primary">Edit</button>
                </td>
                </form>>
            </tr>
        </c:forEach>




        </tbody>
    </table>
    <form>
        <button name="editId" value="new" formmethod="get" formaction="\eventpointedit" class="btn btn-primary">Add EventPoint</button>
    </form>
</div>


</html>



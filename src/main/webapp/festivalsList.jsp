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
            <th><a id="addButton" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/addFest">Add</a></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="festival" items="${FestivalsList}">
            <tr>
                <td width="5%" scope="row">${festival.id}</td>
                <td width="15%">${festival.name}</td>
                <td width="30%">${festival.description}</td>
                <td width="30%">${festival.geometry}</td>
                <td width="5%" bgcolor="${festival.color}"></td>
                <td width="10%" class="form-group">
                    <form  class="form-inline">
                        <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/editFestival" name="edit" value="${festival.id}">Edit</button>
                        <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/deleteFestival" name="delete" value="${festival.id}">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</html>



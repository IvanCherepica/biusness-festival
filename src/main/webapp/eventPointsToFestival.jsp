<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список событий фестиваля</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>

</body>


<div class="panel-body">
    <h4>Список событий фестиваля</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>festival_id</th>
            <th><a id="addButton" class="btn btn-primary"
                   href="${pageContext.servletContext.contextPath}/admin/eventtomap">Add</a>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="event" items="${eventPoints}">
            <tr>
                <td width="5%" scope="row">${event.id}</td>
                <td width="15%">${event.name}</td>
                <td width="30%">${event.festival_id}</td>
                <td width="10%" class="form-group">
                    <form class="form-inline">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</html>

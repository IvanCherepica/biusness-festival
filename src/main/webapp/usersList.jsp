<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>

</body>


<div class="panel-body">
    <h4>Список пользователей</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>role</th>
            <th><a id="addButton" class="btn btn-primary"
                   href="${pageContext.servletContext.contextPath}/admin/addUser">Add</a>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td width="5%" scope="row">${user.id}</td>
                <td width="15%">${user.name}</td>
                <td width="30%">${user.role}</td>
                <td width="10%" class="form-group">
                    <form class="form-inline">

                        <button type="submit" class="btn btn-primary" formmethod="get"
                                formaction="${pageContext.request.contextPath}/admin/editUser" name="edit"
                                value="${user.id}">Edit
                        </button>

                        <button type="submit" class="btn btn-primary" formmethod="get"
                                formaction="${pageContext.request.contextPath}/admin/deleteUser" name="delete"
                                value="${user.id}">Delete
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Пользователи</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="/css/dashboard.css" %>
    </style>
</head>


<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <jsp:include page="/admin/admin-top-panel.jsp"/>
    </div>
</nav>



<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="/admin/admin-left-panel.jsp">
                <jsp:param name='selected' value='users'/>
            </jsp:include>
        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Список пользователей</h1>
            <div class="table-responsive">
                <table class="table table-striped">

                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Имя</th>
                        <th>Роль</th>
                        <th><a id="addButton" class="btn btn-primary"
                               href="${pageContext.servletContext.contextPath}/admin/addUser">Добавить</a>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${usersList}">
                        <tr>
                            <td width="5%" scope="row">${user.id}</td>
                            <td width="55%">${user.name}</td>
                            <td width="20%">${user.role}</td>
                            <td width="20%" class="form-group">
                                <form class="form-inline">

                                    <button type="submit" class="btn btn-primary" formmethod="get"
                                            formaction="${pageContext.request.contextPath}/admin/editUser" name="edit"
                                            value="${user.id}">Изменить
                                    </button>

                                    <button type="submit" class="btn btn-primary" formmethod="get"
                                            formaction="${pageContext.request.contextPath}/admin/deleteUser" name="delete"
                                            value="${user.id}">Удалить
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>







</body>


</html>


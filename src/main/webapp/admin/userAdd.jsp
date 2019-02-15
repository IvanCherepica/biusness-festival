<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Добавление пользователя</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="/css/dashboard.css" %>
        .container{
            width:100%;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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


        <div class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-2 main">
                <h1 class="page-header">Добавление пользователя</h1>
                <form action="${pageContext.request.contextPath}/admin/addUser" method="POST">
                    <div class="form-group edit-group">

                        <label for="name">Имя</label>
                        <input type="text" class="form-control" id="name" name="name">

                        <label for="password">Пароль</label>
                        <input class="form-control" id="password" rows="3" name="password">

                        <label for="role">Роль</label>
                        <select class="form-control" id="role" rows="2" name="role"
                                  placeholder="${user.role}">
                            <option>user</option>
                            <option>admin</option>
                        </select>
                        <br>
                        <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
                        <c:if test="${isExist}">
                            <div id="login-message" class="alert alert-danger container">
                                <h3>Пользователь уже Сущетвует</h3>
                                <div id="error-message">попробуйте еще раз</div>
                            </div>
                        </c:if>
                    </div>
                </form>
        </div>
        <div class="col-sm-3 col-md-6"></div>
    </div>
</div>
</body>
</html>
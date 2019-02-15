<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Добавление фестиваля</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="/css/dashboard.css" %>
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
            <h1 class="page-header">Создание Фестиваля</h1>
                <form action="${pageContext.request.contextPath}/admin/addFest" method="POST">
                    <div class="form-group edit-group">
                        <label for="id">Id</label>
                        <input type="text" class="form-control" id="id" name="id"
                               value="${festival.id}" readonly>

                        <label for="name">Название</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="введите название">

                        <label for="description">Описание</label>
                        <textarea class="form-control" id="description" rows="3" name="description"
                                  placeholder="введите описание"></textarea>

                        <label for="geometry">Координаты</label>
                        <textarea class="form-control" id="geometry" rows="4" name="geometry"
                                  placeholder="${festival.geometry}"></textarea>

                        <label for="color">Цвет</label>
                        <input type="color" class="form-control" id="color" name="color"
                               value="${festival.color}">
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
                </form>
        </div>
        <div class="col-sm-3 col-md-6"></div>
    </div>
</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Редактирование пользователя</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="/css/dashboard.css" %>
    </style>

    <script
            src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <!--BEAUTIFUL MULTIPLE SELECTS WITH LIVE SEARCH-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
    <!-- Soo beautiful -->


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
                <h1 class="page-header">Редактирование пользователя</h1>
                <form action="${pageContext.request.contextPath}/admin/editUser" method="POST">
                    <div class="form-group edit-group">
                        <label for="id">Id</label>
                        <input type="text" class="form-control" id="id" name="id"
                               value="${user.id}" readonly>

                        <label for="name">Имя</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="${user.name}">

                        <label for="password">Пароль</label>
                        <input class="form-control" id="password" rows="3" name="password"
                                  value="${user.password}">

                        <label for="role">Роль</label>
                        <select class="form-control" id="role" rows="2" name="role"
                                placeholder="${user.role}" >
                            <option>user</option>
                            <option>admin</option>
                        </select>

                        <label for="epoint">События</label>
                        <select name="epoint" id="epoint" class="selectpicker" multiple="multiple" data-live-search="true">
                            <c:forEach var="uevent" items="${ueventsp}">
                                <option value="${uevent.id}" selected>${uevent.name}</option>
                            </c:forEach>
                            <c:forEach var="event" items="${eventsp}">
                                <option value="${event.id}">${event.name}</option>
                            </c:forEach>
                        </select>


                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Сохранить"/>
                </form>
        </div>
        <div class="col-sm-3 col-md-6"></div>
    </div>
</div>
</body>
</html>

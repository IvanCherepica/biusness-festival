<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Фестивали</title>

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
                <jsp:param name='selected' value='festivals'/>
            </jsp:include>
        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Список фестивалей</h1>
            <div class="table-responsive">
                <table class="table table-striped">

                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Имя</th>
                        <th>Описание</th>
                        <th>Координаты</th>
                        <th>Цвет</th>
                        <th><a id="addButton" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/addFest">Добавить</a></th>
                    </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="festival" items="${FestivalsList}">
                        <tr>
                            <td width="5%" scope="row">${festival.id}</td>
                            <td width="15%">${festival.name}</td>
                            <td width="25%">${festival.description}</td>
                            <td width="25%"
                                style="white-space: nowrap;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                max-width: 25vw;">
                                    ${festival.geometry}</td>
                            <td width="5%" bgcolor="${festival.color}"></td>
                            <td width="20%" class="form-group">
                                <form  class="form-inline">
                                    <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/editFestival" name="festivalId" value="${festival.id}">Изменить</button>
                                    <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/deleteFestival" name="festivalId" value="${festival.id}">Удалить</button>
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



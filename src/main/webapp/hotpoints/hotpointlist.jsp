
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список Хот-Поинтов</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>
<body>

</body>


<div class="panel-body">
    <h4>Список Хот-поинтрв</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Координаты</th>
            <th>Цвет</th>
            <th>Id фестиваля</th>
            <form>
                <button formmethod="get" formaction="/admin/hotpoints/addhot" class="btn btn-primary">Добваить</button>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hotPoint" items="${HotPointList}">
            <tr>
                <form>
                    <td>${hotPoint.id}</td>
                    <td>${hotPoint.name}</td>
                    <td>${hotPoint.description}</td>
                    <td>${hotPoint.geometry}</td>
                    <td  bgcolor="${hotPoint.color}"></td>
                    <td>${hotPoint.festival.name} (id:${hotPoint.festival.id})</td>
                    <td>
                        <button name="hPointId" value="${hotPoint.id}" formmethod="post" formaction="\admin\hotpoints\delete" class="btn btn-primary">Удалить</button>
                        <button name="hPointId" value="${hotPoint.id}" formmethod="get" formaction="\admin\hotpoints\edit" class="btn btn-primary">Изменить</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form>
        <button formmethod="get" formaction="\logout" class="btn btn-primary">Выйти</button>
    </form>
</div>


</html>



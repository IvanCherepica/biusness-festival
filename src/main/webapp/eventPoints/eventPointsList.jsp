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
    <title>Список Мест</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
    </head>
<body>

</body>


<div class="panel-body">
    <h4>Места Событий</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Координаты</th>
            <th>Цвет</th>
            <th>Id фестиваля</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="eventPoint" items="${EventPointsList}">
            <tr>
                <form>
                <td width="5%">${eventPoint.id}</td>
                <td width="15%">${eventPoint.name}</td>
                <td width="25%">${eventPoint.description}</td>
                <td width="20%"
                    style="white-space: nowrap;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                max-width: 25vw;">${eventPoint.geometry}</td>
                <td width="5%" style="background-color:${eventPoint.color};"></td>
                <td width="5%">${eventPoint.festival.name} (id:${eventPoint.festival.id})</td>
                <td width="20%">
                    <button name="eventPointId" value="${eventPoint.id}" formmethod="post" formaction="\admin\eventpoints\delete" class="btn btn-primary">Удалить</button>
                    <button name="eventPointId" value="${eventPoint.id}" formmethod="get" formaction="\admin\eventpoints\edit" class="btn btn-primary">Редактировать</button>
                </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form>
        <button formmethod="get" formaction="\admin\eventpoints\create" class="btn btn-primary">Добавить</button>
    </form>
    <form>
        <button formmethod="get" formaction="\logout" class="btn btn-primary">Выйти</button>
    </form>
</div>


</html>



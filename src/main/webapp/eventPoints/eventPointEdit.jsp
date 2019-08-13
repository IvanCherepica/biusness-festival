<%--
  Created by IntelliJ IDEA.
  User: NICK
  Date: 10.02.2019
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редакция Места Событий</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Редакия Места событий</h2>
                <form action="/admin/eventpoints/edit" method="POST" class="form-group login-group">
                    <select name="festivalId" value="${eventPoint.festival.id}"  class="form-control" id="sel1">
                        <c:forEach var="item" items="${festivals}">
                            <option value="${item.id}" ${item.id == eventPoint.festival.id ? 'selected="selected"' : ''}>${item.name} (id:${item.id})</option>
                        </c:forEach>
                    </select>

                    <input
                            name="eventPointId" value="${eventPoint.id}" required
                            type="hidden" />

                    <input placeholder="введите имя"
                           name="name" value="${eventPoint.name}" required
                           class="form-control"/>

                    <textarea placeholder="введите краткое описание"
                              name="description" value="${eventPoint.description}"
                              class="login-form form-control" rows="3">${eventPoint.description}</textarea>

                    <textarea placeholder="введите координаты"
                              name="geometry" value="${eventPoint.geometry}" required
                              class="login-form form-control" rows="6">${eventPoint.geometry}</textarea>

                    <input type="color"
                           name="color" value="${eventPoint.color}" required
                           class="form-control"/>

                    <button type="submit" class="btn btn-primary btn-block">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

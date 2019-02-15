<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редакция Хот-Поинта</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Редакция Хот-Поинта</h2>
                <form action="" method="POST" class="form-group login-group">
                    <select name="fId" value="${hPoint.festival.id}"  class="form-control" id="sel1">
                        <c:forEach var="item" items="${festivals}">
                            <option value="${item.id}" ${item.id == hPoint.festival.id ? 'selected="selected"' : ''}>${item.name} (id:${item.id})</option>
                        </c:forEach>
                    </select>

                    <input
                            name="hPointId" value="${hPoint.id}" required
                            type="hidden" />

                    <input placeholder="введите имя"
                           name="name" value="${hPoint.name}" required
                           class="form-control"/>

                    <textarea placeholder="введите краткое описание"
                              name="description" value="${hPoint.description}" required
                              class="login-form form-control" rows="3">${hPoint.description}</textarea>

                    <textarea placeholder="введите координаты"
                              name="geometry" value="${hPoint.geometry}" required
                              class="login-form form-control" rows="6">${hPoint.geometry}</textarea>

                    <input type="color"
                           name="color" value="${hPoint.color}" required
                           class="form-control"/>

                    <button type="submit" class="btn btn-primary btn-block">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

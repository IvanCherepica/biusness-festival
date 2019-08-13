<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Хот-Поинт</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Добавить Хот-Понит</h2>
                <form action="" method="POST" class="form-group login-group">
                    <select name="festivalId" value="${festivalId}" class="form-control" id="sel1">
                        <c:forEach var="item" items="${festivals}">
                            <option value="${item.id}" ${item.id == festivalId ? 'selected="selected"' : ''}>${item.name} (id:${item.id})</option>
                        </c:forEach>
                    </select>

                    <input placeholder="введите имя"
                           name="name" required
                           class="form-control"/>

                    <textarea placeholder="введите краткое описание"
                              name="description" required
                              class="login-form form-control" rows="3"></textarea>

                    <textarea placeholder="введите координаты"
                              name="geometry" required
                              class="login-form form-control" rows="6"></textarea>

                    <input type="color"
                           name="color" required
                           class="form-control"/>

                    <button type="submit" class="btn btn-primary btn-block">Создать</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

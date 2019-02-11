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
    <title>EventPoints</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>EventPoint create</h2>
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

                    <button type="submit" class="btn btn-primary btn-block">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

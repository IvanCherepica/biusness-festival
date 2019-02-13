<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add festival</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 edit-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Создание Фестиваля</h2>
                <form action="${pageContext.request.contextPath}/admin/addFest" method="POST">
                    <div class="form-group edit-group">
                        <label for="id">Id</label>
                        <input type="text" class="form-control" id="id" name="id"
                               value="${festival.id}" readonly>

                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="${festival.name}">

                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" rows="3" name="description"
                                  placeholder="${festival.description}"></textarea>

                        <label for="geometry">Geometry</label>
                        <textarea class="form-control" id="geometry" rows="4" name="geometry"
                                  placeholder="${festival.geometry}"></textarea>

                        <label for="color">Color</label>
                        <input type="color" class="form-control" id="color" name="color"
                               value="${festival.color}">
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
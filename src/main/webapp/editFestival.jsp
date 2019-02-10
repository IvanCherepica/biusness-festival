<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редактирование</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        body {
            background: #eeeeee;
        }
        .edit-form {
            margin-top: 100px;
            margin-top: 12px;
        }
        .edit-group :last-child {
            margin-top: 5px;
            max-width: 280px;
        }
        .empty-block {
            height: 88px;
        }

    </style>

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 edit-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Редактирование Фестиваля</h2>
                <form action="${pageContext.request.contextPath}/admin/editFestival" method="POST">
                    <div class="form-group edit-group">
                        <label for="id">Id</label>
                        <input type="text" class="form-control" id="id" name="id"
                               value="${festival.id}" readonly>

                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               value="${festival.name}">

                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" rows="3" name="description"
                                  value="${festival.description}">${festival.description}</textarea>

                        <label for="geometry">Geometry</label>
                        <textarea class="form-control" id="geometry" rows="4" name="geometry"
                                  value="${festival.geometry}">${festival.geometry}</textarea>

                        <label for="color">Color</label>
                        <input type="color" class="form-control" id="color" name="color"
                               value="${festival.color}">
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Сохранить"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
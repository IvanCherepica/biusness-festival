<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        body {
            background: #eeeeee;
        }
        .login-form {
            margin-top: 100px;
            margin-top: 12px;


        }

        .login-group :last-child {
            margin-top: 5px;
            max-width:280px;
        }

        .login-group :last-child {
            margin-top: 5px;
            max-width:280px;
        }
        .empty-block {
            height: 88px;
        }

    </style>

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Создание Фестиваля</h2>
                <form action="${pageContext.request.contextPath}/addFest" method="POST">
                    <div class="form-group login-group">
                        <input type="text" class="form-control"  name="name" placeholder="название Фестиваля
                        "       required></input>
                        <textarea   class="login-form form-control " rows="3" name="description" placeholder="краткое описание
                        " required></textarea>
                        <textarea   class="login-form form-control " rows="6" name="geometry" placeholder="координаты
                        " required></textarea>

                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
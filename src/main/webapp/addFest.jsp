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
            /*margin-top: 100px;*/
            margin-top: 12px;


        }
        <textarea class="login-form" id="exampleFormControlTextarea1" rows="3"></textarea>
                                                                                 .login-group :last-child {
                                                                                     margin-top: 5px;
                                                                                     max-width:280px;
                                                                                 }
        <textarea class="login-form" id="exampleFormControlTextarea1" rows="3"></textarea>
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

        <h2>Создание Фестиваля</h2>
        <form action="${pageContext.request.contextPath}/addfest" method="POST">
            <div class="form-group login-group">
                <input type="text" class="form-control"  name="name" placeholder="название Фестиваля"
                       required/>

                <textarea  class="form-control" rows="5" name="description" placeholder="краткое описание" required></textarea>
                <textarea  class="form-control" rows="5" name="geometry" placeholder="координаты" required></textarea>

            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
        </form>
    </div>
</div>
</div>
</div>
</body>
</html>
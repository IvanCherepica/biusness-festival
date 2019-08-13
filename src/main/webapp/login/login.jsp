<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Festival Map</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 login-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2 style="text-align: center;">Добро пожаловать</h2>
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <div class="form-group login-group">
                        <input type="text" class="form-control" id="uName" name="login" placeholder="Логин"
                               required/>
                        <br>
                        <input type="password" class="form-control" type="text" id="uPass" name="password"
                               required placeholder="Пароль" required/>
                        <br>
                        <input type="submit" class="btn btn-primary btn-block" value="Войти"/>
                    </div>
                </form>
                    <form action="${pageContext.request.contextPath}/registration" method="GET">
                        <input type="submit" class="btn btn-primary btn-block" value="Зарегистрироваться"/>
                    </form>
                <br>
                <c:if test="${isInvalid}">
                    <div id="login-message" class="alert alert-danger">
                        <h3>Неверные Данные</h3>
                        <div id="error-message">попробуйте еще раз</div>
                    </div>
                </c:if>
                <c:if test="${isPass}">
                    <div id="login-message" class="alert alert-danger">
                        <h3>Неверный пароль</h3>
                        <div id="error">попробуйте еще раз</div>
                    </div>
                </c:if>
                <c:if test="${regSucces}">
                    <div id="login-message" class="alert alert-danger">
                        <h3>Вы Зарегистрированы</h3>
                        <div id="succses-message">введити логин и пароль для входа</div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: matvey
  Date: 10/02/2019
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        .form-control {
            width: 26%;
        }

        .container {
            width: 26%;
        }

        .submit {
            width: 26%;
        }
    </style>
</head>
<body>
<div align="center">
    <div class="main">

        <h2>Регистрация</h2>

        <form action="${pageContext.servletContext.contextPath}/registration" method="POST">
            <c:if test="${!regSucces}">
                <div class="field">
                    <input type="text" class="form-control" id="uName" name="name" placeholder="Логин" required/>
                </div>
                <br>
                <div class="field">
                    <input type="password" class="form-control" id="uPass" name="password" placeholder="Пароль"
                           required/>
                </div>
                <br>
                <div class="submit">
                    <input type="submit" class="btn btn-primary btn-block" align="center" value="Зарегистрироваться"/>
                </div>
                <br>
                <c:if test="${isExists}">
                    <div id="login-message" class="alert alert-danger container">
                        <h3>Пользователь уже Сущетвует</h3>
                        <div id="error-message">попробуйте еще раз</div>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${regSucces}">
                <div id="login-message" class="alert alert-success container">
                    <h3>Вы Зарегистрированы</h3>
                    <div id="succses-message">перейдите на страницу логина</div>
                </div>
                <div class="submit">
                <input type="submit" class="btn btn-primary btn-block " value="Перейти"/>
                </div>
            </c:if>

        </form>
    </div>
</div>
</body>
</html>

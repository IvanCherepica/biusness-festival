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
    <title>Registration</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        .form-control{
            width: 20%;
        }
        .submit{
            width: 20%;
        }
    </style>
</head>
<body>
<div align="center">
    <div class="main">
        <%--<c:if test="${isNotValid}">--%>
            <%--<p class="warning">Invalid name or password!</p>--%>
        <%--</c:if>--%>
            <h2>Регистрация</h2>
        <form action="${pageContext.servletContext.contextPath}/registration" method="POST">
            <div class="field">
                <input type="text" class="form-control" id="uName" name="name" placeholder="Login"/>
            </div>
            <br>
            <div class="field">
                <input type="password" class="form-control" id="uPass" name="password" placeholder="Password" />
            </div>
            <br>
            <div class="submit">
                <input type="submit" class="btn btn-primary btn-block" align="center" value="Submit"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>

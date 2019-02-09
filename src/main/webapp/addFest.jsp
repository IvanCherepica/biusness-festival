<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.03.2018
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> -->
<html>
<head
>
    <title>Add user</title>
    <style>
        .field {
            clear: both;
            text-align: right;
            line-height: 25px;
        }
        .gps {
            clear: both;
            text-align: right;
            line-height: 25px;

        }

        .submit {
            margin-top: 10px;
        }
        label {
            float: left;
            padding-right: 10px;
        }
        .main {
            width: 253px;
            margin: 0 auto;
        }
        .warning {
            color: red;
            background-color: khaki;
        }
    </style>

</head>
<body>
<div align="center">
    <h1>Создание фестиваля</h1>
    <div class="main">
        <form action="${pageContext.servletContext.contextPath}/admin/addFest" method="POST">
            <div class="field">
                <label for="uName">Название</label>
                <input type="text" id="uName" name="name" />
            </div>
            <div class="field">
                <label for="uDesc">Описание</label>
                <input type="text" id="uDesc" name="description" />
            </div>
            <div class="gps">

                <label for="ugps">GPS</label>

                <input  type="text" id="ugps" name="geometry" size="55"/>
            </div>

            <div class="submit">
                <input type="submit" align="center" value="Создать"/>
            </div>

        </form>
    </div>
</div>
</body>
</html>

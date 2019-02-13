<%--
  Created by IntelliJ IDEA.
  User: matvey
  Date: 10/02/2019
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Core Style CSS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        body {
            background: #eeeeee;
        }
        .header {
            padding: 0px 10px 0px 10px;
            margin-bottom: 0px;
        }
        .left-menu {
            background: white;
            padding: 20px 0 0 0;
            min-height: 94vh;
        }
        .left-menu ul li {
            margin: 0px;
        }
        .left-menu ul li a {
            border-radius: 0px;
        }
        .nav-content {
            margin-bottom: -1px;
        }
        .edit-form label {
            font-weight: 100;
            font-size: 20px;
            margin: 10px 0 10px 0;
        }
        .add-user-button {
            margin-top: 30px;
        }

         .usermap {
             margin: 10px 10px 10px 10px;
         }

        .container {
            background-color: white; /* Цвет фона слоя */
            padding: 5px; /* Поля вокруг текста */
            margin: 0 10px 10px 10px;
            width: auto; /* Ширина слоя */
            height: 200px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="panel panel-info">
            <div class="navbar navbar-inverse navbar-static-top header">
                <a class="navbar-brand" href="/user">Your Festival</a>
                <a class="navbar-brand pull-right" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</div>
<div class="usermap">
<jsp:include page="pageWithMap.jsp"/>
</div>
<div class="container">
    <h1>Hello user.</h1>
</div>
</body>
</html>

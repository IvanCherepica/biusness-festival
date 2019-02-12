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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
        $.ajax({
            url: "/rest/info",
            method: "get",
            async: true,
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                $("#userLogin").append("<p> Hello <b>" + data.user.name  + "</b></p>");
                $("#userLogin").append("<p> Your ID: " + data.user.id + "</p>");
                $("#userLogin").append("<p> Your Role: " + data.user.role + "</p>");
                if(data.isInFestival) {
                    $("#festivalInfo").append("<p> Hello <b>" + data.festivalId + "</b></p>");
                    $("#festivalInfo").append("<p> Your ID: " + data.festivalId + "</p>");
                    $("#festivalInfo").append("<p> Your Role: " + data.festivalId + "</p>");
                }
            }
        });
    </script>
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
        .edit-form label {
            font-weight: 100;
            font-size: 20px;
            margin: 10px 0 10px 0;
        }
        .nav-content {
            margin-bottom: -1px;
        }
        .usermap {
            margin: 10px 10px 10px 10px;
            z-index: 1;
            position: relative;
        }
        .main{
            position: relative;
            z-index: 0;
        }
        .informUser {
            position: absolute;
            width: 25%;
            height: 70%;
            right: 10px;
            padding: 10px;
            background: #ffffff;
            z-index: 2;
            opacity: 0.8;
            margin: 10px 10px 10px 10px;
        }
        #userInfo{
            background: #eeeeee;
            height: 40%;
        }
        #festivalInfo{
            background: #eeeeee;
            height: 58%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="panel panel-info">
            <div class="navbar navbar-inverse navbar-static-top header">
                <a class="navbar-brand" href="/user">Your Festival</a>
                <a class="navbar-brand" href="/user/profile">Profile</a>
                <a class="navbar-brand pull-right" href="/logout">Logout</a>
            </div>
        </div>

    </div>
</div>
<div class="main">
    <div class="informUser">
        <div id="userInfo">
            <div id="userImage"><img style="height: 50%; width: 50%" src="http://bootstraptema.ru/snippets/element/2016/profilesection/myprofile.jpg"></div>
            <div id="userLogin"></div>
        </div>
        <div id="festivalInfo">
            <div id="infp"></div>
        </div>
    </div>
    <div class="usermap">
        <jsp:include page="pageWithMap.jsp"/>
    </div>
</div>
<script>
</script>
</body>
</html>

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
        // $.ajax({
        //     url: "/rest/info",
        //     method: "get",
        //     async: true,
        //     error: function (message) {
        //         console.log(message);
        //     },
        //     success: function (data) {
        //         console.log(data);
        //         $("#userLg").append("<p> Hello <b>" + data.user.name + "</b></p>");
        //         $("#userLg").append("<p> Your ID: " + data.user.id + "</p>");
        //         $("#userLg").append("<p> Your Role: " + data.user.role + "</p>");
        //         if (data.isInFestival) {
        //             $("#festivalInfo").append("<p> You are at  <b>" + data.festival.name + "</b></p>");
        //         } else {
        //             $("#festivalInfo").append("<p> There is no information about any festival near you. Please fuck off</b></p>");
        //         }
        //     }
        // });
    </script>
    <style>
        body {
            background: #eeeeee;
        }
        .usermap {
            margin: 3px 3px 3px 3px;
            z-index: 1;
            position: relative;
        }
        .main{
            position: relative;
            z-index: 0;
        }

        .informUser {
            border-radius: 20px;
            position: absolute;
            width: 300px;
            height: 300px;
            right: 10px;
            padding: 10px;
            background: #ffffff;
            z-index: 2;
            opacity: 0.8;
            margin: 10px 10px 10px 10px;
        }

        #userBlock{
            border-radius: 20px;
            text-align: center;
            position: initial;
            width: initial;
            height: initial;
            right: 10px;
            z-index: 4;
            opacity: 1;
            background: #eeeeee;
            height: 270px;
        }
        #festivalBlock{
            border-radius: 20px;
            text-align: center;
            position: initial;
            width: initial;
            height: initial;
            z-index: 0;
            opacity: 1;
            background: #eeeeee;
            height: 0px;
            margin: 20px 0 0 0;
        }
    </style>
</head>
<body>
<div class="main">
    <div id="informUserBlock" class="informUser">
        <div id="userBlock">
            <div id="userImage"></div>
            <div id="userLg"></div>
        </div>
        <div id="festivalBlock">
            <p id="festivalInfo1"></p>
            <p id="festivalInfo2"></p>
        </div>
    </div>
    <div class="usermap">
        <jsp:include page="pageWithMap.jsp"/>
    </div>
</div>
</body>
</html>

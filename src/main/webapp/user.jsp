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
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <style>
        body {
            background: #eeeeee;
        }
        .usermap {
            margin: 3px 3px 3px 3px;
            /*z-index: 1;*/
            position: relative;
        }
        .container{
            width: 568px!important;
        }
        .main{
            position: relative;
            /*z-index: 0;*/
        }

        .informUser {
            border-radius: 20px;
            position: absolute;
            width: 300px;
            height: 293px;
            right: 10px;
            padding: 10px;
            background: #D3D4DA;
            z-index: 2000;
            opacity: 0.8;
            margin: 10px 10px 10px 10px;
            color: #292929;
        }

        #userBlock{
            border-radius: 20px;
            text-align: center;
            position: initial;
            width: initial;
            height: initial;
            right: 10px;
            z-index: 2001;
            opacity: 1;
            background: #ffffff;
            height: 270px;
        }
        #festivalBlock {
            border-radius: 20px;
            text-align: center;
            position: initial;
            width: initial;
            height: initial;
            z-index: 2003;
            opacity: 1;
            background: #eeeeee;
            height: 0px;
            margin: 20px 0 0 0;
        }

        #events{
            margin: 10px 10px 10px 10px;
        }


        .rectangle {
            all: initial;
            counter-reset: li;
            list-style: none;
            font: 14px "Trebuchet MS", "Lucida Sans";
            padding: 0;
            text-shadow: 0 1px 0 rgba(255,255,255,.5);
        }
        .rectangle a {
            position: relative;
            display: block;
            padding: .4em .4em .4em .8em;
            margin: .5em 0 .5em 2.5em;
            background: #D3D4DA;
            color: #444;
            text-decoration: none;
            transition: all .3s ease-out;
        }
        .rectangle a:hover {background: #DCDDE1;}

        .rectangle a:before {
            content: counter(li);
            counter-increment: li;
            position: absolute;
            left: -2.5em;
            top: 50%;
            margin-top: -1em;
            background: #9097A2;
            height: 2em;
            width: 2em;
            line-height: 2em;
            text-align: center;
            font-weight: bold;
        }
        .rectangle a:after {
            position: absolute;
            content: "";
            border: .5em solid transparent;
            left: -1em;
            top: 50%;
            margin-top: -.5em;
            transition: all .3s ease-out;
        }
        .rectangle a:hover:after {
            left: -.5em;
            border-left-color: #9097A2;
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
            <div>
                <ol id="events" class="rectangle">
                </ol>
            </div>
        </div>
    </div>
    <div class="usermap">
        <jsp:include page="userMap/pageWithMap.jsp"/>
    </div>
</div>



</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: NICK
  Date: 15.02.2019
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Карта с пользователями</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=e6f8dfbf-7c6d-464f-9a6a-4308cb58f188&lang=ru_RU"
            type="text/javascript">
    </script>

</head>

<body>
    <div class="map" id="map" style="width: 100%; height: 100%">
    </div>

    <script type="text/javascript">
        <%@include file="/admin/adminGetData.js" %>
    </script>

    <script type="text/javascript">
        <%@include file="/admin/adminMap.js" %>
    </script>

    <script type="text/javascript">
        <%@include file="/admin/adminMapUsersHandler.js" %>
    </script>

    <script>
        ymaps.ready(startPageWithMap);
        startUsersHandler();
    </script>

</body>
</html>

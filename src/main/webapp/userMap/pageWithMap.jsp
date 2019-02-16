<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Geoposition</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >

    <script src="https://api-maps.yandex.ru/2.1/?apikey=e6f8dfbf-7c6d-464f-9a6a-4308cb58f188&lang=ru_RU"
            type="text/javascript">
    </script>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript">
        <%@include file="userMapData.js" %>
    </script>
    <script type="text/javascript">
        <%@include file="userNotification.js" %>
    </script>
    <script type="text/javascript">
        <%@include file="poligonActions.js" %>
    </script>

    <style>
        .check-material input[type="checkbox"]{display:none}
        .check-material input[type="checkbox"]:checked + label{background:#009688;border-color:#009688}
        .check-material input[type="checkbox"]:checked + label:after{transform:scale(1.3)}
        .check-material label{display:block;box-sizing:border-box;width:25px;height:25px;background:#CCC;border:5px solid #7A7A7A;border-radius:10%;position:relative;cursor:pointer;transition:.2s}
        .check-material label:before{content:'';position:absolute;display:block;height:300%;width:300%;top:-100%;left:-100%;z-index:-1;border-radius:50%;transition:.3s}
        .check-material label:after{content:'';display:block;height:100%;width:100%;background:url(https://cbwconline.com/IMG/Codepen/Check.svg) center center no-repeat;background-size:contain;transform:scale(0);transition:.2s}
        .check-material label:active:before{background:#bfbfbf}
    </style>
<%--<script type="text/javascript">--%>
    <%--<%@include file="mapDataReceive.js" %>--%>
<%--</script>--%>

</html>
    <script type="text/javascript">

        connect();

        // get запрос GeometryServlet, get user data
        $.ajax({
            url: "/rest/geometry/get-all-festivals",
            method: "get",
            async: true,
            error: function (message) {
                console.log(message);
            },
            success: function (data) {

                array = data;

                //получение текщей геопопзиции.
                navigator.geolocation.getCurrentPosition(function (position) {

                    x = position.coords.latitude;
                    y = position.coords.longitude;

                    //загрузка данных в карту.
                    ymaps.ready(init);
                });

            }
        });


        //regular update user position
        function geo_success(position) {
            x = position.coords.latitude;
            y = position.coords.longitude;

           // window.setInterval(function(){
                if (myMap != undefined) {

                    if (myPlacemark != undefined) myMap.geoObjects.remove(myPlacemark);

                    //обновляем местоположение метки
                    myPlacemark = new ymaps.Placemark([x, y], {
                        hitContent: 'Hello',
                        balloonContent: 'It is you'
                    }, {
                        iconLayout: 'default#image',
                        // iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
                        // iconImageSize: [32, 32],
                        // iconImageOffset: [-15, -15]
                    });
                    // добавляем метку на карту
                    myMap.geoObjects.add(myPlacemark);
                }

                if (webSocketClient != undefined && userName!= undefined && userID != undefined) {
                    var message = '{ "coordinates": "' + x + " " + y + '", "userName" : "' +  userName + '", "userID" : "' + userID + '"}';
                    console.log("send to server: " + message);
                    //var jsonObj = {"x" : userX, "y" : userY};
                    //webSocketClient.send(JSON.stringify(jsonObj));
                    webSocketClient.send(message);
                }

                //----------------------------------------------------------------------------------------------------------------
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ!
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //----------------------------------------------------------------------------------------------------------------
                // $.ajax({
                //     url: "/user/geoposition",
                //     method: "post",
                //     async: true,
                //     data: {longitude: position.coords.latitude, latitude: position.coords.longitude},
                //     error: function(message) {
                //         console.log(message);
                //     },
                //     success: function(data) {
                //
                //     }
                // });
                //----------------------------------------------------------------------------------------------------------------
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //----------------------------------------------------------------------------------------------------------------
     //       }, 5000);
        }


        function errorCallback(error){
        alert('ERROR(' + error.code + '): ' + error.message);
        }

        var geo_options = {
            enableHighAccuracy: true,
            maximumAge        : 0,
            timeout           : 5000
        };

        navigator.geolocation.watchPosition(geo_success,errorCallback,geo_options);



        // инициализация карты.
        function init() {

            myMap = new ymaps.Map("map", {
                // определение центра.

                center: [x, y],
                // приближение.
                zoom: 18,
                controls: ['zoomControl'],
                behaviors: ['drag']

            });
            //объект метки

            myPlacemark = new ymaps.Placemark([x,y], {
                hitContent: 'Hello',
                balloonContent: 'It is you'
            }, {
                iconLayout: 'default#image',
                // iconImageHref:'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
                // iconImageSize: [32, 32],
                // iconImageOffset: [-15, -15]
            });
            // добавляем метку на карту
            myMap.geoObjects.add(myPlacemark);

            //draw festivals
            for (var i = 0; i < array.length; i++) {

                var mapObject = array[i];

                // объект геометрицесской фигуры
                myPolygon = new ymaps.Polygon(
                    JSON.parse(mapObject.geometry), // Указываем координаты вершин многоугольника, являющиеся массивом в формате JSON.
                    // {
                    //     balloonContent: mapObject.name
                    // }, // Содержимое балуна.
                    {
                        hintContent: mapObject.name
                    },
                    {
                        strokeColor: mapObject.color,
                        strokeOpacity: 1,
                        fillColor: mapObject.color, //Цвет обводки и цвет поля.
                        //fillMethod: 'stretch', // Тип заливки фоном
                        opacity: 0.5,

                        // stroke: falseУбираем видимость обводки.
                    }
                );

                // Добавляем многоугольник на карту.
                myMap.geoObjects.add(myPolygon);
                festivalPolygons[mapObject.id] = myPolygon;
                myPolygon.events.add('click', function (event) {

                    festilvalPoligonOnClick(event);

                });
            }

            //draw event points
            GetEventPoints();

        }


    </script>
</head>
<body >
    <div class="container-fluid">
        <div class="map" id="map" style="width: 100%; height: 100%">
        </div>
    </div>

<!-- Modal -->
<div id="festival_list_Modal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"><a id="festival_list_title"></a></h4>--%>
            <%--</div>--%>
            <div id="festival_list_topWindow" class="modal-body">
                <div id="festival_list_body" class="container">
                    <%--<button id="festival_list_close" type="button" class="close" data-dismiss="modal">&times;</button>--%>
                    <%--<h2><a id="festival_list_title"></a></h2>--%>
                    <%--<p id="festival_list_title_description"></p>--%>
                    <%--<table id="festival_list_table" class="table table-striped">--%>
                    <%--</table>--%>
                </div>
            </div>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
            <%--</div>--%>
        </div>

    </div>
    <%--<div class='customAlert'>--%>
        <%--<p class='message'></p>--%>
        <%--<input type='button' class='confirmButton' value='Ok'>--%>
    <%--</div>--%>
    <%--<input type='button' class='rab' value='Raise alert'>--%>
</div>




</body>
</html>
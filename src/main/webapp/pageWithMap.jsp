<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Geoposition</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


    <script src="https://api-maps.yandex.ru/2.1/?apikey=e6f8dfbf-7c6d-464f-9a6a-4308cb58f188&lang=ru_RU"
            type="text/javascript">
    </script>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="userNotification.js" %>
    </script>
    <script type="text/javascript">
        <%@include file="userMap/poligonActions.js" %>
    </script>

<%--<script type="text/javascript">--%>
    <%--<%@include file="mapDataReceive.js" %>--%>
<%--</script>--%>




</html>
    <script type="text/javascript">

        connect();

        // в массиве будут храниться координаты геометрической фигуры.
        var array;

        //Карта, метка на карте.
        //Ширина,долгота.
        var myMap, myPlacemark;
        var x, y;
        var myPolygon;
        var a, b;

        // get запрос GeometryServlet
        $.ajax({
            url: "/rest/geometry",
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
                balloonContent: 'Its me',
                hitContent: 'Hello'

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
<div class="map" id="map" style="width: 100%; height: 100%">
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
</div>
</body>
</html>
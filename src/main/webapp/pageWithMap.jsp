<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Geoposition</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


    <script src="https://api-maps.yandex.ru/2.1/?apikey=e6f8dfbf-7c6d-464f-9a6a-4308cb58f188&lang=ru_RU" type="text/javascript">
    </script>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="userNotification.js" %>
    </script>

    <script type="text/javascript">

        connect();

        // в массиве будут храниться координаты геометрической фигуры.
        var array;

        //Карта, метка на карте.
        //Ширина,долгота.
        var myMap, myPlacemark;
        var x,y;

        // get запрос GeometryServlet
        $.ajax({
            url: "/rest/geometry",
            method: "get",
            async: true,
            error: function(message) {
                console.log(message);
            },
            success: function(data) {

                array = data;

                // получение текщей геопопзиции.
                navigator.geolocation.getCurrentPosition(function(position) {

                    x = position.coords.latitude; y = position.coords.longitude;

                    //загрузка данных в карту.
                    ymaps.ready(init);
                });

            }
        });

        // инициализация карты.
        function init(){

            myMap = new ymaps.Map("map", {
                // определение центра.

                center: [x, y],
                // приближение.
                zoom: 18,
                controls: ['zoomControl'],
                behaviors: ['drag']

            });
            //объект метки
            myPlacemark = new ymaps.Placemark([x,y],{
                balloonContent: 'Its me',
                hitContent: 'Hello'
            });
            // добавляем метку на карту
            myMap.geoObjects.add(myPlacemark);

            //console.log("Map array " + array );
            // объект геометрицесской фигуры
            var myPolygon = new ymaps.Polygon([
                    // Указываем координаты вершин многоугольника, являющиеся массивом
                    // в формате JSON.
                    array
                ],
                // Описываем свойства геообъекта.
                {
                    // Содержимое балуна.
                    balloonContent: "Фестиваль JAVA BOOTCAMP"
                }, {
                    // Описываем опции геообъекта.
                    // Цвет обводки и цвет поля.
                    strokeColor: "#FFFFFF",

                    fillColor: "#FF1F1F70",
                    // Тип заливки фоном.
                    fillMethod: 'stretch',
                    // Убираем видимость обводки.
                    //stroke: false
                }
            );
            // Добавляем многоугольник на карту.
            myMap.geoObjects.add(myPolygon);
        }


    </script>
</head>
<body >
<div class="map" id="map" style="width: 100%; height: 100%">
</div>
</body>
</html>
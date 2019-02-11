<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Geoposition</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


    <script src="https://api-maps.yandex.ru/2.1/?apikey=e6f8dfbf-7c6d-464f-9a6a-4308cb58f188&lang=ru_RU" type="text/javascript">
    </script>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>


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

                console.log(data);

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
                zoom: 16
            });
            //объект метки
            myPlacemark = new ymaps.Placemark([x,y],{
                balloonContent: 'Its me',
                hitContent: 'Hello'
            });
            // добавляем метку на карту
            myMap.geoObjects.add(myPlacemark);


            for (var i = 0; i < array.length; i++) {
                var polygonCoords = array[i].geometry;
                console.log(polygonCoords);

                //console.log("Map array " + array );
                // объект геометрицесской фигуры
                var myPolygon = new ymaps.Polygon(
                        // Указываем координаты вершин многоугольника, являющиеся массивом
                        // в формате JSON.
                        JSON.parse(polygonCoords),
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
                        fillMethod: 'stretch'
                        // Убираем видимость обводки.
                        //stroke: false
                    }
                );
                // Добавляем многоугольник на карту.
                myMap.geoObjects.add(myPolygon);
            }
        }
    </script>
</head>
<body >
<div id="map" style="width: 600px; height: 400px">

    test

</div>
</body>
</html>
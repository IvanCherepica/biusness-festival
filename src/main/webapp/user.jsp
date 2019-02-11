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
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
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
                    ymaps.ready(init);
                });
                //$('#somediv').append(data.name);
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
    <title>User</title>
</head>
<body>
<div id="map" style="width: 600px; height: 400px"></div>
</body>
</html>

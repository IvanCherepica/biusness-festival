
var myMap;

function startPageWithMap() {

    console.log("[PageWithMap] Starting page... Need User location");

    if(navigator.geolocation) {
        getAdminLocation(ShowMap);
    }else{
        document.getElementById("map").innerHTML = "[adminMap.js] Geolocation API не поддерживается в вашем браузере";
    }
}


function getAdminLocation(callback) {

    navigator.geolocation.getCurrentPosition(function (position) {

        var receivedX = position.coords.latitude; //x - latitude, y - longitude
        var receivedY = position.coords.longitude;

        console.log("[adminMap.js] Coordinates received. latitude(x)=" + receivedX + "; longitude(y)=" + receivedY);

        if (callback != undefined) callback(receivedX, receivedY);

    }, function (positionError) {
        console.log(positionError);
    });
}


function ShowMap(xCenterMap, yCenterMap){
    if (myMap == undefined) {
        console.log("[adminMap.js] Creating map...");
        myMap = new ymaps.Map("map", {
            center: [xCenterMap, yCenterMap], //x - latitude, y - longitude
            zoom: 18,
            controls: ['zoomControl'],
            behaviors: ['drag']
        });
    }

    GetFestivals();
}

function GetFestivals() {
    // get запрос GeometryServlet, get user data
    $.ajax({
        url: "/rest/geometry/get-all-festivals",
        method: "get",
        async: true,
        error: function (message) {
            console.log(message);
        },
        success: function (data) {

            DrawFestivals(data);

        }
    });

}




function DrawFestivals(array) {

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


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

    GetFestivalsForAdmin(DrawMapUnits);
    // GetEventPoints(DrawMapUnits);
}


function DrawMapUnits(arrayOfMapUnits){
    for (var i = 0; i < arrayOfMapUnits.length; i++) {

        var mapObject = arrayOfMapUnits[i];

        console.log("[adminMap.js] DrawMapUnit: name: " +mapObject.name + " fillColor: " + mapObject.color);
        console.log("[adminMap.js] DrawMapUnit: geometry: " + mapObject.geometry);


        // объект геометрицесской фигуры
        var myPolygon = new ymaps.Polygon(
            JSON.parse(mapObject.geometry), // Указываем координаты вершин многоугольника, являющиеся массивом в формате JSON.
            //{ balloonContent: mapObject.name}, // Содержимое балуна.
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


        // var xc = mapObject.center.substr(0,str.indexOf(' '));
        //
        // console.log(xc);


        // var myCircle = new ymaps.Circle([
        //     // Координаты центра круга.
        //     JSON.parse(mapObject.center),
        //     // Радиус круга в метрах.
        //     10000
        // ], {
        //     // Описываем свойства круга.
        //     // Содержимое балуна.
        //     balloonContent: "Радиус круга - 10 км",
        //     // Содержимое хинта.
        //     hintContent: "Подвинь меня"
        // }, {
        //     // Задаем опции круга.
        //     // Включаем возможность перетаскивания круга.
        //     draggable: true,
        //     // Цвет заливки.
        //     // Последний байт (77) определяет прозрачность.
        //     // Прозрачность заливки также можно задать используя опцию "fillOpacity".
        //     fillColor: "#DB709377",
        //     // Цвет обводки.
        //     strokeColor: "#990000",
        //     // Прозрачность обводки.
        //     strokeOpacity: 0.8,
        //     // Ширина обводки в пикселях.
        //     strokeWidth: 1
        // });
        // myMap.geoObjects.add(myCircle);// Добавляем круг на карту.
    }
}
var festivalPolygons= {};



function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
}

function festilvalPoligonOnClick(event) {
    var currentPoligon = event.get('target');
    var currentPoligonID = getKeyByValue(festivalPolygons, currentPoligon);

    $("#festival_list_title").text(currentPoligonID);

    var eventPoinList = GetEventPointsForFesival(currentPoligonID);

    $("#festival_list_Modal").modal('show');
    $("#error-edit-message").removeClass('hidden');


    //console.log('polygon clicked. currentPoligonID:' + currentPoligonID);
}


function GetEventPoints(){
    console.log("[GetData] Receiving event points...");

    // if (festival_id == undefined) {
    //
    // }

    // get запрос EventPointToMapServlet
    $.ajax({
        url: "/user/event-to-map",
        method: "get",
        async: true,
        error: function (message) {
            console.log(message);
        },
        success: function (data) {

            console.log("[GetData] EventPoints received. Count=" + data.length);

            DrawMapUnits(data);
        }
    });
}

function GetEventPointsForFesival(festival_id){
    console.log("[GetData] Receiving event points...");

    // if (festival_id == undefined) {
    //
    // }

    // get запрос EventPointToMapServlet
    $.ajax({
        url: "/user/event-to-map",
        method: "get",
        async: true,
        data: {festival_id : festival_id},
        error: function (message) {
            console.log(message);
        },
        success: function (data) {

            console.log("[GetData] EventPoints received. Count=" + data.length);

            return data;

        }
    });
}

function DrawMapUnits(arrayOfMapUnits){
    for (var i = 0; i < arrayOfMapUnits.length; i++) {

        var mapObject = arrayOfMapUnits[i];

        console.log("[PageWithMap] DrawMapUnit: name: " +mapObject.name + " fillColor: " + mapObject.color);
        console.log("[PageWithMap] DrawMapUnit: geometry: " + mapObject.geometry);


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
                opacity: 0.3,
                // stroke: falseУбираем видимость обводки.
            }
        );

        // Добавляем многоугольник на карту.
        myMap.geoObjects.add(myPolygon);
    }
}


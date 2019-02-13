var festivalPolygons= {};


function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
}

function festilvalPoligonOnClick(event) {
    var currentPoligon = event.get('target');
    var currentPoligonID = getKeyByValue(festivalPolygons, currentPoligon);

    GetEventPointsForFesival(currentPoligonID);

    //eventPointList.each()

    //console.log('polygon clicked. currentPoligonID:' + currentPoligonID);
}


function openListOfFesivalEventPoints(data,festival_id) {
    $("#festival_list_title").append("<p> " + data.festival.name + "</p>");
    $("#festival_list_title_description").text(data.festival.description);

    //Column names
    $("#festival_list_table").append("<thead> <tr><th>Event Point</th><th>Description</th><th>Working time</th></tr></thead><tbody>");


    $.each(JSON.parse(data.eventPointsGson), function (index, value) {
        console.log(value.name);
        $("#festival_list_table").append("<tr><td>" + value[4] + "</td><td>" + value[2] + "</td><td></td></tr>");

        //$("#festival_list_title").append("<p> " + value[4] + "</p><p> " + value[2] + "</p>");

    });

    $("#festival_list_table").append("</tbody>");
    $("#festival_list_Modal").modal('show');
    $("#error-edit-message").removeClass('hidden');

}


function GetEventPoints(){
    console.log("[GetData] Receiving event points...");


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

    // get запрос EventPointToMapServlet
    $.ajax({
        url: "/map/data_for_festivalList",
        method: "get",
        async: true,
        data: {festival_id : festival_id},
        error: function (message) {
            console.log(message);
        },
        success: function (data) {

            console.log("[GetData] EventPoints received. Count=" + data.length);

            openListOfFesivalEventPoints(data,festival_id);

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
                opacity: 0.5,
                // stroke: falseУбираем видимость обводки.
            }
        );

        // Добавляем многоугольник на карту.
        myMap.geoObjects.add(myPolygon);
    }
}


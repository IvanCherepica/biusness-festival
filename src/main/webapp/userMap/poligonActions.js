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
    $("#festival_list_body").append("<button id=\"festival_list_close\" type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>");

    $("#festival_list_body").append("<h2>" + data.festival.name + "</h2>");
    $("#festival_list_body").append("<p>" + data.festival.description + "</p>");


    $("#festival_list_body").append("<table id=\"festival_list_table\" class=\"table table-striped\"></table>");
    //Column names
    $("#festival_list_table").append("<thead> <tr><th>Event Point</th><th>Description</th><th>Working time</th></tr></thead><tbody>");

    //fill the table
    $.each(data.eventPointList, function (index, value) {

        $("#festival_list_table").append("<tr href=\"javascript:void(0)\" onclick=\"moveMapCentrToPoint('"+ value.center + "', '"+ value.id + "')\" id='" + value.id + "' ><td>" + value.name + "</td><td>" + value.description + "</td><td></td></tr>");

    });

    $("#festival_list_table").append("</tbody>");
    $("#festival_list_Modal").modal('show');

    //clear modal window after close
    $("#festival_list_Modal").on("hide.bs.modal", function (e) {
        // $("#festival_list_table > tbody > tr").each(function (index, value) {
        //         console.log("index : " + index + "; value : " + $(value).attr('id'));
        // });


        $("#festival_list_body").empty();


    });
    $("#error-edit-message").removeClass('hidden');

}



function moveMapCentrToPoint(centr, eventPoinID) {
    console.log("centr : " + centr + " eventPoinID : " + eventPoinID);
    var centrX = centr.split(" ")[0];
    var centrY = centr.split(" ")[1];


    $("#festival_list_close").click();
    $("#festival_list_table").remove();
    if (centrX!=undefined&&centrY!=undefined) {
        myMap.setCenter([Number(centrX), Number(centrY)], 17, {checkZoomRange: true});
    }

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



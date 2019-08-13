function getDataForFestival(fesivalId) {
    $.ajax({
        url: "/user/data_for_festival",
        method: "get",
        async: true,
        data: {fesivalId: fesivalId},
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            console.log(data);
            processDataForFestivalBlock(data);
            //processDataForFestivalBlock(event, eventspoints);
        }
    });

}

function getUserSchedule() {
    $.ajax({
        url: "/user/get_user_schedule",
        method: "get",
        async: true,
        data: {userId: userID},
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            console.log(JSON.stringify(data));
            fillUserSchedule(data);
        }
    });


}


function fillUserSchedule(data) {
    $("#user_schedule").empty();

    $("#user_schedule").append("<table id=\"schedule_table\" class=\"table table-striped\"></table>");

    $.each(data.eventsList, function (index, value) {

        // $("#schedule_table").append("<tr href=\"javascript:void(0)\" onclick=\"moveMapCentrToPoint('"+ value.center + "', '"+ value.id + "')\" id='" + value.id + "' ><td>" + value.name + "</td><td>" + value.description + "</td></tr>");
        $("#schedule_table").append("<tr id='" + value.id + "' ><td>" + value.name + "</td><td>" + value.description + "</td></tr>");

    });


}


function processDataForFestivalBlock(data) {
    console.log(data);
    var stringBuilder = "";
    if (data !== undefined) {
        $("#festivalInfo1").text(data.name);
        //$("#festivalInfo1").arguments(onclick=moveMapCentrToPoint(data.coordinates));


        $("#festivalInfo2").text(data.description);
        $(".informUser").css('width', '300');

        if (flag === false) {
            stringBuilder += ("<h4><b>Todays events</b></h4>");
            for (var i = 0; i < data.eventPointsList.length; i++) {
                var j = i + 1;
                stringBuilder += ("<li style='margin: 3px 3px 3px 3px;'><a href=\"#\"><button style='color: #fff; /* цвет текста */\n" +
                    "  text-decoration: none; \n" +
                    "  user-select: none; \n" +
                    "  background: rgb(212,75,56); \n" +
                    "  border-radius: 20px; \n" +
                    "  height: 6% ; \n" +
                    "  width: 100%; \n" +
                    "  padding: .1em 0.5em; \n" +
                    "  outline: none; ' data-toggle=\"collapse\" data-target=\"#demo" + j
                    + "\">" + data.eventPointsList[i].name + "</button></li></a>\n" +
                    "<div id=\"demo" + j + "\" class=\"collapse\"><ol>");
                for (var k = 0; k < data.eventPointsList[i].eventsList.length; k++) {
                    stringBuilder += ("<li>" + data.eventPointsList[i].eventsList[k].name + "</li>");
                }
                if (i === data.eventPointsList.length - 1) {
                    flag = true;
                }
                stringBuilder += ("</ol></div>");
            }
        }
        $("#events").append(stringBuilder);
    }
}

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
                console.log(JSON.stringify(data));
                processDataForFestivalBlock(data)
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
    console.log(eventspoints);
    if(event!==undefined && eventspoints!==undefined) {
        isInFestival = JSON.parse(event.data).isInFestival;
        festival = JSON.parse(event.data).festival;
        $("#festivalInfo1").text("You are at " + festival.name);
        $("#festivalInfo2").text("About: " + festival.description);
        $("#informUserBlock").css('height', '770');
        $("#festivalBlock").css('height', '450');
        if (flag === false) {
            $("#events").append("<h4><b>Todays events</b></h4>");
            for (var i = 0; i < eventspoints.length; i++) {
                var j = i + 1;
                $("#events").append("<li style='margin: 3px 3px 3px 3px;'><a href=\"#\"><button style='color: #fff; /* цвет текста */\n" +
                    "  text-decoration: none; /* убирать подчёркивание у ссылок */\n" +
                    "  user-select: none; /* убирать выделение текста */\n" +
                    "  background: rgb(212,75,56); /* фон кнопки */\n" +
                    "  padding: .1em 0.5em; /* отступ от текста */\n" +
                    "  outline: none; /* убирать контур в Mozilla */' data-toggle=\"collapse\" data-target=\"#demo" + j
                    + "\">"+ eventspoints[i].name +"</button></li></a>\n" +
                    "\n" +
                    "<div id=\"demo" + j + "\" class=\"collapse\">\n" +
                    "Description:"+ eventspoints[i].description +"\n" +
                    "</div>");
                if (i === eventspoints.length - 1) {
                    flag = true;
                }
            }
        }
    }
}
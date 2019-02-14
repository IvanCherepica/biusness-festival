
var userName;
var userID;
var isInFestival;
var festival;
var user;

// get request for user name and id
$.ajax({
    url: "/rest/userdata",
    method: "get",
    async: true,
    error: function(message) {
        console.log(message);
    },
    success: function(data) {
        processDataForUserPage(data);
    }
});


//send message to server with user coordinates
function sendMessage(webSocketClient) {
        navigator.geolocation.watchPosition(function (position) {
            userX = position.coords.latitude;
            userY = position.coords.longitude;

            var message = '{ "coordinates": "' + userX + " " + userY + '", "userName" : "' +  userName + '", "userID" : "' + userID + '"}';
            //var jsonObj = {"x" : userX, "y" : userY};
            //webSocketClient.send(JSON.stringify(jsonObj));
            webSocketClient.send(message);
            newPlacemark(myMap, userX, userY);
        });


}

//connect to server
function connect() {
    var webSocketClient = new WebSocket("ws://localhost:8080/compareLocations");
    webSocketClient.onopen = function (event) {
        console.log("onopen " );
        sendMessage(webSocketClient);
        };
    webSocketClient.onmessage = function (event) {
            var messageToUser = JSON.parse(event.data).message;
            processDataForFestivalBlock(event, festivalEvents);
            if (messageToUser.localeCompare("") != 0 ) {
                sendWelcomMessage(messageToUser);
            }
            var messaaaage = "dsdsdsdsds";
            sendMessage(messaaaage);
        };
    webSocketClient.onclose = function (event) {
            console.log("close");

        }
}


function sendWelcomMessage(message) {
    confirm(message);
}

function newPlacemark(myMap, x ,y) {
    if (myMap != undefined) {

        myMap.geoObjects.remove(myPlacemark);
        //обновляем местоположение метки
        myPlacemark = new ymaps.Placemark([x, y], {
            hitContent: 'Hello',
            balloonContent: 'It is you'
        }, {
            iconLayout: 'default#image',
            iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
            iconImageSize: [32, 32],
            iconImageOffset: [-15, -15]
        });
        // добавляем метку на карту
        myMap.geoObjects.add(myPlacemark);
    }
}


function processDataForUserPage(data) {
    userName = data.name;
    userID = data.id;
    isInFestival = data.isInFestival;
    user = data.user;
    console.log("userName " + userName + "; userID " + userID + "; isInFestival " + isInFestival);
    $("#userLg").append("<img " +
        "style=\"margin-top: 10px; " +
        "border-radius: 50%;" +
        " opacity: 0.8; " +
        "width: 50%; height: 50%\" " +
        "src=\"" + user.imagePath +"\">");

    $("#userLg").append("<p> Hello <b>" + userName + "</b></p>");
    $("#userLg").append("<p> Your ID: " + userID + "</p>");
    $("#userLg").append("<p> Your Role: " + data.user.role + "</p>");
}

var flag = false;

function processDataForFestivalBlock(event, eventspoints) {
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
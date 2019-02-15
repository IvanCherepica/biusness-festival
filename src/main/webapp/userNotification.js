
var userName;
var userID;
var isInFestival;
var festival;
var user;


var userX;
var userY;
var webSocketClient;

//getPositionTimeless();

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


// //send message to server with user coordinates
// function sendMessage(webSocketClient) {
//         navigator.geolocation.getCurrentPosition(function (position) {
//             userX = position.coords.latitude;
//             userY = position.coords.longitude;
//             var message = '{ "coordinates": "' + userX + " " + userY + '", "userName" : "' +  userName + '", "userID" : "' + userID + '"}';
//             //var jsonObj = {"x" : userX, "y" : userY};
//             //webSocketClient.send(JSON.stringify(jsonObj));
//             webSocketClient.send(message);
//             newPlacemark(myMap);
//
//         });
// }

//connect to server
function connect() {
    webSocketClient = new WebSocket("wss://594d738d.ngrok.io/compareLocations");

    webSocketClient.onopen = function (event) {
        console.log("onopen. event=" + event );
        MyWatchPosition(SendCoordinates);
        };

    webSocketClient.onmessage = function (event) {
        console.log("onmessage. event=" + event );



        var messageToUser = JSON.parse(event.data).message;
            processDataForFestivalBlock(event, festivalEvents);

            if (messageToUser.localeCompare("") != 0 ) {
                sendWelcomMessage(messageToUser);
            }

            //MyWatchPosition(SendCoordinates);

        };

    webSocketClient.onclose = function (event) {
            console.log("onclose. event" + event);
            connect();
        }

    webSocketClient.onerror = function (event) {
        console.log("error " + event);

    }
}


function sendWelcomMessage(message) {
    confirm(message);
}


function SendCoordinates(xxx, yyy) {

    var message = '{ "coordinates": "' + xxx + " " + yyy + '", "userName" : "' +  userName + '", "userID" : "' + userID + '"}';
    console.log("webSocketClient.onmessage=" + message);
    newPlacemark(myMap, xxx, yyy);
    webSocketClient.send(message);
};


function newPlacemark(myMap, x ,y) {
    if (myMap != undefined) {

        if (myPlacemark != undefined) myMap.geoObjects.remove(myPlacemark);

        //обновляем местоположение метки
        myPlacemark = new ymaps.Placemark([x, y], {
            hitContent: 'Hello',
            balloonContent: 'It is you'
        }, {
            iconLayout: 'default#image'
            //iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
            //iconImageSize: [32, 32],
            //iconImageOffset: [-15, -15]
        });
        // добавляем метку на карту
        myMap.geoObjects.add(myPlacemark);
    }
}
// function newPlacemark(myMap) {
//     if (myMap != undefined) {
//
//         // получение текщей геопопзиции.
//         navigator.geolocation.getCurrentPosition(function (position) {
//
//             x = position.coords.latitude;
//             y = position.coords.longitude;
//
//             myMap.geoObjects.remove(myPlacemark);
//             //обновляем местоположение метки
//             myPlacemark = new ymaps.Placemark([x, y], {
//                 hitContent: 'Hello',
//                 balloonContent: 'It is you'
//                 }, {
//                     iconLayout: 'default#image',
//                     iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
//                     iconImageSize: [32, 32],
//                     iconImageOffset: [-15, -15]
//                 });
//             // добавляем метку на карту
//             myMap.geoObjects.add(myPlacemark);
//             //setTimeout(newPlacemark(myMap),10000);
//         });
//     }
// }


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




function MyWatchPosition(callback) {
    console.log("MyWatchPosition...")

    var options = {
        timeout: 5000, // timeout at 60000 milliseconds (60 seconds)
        enableHighAccuracy: true,
        maximumAge: 0
    };
    //https://developer.mozilla.org/en-US/docs/Web/API/PositionOptions



    navigator.geolocation.watchPosition(function (position) {
        x = position.coords.latitude;
        y = position.coords.longitude;

        userX = x;
        userY = y;

        console.log(x, y);

        SendCoordinates(x, y);

        //if (callback != undefined) callback(x, y);


    }, function (err) {
            console.log(err);
            if(err.code == 1) {
                alert("Error: Access is denied!");
            } else if( err.code == 2) {
                alert("Error: Position is unavailable!");
            }
        },
        options
    );


}
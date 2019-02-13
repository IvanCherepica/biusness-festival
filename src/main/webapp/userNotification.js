
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
        userName = data.name;
        userID = data.id;
        isInFestival = data.isInFestival;
        user = data.user;
        console.log("userName " + userName + "; userID " + userID + "; isInFestival " + isInFestival);
        $("#userLg").append("<img " +
            "style=\"margin-top: 10px; " +
            "border-radius: 50%;" +
            " opacity: 0.8; " +
            "width: 50%; height: 55%\" " +
            "src=\"http://bootstraptema.ru/snippets/element/2016/profilesection/myprofile.jpg\">");

        $("#userLg").append("<p> Hello <b>" + userName + "</b></p>");
        $("#userLg").append("<p> Your ID: " + userID + "</p>");
        $("#userLg").append("<p> Your Role: " + data.user.role + "</p>");
    }
});


//send message to server with user coordinates
function sendMessage(webSocketClient) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userX = position.coords.latitude;
            userY = position.coords.longitude;
            var message = '{ "coordinates": "' + userX + " " + userY + '", "userName" : "' +  userName + '", "userID" : "' + userID + '"}';
            //var jsonObj = {"x" : userX, "y" : userY};
            //webSocketClient.send(JSON.stringify(jsonObj));
            webSocketClient.send(message);
            newPlacemark(myMap);

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
            console.log("event.data " + event.data);
            var messageToUser = JSON.parse(event.data).message;
            isInFestival = JSON.parse(event.data).isInFestival;
            festival = JSON.parse(event.data).festival;
            $("#festivalInfo1").text("You are at " + festival.name);
            $("#festivalInfo2").text("About: " + festival.description);
            $("#informUserBlock").css('height','770');
            $("#festivalBlock").css('height','450');

            if (messageToUser.localeCompare("") != 0 ) {
                sendWelcomMessage(messageToUser);
            }
            sendMessage(webSocketClient);
        };
    webSocketClient.onclose = function (event) {
            console.log("close");

        }
}


function sendWelcomMessage(message) {
    confirm(message);
}

function newPlacemark(myMap) {
    if (myMap != undefined) {

        // получение текщей геопопзиции.
        navigator.geolocation.getCurrentPosition(function (position) {

            x = position.coords.latitude;
            y = position.coords.longitude;

            myMap.geoObjects.remove(myPlacemark);
            //обновляем местоположение метки
            myPlacemark = new ymaps.Placemark([x, y], {
                hitContent: 'Hello',
                balloonContent: 'Its me'
                }, {
                    iconLayout: 'default#image',
                    iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
                    iconImageSize: [32, 32],
                    iconImageOffset: [-15, -15]
                });
            // добавляем метку на карту
            myMap.geoObjects.add(myPlacemark);
            //setTimeout(newPlacemark(myMap),10000);
        });
    }
}




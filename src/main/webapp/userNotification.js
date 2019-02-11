
var userName;
var userID;
var isInFestival;

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

        console.log("userName " + userName + "; userID " + userID + "; isInFestival " + isInFestival);

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
                balloonContent: 'Its me',
                hitContent: 'Hello'
            });
            // добавляем метку на карту
            myMap.geoObjects.add(myPlacemark);
            //setTimeout(newPlacemark(myMap),10000);
        });
    }
}
// в массиве будут храниться координаты геометрической фигуры.
var array;

//Карта, метка на карте.
//Ширина,долгота.
var myMap, myPlacemark;
var x, y;
var myPolygon;
var webSocketClient
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
    webSocketClient = new WebSocket("ws://localhost:8080/compareLocations");
    webSocketClient.onopen = function (event) {
        console.log("onopen:" + event.data );
        //sendMessage(webSocketClient);
        };
    webSocketClient.onmessage = function (event) {
        console.log("onMessage: " + event.data);
            var messageToUser = JSON.parse(event.data).message;
            // processDataForFestivalBlock(event, festivalEvents);
            if (messageToUser.localeCompare("") != 0 ) {
                sendWelcomMessage(messageToUser);
                var fesivalId = JSON.parse(event.data).fesivalId;
                getDataForFestival(fesivalId);
            }


            //sendMessage(webSocketClient);
        };
    webSocketClient.onclose = function (event) {
            console.log("close: " + event.data);
            //connect();
        }
    webSocketClient.onerror = function (event) {
        console.log("error " + event.data);

    }
}


function sendWelcomMessage(message) {
    //var toastHeader = header;
    //var toastMessage = message;
    confirm(message);
    // document.getElementById("toastHeader").innerHTML = "Welcome";
    // document.getElementById("toastMessage").innerHTML = "Вы прибыли на <b>JavaBootCamp</b>";
    // $('.toast').toast('show');
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


//----------------------------------------------------------------------------------------------------------------
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ! НЕ УДАЛЯТЬ!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//----------------------------------------------------------------------------------------------------------------
// window.setInterval(function(){
//     $.ajax({
//         url: "/user/get_coordinats_to_client",
//         method: "get",
//         async: true,
//         error: function(message) {
//             console.log(message);
//         },
//         success: function(data) {
//             console.log("Take it! " + data.longitudeX + " " + data.latitudeY);
//             myMap.geoObjects.remove(myPlacemark);
//             myPlacemark = new ymaps.Placemark([data.longitudeX, data.latitudeY], {
//                 hitContent: 'Hello',
//                 balloonContent: 'It is you'
//             }, {
//                 iconLayout: 'default#image',
//                 iconImageHref: 'http://thebestapp.ru/wp-content/uploads/2016/07/Location_marker@2x.png',
//                 iconImageSize: [32, 32],
//                 iconImageOffset: [-15, -15]
//             });
//             // добавляем метку на карту
//             myMap.geoObjects.add(myPlacemark);
//
//             //myMap.setCenter([Number(data.longitudeX), Number(data.latitudeY)], 17, {checkZoomRange: true});
//         }
//     });
//     console.log("server, give mi coordinats!");
// }, 10000);
//
//----------------------------------------------------------------------------------------------------------------
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//----------------------------------------------------------------------------------------------------------------


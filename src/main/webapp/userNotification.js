
// function userNotification() {
//     //var timerID =
//         setTimeout(setInterval(notifyUserAboutFestival(), 100000), 10000000);
//     //setInterval(notifyUserAboutFestival(), 100000)
//     function notifyUserAboutFestival() {
//         //alert("Begin");
//         console.log("Begin");
//         //Get user current coordinats
//         var userX, userY;
//         var userArray;
//         navigator.geolocation.getCurrentPosition(function (position) {
//             userX = position.coords.latitude;
//             userY = position.coords.longitude;
//             console.log("navigator x " + userX + " ; y " + userY  );
//
//             $.ajax({
//                 url: "/compareLocations",
//                 method: "get",
//                 data: {xPosition: userX, yPosition: userY},
//                 async:true,
//                 error: function (message) {
//                     console.log("Error " + message);
//                 },
//                 success: function (data) {
//                     console.log("data " + data);
//
//                     userArray = data;
//                 }
//             });
//             console.log("userArray " + userArray);
//         });
//         console.log("userX " + userX + " ; userY " + userY  );
//         // get request for compear user location whith festival position
//
//
//         //timerID = setTimeout(notifyUserAboutFestival(), 100000);
//     }
// }
//
//
// function notification2() {
//
//
//     var ws;
//     notificationUser(ws);
//     function notificationUser(ws) {
//         ws = new WebSocket("ws://localhost:8080/compareLocations");
//         ws.onopen = function (event) {
//
//         }
//         ws.onmessage = function (event) {
//             console.log("event.data " + event.data);
//
//         }
//         ws.onclose = function (event) {
//             console.log()
//
//         }
//
//
//     };
//
//     function sendMessage(message) {
//         ws.send(message);
//     }
//
//     navigator.geolocation.getCurrentPosition(function (position) {
//         userX = position.coords.latitude;
//         userY = position.coords.longitude;
//         var message = userX + ";" + userY;
//         sendMessage(message);
//     });
//
// }

// var webSocketClient = null;
//
// function connect () {
//     webSocketClient = new WebSocket("ws://localhost:8080/compareLocations");
//     webSocketClient.onmessage = function (event) {
//         console.log("event.data " + event.data);
//         // var messagesArea = document.getElementById("messages");
//         // var jsonObj = JSON.parse(event.data);
//         // var message = jsonObj.user + ": " + jsonObj.message + "\r\n";
//         // messagesArea.value = messagesArea.value + message;
//         // messagesArea.scrollTop = messagesArea.scrollHeight;
//     };
// }
//
// function disconnect () {
//     webSocketClient.close();
// }
//
// webSocketClient.onopen = function (event) {
//     console.log(" ertert");
//
// }
//
//
//
//
//
function sendMessage(webSocketClient) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userX = position.coords.latitude;
            userY = position.coords.longitude;
            var message = userX + ";" + userY;
            var jsonObj = {"x" : userX, "y" : userY};
            webSocketClient.send(JSON.stringify(jsonObj));
        });
}


function connect() {
    var webSocketClient = new WebSocket("ws://localhost:8080/compareLocations");
    webSocketClient.onopen = function (event) {
        console.log("onopen " );
        sendMessage(webSocketClient);
        }
    webSocketClient.onmessage = function (event) {
            console.log("event.data " + event.data);

        }
    webSocketClient.onclose = function (event) {
            console.log("close");

        }
}


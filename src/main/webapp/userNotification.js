
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


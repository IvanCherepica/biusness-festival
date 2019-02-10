
//send message to server with user coordinates
function sendMessage(webSocketClient) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userX = position.coords.latitude;
            userY = position.coords.longitude;
            var message = userX + " " + userY;
            //var jsonObj = {"x" : userX, "y" : userY};
            //webSocketClient.send(JSON.stringify(jsonObj));
            webSocketClient.send(message);
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
            sendMessage(webSocketClient);
        };
    webSocketClient.onclose = function (event) {
            console.log("close");

        }
}


// get request for user name and id
$.ajax({
    url: "/rest/userdata",
    method: "get",
    async: true,
    error: function(message) {
        console.log(message);
    },
    success: function(data) {

        console.log("user data" + data);

    }
});

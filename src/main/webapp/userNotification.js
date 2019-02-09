
function userNotification() {
    var timerID = setTimeout(notifyUserAboutFestival(), 10000);

    function notifyUserAboutFestival() {
        //Get user current coordinats
        var x, y;
        var array;
        navigator.geolocation.getCurrentPosition(function (position) {
            x = position.coords.latitude;
            y = position.coords.longitude;
        });

        // get request for compear user location whith festival position
        $.ajax({
            url: "/compearLocations",
            method: "get",
            data: {xPosition: x, yPosition: y},
            error: function (message) {
                console.log(message);
            },
            success: function (data) {
                console.log(data);

                array = data;
                //$('#somediv').append(data.name);
            }
        });

        timerID = setTimeout(notifyUserAboutFestival(), 1000);
    }
}
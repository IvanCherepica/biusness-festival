
function userNotification() {
    //var timerID = setTimeout(notifyUserAboutFestival(), 1000000);
    setInterval(notifyUserAboutFestival(), 10000)
    function notifyUserAboutFestival() {
        //alert("Begin");
        //Get user current coordinats
        var x, y;
        var array;
        navigator.geolocation.getCurrentPosition(function (position) {
            x = position.coords.latitude;
            y = position.coords.longitude;
        });
        console.log("x " + x + " ; y " + y  );
        // get request for compear user location whith festival position
        $.ajax({
            url: "/compearLocations",
            method: "get",
            data: {xPosition: x, yPosition: y},
            async:false,
            error: function (message) {
                console.log(message);
            },
            success: function (data) {
                console.log(data);

                array = data;
                //$('#somediv').append(data.name);
            }
        });
        console.log(array);
        //timerID = setTimeout(notifyUserAboutFestival(), 100000);
    }
}
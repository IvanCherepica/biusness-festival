
function userNotification() {
    //var timerID =
        setTimeout(setInterval(notifyUserAboutFestival(), 100000), 10000000);
    //setInterval(notifyUserAboutFestival(), 100000)
    function notifyUserAboutFestival() {
        //alert("Begin");
        console.log("Begin");
        //Get user current coordinats
        var userX, userY;
        var userArray;
        navigator.geolocation.getCurrentPosition(function (position) {
            userX = position.coords.latitude;
            userY = position.coords.longitude;
            console.log("navigator x " + userX + " ; y " + userY  );

            $.ajax({
                url: "/compareLocations",
                method: "get",
                data: {xPosition: userX, yPosition: userY},
                async:true,
                error: function (message) {
                    console.log("Error " + message);
                },
                success: function (data) {
                    console.log("data " + data);

                    userArray = data;
                }
            });
            console.log("userArray " + userArray);
        });
        console.log("userX " + userX + " ; userY " + userY  );
        // get request for compear user location whith festival position


        //timerID = setTimeout(notifyUserAboutFestival(), 100000);
    }
}
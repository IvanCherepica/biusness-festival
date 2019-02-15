

var userPlaceMark;



function startUsersHandler() {

    window.setInterval(function(){
        console.log("[adminMapUsersHandler.js] Server, give me user`s coordinates!");

        $.ajax({
            url: "/admin/getUsersCoordinates",
            method: "get",
            async: true,
            error: function(message) {
                console.log("[adminMapUsersHandler.js] Error: " + message);
            },
            success: function(data) {
                //x - latitude, y - longitude

                console.log("[adminMapUsersHandler.js] Received: " + "latitude(x)=" + data.latitudeX + " longitude(y)=" + data.longitudeY);
                updateUserPlaceMark(data.latitudeX, data.longitudeY);
            }
        });

    }, 5000);

}



function updateUserPlaceMark(xUser, yUser){
    if (myMap != undefined){
        if (userPlaceMark != undefined) {
            console.log("[adminMapUsersHandler.js] UserPlaceMark is removing from map...");
            myMap.geoObjects.remove(userPlaceMark);
        }

        console.log("[adminMapUsersHandler.js] UserPlaceMark is updating on map...");

        userPlaceMark = new ymaps.Placemark([xUser, yUser], {
            balloonContent: 'Its me',
            hitContent: 'Hello'
        });

        myMap.geoObjects.add(userPlaceMark);
    }
}
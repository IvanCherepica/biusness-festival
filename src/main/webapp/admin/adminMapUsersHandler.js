

var userPlaceMark;
var userCurrentFestivalId;


function startUsersHandler() {

    userCurrentFestivalId = -1;

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

                updateUserPlaceMark(data.latitudeX, data.longitudeY);

                var isNeedToInform = false;

                if (userCurrentFestivalId != data.userCurrentFestivalId){
                    isNeedToInform =  true;
                    userCurrentFestivalId = data.userCurrentFestivalId;
                }

                console.log("[adminMapUsersHandler.js] userCurrentFestivalId: " + data.userCurrentFestivalId + " isNeedToInform: " + isNeedToInform);
                console.log("[adminMapUsersHandler.js] Received: " + "latitude(x)=" + data.latitudeX + " longitude(y)=" + data.longitudeY);

                if (isNeedToInform) alert("AAAAAAAAAAAAA");
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
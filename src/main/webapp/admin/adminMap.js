
var myMap;

function startPageWithMap() {

    console.log("[PageWithMap] Starting page... Need User location");

    if(navigator.geolocation) {
        getAdminLocation(ShowMap);
    }else{
        document.getElementById("map").innerHTML = "[adminMap.js] Geolocation API не поддерживается в вашем браузере";
    }
}


function getAdminLocation(callback) {

    navigator.geolocation.getCurrentPosition(function (position) {

        var receivedX = position.coords.latitude; //x - latitude, y - longitude
        var receivedY = position.coords.longitude;

        console.log("[adminMap.js] Coordinates received. latitude(x)=" + receivedX + "; longitude(y)=" + receivedY);

        if (callback != undefined) callback(receivedX, receivedY);

    }, function (positionError) {
        console.log(positionError);
    });
}


function ShowMap(xCenterMap, yCenterMap){
    if (myMap == undefined) {
        console.log("[adminMap.js] Creating map...");
        myMap = new ymaps.Map("map", {
            center: [xCenterMap, yCenterMap], //x - latitude, y - longitude
            zoom: 18,
            controls: ['zoomControl'],
            behaviors: ['drag']
        });
    }

    // GetFestivals(DrawMapUnits);
    // GetEventPoints(DrawMapUnits);
}
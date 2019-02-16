


function GetFestivalsForAdmin(callback) {
    console.log("[GetData] Receiving festivals...");
// get запрос GeometryServlet
    $.ajax({
        url: "/rest/geometry/get-all-festivals",
        method: "get",
        async: true,
        error: function (message) {
            console.log(message);
        },
        success: function (data) {
            console.log("[GetData] Festivals received. Count=" + data.length);
            callback(data);
        }
    });

}


function GetEventPointsForAdmin(callback){
    console.log("[GetData] Receiving event points...");

// get запрос EventPointToMapServlet
    $.ajax({
        url: "/rest/geometry/event-points", //TODO
        method: "get",
        async: true,
        error: function (message) {
            console.log(message);
        },
        success: function (data) {
            console.log("[GetData] EventPoints received. Count=" + data.length);
            callback(data);
        }
    });
}




function GetUserForAdmin(callback) {
    // get request for user name and id
    console.log("[GetData] UserData recieving...");
    $.ajax({
        url: "/rest/userdata", //TODO
        method: "get",
        async: true,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            console.log("[GetData] UserData received.");
            callback(data);
        }
    });
}



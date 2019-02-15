<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="author" content="Aurelio De Rosa">
    <title>Geolocation API Demo by Aurelio De Rosa</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

 <body>

<script>
    var userX;
    var userY;

    navigator.geolocation.watchPosition(function (position) {
        userX = position.coords.latitude;
        userY = position.coords.longitude;

        window.setInterval(function(){
            $.ajax({
                url: "/user/geoposition",
                method: "post",
                async: true,
                data: {longitude: position.coords.latitude, latitude: position.coords.longitude},
                error: function(message) {
                    console.log(message);
                },
                success: function(data) {

                }
            });
        }, 5000);
    });

</script>
</body>
</body>
</html>

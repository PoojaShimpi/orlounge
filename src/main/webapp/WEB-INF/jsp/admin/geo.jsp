<html>
<head>
    <script src="assets/javascripts/geo/geolocator.min.js"></script>
    <script type="text/javascript">

        geolocator.config({
            language: "en",
            google: {
                version: "3",
                key: "AIzaSyA8KWe4bn4fWY94o09POuqPV4oh0OR70PQ"
            }
        });

        window.onload = function () {
            var options = {
                enableHighAccuracy: true,
                timeout: 5000,
                maximumWait: 10000,     // max wait time for desired accuracy
                maximumAge: 0,          // disable cache
                desiredAccuracy: 30,    // meters
                fallbackToIP: true,     // fallback to IP if Geolocation fails or rejected
                addressLookup: true,    // requires Google API key if true
                timezone: false,         // requires Google API key if true
                map: "map-canvas",      // interactive map element id (or options object)
                staticMap: true         // map image URL (boolean or options object)
            };
            geolocator.locate(options, function (err, location) {
                if (err) return console.log(err);
                document.getElementById('res').innerHTML = JSON.stringify(location);
                console.log(location);
            });
        };

    </script>
</head>
<body>
<div id="map-canvas" style="width:600px;height:400px"></div>
<div id="res">

</div>
</body>
</html>
<%@ page import="org.orlounge.common.UserToken" %>
<%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
%>
<script type="text/javascript">
    var groupId = <%=token.getCurrentGroupId()%>;
    geolocator.config({
        language: "en",
        google: {
            version: "3",
            key: "<%=token.getRestrictionInfo().getRestrictionsForDevice(UserToken.DeviceType.ALL).get(0).getKey()%>"
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
            timezone: false         // requires Google API key if true
          //  map: "map-canvas",      // interactive map element id (or options object)
          //  staticMap: true         // map image URL (boolean or options object)
        };
        geolocator.locate(options, function (err, location) {
            if (err) return console.log(err);


            var lat = location.coords.latitude;
            var lon = location.coords.longitude;

            $.ajax({url:'checkLocation?lat='+lat+"&lon="+lon, success: function(resp){
                if(!resp){
                    window.location.href = 'geo-error.html';
                }
            }});
            localStorage.setItem("geoLocation",JSON.stringify(location) );
            console.log(location);
        });
    };

</script>
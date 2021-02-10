<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="icon" href="resources/other/favico.ico" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geo Restricted</title>
    <!-- Stylesheets -->
    <style>
        /*******************************
		Table of Contents

	1.0 Typography
	2.0 Reset
    3.0 Page Content
    4.0 Home Button
	5.0 Header
	6.0 Footer
	7.0 Search Form

*******************************/

        /* === Typography === */
        @font-face{font-family:'MuseoSans-700';src:url("../fonts/museo.eot");
            src:url("../fonts/museo.eot") format("embedded-opentype"),
            url("../fonts/museo.woff") format("woff"),
            url("../fonts/museo.ttf") format("truetype")}

        /* === Reset === */
        body{font-family:'Helvetica Neue',Arial,Helvetica, Verdana, sans-serif;margin:0;padding:0;}
        ol,ul,li{list-style:none;margin:0;padding:0;border:0}
        a{text-decoration:none;}
        ::-moz-selection{background-color:#adcdea;color:#333;text-shadow:none}
        ::selection{background-color:#adcdea;color:#333;text-shadow:none}
        textarea, input { outline: none; }
        #errorpage.error404{background:url("resources/other/bg.jpg"); color:#fff;}
        /* === page content === */
        #pagewrap{min-height:100%}

        #content{
            padding-left:20px;
            margin-left:25px;
        }
        /* === home button === */
        .button{
            background-image:-webkit-gradient(linear, 50% 100%, 50% 0%, color-stop(0%, #f3f3f3), color-stop(49%, #f0f0f0), color-stop(51%, #f8f8f8), color-stop(100%, #fff));
            background-image:-webkit-linear-gradient(bottom, #f3f3f3 0%, #f0f0f0 49%, #f8f8f8 51%, #fff 100%);
            background-image:-moz-linear-gradient(bottom, #f3f3f3 0%, #f0f0f0 49%, #f8f8f8 51%, #fff 100%);
            background-image:-o-linear-gradient(bottom, #f3f3f3 0%, #f0f0f0 49%, #f8f8f8 51%, #fff 100%);
            background-image:linear-gradient(bottom, #f3f3f3 0%,#f0f0f0 49%,#f8f8f8 51%,#ffffff 100%);
            display:inline-block;
            background-color:#f0f0f0;
            color:#3987cc;
            vertical-align:middle;
            text-align:center;
            text-shadow:0 1px white;
            font-weight:500;
            line-height:18px;
            -webkit-box-shadow:inset 0 1px 0 rgba(255,255,255,0.15),0 1px 3px rgba(0,0,0,0.5);
            -moz-box-shadow:inset 0 1px 0 rgba(255,255,255,0.15),0 1px 3px rgba(0,0,0,0.5);
            box-shadow:inset 0 1px 0 rgba(255,255,255,0.15),0 1px 3px rgba(0,0,0,0.5);
            border:none;
            padding:13px 30px;
            border-radius:3px;
            font-size:18px;
        }
        .button:hover{opacity:0.85 !important;color:black}

        /* === header === */
        #header{height:52px;width:100%;position:relative;z-index:999;background:#333;border-bottom:1px solid #e5e5e5}
        #header .container .logo{height:42px;margin:5px 0; vertical-align:middle;border:0}
        #header .container {font-weight:700; font-size:18px; line-height:18px; color: #fff; font-family: "MuseoSans-700",Helvetica,Arial,Verdana,sans-serif; vertical-align:middle;}
        .link-id{font-weight:300; font-size:14px; line-height:14px; color: #fff; font-family: "MuseoSans-700",Helvetica,Arial,Verdana,sans-serif; vertical-align:middle;text-decoration: underline;}
        .link{font-weight:700; font-size:18px; line-height:18px; color: #fff; font-family: "MuseoSans-700",Helvetica,Arial,Verdana,sans-serif; vertical-align:middle;}

        #main-content{position:relative;margin-bottom:-100px}
        .duck-animation{background-position:-1000px 30%;background-repeat:no-repeat;height:100px}


        /* === Media Query === */

    </style>
    <link rel="stylesheet" href="resources/vendor/font-awesome/css/font-awesome.css" />

    <!--[if lt IE 9]>
    <!-- Javascripts -->
    <script src="resources/vendor/jquery/jquery.js"></script>
    <script type="text/javascript" charset="utf-8" src="resources/plax.js"></script>
</head>

<body id="errorpage" class="error404">
<div id="pagewrap">
    <!--Header Start-->
    <div id="header" class="header">
        <div class="container">
            <img class="logo" src="resources/other/logo_transparent.png" alt=""/>
        </div>
    </div><!--Header End-->

    <!--page content-->
    <div id="wrapper" class="clearfix">
        <div id="parallax_wrapper">
            <div id="content">
                <h1>Enabling Geolocation</h1>
                    <p>
                        The browser you are using will ask you for your permission to give your location.<br/>
                    By default, your browser will have geolocation enabled. If you have disabled geolocation in your browser, it's easy to enable it again. <br/>
                        Select your browser from the list below:<br/>
                        <a href="#ie" class="link-id"> Internet Explorer</a><br/>
                        <a href="#ff" class="link-id">Mozilla Firefox</a><br/>
                        <a href="#gc" class="link-id">Google Chrome</a><br/>
                        <a href="#sf" class="link-id">Safari</a><br/>
                        <a href="#op" class="link-id">Opera</a><br/>
                    </p>

                    <div id="ie">

                        <span class="link"><span class="fa fa-internet-explorer"></span> Internet Explorer</span>

                        <p>
                            Under Tools >> Internet Options>> Privacy - look under the Location section, press the "Clear Sites" button
                            and be sure "Never allow websites to request your physical location" is unchecked.
                            Press OK.
                        </p>

                        <img src="resources/other/ie/IEPrivacy.jpg"/>
                        <Br/>
                    Then refresh the screen showing orlounge by pressing the F5 button and
                        you should see a box appear asking for you permission to give your location.
                        <br/>Select "Allow once" or Options for this site and "Always allow". Here's more information on geolocation in Internet Explorer.

                    </div>
                <br/>
                <br/>
                    <div id="ff">
                        <span class="link">Mozilla Firefox</span>
                        <p>
                            Open Firefox and navigate to waziggle.com. From the Tools menu at the top of your
                            Firefox window (if it's not visible, press F11), select Page Info. Select the Permissions tab.
                            <br/>Change the setting for Share Location.<Br/>

                            <img src="resources/other/ff/firefoxShareLoc.jpg"/>
                            <br/>
                            Then refresh the screen showing orlounge by pressing the F5 button and you should see a box appear asking for you permission to
                            give your location.
                            <br/>
                            Select Share Location or choose from the dropdown list. Here's more information on geolocation from Mozilla Firefox.<br/>
                            <img src="resources/other/ff/firefoxShareLoc2.jpg"/>

                        </p>

                    </div>




                    <br/>
                    <br/>


                    <div id="gc">
                        <span class="link">Chrome</span>
                        <p>
                            Click the Chrome menu icon   on the Chrome Toolbar. <br/>
                            Select Settings.<br/>
                            Click Show Advanced Settings (at the bottom of screen).<br/>
                            In the "Privacy" section click Content Settings.<br/>
                            In the dialog that appears, scroll down to the "Location" section.<br/>
                            Select your default permission for future location.
                            Click Manage exceptions to remove previously-granted permissions or denials for specific sites.<br/>
                            <img src="resources/other/gc/chromeSettings.jpg"/>

                            <br/>
                            Open Chrome and goto orlounge.com and you should see a box appear asking for your permission to give your location.
                            <Br/>Select Allow or choose from the dropdown list. Here's more information from Google Chrome.
                            <br/>
                            <img src="resources/other/gc/ChromePermissionBar.jpg"/><br/>

                        </p>
                    </div>
                    <br/><br/>
                    <div id="sf">
                        <span class="link">Safari</span>
                        <p>
                            You can reset website authorizations in Safari 5 by choosing Reset Safari from the Safari menu, then enabling
                            "Reset all location warnings" checkbox (don't select the other checkboxes unless you are sure you want to reset those as well).
                            <br/>
                            <img src="resources/other/sf/safariSettings.jpg"/>
                            <br/>
                            Open Safari and goto orlounge.com and you should see a box appear asking for your permission to give your location.
                            Select Allow. Here's more information from Apple.
                            <br/>
                            <img src="resources/other/sf/safariPermission.jpg"/>
                        </p>






                    </div>
                    <br/>
                    <br/>

                    <div id="op">

                        <span class="link"> Opera</span>
                        <p>
                            From the Opera menu, go to Settings > Preferences > Advanced > Network, and check "Enable geolocation".
                            <br/>
                            <img src="resources/other/op/operaSettings.jpg"/><br/>
                            Open Opera and goto orlounge.com and you should see a box appear asking for your permission to give your location.
                            Select Allow. Here's more information from Opera.
                            <br/>
                            <img src="resources/other/op/operaPermission.jpg"/><br/>

                     </p>
                </div>
                <Br/>
                <br/>
                <a href="index.html" title="" class="button">Okay</a>
            </div>
            <!--parallax-->
        </div>
    </div>

</div><!-- end pagewrap -->

<!--page footer-->

<script>

    $(function(e){
        var t = localStorage.getItem("geoLocation");
        if(t){
            var loc = JSON.parse(t);
            $('.curr').text(loc.formattedAddress);
        }
    })

</script>

</body>
</html>


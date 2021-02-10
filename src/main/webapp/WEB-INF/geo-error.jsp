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

        /* === page content === */
        #pagewrap{min-height:100%}
        .container{margin:0 auto;width:960px}
        #wrapper .container{margin:25px auto}

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
        #header{height:52px;position:fixed;width:100%;position:relative;z-index:999;background:#333;border-bottom:1px solid #e5e5e5}
        #header .container .logo{height:42px;margin:5px 0; vertical-align:middle;border:0}
        #header .container {font-weight:700; font-size:18px; line-height:18px; color: #fff; font-family: "MuseoSans-700",Helvetica,Arial,Verdana,sans-serif; vertical-align:middle;}
        #header .container .link{font-weight:700; font-size:18px; line-height:18px; color: #fff; font-family: "MuseoSans-700",Helvetica,Arial,Verdana,sans-serif; vertical-align:middle;}

        /* === footer === */
        #footer{font-size:11px;line-height:11px;color:#666666;overflow:hidden;clear:both;position:relative;text-shadow:rgba(255,255,255,0.3) 0px 1px;padding:0px;}
        #footer .container{padding:0 0 10px}
        #footer .container .copyright_info{float:left;width:450px}
        #footer .container .copyright_info li{float:left;margin-right:10px}
        #footer .container .links{float:right;width:450px}
        #footer .container .links li{float:right;margin-left:10px}

        #main-content{position:relative;margin-bottom:-100px}
        .duck-animation{background-position:-1000px 30%;background-repeat:no-repeat;height:100px}

        /* === Search form === */
        #email {
            padding: 25px;
            height: 30px;
            text-align: center;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            overflow: hidden;
        }

        #email input {
            padding: 15px 110px 15px 20px;
            width: 185px;
            font-size: 14px;
            color: rgba(17, 26, 24, 1);
            border: 0px solid #666;
            -webkit-border-radius: 40px;
            -moz-border-radius: 40px;
            border-radius: 40px;
            -webkit-box-shadow: 0px 0px 8px 1px rgba(0, 0, 0, .45);
            -moz-box-shadow: 0px 0px 8px 1px rgba(0, 0, 0, .45);
            box-shadow: 0px 0px 8px 1px rgba(0, 0, 0, .45);
            margin-right:-98px;
            background:transparent;
        }

        #email button {
            border: none;
            background:#10489d;
            padding: 12px 20px;
            -webkit-border-radius: 40px;
            -moz-border-radius: 40px;
            border-radius: 40px;
            color: white;
            font-size: 12px;
            font-weight:bold;
            text-shadow: 2px 1px 5px rgba(0, 0, 0, .5);
            opacity:0.5;
            filter:progid:DXImageTransform.Microsoft.Alpha(opacity=50);
            -moz-opacity: 0.5;
            -khtml-opacity: 0.5;
        }

        #email button:hover  {
            opacity: .85;
            filter:progid:DXImageTransform.Microsoft.Alpha(opacity=85);
            -moz-opacity: 0.85;
            -khtml-opacity: 0.85;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            -o-transition: all 0.1s linear;
            -ms-transition: all 0.1s linear;
            transition: all 0.1s linear;
            cursor:pointer;
        }

        #email button:active {
            border: 0px solid rgba(0, 0, 0, .2);
            background: -webkit-gradient(linear, left top, left bottom, from(#10489d), to(#389cfa));
            background: -webkit-linear-gradient(top, #10489d, #10489d);
            background: -moz-linear-gradient(top, #10489d, #10489d);
            background: -ms-linear-gradient(top, #10489d, #10489d);
            background: -o-linear-gradient(top, #10489d, #10489d);
        }

        #subscribe-success {
            margin-top: 35px;
            font-size: 1.1em;
            font-weight: bold;
            text-shadow: 1px 1px 5px rgba(110, 110, 110, 0.8);
            text-align: center;
        }

        /* === Media Query === */
        @media screen and (min-width: 768px){
            #errorpage #header{border-bottom:none;box-shadow:0px 1px 3px rgba(0,0,0,0.25)}
            #errorpage #parallax_wrapper{width:1920px;min-height:100%;position:relative;margin-left:-960px}
            #errorpage #content{width:650px;margin:0 auto;color:#fff;text-align:center;z-index:2;position:fixed;top:50%;left:50%;margin:-385px -300px 0}
            #errorpage #content .button{margin-top:35px;cursor:pointer;position:relative;z-index:99999;-webkit-border-radius: 30px; -moz-border-radius: 30px;
                border-radius: 30px;}
            #errorpage #content h1{font-color:#fff;font-family:"MuseoSans-700",Helvetica,Arial,Verdana,sans-serif;font-size:45px;font-weight:normal;line-height:55px;text-shadow:0px 1px 3px rgba(0,0,0,0.45)}
            #errorpage #content h2{font-color:#fff;font-family:"MuseoSans-700",Helvetica,Arial,Verdana,sans-serif;font-size:30px;font-weight:normal;line-height:40px;text-shadow:0px 1px 3px rgba(0,0,0,0.45)}
            #errorpage #content p{font-family:HelveticaNeue,Arial,Verdana,sans-serif;font-size:18px;font-weight:normal;width:512px;margin:0 auto;line-height:26px;text-shadow:0px 1px 3px rgba(0,0,0,0.45)}
            #errorpage #content .info{font-family:HelveticaNeue,Arial,Verdana,sans-serif;font-size:13px;font-weight:normal;width:512px;margin:0 auto;line-height:26px;text-shadow:0px 1px 3px rgba(0,0,0,0.45)}
            #errorpage.error404{background:url("resources/other/bg.jpg")}
            #errorpage.error404 #content{width:590px;z-index:9999}
            #errorpage.error404 h1{margin:0 auto;padding:120px 0 20px}
            #errorpage.error404 p{width:440px}
            #errorpage.error404 p a{color:#fffbe5;font-weight:bold;text-decoration:underline}
            #errorpage.error404 .scene{position:absolute;bottom:-100px;display:block;width:1920px}

            /* === scene's === */
            #errorpage.error404 .scene.scene_1{height:336px;background:url(resources/other/scene_1.png) no-repeat left top;pozition:relative; z-index:4;}
            #errorpage.error404 .scene.scene_2{height:336px;background:url(resources/other/scene_2.png) no-repeat left top;pozition:relative; z-index:3;}
            #errorpage.error404 .scene.scene_3{height:336px;background:url(resources/other/scene_3.png) no-repeat left top;pozition:relative; z-index:2;}
            #errorpage.error404 .scene.scene_4{height:336px;background:url(resources/other/scene_4.png) no-repeat left top;pozition:relative; z-index:1;}

            /* === footer === */
            #errorpage.error404 #footer .container{color:#fff;margin-top:0;font-size:12px;text-shadow:none}
            #errorpage.error404 #footer .container a{color:#fff}
            #errorpage #footer{background-image:none;border:none;box-shadow:none;z-index:9999;height:30px;margin-top:-30px}
            #errorpage #wrapper{padding:0;overflow:hidden;position:relative}}

        @media screen and (max-width: 767px){
            #errorpage #header .container{width:auto}
            #errorpage #content{background:#fff;padding:20px;margin:20px;border:1px solid #ccc;text-align:center;border-radius:5px;box-shadow:0px 1px 0px rgba(0,0,0,0.05)}
            #errorpage #content h1{font-size:24px;line-height:28px;margin:0 0 10px}
            #errorpage #content p{font-size:14px;line-height:18px;color:#666;margin:0 0 10px}
            #errorpage #footer {display:none}
        }


    </style>
    <link rel="stylesheet" href="resources/vendor/font-awesome/css/font-awesome.css" />

    <!--[if lt IE 9]>
    <!-- Javascripts -->
    <script src="resources/vendor/jquery/jquery.js"></script>
    <script type="text/javascript" charset="utf-8" src="resources/plax.js"></script>
</head>
<%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
%>
<body id="errorpage" class="error404">
<div id="pagewrap">
    <!--Header Start-->
    <div id="header" class="header">
        <div class="container">
            <img class="logo" src="resources/other/logo_transparent.png" alt=""/>
            is
            <a href="#" title="logo" class="link">Geo Restricted</a>
        </div>
    </div><!--Header End-->

    <!--page content-->
    <div id="wrapper" class="clearfix">
        <div id="parallax_wrapper">
            <div id="content">
                <h1><%=token.getCurrentGroupName()%></h1>
                <h2>has enabled Geo Fencing</h2>
                <p>You are not allowed to access from your current location.</p>
                <p class="info">
                    <span class="fa fa-map-marker"></span> <span class="curr"></span>.
                <br/>
                Not your accurate Location? Please make sure you Allow ORLounge to access your location on asked.
                <br/>
                    <a href="geo-info.html" style="text-decoration:underline">Having problem in allowing location access?</a><br/>

                </p>
                <a href="index.html" title="" class="button">Okay</a>
            </div>
            <!--parallax-->
            <span class="scene scene_1"></span>
            <span class="scene scene_2"></span>
            <span class="scene scene_3"></span>
            <span class="scene scene_4"></span>
        </div>
    </div>

</div><!-- end pagewrap -->

<!--page footer-->
<div id="footer">
    <div class="container">
        <ul class="copyright_info">
            <li>Please check with your LSA to get more information about the Geo fenced regions</li>
            <li>&middot;</li>
        </ul>
        <!--social links-->
        <ul class="links">
            <li><a href="#">Facebook</a></li>
            <li>&middot;</li>
            <li><a href="#">Twitter</a></li>
            <li>&middot;</li>
            <li><a href="#">Youtube</a></li>
            <li>&copy; 2017 ORLounge</li>
        </ul>
    </div>
</div><!--end page footer-->
<script>
    $(function(e){
        var x=document.documentElement.clientHeight;
        var y=e(".header").outerHeight();
        e("#parallax_wrapper").css("height",x-y+"px");
        e("#parallax_wrapper").css("left",50+"%");
        e(".scene_1").plaxify({"xRange":0,"yRange":0,"invert":true}),
            e(".scene_2").plaxify({"xRange":70,"yRange":10,"invert":true}),
            e(".scene_3").plaxify({"xRange":70,"yRange":10,"invert":false}),
            e(".scene_4").plaxify({"xRange":70,"yRange":4,"invert":true}),
            e.plax.enable();
    });
    $(function(e){
        var t = localStorage.getItem("geoLocation");
        if(t){
            var loc = JSON.parse(t);
            $('.curr').text(loc.formattedAddress);
        }
    })

</script>
<%
    session.invalidate();
%>
</body>
</html>


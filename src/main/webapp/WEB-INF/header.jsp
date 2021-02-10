<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<head>
<style>
.center {
  margin: auto;
  width: 60%;
  padding: 10px;
}
 .relative {
            padding: 10px;
            position: relative;
            background-color: #fff;
            margin: 10px;
        }

        .navitem {
            display: inline-block;

            height: 30px;
            text-align: center;
            border: gray;

            color: #fff;
            cursor: pointer;
            font-weight: bold;
        }

</style>
    <!-- Basic -->
    <meta charset="UTF-8">

    <title>OR LOUNGE</title>
    <meta name="google-site-verification" content="j_lpBW9rbq1-QvC3jl04dqtqcoNwglc_xUV0uh43kCU" />
    <meta name="keywords" content="ORLounge" />
    <meta name="keywords" content="Operating Room" />
    <meta name="keywords" content="Lounge" />
    <meta name="author" content="orlounge.com">
    <link rel="icon" href="resources/other/favico.ico" type="image/x-icon"/>


    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <!-- Web Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

    <!-- Vendor CSS -->
    <link type="application/x-font-ttf" href="resources/stylesheets/glyphicons-halflings-regular.ttf" />

    <script src="https://kit.fontawesome.com/50b65719c4.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="resources/app-minify.css">
    <%--<link rel="stylesheet" href="resources/vendor/font-awesome/css/font-awesome.css">--%>
    <link rel="stylesheet" href="resources/stylesheets/skins/default.css" />
    <link rel="stylesheet" href="resources/stylesheets/theme-custom.css">



    <!-- Head Libs -->
    <script src="resources/vendor/modernizr/modernizr.js"></script>
    <script src="resources/app/myAjax.js"></script>
    <script src="resources/vendor/jquery/jquery.js"></script>
    <script src="resources/javascripts/jquery.tagcloud.js"></script>


    <style type="text/stylesheet">
        #tag-cloud > a:hover{
            text-decoration: no-underline;
        }
        .no-js #loader { display: none;  }
        .js #loader { display: block; position: absolute; left: 100px; top: 0; }

    </style>
    <script>
        $(window).load(function() {
            // Animate loader off screen
            $(".se-pre-con").fadeOut(100);
        });
    </script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="resources/javascripts/pdf.js"></script>
    <%
        UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
        if(ProcessData.isValid(token) && token.isHavingAccessRestriction()){
    %>

    <script src="resources/javascripts/geo/geolocator.min.js"></script>

    <%
        }
    %>
</head>
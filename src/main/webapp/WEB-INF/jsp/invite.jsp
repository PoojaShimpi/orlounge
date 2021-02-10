<!doctype html>
<html class="fixed">
<head>

    <!-- Basic -->
    <meta charset="UTF-8">

    <meta name="keywords" content="HTML5 Admin Template" />
    <meta name="description" content="Porto Admin - Responsive HTML5 Template">
    <meta name="author" content="okler.net">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <!-- Web Fonts  -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

    <!-- Vendor CSS -->
    <link rel="stylesheet" href="resources/vendor/bootstrap/css/bootstrap.css" />

    <link rel="stylesheet" href="resources/vendor/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" href="resources/vendor/magnific-popup/magnific-popup.css" />
    <link rel="stylesheet" href="resources/vendor/bootstrap-datepicker/css/datepicker3.css" />

    <!-- Theme CSS -->
    <link rel="stylesheet" href="resources/stylesheets/theme.css" />

    <!-- Skin CSS -->
    <link rel="stylesheet" href="resources/stylesheets/skins/default.css" />

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="resources/stylesheets/theme-custom.css">

    <!-- Head Libs -->
    <script src="resources/vendor/modernizr/modernizr.js"></script>

</head>
<body style="min-height: 830px;">
<%
    boolean success = request.getParameter("success") != null ? Boolean.valueOf( request.getParameter("success")) : false;
%>
<!-- start: page -->
<section class="col-md-2"></section>
<section class="col-md-6">
    <div class="center-sign">
        <a href="www.orlounge.com" class="logo pull-left">
            <img src="resources/other/logo_7.png" height="54" width="150" alt="OR Lounge" />
        </a>

        <div class="panel panel-sign">
            <div class="panel-title-sign mt-xl text-right">
                <h4 class="title text-uppercase text-weight-bold m-none">
                    <i class="fa fa-envelope mr-xs"></i> Tell a hospital friend
                </h4>
            </div>
            <div class="panel-body" style="margin-top: 60px;">
                <% if(success){
                    %>
                <div class="alert alert-success">
                    <p class="m-none text-weight-semibold h6">We have sent an invitation mail. Thank you!</p>
                </div>
                <%
                }
                %>

                <div class="alert alert-danger hide error-msg" >
                    <p class="m-none text-weight-semibold h6">Mail Address cannot be blank</p>
                </div>

                <div class="alert alert-info">
                    <p class="m-none text-weight-semibold h6">Enter your friend's email address below</p>
                </div>

                <script type="text/javascript">
                    function submitData(){
                        $('.error-msg').addClass('hide');
                        if(!$('#mail').val()){
                            $('.error-msg').removeClass('hide');
                            return;
                        }

                      $('#inviteForm').submit();
                    }
                </script>
                <form action="inviteUser" id="inviteForm" method="POST">
                    <div class="media-body">
                        <div class="form-group">
                            <div class="col-md-10">
                                <input id="mail" name="email" type="email" placeholder="E-mail" class="form-control input-sm" />
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <div class="col-md-10">
                                <label class="bold" >Subject :</label>
                                <label>Checkout this website - www.ORlounge.com </label>
                            </div>
                        </div>


                        <div class="form-group">

                            <div class="col-md-10">
                                <label class="bold">Email:</label>
                                <textarea id="emailMessage" name="emailMessage" class="form-control animated " rows="10">www.orlounge.com is an online site for the operating room community.My Operating Room has created a free local OR lounge site for our staff to work together.Check this out! Go to www.orlounge.com to register...</textarea>

                            </div>


                        </div>
                        <div class="form-group">
                            <div class="col-md-10">
						        <input class="btn btn-primary btn-sm" type="button" onclick="submitData()" value="Invite"/>
                            </div>
						</div>



                    </div>

                    <p class="text-center mt-lg">Visit OR Lounge Site? <a href="index.html">Click Here!</a>
                </form>
            </div>
        </div>


    </div>
</section>
<section class="col-md-2"></section>
<!-- end: page -->

<!-- Vendor -->
<script src="resources/vendor/jquery/jquery.js"></script>
<script src="resources/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.js"></script>
<script src="resources/vendor/nanoscroller/nanoscroller.js"></script>
<script src="resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/vendor/magnific-popup/magnific-popup.js"></script>
<script src="resources/vendor/jquery-placeholder/jquery.placeholder.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="resources/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="resources/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="resources/javascripts/theme.init.js"></script>




</body>
</html>
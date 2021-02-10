<!doctype html>
<html class="fixed">
<head>

    <!-- Basic -->
    <meta charset="UTF-8">


    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <!-- Web Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

    <!-- Vendor CSS -->
    <link rel="stylesheet" href="../resources/vendor/bootstrap/css/bootstrap.css" />

    <link rel="stylesheet" href="../resources/vendor/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" href="../resources/vendor/magnific-popup/magnific-popup.css" />
    <link rel="stylesheet" href="../resources/vendor/bootstrap-datepicker/css/datepicker3.css" />

    <!-- Theme CSS -->
    <link rel="stylesheet" href="../resources/stylesheets/theme.css" />

    <!-- Skin CSS -->
    <link rel="stylesheet" href="../resources/stylesheets/skins/default.css" />

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="../resources/stylesheets/theme-custom.css">

    <!-- Head Libs -->
    <script src="../resources/vendor/modernizr/modernizr.js"></script>

</head>
<body style="min-height: 830px;">
<%
    boolean success = request.getAttribute("success") != null ? Boolean.valueOf( request.getAttribute("success").toString()) : false;
    String message = request.getAttribute("message") != null ?  request.getAttribute("message").toString() : null;


%>
<!-- start: page -->
<section class="col-md-2"></section>
<section class="col-md-6">
    <div class="center-sign">



        <div class="panel panel-sign">

            <div class="panel-title-sign mt-xl text-right">
                <table>
                    <Tr>
                        <td colspan="2">
                            <a href="index.html" class="logo pull-left">
                                <img src="resources/other/email_banner_bar.png" height="250" width="800" alt="OR Lounge" />
                            </a>
                        </td>

                    </Tr>
                    <Tr>

                        <td colspan="2">
                            <h4 class="title text-uppercase text-weight-bold pull-left " style="padding-left: 20px;">
                                Password Recovery Page
                            </h4>
                        </td>
                    </Tr>


                </table>
            </div>
            <div class="panel-body" style="margin-top:0px;">
                <% if(!success  && message!= null){
                    %>
                <div class="alert alert-danger">
                    <p class="m-none text-weight-semibold h6"><%=message%></p>
                </div>
                <%

                }

                if(success){
                    %>
                <div class="alert alert-success">
                    <p class="m-none text-weight-semibold h6">We have sent you an email on your email address. Please check your email inbox for it.
                    Click to <a href="index.html">Login</a></p>
                </div>
                <%

                } else {
                    %>



                <script type="text/javascript">
                    function submitData(){

                        $('.error-msg').addClass('hide');
                        var msg = "";
                        var valid= true;


                        var email = $('#email').val();
                        if(!email || email.length === 0){
                            msg +=' Email invalid.<br/>';
                            valid = false;
                        }

                        if(valid){
                            $('#recoveryForm').submit();

                        }else {
                            $('.error-msg').html(msg);
                            $('.error-msg').removeClass('hide');
                            return;
                        }

                    }
                </script>

                <form action="recoveryPassword" id="recoveryForm" method="POST">
                    <div class="media-body">
                        <div class="alert alert-danger hide error-msg" >
                            <p class="m-none text-weight-semibold h6"></p>
                        </div>
                        <p>
                            <strong>Please enter your registered email address </strong>
                        </p>

                        <div class="form-group">
                            <div class="col-md-6">
                                <input id="email" name="email" type="email" placeholder="Email Address" class="form-control input-sm" />
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="col-md-6">
                                <input style="background: darkgreen;" class="btn btn-primary btn-sm" type="button" onclick="submitData('APPROVE')"
                                      value="Recover"/>
                            </div>

                        </div>



                    </div>

                    <p class="text-center mt-lg">Visit OR Lounge Site? <a href="../index.html">Click Here!</a>
                </form>
                <%
                }
                %>




            </div>
        </div>


    </div>
</section>
<section class="col-md-2"></section>
<!-- end: page -->

<!-- Vendor -->
<script src="../resources/vendor/jquery/jquery.js"></script>
<script src="../resources/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.js"></script>
<script src="../resources/vendor/nanoscroller/nanoscroller.js"></script>
<script src="../resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="../resources/vendor/magnific-popup/magnific-popup.js"></script>
<script src="../resources/vendor/jquery-placeholder/jquery.placeholder.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="../resources/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="../resources/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="../resources/javascripts/theme.init.js"></script>




</body>
</html>
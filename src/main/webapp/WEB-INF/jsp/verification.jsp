<%@ page import="org.orlounge.bean.GroupBean" %>
<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
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

    GroupBean groupBean = (GroupBean) request.getAttribute("group");
    UserInfo user = (UserInfo) request.getAttribute("user");
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
                                 Member Verification Page
                             </h4>
                         </td>
                     </Tr>


                </table>
            </div>
            <div class="panel-body" style="margin-top:0px;">
                <% if(success  && message!= null){
                    %>
                <div class="alert alert-success">
                    <p class="m-none text-weight-semibold h6"><%=message%></p>
                </div>
                <%

                }else if(success){
                    %>
                <div class="alert alert-success">
                    <p class="m-none text-weight-semibold h6">We have recorded your verification response. Thank you!</p>
                </div>
                <%

                } else {
                    %>

                <div class="alert alert-info">
                    <p class="m-none text-weight-semibold h6 col-md-12">

                    <table>
                        <tr>
                            <td width="70%" style="padding: 5px;margin: 0;text-align: left;vertical-align: text-top;">
                                <strong><%=user.getFirstName()+" "+ user.getLastName()%></strong>, pictured on left,
                                has requested to create an operating room lounge site for <%=groupBean.getGroupName()%>
                            </td>
                        </tr>
                    </table>


                    </p>
                </div>

                <script type="text/javascript">
                    function submitData(action){
                        if(!action){
                            return;
                        }
                        $('.error-msg').addClass('hide');
                        var msg = "";
                        var valid= true;
                        var firstName = $('#firstName').val();
                        if(!firstName || firstName.length === 0){
                            msg +=' First Name invalid.<br/>';
                            valid = false;
                        }
                        var lastName = $('#lastName').val();
                        if(!lastName || lastName.length === 0){
                            msg +=' Last Name invalid.<br/>';
                            valid = false;
                        }
                        var email = $('#email').val();
                        if(!email || email.length === 0){
                            msg +=' Email invalid.<br/>';
                            valid = false;
                        }
                        var phNo = $('#phNo').val();
                        if(!phNo || phNo.length === 0){
                            msg +=' Phone Number invalid.<br/>';
                            valid = false;
                        }
                        var role = $('#role').val();
                        if(!role || role.length === 0){
                            msg +=' Role/Designation invalid.<br/>';
                            valid = false;
                        }

                        if(valid){
                            $('#action').val(action.toUpperCase());
                            var ret = confirm('Are you sure you want to '+(action==='APPROVE'? 'approve': 'reject')+' the member');
                            if(ret === true)
                                $('#verifyForm').submit();

                        }else {
                            $('.error-msg').html(msg);
                            $('.error-msg').removeClass('hide');
                            return;
                        }

                    }
                </script>

                <form action="verifyUser" id="verifyForm" method="POST">
                    <div class="media-body">
                        <div class="alert alert-danger hide error-msg" >
                            <p class="m-none text-weight-semibold h6"></p>
                        </div>
                        <p>
                            <strong>My Details : </strong> ( All fields are mandatory )
                        </p>
                        <div class="form-group">
                            <div class="col-md-6">
                                <input id="firstName" name="firstName" type="text" placeholder="First Name" class="form-control input-sm" />
                                <input id="action" name="action" type="hidden" />
                                <input id="groupId" name="groupId" type="hidden" value="<%=groupBean.getGroupId()%>" />
                                <input id="userId" name="userId" type="hidden" value="<%=user.getUserId()%>" />
                            </div>
                            <div class="col-md-1">
                            </div>
                            <div class="col-md-6">
                                <input id="lastName" name="lastName" type="text" placeholder="Last Name" class="form-control input-sm" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <input id="email" name="email" type="email" placeholder="Email Address" class="form-control input-sm" />
                            </div>
                            <div class="col-md-1">
                            </div>
                            <div class="col-md-6">
                                <input id="phNo" name="phNo" type="text" placeholder="Phone Number" class="form-control input-sm" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <input id="role" name="role" type="text" placeholder="Role/Designation at Hospital" class="form-control input-sm" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6">
                                <input style="background: darkgreen;" class="btn btn-primary btn-sm" type="button" onclick="submitData('APPROVE')"
                                       value="I verify that this person is a working member of <%=groupBean.getGroupName()%> operating room lounge."/>
                            </div>
                            <div class="col-md-6">
                                <input style="background: #c2212d; " class="btn red btn-primary btn-sm" type="button" onclick="submitData('REJECT')"
                                       value="This person is not a working member of <%=groupBean.getGroupName()%> operating room lounge."/>
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
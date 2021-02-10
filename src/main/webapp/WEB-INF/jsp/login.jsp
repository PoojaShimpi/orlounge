<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html class="fixed">

<jsp:include page="../header.jsp"/>
<body style="min-height: 830px;">
<section class="body">

<!-- start: header -->

<header style="padding-top:100px;width:50%; height:500px;background-color:black;margin-left:350px;margin-top:50px;" class="col-md-12">



<%
    final UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<div class="logo-container">
    <a href="../" class="logo" style="margin-left:250px;">
        <img src="resources/other/logo_transparent.png">
    </a>

    <script type="text/javascript">
        $(document).ready(function(){
            //$('.logo').html('<img style="width: 175px;height:50px" src="other/logo_7.png" alt="ORLounge"/>');

        });
    </script>

    <p class="logo-text nav-form main-page-small-line" style="margin-left:200px;">
        AN ONLINE OPERATING ROOM COMMUNITY
    </p>
</div>


<!-- start: search & user box -->
<div>

    <sec:authorize access="isAnonymous()">

    <form action="j_spring_security_check" id="loginform" method="POST" class="signin-form clearfix nav-form">





                <div class="input-group input-group-icon" style="width:500px;padding-left:180px;">
                    <input type="text" class="form-control input" name="email" id="q" placeholder="Email id" style="width:320px; height:50px;">
    			        <span class="input-group-addon">
    										<span class="icon">
    											<i class="fa fa-user" aria-hidden="true"></i>
    										</span>
    							</span>
                </div>
                    <div class="input-group input-group-icon pull-up" style="margin-top:15px;width:500px;padding-left:180px;">
                                    <input type="password" class="form-control input" name="password" id="pass" placeholder="Password" style="width:320px; height:50px;">
                                    <span class="input-group-addon">
                    										<span class="icon">
                    											<i class="fa fa-lock" aria-hidden="true"></i>
                    										</span>
                    							</span>


                                </div>
                <div class="input-group input-group-icon pull-right" style="background: none;border: none;padding: 0;margin-top: 20px;">
                    <span class="input-group-addon" style="background: none; border:none;color: #ffffff;font-size: 12px;">
                    <a style="color: #ffffff;" class="bold" href="forgotPassword"> Forgot Password <i class="fa fa-question-circle" aria-hidden="true"></i>
                        </a></span>
                </div><div class="">
                    <button type="submit" style="margin-top:70px;margin-left:290px;height:50px;" onclick="callsubmit()" class="btn btn-primary hidden-xs " id="signBtn">Sign In
                    </button>
                </div>



            </form>

       <script type="application/javascript">
            function callsubmit() {
                $('#loginform').submit();
            }
        </script>
    </sec:authorize>

    <sec:authorize access="isFullyAuthenticated()">
        <span class="separator"></span>
        <ul class="notifications">
            <li>
                <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
                    <i class="fa fa-globe"></i>
                    <span class="badge">3</span>
                </a>

                <div class="dropdown-menu notification-menu large">
                    <div class="notification-title">
                        <span class="pull-right label label-default">3</span>
                        Notifications
                    </div>

                    <div class="content">
                        <ul>
                            <li>
                                <p class="clearfix mb-xs">
                                    <span class="message pull-left">Generating Sales Report</span>
                                    <span class="message pull-right text-dark">60%</span>
                                </p>

                                <div class="progress progress-xs light">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 60%;"></div>
                                </div>
                            </li>

                            <li>
                                <p class="clearfix mb-xs">
                                    <span class="message pull-left">Importing Contacts</span>
                                    <span class="message pull-right text-dark">98%</span>
                                </p>

                                <div class="progress progress-xs light">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="98" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 98%;"></div>
                                </div>
                            </li>

                            <li>
                                <p class="clearfix mb-xs">
                                    <span class="message pull-left">Uploading something big</span>
                                    <span class="message pull-right text-dark">33%</span>
                                </p>

                                <div class="progress progress-xs light mb-xs">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="33" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 33%;"></div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
                    <i class="fa fa-bullhorn"></i>
                    <span class="badge">4</span>
                </a>

                <div class="dropdown-menu notification-menu">
                    <div class="notification-title">
                        <span class="pull-right label label-default">New</span>
                        Announcements
                    </div>

                    <div class="content">
                        <ul>
                            <li>
                                <a href="#" class="clearfix">
                                    <figure class="image">
                                        <img src="resources/images/user_50.jpg" alt="Joseph Doe Junior"
                                             class="img-circle"/>
                                    </figure>
                                    <span class="title">Joseph Doe</span>
                                    <span class="message">Lorem ipsum dolor sit.</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="clearfix">
                                    <figure class="image">
                                        <img src="resources/images/user_50.jpg" alt="Joseph Junior" class="img-circle"/>
                                    </figure>
                                    <span class="title">Joseph Junior</span>
                                    <span class="message truncate">Truncated message. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet lacinia orci. Proin vestibulum eget risus non luctus. Nunc cursus lacinia lacinia. Nulla molestie malesuada est ac tincidunt. Quisque eget convallis diam, nec venenatis risus. Vestibulum blandit faucibus est et malesuada. Sed interdum cursus dui nec venenatis. Pellentesque non nisi lobortis, rutrum eros ut, convallis nisi. Sed tellus turpis, dignissim sit amet tristique quis, pretium id est. Sed aliquam diam diam, sit amet faucibus tellus ultricies eu. Aliquam lacinia nibh a metus bibendum, eu commodo eros commodo. Sed commodo molestie elit, a molestie lacus porttitor id. Donec facilisis varius sapien, ac fringilla velit porttitor et. Nam tincidunt gravida dui, sed pharetra odio pharetra nec. Duis consectetur venenatis pharetra. Vestibulum egestas nisi quis elementum elementum.</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="clearfix">
                                    <figure class="image">
                                        <img src="resources/images/user_50.jpg" alt="Joe Junior" class="img-circle"/>
                                    </figure>
                                    <span class="title">Joe Junior</span>
                                    <span class="message">Lorem ipsum dolor sit.</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="clearfix">
                                    <figure class="image">
                                        <img src="resources/images/user_50.jpg" alt="Joseph Junior" class="img-circle"/>
                                    </figure>
                                    <span class="title">Joseph Junior</span>
                                    <span class="message">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet lacinia orci. Proin vestibulum eget risus non luctus. Nunc cursus lacinia lacinia. Nulla molestie malesuada est ac tincidunt. Quisque eget convallis diam.</span>
                                </a>
                            </li>
                        </ul>

                        <hr/>

                        <div class="text-right">
                            <a href="#" class="view-more">View All</a>
                        </div>
                    </div>
                </div>
            </li>

        </ul>
    </sec:authorize>

    <sec:authorize access="isFullyAuthenticated()">
        <span class="separator"></span>

        <div id="userbox" class="userbox">


            <a href="#" data-toggle="dropdown">
                <figure class="profile-picture">
                    <img src="resources/images/user_50.jpg" alt="Joseph Doe" class="img-circle"
                         data-lock-picture="resources/images/user_100.jpg"/>
                </figure>

                <div class="profile-info" data-lock-name="<%=token.getFirstName()+" "+token.getLastName()%>"
                     data-lock-email="<%=token.getUserCode()%>">
                    <span class="name"><%=token.getFirstName() + " " + token.getLastName()%></span>
                    <span class="role"><%=token.getRole()%></span>

                </div>

                <i class="fa custom-caret"></i>

            </a>

            <div class="dropdown-menu">
                <ul class="list-unstyled">
                    <li class="divider"></li>
                        <%--<li>
                            <a role="menuitem" tabindex="-1" href="pages-user-profile.html"><i class="fa fa-user"></i> My Profile</a>
                        </li>
                        <li>
                            <a role="menuitem" tabindex="-1" href="#" data-lock-screen="true"><i class="fa fa-lock"></i> Lock Screen</a>
                        </li>--%>
                    <li>
                        <a role="menuitem" tabindex="-1" href="logout"><i class="fa fa-power-off"></i> Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </sec:authorize>

</div>
<!-- end: search & user box -->
</header>
<script type="text/javascript">
    $('#q').on('blur',function(){
        $('#pass').focus();
    });
    $('#pass').on('blur', function(){
        $('#signBtn').focus();
    });
    $(document).ready(function(){
        $('.forgot-btn').tooltip({title: "Hooray", placement: "top"});

    });
</script>
<!-- end: header -->
</section>

<jsp:include page="../mainScripts.jsp"/>
<script type="text/javascript">
    var radio = 'hospital';
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            //$('.testBtn').fadeIn();
        } else {
            //$('.testBtn').fadeOut();
        }
    });
    // $(document).ready(function () {
    $("#otp").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        console.log('called me otp')
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
                    // Allow: Ctrl+A, Command+A
                (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                    // Allow: home, end, left, right, down, up
                (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode >
                105)) {
            e.preventDefault();
        }
    });
    // });
    $('.testBtn').click(function () {
        $('html, body').animate({
            scrollTop: 500
        }, 800);
        return false;
    });

    function checkEmail() {
        var username = $('#email').val();
        myAjax({
            url: "checkUserNameExists",
            genErrToast: false,
            data: {
                username: username
            },
            success: function (result) {
                if (result !== true) {
                    new PNotify({
                        title: '.',
                        text: "The Email already exists.",
                        type: 'error'
                    });
                    $('#validate').attr('disabled', true);

                } else {
                    $('#validate').attr('disabled', false);
                }

            }
        });
    }

    $("#qrRole").change(function () {
        var qrRole = $('option:selected', this).text();
        var email = $('#email').val();
        console.log(radio);
        if (radio === 'ambulatory_center') {
            var hospitalId = $('#ambituary').val();
        } else if (radio === 'hospital') {
            var hospitalId = $('#hospital').val();
        }
        var data = "email=" + email + "&role=" + qrRole + "&hospital_id=" + hospitalId;
        console.log(qrRole, email, hospitalId);
        /*myAjax({
         type: "POST",url: "checkemailbyrole", genErrToast: false, data: {email: email,role:qrRole,hospital_id:hospitalId}, success: function (result) {
         alert(result);
         }
         });*/
        myAjax({
            method: "POST",
            url: "checkemailbyrole",
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success: function (result) {
                if (!result.success) {
                    new PNotify({
                        title: '',
                        text: "Your are already registered user with ORM",
                        type: 'error'
                    });
                    $('#validate').attr('disabled', true);
                } else {
                    $('#validate').attr('disabled', false);
                }
            }

        });
        myAjax({
            method: "POST",
            url: "checkemailbyrole",
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success: function (result) {
                if (!result.success) {
                    new PNotify({
                        title: '',
                        text: "Your are already registered user with ORM",
                        type: 'error'
                    });
                    $('#validate').attr('disabled', true);
                } else {
                    $('#validate').attr('disabled', false);
                }
            }

        });

    });


    function radioButtonChange(val) {
        radio = val;
        if (val === 'ambulatory_center') {
            console.log('ambulatory_center', val, $('#hospital'));
            $('#hospital').addClass('isActiveHospital')
            $('#ambituary').removeClass('isActiveAmbultary')

        } else if (val === 'hospital') {
            console.log('hospital', val, $('#ambituary'));
            $('#hospital').removeClass('isActiveHospital')
            $('#ambituary').addClass('isActiveAmbultary')

        }
    }

    function onHospitalNoBlur() {

        var email = $('#email').val();
        var hospitalPhNoMask = $('#hospital-phone').val().replace(/[^0-9\.]/g, '');
        if (hospitalPhNoMask) {
            var data = "email=" + email + "&hospitalPhNo=" + encodeURIComponent('+') + "91" + hospitalPhNoMask;
        }
        if(email && hospitalPhNoMask){
        myAjax({
            method: "POST",
            url: "requestotp",
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success: function (result) {
                if (!result.success) {
                    new PNotify({
                        title: '',
                        text: "Email or Hospital phone no is missing",
                        type: 'error'
                    });
                    $('#validate').attr('disabled', true);
                } else {
                    new PNotify({
                        title: '',
                        text: "Please enter your OTP we send you",
                        type: 'success'
                    });
                     $('#otp').prop('disabled', false);
                     $('#verify').removeClass('disabled');
                     $('#verify').prop('disabled', false);

                    // $('#validate').attr('disabled', false);
                }
            }
        });
        }
    }

    function fixInputs(toFix){
        if(toFix){
            $('#verify').hide();
            $('#verified').show();
        }else {
            $('#verify').show();
            $('#verified').hide();
        }
        $('#hospital-phone').prop('readonly', toFix);
        $('#otp').prop('readonly', toFix);

    }

    function verifyOtp(e) {
        if ($('#otp').prop('disabled')) {
            return;
        }
        var email = $('#email').val();
        var otp = $('#otp').val();
        var data = "email=" + email + "&otp=" + otp;
        myAjax({
            method: "POST",
            url: "verfiyotp",
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success: function (result) {
                if (!result.success) {
                    new PNotify({
                        title: '',
                        text: "Your OTP is invalid",
                        type: 'error'
                    });
                    $('#validate').attr('disabled', true);
                    fixInputs(false);
                } else {
                    new PNotify({
                        title: '',
                        text: "Your OTP is verified",
                        type: 'success'
                    });
                    $('#validate').attr('disabled', false);
                    fixInputs(true);
                }
            }
        });
    }
</script>
<jsp:include page="notification-box.jsp"/>
</body>
</html>

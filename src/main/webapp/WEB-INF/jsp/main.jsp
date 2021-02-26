<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
    if (ProcessData.isValid(token)) {
        response.sendRedirect("/messageboard.html");
    }

%>
<!doctype html>
<html class="fixed">
<jsp:include page="../header.jsp"/>

<body style="min-height: 830px;background-color:white;">
<section class="body" style="font-family:palatino;">
    <%--<jsp:include page="../head-section.jsp"/>--%>

    <div >
        <jsp:include page="extract-notification.jsp"/>
        <div class="panel center">

        <div >
                          <section class="panel panel-horizontal  home-panel" style="background-color:white;">
                          <div class="col-md-4">   <img src="resources/images/lounge-logo.png" style="height:132.87px;">
                          </div>
                             <div class="col-md-8">         <p style="font-size:20px;;margin-top:20px;"> <b>
                                                                <b>     WWW.ORLounge.com is for the operating room community</b>
                                                                 </b></p>
                                                                 <p style="font-size:20px;margin-top:20px;">
                                                                     Any staff from the operating room can create a private Lounge to work together
                                                                 </p>
                                                            </div>
                               </section><b>
                               </b><b>
                            </b></div>
                            </div>

        <section role="main" style="background-image: url('resources/images/orlounge.png');background-repeat: no-repeat;">
<section style="left:15%;margin-left:250px;">





                </section>
            <!-- start: page -->

            <div class="clearfix">
                <div class="center" style="margin-top:330px;">
                    <div class="panel">


                    </div>
  <div class="col-md-3" style="background-color:white;">
  <div class="panel-body p-lg" style="background-color:white;">

                                                <h4 class="text-weight-semibold mt-sm">
                    Build your own library.</h4>

                                            </div>
                                                <img src="resources/images/library.png">


                                        </div>

                    <div class="col-md-3" style="height:230.64px;background-color:white;">
  <div class="panel-body p-lg" style="background-color:white;">

                                                <h4 class="text-weight-semibold mt-sm">
                    Updated in real-time.</h4>

                                            </div>
                                                <img src="resources/images/thinMB.png" style="height:132.87px;">


                                        </div>

                    <div class="col-md-3" style="height:230.64px;background-color:white;">
  <div class="panel-body p-lg" style="background-color:white;">

                                                <h4 class="text-weight-semibold mt-sm">
                    Coordinate OR activities.</h4>

                                            </div>
                                                <img src="resources/images/coordination.png" style="height:132.87px;">


                                        </div>

                    <div class="col-md-3" style="height:230.64px;background-color:white;">
   <div class="panel-body p-lg" style="background-color:white;">

                                                <h4 class="text-weight-semibold mt-sm">
                    Increase Productivity .</h4>

                                            </div>
                                                <img src="resources/images/productivity.png" style="height:132.87px;">


                                        </div>

<div class="row">
  <div class="col-md-12" style="padding-top:60px;padding-left:200px;">
  <div class="col-md-4">
  <form class="navitem" method="get" action="register-holder.html">
                                        <input type="submit" value="Register Here" style="background-color: #00BFFF; /* Green */ border: none;color: white;border-radius: 2px;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 13px;margin: 4px 2px;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);cursor: pointer;-webkit-transition-duration: 0.4s; /* Safari */transition-duration: 0.4s;">
                                        </form>
  </div>
  <div class="col-md-4">
   <form class="navitem" method="get" action="login.html">
                                                                               <input type="submit" value="  Login Here    " style="background-color: #00BFFF; /* Green */ border: none;color: white;border-radius: 2px;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 13px;margin: 4px 2px;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);cursor: pointer;-webkit-transition-duration: 0.4s; /* Safari */transition-duration: 0.4s;">
                                                                               </form>
    </div>
  </div>
</div>


                </div>

                <%--<div class="col-md-4">
                    <jsp:include page="register.jsp"/> -->
                    <%--<section class="panel panel-horizontal  home-panel">

                    <header class="panel-heading bg-white  mt-sm">
                        <div style="font-size: 35px;" class="panel-heading-icon bg-primary ">
                            <i class="fa fa-user-md"></i>
                        </div>
                    </header>
                    <div class="panel-body p-lg">
                        <h5 class="text-weight-semibold mt-sm">
                            Healthcare Collaboration Apps
                        </h5>
                        <p>
                           HCA Apps. ( <a href="#"  data-toggle="modal" data-target="#hcaModal">Click here</a> to know more )

                        </p>
                    </div>
                    <div id="hcaModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">HCA Apps</h4>
                                </div>
                                <div class="modal-body">
                                    <p>
                                        Medical care is always a team effort. The team members include residents,
                                        physical therapists, social workers, specialty nurses, administrators,
                                        billing professionals and many others. No provider works alone.<br/>
                                        <a>www.orlounge.com</a> is a new breed of software application we call <b style="color:#0088cc;font-size:12px;line-height:18px;font-family:Arial,sans-serif">HCApps</b>.
                                        We build ready-to-use Healthcare Collaborative Applications (<b style="color:#0088cc;font-size:12px;line-height:18px;font-family:Arial,sans-serif">HCApps</b>) to
                                        help the care team work together in a particular area of care, with its own members, tools, goals and outcome.<br/>
                                        Each <b style="color:#0088cc;font-size:12px;line-height:18px;font-family:Arial,sans-serif">HCApp</b> is private and local and its contents are the input of the members
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>

                        </div>
                    </div>

                  </section>--%>
                <%-- </div>--%>
<div class="col-md-12" style="padding-top:35px;padding-left:400px;margin-top:30px;color:white;font-size:25px;background-color:#87CEFA;height:70px;width:1500px;"> <b> It's a dashboard, a collaborative, productivity, management and social site </b>
</div>
            </div>


            <div>

                <div class="row">


                    <div class="col-md-12 col-lg-12 col-xl-6">


                    </div>

                </div>

              <%--  <section class="panel panel-horizontal">
                    <header class="panel-heading bg-white  mt-sm">
                        <div class="panel-heading-icon bg-primary ">
                            <i class="fa fa-cogs"></i>
                        </div>
                    </header>
                    <div class="panel-body ">
                        <h4 class="text-weight-semibold mt-sm">
                            Manage each site yourself
                        </h4>

                        <p>
                            Each site is administered locally, under the<br/> full control of each OR Manager,<br/>
                            who
                            manages the members and contents and is responsible<br/>
                            for its own security and privacy.

                        </p>
                    </div>
                    <header class="panel-heading bg-white  mt-sm">
                        <div class="panel-heading-icon bg-primary ">
                            <i class="fa fa-cubes"></i>
                        </div>
                    </header>
                    <div class="panel-body p-lg">
                        <h4 class="text-weight-semibold mt-sm">
                            Working as a team
                        </h4>

                        <p>
                            Everyone is literally on the same (web) page.<br/> OR Managers, doctors and nurses can
                            actually work together.

                        </p>
                    </div>

                    <header class="panel-heading bg-white  mt-sm">
                        <div class="panel-heading-icon bg-primary ">
                            <i class="fa fa-cloud"></i>
                        </div>
                    </header>
                    <div class="panel-body p-lg">
                        <h4 class="text-weight-semibold mt-sm">
                            Hosted on the cloud


                        </h4>

                        <p>
                            No network to administer or software to maintain. No hustle. No fees!

                        </p>
                    </div>

                </section> --%>


        </section>

    </div>
    <!-- end: page -->
</section>
</div>
</aside>
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
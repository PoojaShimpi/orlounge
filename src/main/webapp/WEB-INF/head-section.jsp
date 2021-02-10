<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- start: header -->
<header class="header main-page-header col-md-12">

<%
    final UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<div class="logo-container">
    <a href="../" class="logo">
        <img src="resources/other/logo_transparent.png" />
    </a>

    <script type="text/javascript">
        $(document).ready(function(){
            //$('.logo').html('<img style="width: 175px;height:50px" src="other/logo_7.png" alt="ORLounge"/>');

        });
    </script>

    <p class="logo-text nav-form main-page-small-line">
        AN ONLINE OPERATING ROOM COMMUNITY
    </p>
</div>


<!-- start: search & user box -->
<div class="header-right">


    <sec:authorize access="isAnonymous()">


        <form action="j_spring_security_check" id="loginform" method="POST" class="signin-form clearfix nav-form">


            <div class='input-group  pull-right'>
                <button type="submit" style="margin-top:6px;" onclick="callsubmit()" class="btn btn-primary hidden-xs " id="signBtn">Sign In
                </button>
            </div>
            <div class='input-group input-group-icon pull-right'>
                <input type="password" class="form-control input" name="password" id="pass" placeholder="Password">
                <span class="input-group-addon">
										<span class="icon">
											<i class="fa fa-lock"></i>
										</span>
							</span>


            </div>

            <div class="input-group input-group-icon pull-right">
                <input type="text" class="form-control input" name="email" id="q" placeholder="Email id">
			        <span class="input-group-addon">
										<span class="icon">
											<i class="fa fa-user"></i>
										</span>
							</span>
            </div>
            <div class='input-group input-group-icon pull-right' style="background: none;border: none;padding: 0;margin-top: 0;margin-bottom: 4px;">
                <span class="input-group-addon" style="background: none; border:none;color: #ffffff;font-size: 12px;">
                <a style="color: #ffffff;" class="bold" href="forgotPassword"> Forgot Password <i class="fa fa-question-circle"></i>
                    </a></span>
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

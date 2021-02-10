<%@ page import="org.orlounge.common.UserToken" %>
<section role="main" width="" class="content-body">
    <header class="page-header hospital-title-header">
        <span class="hospital-title-header-h2">
            <%=session.getAttribute("HOSPITAL_NAME").toString().toUpperCase()%>
         </span>
        <%
            UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
            String alertLine = !token.isHasOrmAsLSA() ?
                    "<span style='padding:1px;background-color:green;color:white;font-weight:bold;'>Your site is Secure Site</span>" : "<span  style='padding:1px;background-color:red;color:white;font-weight:bold;'> This site is not secure site, Why? See This</span>";
        %>

        <div class="right-wrapper pull-right">
            <ol class="breadcrumbs">
                <li>
                    <a href="home.html">
                        <i class="fa fa-home"></i>
                    </a>
                </li>
                <%--<li><%=alertLine%></li>--%>
            </ol>

            <a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-right"></i></a>
        </div>
    </header>

    <!-- start: page -->

    <div class="row">
    <div class="col-md-12 col-lg-12">
        <div class="row">
            <div class="col-md-12">
                <h3 class="mt-lg">Welcome To ORLounge!!!</h3>
                <p class="mb-lg">Hope you have a great time here.</p>

                <section class="panel-group mb-xlg">
                    <div class="widget-twitter-profile">

                        <div class="profile-info">
                            <div class="profile-picture">
                                <img src="resources/images/!logged-user.jpg" alt="">
                            </div>
                            <div class="profile-account">
                                <h3 class="name text-weight-semibold"><%=token.getFirstName()+ " " + token.getLastName()%></h3>
                                <a href="#" class="account"><%=token.getIpAddress()%></a>
                            </div>
                            <ul class="profile-stats">
                                <%--<li>
                                    <h5 class="stat text-uppercase">Tweets</h5>
                                    <h4 class="count">60</h4>
                                </li>
                                <li>
                                    <h5 class="stat text-uppercase">Following</h5>
                                    <h4 class="count">139</h4>
                                </li>--%>
                                <li>
                                    <h5 class="stat text-uppercase"></h5>
                                    <h4 class="count"><%=token.getRole()%></h4>
                                </li>
                            </ul>
                        </div>
                        <div class="profile-quote">
                            <blockquote>
                                <p>
                                    What you do Today can improve all your tomorrows.
                                </p>
                            </blockquote>
                            <div class="quote-footer">
                                <span class="datetime">- Ralph Marston</span>
                                <%--<a href="#">Details</a>--%>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <div class="col-md-12 col-xl-6">
                <section class="panel">
                    <header class="panel-heading bg-tertiary">
                        Quick Links
                    </header>
                    <div class="panel-body">
                        <p><a href="profile.html"><i class="fa fa-user mr-xs"></i>Profile</a></p>
                        <p><a href="loungeworks.html">Lounge Works</a></p>
                        <p><a href="messageboard.html"> Message Board</a></p>
                    </div>
                </section>
            </div>
        </div>
    </div>

    </div>
    <!-- end: page -->
</section>

<%@ page import="org.orlounge.common.UserToken" %>
<aside id="sidebar-right" class="sidebar-right">
    <div class="nano">

        <%
            UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
        %>
        <div class="nano-content">
            <a href="#" class="mobile-close visible-xs">
                Collapse <i class="fa fa-chevron-right"></i>
            </a>

            <div class="sidebar-right-wrapper">

                <%
                    if(!token.isHasOrmAsLSA()){
                %>
                <div class="sidebar-widget">

                    <h6>Why Not Secure Site ?</h6>
                    <p>

                        This site has a temporary local site administrator. For security reasons, and to derive maximum benefits,
                        it should have a permanent local site administrator and here's why.


                        <ul class="white">
                            The LSA
                            <li class="white">
                                can approve, or reject all requests for membership, and
                            </li>
                            <li class="white">
                                even expel them, because the LSA knows the situation best.
                            </li>
                            <li class="white">
                                manages contents by deleting offensive, inappropriate or illegal contents.
                            </li>
                            <li class="white">
                                can work with the hospital network administrator to limit site access.
                            </li>
                            <li class="white">
                                can appoint an assistant to help.
                            </li>

                            <li class="white">
                                can invite other OR members to join the site, post official notices so that everyone knows what's going on
                            </li>
                        </ul>
                    We suggest that your OR Manager or the assistant is the best person to be Local Site Administrator.
                    </p>

                </div>
                <%
                    }
                %>

                <div class="">

                    <h6>Invite Member to Join this Site?</h6>
                    <p>
                        You can invite a member to join this site. we shall send them the invite mail,
                        But. for getting the user using the site, The Local Site Administrator needs to approve him/her.


                    <a target="_blank" href="invite.html" onclick="location.href='invite.html'">Click here</a> to invite.

                </div>


                <div class="sidebar-widget widget-calendar">
                    <h6>Plan your schedules...</h6>
                    <div data-plugin-datepicker data-plugin-skin="dark" ></div>

                    <%--<ul>
                        <li>
                            <time datetime="2014-04-19T00:00+00:00">04/19/2014</time>
                            <span>Company Meeting</span>
                        </li>
                    </ul>--%>
                </div>

<%--
                <div class="sidebar-widget widget-friends">
                    <h6>Friends</h6>
                    <ul>
                        <li class="status-online">
                            <figure class="profile-picture">
                                <img src="resources/images/user.jpg" alt="Joseph Doe" class="img-circle">
                            </figure>
                            <div class="profile-info">
                                <span class="name">Joseph Doe Junior</span>
                                <span class="title">Hey, how are you?</span>
                            </div>
                        </li>
                        <li class="status-online">
                            <figure class="profile-picture">
                                <img src="resources/images/user.jpg" alt="Joseph Doe" class="img-circle">
                            </figure>
                            <div class="profile-info">
                                <span class="name">Joseph Doe Junior</span>
                                <span class="title">Hey, how are you?</span>
                            </div>
                        </li>
                        <li class="status-offline">
                            <figure class="profile-picture">
                                <img src="resources/images/user.jpg" alt="Joseph Doe" class="img-circle">
                            </figure>
                            <div class="profile-info">
                                <span class="name">Joseph Doe Junior</span>
                                <span class="title">Hey, how are you?</span>
                            </div>
                        </li>
                        <li class="status-offline">
                            <figure class="profile-picture">
                                <img src="resources/images/user.jpg" alt="Joseph Doe" class="img-circle">
                            </figure>
                            <div class="profile-info">
                                <span class="name">Joseph Doe Junior</span>
                                <span class="title">Hey, how are you?</span>
                            </div>
                        </li>
                    </ul>
                </div>
--%>

            </div>
        </div>
    </div>
</aside>
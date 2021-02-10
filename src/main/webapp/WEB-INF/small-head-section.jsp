<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="jsp/checkSession.jsp"/>

<head>
    <link href='https://fonts.googleapis.com/css?family=Nunito' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
</head>
<%
    final UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>
<!-- start: header -->
<header class="header topHeaderbar">

    <div class="clearfix"></div>
    <table class="col-sm-12">
        <tr class="header-top">
            <td class="col-sm-9">
                <div class="logo-container login-after-logo">
                    <a href="" class="logo">
                    </a>
                    <p class="top-header-sub-head">
                        AN ONLINE OPERATING ROOM COMMUNITY
                    </p>

                </div>
            </td>
            <td class="col-sm-3">

                <div>
                    <sec:authorize access="isAnonymous()">
                        <form action="index.html" id="loginform" method="POST" class="search nav-form ">
                            <input type="hidden" name="ref" id="ref_fld"/>
                            <button type="submit" class="btn btn-primary hidden-xs">Sign In</button>
                        </form>
                        <script type="application/javascript">
                            function callsubmit() {
                                $('#ref_fld').val(window.href);
                                $('#loginform').submit();
                            }
                        </script>
                    </sec:authorize>
                    <sec:authorize access="isFullyAuthenticated()">

                        <span class="separator hidden"></span>

                        <div id="userbox" class="userbox pull-right">
                            <script type="text/javascript">
                                var myImage = '<%=token.getUserImage()%>' || 'resources/images/user.jpg';
                            </script>

                            <a href="#" data-toggle="dropdown">
                                <figure class="profile-picture">
                                    <img src="getImageDocument?filePath=<%=token.getUserImage()%>" alt=""
                                         class="img-responsive"
                                         data-lock-picture="getImageDocument?filePath=<%=token.getUserImage()%>"/>
                                </figure>

                                <div class="profile-info" data-lock-name="<%=token.getFirstName()+" "+token.getLastName()%>"
                                     data-lock-email="<%=token.getUserCode()%>">
                                    <span class="name"> <%=token.getFirstName() %></span>
                                    <span class="role">
                                </div>

                                <i class="fa custom-caret"></i>

                            </a>

                            <div class="dropdown-menu">
                                <ul class="list-unstyled">
                                    <li class="divider"></li>
                                    <li>
                                        <a role="menuitem" tabindex="-1" href="profile.html"><i class="fa fa-user"></i> My
                                            Profile</a>
                                    </li>
                                    <li>
                                        <a role="menuitem" tabindex="-1" href="#" data-lock-screen="true"><i
                                                class="fa fa-lock"></i>
                                            Lock Screen</a>
                                    </li>
                                    <li>
                                        <a role="menuitem" tabindex="-1" href="logout"><i class="fa fa-power-off"></i>
                                            Logout</a>
                                    </li>
                                    <%
                                        if (token.isAdmin() || token.isLSA() && Objects.nonNull(token.getGroupAccessList()) && token.getGroupAccessList().size()> 1) {
                                    %>

                                    <li>
                                        <ul class="list-unstyled">
                                            <li class="divider"></li>
                                            <li>
                                                <a role="menuitem" tabindex="-1" href="orltoenter.html"><i
                                                        class="fa fa-power-off"></i>Switch ORLS</a>
                                            </li>

                                        </ul>
                                    </li>

                                    <%
                                        }
                                    %>

                                </ul>

                            </div>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="isFullyAuthenticated()">
                        <span class="separator hidden"></span>
                    </sec:authorize>
                </div>
            </td>
        </tr>
    </table>
    <div class="clearfix"></div>
    <div>


    </div>

    <div class="annnouncements-container adbar col-md-12" style="overflow-y: hidden;
    max-height: 160px;">
                  <div class="col-md-3 announc-panel">
                                        <h5 class="announc-title" style="text-align: center;font-weight: 700;">What's Coming Up!</h5>
                                        <div class="announce-body">

                                            <div class="row">
                                                <div class="col-sm-15 announce-box-teaser">
                                                    <figure class="figure col-sm-1 col-md-1">
                                                        <img class="" alt="Joseph Doe Junior" src="resources/other/MMM.png">

                                                    </figure>
                                                    <span class="text col-sm-12 col-md-11 bold">

                                                    <span class="text col-sm-11">

                                                 Enhance your Lounge experience with what is coming up:<br>
                                                                Mobile Medical Messaging (MMM) App - A specially developed app built for our members.<br>
                                                                Special Interest Lounges - Lounges for anesthesiologists, nurses, OR manager, residents and surgeons <br>
                                                                Off Duty Lounge -  A Lounge to socialize and personal shopping <br>
                                                    </span>
                                                    <a class="readmore modal-with-move-anim" href="#modalreadmore1">...read more</a>
                                                </span>
                                                </div>

                                            </div>
                                            <div class="row hidden">
                                                <div class="col-md-6 announce-box-teaser nomrg">
                                                    <figure class="figure">
                                                        <img class="" alt="Joseph Doe Junior" src="getImageDocument?filePath=announcement/M/steris.jpg">
                                                    </figure>
                                                    <span class="text">
                                                    Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered shoulder-to-shoulder and head-to Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered <a class="readmore modal-with-move-anim" href="#modalreadmore">...read more</a>
                                                </span>
                                                </div>
                                                <div class="col-md-6 announce-box-teaser nomrg">
                                                    <figure class="figure">
                                                        <img class="" alt="Joseph Doe Junior" src="getImageDocument?filePath=announcement/M/steris.jpg">
                                                    </figure>
                                                    <span class="text">
                                                Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered shoulder-to-shoulder and head-to Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered <a class="readmore modal-with-move-anim" href="#modalreadmore">...read more</a>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
            <div class="col-md-3 announc-panel">
                <h5 class="announc-title" style="text-align: center;font-weight: 700;">Recent Announcements</h5>
               <div>
                               <form method="get" target="_blank" action="https://docs.google.com/document/d/1IpDk5BBeN9bNgLFa7SAtPX4clYXRZChnbLjJtbcaJoc/edit?usp=sharing">
                               <input type="submit" value="Click for Recent Announcements" style="background-color: #4CAF50; /* Green */ border: none;color: white;border-radius: 2px;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 13px;margin: 4px 2px;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);cursor: pointer;-webkit-transition-duration: 0.4s; /* Safari */transition-duration: 0.4s;">
                               </form>
                            	</div>
<!--
                <div class="announc-body announce-overflow recent-announcement bold watermark" style="color:#000;font-family:Arial;">
                    <marquee class="recent-announcement" behavior="alternate" direction="up" id="recentMarq" scrolldelay="1000"
                             onmouseover="document.getElementById('recentMarq').stop();"
                             onmouseout="document.getElementById('recentMarq').start()">
                        <a href="#recentannouncementLink" class="announc-link modal-with-move-anim" style="font-family:Arial;line-height:20px; color:#000;"
                           title="Demo Announcement 1">
                            Demo Announcement 1
                        </a>
                        <a href="#recentannouncementLink" class="announc-link modal-with-move-anim" style="line-height:20px;color:#000;"
                           title="Demo Announcement 2">
                            Demo Announcement 2
                        </a>
                        <a href="#recentannouncementLink" class="announc-link modal-with-move-anim" style="line-height:20px;color:#000;"
                           title="Demo Announcement 3">
                            Demo Announcement 3
                        </a>
                        <a href="#recentannouncementLink" class="announc-link modal-with-move-anim" style="line-height:20px;color:#000;" title="Demo Announcement 4">
                            Demo Announcement 4
                        </a>
                        <a href="#recentannouncementLink" class="announc-link modal-with-move-anim" style="line-height:20px;color:#000;"
                           title="Demo Announcement 5">
                            Demo Announcement 5
                        </a>
                    </marquee>
                </div> -->
            </div>
            <div class="col-md-3 announc-panel">
                <h5 class="announc-title" style="text-align: center;font-weight: 700;">Announcements</h5>
                <div class="announce-body">

                    <div class="row">
                        <div class="col-sm-15 announce-box-teaser">
                            <figure class="figure col-sm-1 col-md-1">
                                <img class="" alt="Joseph Doe Junior"
                                     src="resources/other/announcement.jpg" />

                            </figure>
                            <span class="text col-sm-12 col-md-11 bold">
                            <span class="text col-sm-12">
                              Announcement Ad &nbsp;&nbsp;<a href="<%=AppConstants.BASE_URL%>" target="_blank">website</a>&nbsp;&nbsp;Contact : email@domain.com &nbsp;&nbsp;Tags : OR Table, General
                            </span>
                            <span class="text col-sm-11">
                                <br/>
                                An Announcement is a DIGITAL BROCHURE.<br/>
                                Instead of mailing brochures (with no certainty of where they end up), product news, conference announcement,
                                company news, newsletters etc, can be published here.
                                Announcement Ads are better and more effective than their hardcopy versions.
                                They cost less to produce, and less effort to create.
                                They have a better chance to be seen and read.

                            </span>
                            <a class="readmore modal-with-move-anim" href="#modalreadmore">...read more</a>
                        </span>
                        </div>

                    </div>
                    <div class="row hidden">
                        <div class="col-md-6 announce-box-teaser nomrg">
                            <figure class="figure">
                                <img class="" alt="Joseph Doe Junior" src="getImageDocument?filePath=announcement/M/steris.jpg">
                            </figure>
                            <span class="text">
                            Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered shoulder-to-shoulder and head-to Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered <a
                                    class="readmore modal-with-move-anim" href="#modalreadmore">...read more</a>
                        </span>
                        </div>
                        <div class="col-md-6 announce-box-teaser nomrg">
                            <figure class="figure">
                                <img class="" alt="Joseph Doe Junior" src="getImageDocument?filePath=announcement/M/steris.jpg">
                            </figure>
                            <span class="text">
                        Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered shoulder-to-shoulder and head-to Combined with a single fixed arm, dual fixed arm, or straight drop vertical fixed pendant (supply head only) patients are covered <a
                                    class="readmore modal-with-move-anim" href="#modalreadmore">...read more</a>
                    </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 announc-panel">
                <h5 class="announc-title" style="text-align: center;font-weight: 700;">OR Search</h5>
				  <div class="announce-body">
                    <div class="head-section">
                        <ul>
                            <li>
                                <img src="resources/other/or-search.jpeg" />
                            </li>

                        </ul>
                    </div>

                </div>
            </div>

    </div>
<div>
   <!-- <div class="form-group">
    				<label class="col-md-2 control-label">Body</label>
   			 <div class="col-md-10">
       				 <input  type="hidden" id="announcement" name="text"/>

            <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 100, "codemirror": { "theme": "ambiance"} }'>
            </div>
    </div>
    <div class="col-md-14">
             <a style="float: left !important;" class="btn btn-primary pull-right" href="#save" onclick="dataSubmit123()" >Save</a>
    </div>
</div> -->
<!-- <div class="panel-footer clearfix">

</div> -->
</div>
    <div class="clearfix"></div>

    <div id="whatscomingupinfo" class="zoom-anim-dialog modal-block modal-block-full mfp-hide readmore-panel">
        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title">What's Coming Up!</h2>

            <div class="panel-body">

                <section class="col-md-3 panel panel-featured panel-featured-custom panel-featured-primary">
                    <header class="panel-heading panel-heading-custom   ">
                        <h4 class="panel-title-custom" >What's Coming Up !</h4>
                    </header>
                    <div class="panel-body panel-body-custom">
                        <div class="announce-body  announce-overflow recent-announcement   "
                             style="cursor: pointer;cursor:hand;">

                            <font class="bold underline" onclick="loadDesc('sil')">Special Interest Lounges</font><br/>
                            <font class="bold underline" onclick="loadDesc('mob')">Mobile Messaging App</font><br/>
                            <font class="bold underline" onclick="loadDesc('off')">Off Duty</font><br/>
                            <font class="bold underline" onclick="loadDesc('off')">Off Duty</font><br/>
                            <font class="bold underline" onclick="loadDesc('orls')">What is different ABOUT this site?</font><br/>

                        </div>
                    </div>
                </section>
                <section class="col-md-3 panel panel-featured panel-featured-custom panel-featured-primary">
                    <header class="panel-heading panel-heading-custom ">
                        <h4 class="panel-title-custom modal-with-move-anim" href="#whatscomingupmodal">What is it</h4>
                    </header>
                    <div class="panel-body panel-body-custom">
                        <div class="announce-body  announce-overflow recent-announcement   "
                        >
                            <p class="whatisithead bold"></p>
                            <p class="whatisit">

                            </p>
                        </div>

                    </div>
                </section>
                <section class="col-md-4 panel panel-featured panel-featured-custom panel-featured-primary">
                    <header class="panel-heading panel-heading-custom">
                        <div class="panel-actions panel-actions-custom">
                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                        </div>

                        <h4 class="panel-title-custom">New Social Sites Tagcloud..</h4>
                    </header>
                    <div class="panel-body panel-body-custom">
                        <div class="announce-body  announce-overflow recent-announcement  " id="tag-cloud">

                            <img src="resources/other/tag_cloud_0.png"/>
                        </div>
                    </div>
                </section>

            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
    <div id="voteinfo" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title"> Vote your priority development</h2>

            <div class="panel-body">

                <div class="modal-text">
                    <%
                        List<UserToken.VoteInfo> infos = token.getVoteInfos();

                    %>
                    <div class="row mb-lg">
                        <font class="bold">Use 1-<%=infos.size()%> to indicate priorities 1 being top priority</font>

                    </div>
                    <p id="vote-err" class="red"></p>
                    <table>
                        <%
                            int i = 0;
                            for (UserToken.VoteInfo v : infos) {
                        %>
                        <tr>
                            <td>
                                <%=v.getText()%>
                            </td>
                            <td>
                                <input type="number" id="<%=v.getId()%>" name="vote_num" min="1" max="<%=infos.size()%>"
                                       value="<%=++i%>"/>
                            </td>
                        </tr>

                        <%
                            }
                        %>
                    </table>

                    <button class="btn" onclick="submitVotes()">Submit Vote</button>

                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
    <div id="whatisitreadmore" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title"></h2>

            <div class="panel-body">

                <div class="modal-text">


                    <p class="whatisittext bold">


                    </p>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
    <div id="modalreadmore1" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
                <section class="panel">

                    <h2 class="panel-title bold">What's Coming Up!'</h2>

                    <div class="panel-body bold">

                        <div class="modal-text">
                            <div class="row mb-lg">
                                <figure class="figure col-sm-4">
                                    <img class="" alt="Company Logo" src="resources/other/announcement.jpg">
                                </figure>
                                <div class="address col-sm-6 pull-right">
                                    <p>Website : <a href="http://orlounge.com" target="_blank">Website</a></p>

                                    <p>Contact Email : email@domain.com</p>


                                    <a href="#">Announcement Ad</a>
                                </div>
                            </div>

                            <p>
                                Mobile Medical Messaging (MMM) app
                                </p><figure class="figure col-sm-4">
                                    <img class="" alt="Company Logo" src="resources/other/MMM.png">
                                </figure>A dedicated medical mobile messaging app for private and group conversation between members only.
    Whatsapp is not safe. Facebook is integrating it with other Facebook products which means that they will now read all text, send you product information, introduce âfriendsâ etc.
    Full end-to-end encryption.
    Fully integrated with the Message Board so that members can stay in touch with the OR or any member from anywhere.
    Convenient communications between members.

    <br><br>
        <p>
        CORECAST </p>
        <figure class="figure col-sm-4">
                                    <img class="" alt="Company Logo" src="resources/other/corecast.png">
                                </figure>
    Watch live streaming live video for  your in-session education.<br>

                                 <br><br><br><br>

     <!--   <p>               Off Duty Lounge</p>
          <figure class="figure col-sm-4">
                                    <img class="" alt="Company Logo" src="resources/other/offduty.png">
                                </figure>
        Relax when you are not operating
    Shopping  Travels  Games Investment  Entertainment  Socialising and more..
    <br><br><br><br>
                            <p>Special interest Lounges</p>
            <figure class="figure col-sm-4">
                                    <img class="" alt="Company Logo" src="resources/other/specialinterest.png">
                                </figure>
    Special Interest Lounges can be created
    A hospital can easily work with other hospital lounges or  similar groups.

    -->
                        </div>
                    </div>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-md-12 text-right">

                                <button class="btn btn-default modal-dismiss" id="myexit">Exit</button>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>

    <div id="modalreadmore" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title bold">Announcement</h2>

            <div class="panel-body bold">

                <div class="modal-text">
                    <div class="row mb-lg">
                        <figure class="figure col-sm-4">
                            <img class="" alt="Company Logo" src="resources/other/announcement.jpg">
                        </figure>
                        <div class="address col-sm-6 pull-right">
                            <p>Website : <a href="<%=AppConstants.BASE_URL%>" target="_blank">Website</a></p>

                            <p>Contact Email : email@domain.com</p>

                            <%--<p>Tags : OR Table, General</p>--%>
                            <a href="#">Announcement Ad</a>
                        </div>
                    </div>

                    <p>
                        An Announcement is a DIGITAL BROCHURE.

                        Instead of mailing brochures (with no certainty of where they end up), product news, conference announcement, company news, newsletters etc, can be published here.
                        <br/>
                        Announcement Ads are better and more effective than their hardcopy versions.<br/>
                        They cost less to produce, and less effort to create.<br/>
                        They have a better chance to be seen and read.<br/>
                        There is no need to organize and sort old mail brochures.<br/>
                        If a copy is missed, it can be retrieved.<br/>
                        Immediate response is possible with prominent links.<br/>
                        ...  and much more.
                        <Br/>
                        <Br/>
                        <Br/>
                        Contact information is prominently displayed so that users can contact the owners immediately.<br/>

                        If readers miss an Announcement, the Announcement title is displayed under the Recent column for another 6 days.
                        If users do not have the time to read the Announcement, and wish to read later, they can easily search for previously published announcements.

                    </p>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
    <div id="bugModal" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title" style="color:00000;">Report A Bug?</h2>

            <div class="panel-body">

                <div class="modal-text">
                    <div class="row mb-lg col-md-10">

                        Report Bug in
                        <br/>
                        <%
                            List<String> type = Arrays.asList("Registeration Process",
                                    "Local site administrator appointment and Role",
                                    "Loungeworks",
                                    "Directory",
                                    "Schedule",
                                    "Checklist",
                                    "Administrative Manuals",
                                    "Notices",
                                    "Preference List",
                                    "Postoperative Care",
                                    "In-service",
                                    "Manage a Project",
                                    "Message Board",
                                    "Others"
                            );
                            Collections.sort(type);
                        %>
                        <select class="form-control" name="type" id="bugType">
                            <%
                                for (final String each : type) {
                            %>
                            <option value="<%=each%>"><%=each%>
                            </option>
                            <%
                                }
                            %>

                        </select>

                        <br/>
                        <section class="col-sm4 form-group">
                            <label >Comments</label>
                            <textarea class="form-control" name="text" id="bugTxt">

                    </textarea><br/>
                        </section>

                        <button class="btn" onclick="submitBug()">Submit</button>

                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss report-bug-exit">Exit</button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
    <div id="betaModal" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">

        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <h2 class="panel-title" style="color:00000;">Beta Testing?</h2>

            <div class="panel-body">

                <div class="modal-text">
                    <div class="row mb-lg">
                        <p>
                            Beta testing a site is a necessary step before the site's final release.
                            Beta users report bugs and problems in using the site. Suggestion for new features is not part
                            of
                            beta testing, although they are always welcome.
                            Except for suggestion to improve site security.
                            We want to make the appointment of a local site administrator a smooth process and welcome
                            suggestions.
                        </p>

                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">

                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
        </section>


    </div>
    <div id="securityModal" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">

        <section class="panel">
            <%--- <header class="panel-heading"></header>--%>
            <header class="panel-title">
                <img src="resources/images/sec_alert_3.png"/>
                <h2 class="" style="color:#000000;">
                    Site Security Statement
                </h2>
            </header>


            <div class="panel-body">

                <div class="modal-text bold">
                    <div class="col-md-12">
                        We take privacy and security seriously.
                        <br/>
                        We have instituted the following security and privacy measures
                        <ul>
                            <li>
                                A  member can only create a real listed hospital OR group
                            </li>
                            <li>
                                A hospital ID photo is required during registration
                            </li>
                            <li>
                                A member must agree to the Terms of Use, which clearly states that
                                this site is for the OR members to discuss their work, and not their patients
                            </li>
                            <li>
                                2-step verification for the person who first creates the site
                            </li>
                            <li>
                                A members-only mobile messaging app
                            </li>
                        </ul>
                        Next, the site needs a local site administrator.<br/>
                        <br/>
                        <div style="padding:5px;">
                            Here's why:

                            <div class="herewhy">
                                The Local Site Administrator
                                <ul>
                                    <li>
                                        Approves, or rejects all requests for membership,
                                        and even expels them, based on the administrator's personal knowledge.
                                    </li>
                                    <li>
                                        Manage site content including deleting offensive, inappropriate or illegal contents.
                                    </li>
                                    <li>
                                        Can appoint an assistant
                                    </li>
                                    <li>
                                        Can invite all OR members to join the site to work together
                                    </li>
                                    <li>
                                        Can post official documents and notices
                                    </li>

                                </ul>
                                <br/>
                                <br/>

                                These are done with a special permissioned Site Management Panel.

                            </div>

                        </div>

                        <br/>

                        If this site has a temporary site administrator (who is the member who creates this site),
                        a permanent official local site administrator must be appointed as soon as possible for the reasons stated above.


                        <div class="herewhy">
                            Suggestion for the role of official permanent local site administer
                            <ul>
                                <li>
                                    Anyone can be appointed, including any administrative staff or the OR Manager


                                </li>
                                <li>
                                    The appointee must first register to join the site.
                                </li>

                            </ul>
                            <br/>
                            <br/>


                        </div>
                        <br/>
                        <div class="resp-lsa">
                            Here's how the current temporary local site administrator transfers the role to another member<br/>

                            <ul>
                                <li>
                                    Make sure the appointee is member
                                </li>
                                <li>
                                    Open the Site Management Panel under Administration Menu
                                </li>
                                <li>
                                    Select the member you want to transfer the role to
                                </li>
                                <li>
                                    Click the gear icon(<img src="resources/other/gear.png"/>)on the right side against the member's name
                                </li>
                                <li>
                                    Logout. (You must log out before the transfer is effective).
                                </li>
                            </ul>

                        </div>

                        <br/>
                        The Security Alert button is intentionally kept to be a security reminder.
                        <br/>
                        <br/>
                        <br/>
                        <div class="resp-lsa">

                            <font class="underline red " style="font-size:16px;">Enhanced Security and Privacy Service (ESPS)</font>
                            <br/>
                            For those who are still uncomfortable about the distribution of information outside the hospital, we offer an Enhanced Security and Privacy Service. (ESPS).<br/>

                            ESPS uses geofencing to create a virtual perimeter around your hospital. Any computing  device situated physically inside the
                            coordinates of the perimeter can access the site. Any device outside the perimeter cannot access the site.
                            Multiple physical hospital sites can geofenced.
                            Thus, a local site administrator can control access to the site.
                            ESPS will inconvenience some people who want to access the site when they are outside the hospital.
                            This is a trade-off that the local site administrator must make.
                            <br/>
                            ESPS is a paid service.


                        </div>

                        <div class="clearfix"></div>
                        <br/>
                        <br/>
                        <div>
                            <img src="resources/images/how_it_works.png"/>
                        </div>
                        <br/>
                        <div class="how-to-use">
                            <h4 class="bold">
                                How to use this site
                            </h4>

                            <ul>
                                <li>
                                    This site is for members to work together.
                                    It is about working in the operating room, and not about the patient.
                                    Therefore, a patient's condition should not be discussed.
                                </li>
                                <li>
                                    An official local site administrator is preferred for optimal security and privacy(see above).
                                    All members are encouraged to report HIPPA violation to the administrator.
                                </li>
                                <li>
                                    All members have different permissions to post, to view and to comment.
                                    This gives different members a choice of public and private communication.
                                    The Mobile Messaging App can also be used for private messaging.
                                </li>

                            </ul>
                            <br/>
                            <h4 class="bold underline">Understanding your permission to create, view and comment</h4>
                            <br/>
                            A member's permission to use the site is defined by the member's role, as selected by the member during registration.<br/>
                            The following permission table summarizes the permissions for all members. Members can only see the Loungeworks menus according to their role.

                            <table class="bold" cellspacing="2" cellpadding="2" border="1" width="100%">
                                <tr class="text-center underline">
                                    <td >
                                        Loungeworks
                                    </td>
                                    <td>
                                        Write
                                    </td>
                                    <td>
                                        Read
                                    </td>
                                </tr>
                                <tr class="text-center">
                                    <td>Staff Directory</td>
                                    <td>All</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Call Schedule</td>
                                    <td>LSA</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Administrative Manual</td>
                                    <td>LSA</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Notices</td>
                                    <td>LSA</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Checklist</td>
                                    <td>All</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>In Service</td>
                                    <td>LSA</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Postoperative Care</td>
                                    <td>Surgeon</td>
                                    <td>Surgeon and ORManager</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Preference List</td>
                                    <td>Surgeon</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Anaesthesia Setup</td>
                                    <td>Anaesthesiologist</td>
                                    <td>All</td>
                                </tr>
                                <tr class="text-center">
                                    <td>Collaborate</td>
                                    <td>All</td>
                                    <td>Depends On assignment</td>
                                </tr>

                            </table>
                            Loungeworks are static information. However, changes in hospital policy in patient care management will necessitate new forms of Loungeworks.
                            For example, Enhanced Recovery After Surgery (ERAS).
                            <br/>
                            Use Message Board and Forum to comment on Loungeworks where applicable.<br/>
                            Only the local site administrator (or assistant) can create Call Schedule, Administrative Manuals, Notices, In-service.
                            <Br/>
                            Only doctors (or their assistants) can create Preference list, Postoperative Care and Anaesthesia Set up.<br/>
                            All members can create Checklist and can use Collaborate.
                            <br/>
                            <br/>
                            <h4 class="bold ">Supported file format</h4>
                            Supported file formats are files that can be displayed inside this site.<br/>
                            All text must be created in the text editor provided. You may copy text from another file.<br/>
                            Microsoft files cannot be displayed on this site. Powerpoint slides cannot be displayed and must be converted into PDF before uploading.<br/>
                            <br/>
                            <h4 class="bold ">The Messaging Board</h4>
                            This is meant for all members to pass short messages.
                            If you want to pass message to a special group of members,select Role and the message will only be seen by the Role members
                            For private messaging, see "What's coming up"
                            <br/>
                            The Forum is meant for more detail discussion.



                        </div>

                        <div class="clearfix"></div>
                        <br/>
                        <br/>
                        <div class="how-to-make-work">
                            <h4 class="bold">
                                How to make this site work for you
                            </h4>

                            <ul>
                                <li>
                                    The Loungeworks can hold more information.
                                    Loungeworks are sometimes formatted and structured to display information more efficiently.
                                </li>
                                <li>
                                    Create as many checklists as you want and need, and add more details to your Staff Directory Profile, Preference list and Postoperative Care Instructions.
                                </li>
                                <li>
                                    Members can retrieve information on their own and so free the staff to concentrate on direct patient care.
                                </li>
                                <li>
                                    Members can comment on or discuss contents which they otherwise cannot do.
                                </li>
                                <li>
                                    Any member can bring up site problems early for prompt corrective action, including calling people up for a personal discussion.
                                </li>
                                <li>
                                    All staff members should be encouraged to join in order to received official notices.
                                </li>
                                <li>
                                    Login regularly, especially when you are not in the OR all the time.
                                </li>
                                <li>
                                    Use the Mobile Messaging App for private messaging when you are outside the OR.
                                </li>

                            </ul>

                            <br/>

                            Ultimately, this site will be what members make of it. Enjoy!

                        </div>

                        <script type="text/javascript">
                            var show = false;
                            function showhide() {
                                var el = $('.herewhy');
                                if (!show) {
                                    el.fadeIn();
                                } else {
                                    el.fadeOut();
                                }
                                show = !show;
                            }
                            //var el = $('.herewhy').hide();

$('#myexit').click(function(){ $('#modalreadmore1').magnificPopup('close'); });
                        </script>


                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button class="btn btn-default modal-dismiss">Exit</button>
                    </div>
                </div>
            </footer>
            <div id="suggestionModal" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
                <section class="panel">
                    <%--- <header class="panel-heading"></header>--%>
                    <h2 class="panel-title" style="color:00000;">SUGGESTIONS?</h2>

                    <div class="panel-body">

                        <div class="modal-text">
                            <div class="row mb-lg">

                                Suggestions
                                <br/>
                                <textarea name="text" id="suggTxt">

                    </textarea><br/>
                                <button class="btn" onclick="submitSuggestion()">Submit Suggestion</button>

                            </div>
                        </div>
                    </div>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-md-12 text-right">

                                <button class="btn btn-default modal-dismiss">Exit</button>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>

            <div id="recentannouncementLink" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
                <section class="panel">
                    <%--- <header class="panel-heading"></header>--%>
                    <h2 class="panel-title">Announcement</h2>

                    <div class="panel-body">

                        <div class="modal-text">
                            <div class="row mb-lg">

                                <h1 class="bold">YOUR ANNOUCEMENT AD HERE</h1>
                            </div>

                        </div>
                    </div>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-md-12 text-right">

                                <button class="btn btn-default modal-dismiss">Exit</button>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>


            <div id="recentactivity" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
                <section class="panel">
                    <%--- <header class="panel-heading"></header>--%>
                    <h2 class="panel-title">Recent Activities</h2>

                    <div class="panel-body">

                        <div class="modal-text">
                            <div class="row mb-lg">
                                <%--<figure class="figure col-sm-4">--%>
                                <%--<img class="" alt="Joseph Doe Junior" src="getImageDocument?filePath=announcement/M/ann-or.jpg">--%>
                                <%--</figure>--%>
                            </div>

                            <p>

                                Activities in Common lounge.<br/>
                            <ul>
                                <li>
                                    Common Lounge Site
                                </li>
                                <li>
                                    Common Loungeworks Menu Items
                                </li>
                                <li>
                                    Manage a Project
                                    <br/>
                                    Work together to create a collaborative document for any group or purpose
                                </li>
                                <li>
                                    Classfied, Personal , Business, Jobs
                                    <br/>
                                    Members and ORs and businesses can advertise used or personal items or jobs
                                </li>
                                <li>
                                    Checklist
                                    <br/>
                                    Members submit checklist to share with other members
                                </li>
                                <li>
                                    OR Best practice/Guidelines
                                    <br/>
                                    Members can submit this from official sources or share your own with other members
                                </li>
                                <li>
                                    Off best practices
                                    <br/>
                                    Share office efficiency tips
                                </li>
                                <li>
                                    Insurance
                                    <br/>
                                    Guidelines
                                </li>
                                <li>
                                    Message boards and Forums Discussion
                                </li>
                            </ul>


                            </p>
                        </div>
                    </div>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-md-12 text-right">

                                <button class="btn btn-default modal-dismiss">Exit</button>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>
            <div id="searchResult"
                 class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel searchResult-modal" style="width:70%">
                <section class="panel">
                    <%--- <header class="panel-heading"></header>--%>
                    <h2 class="panel-title">Search Result <span class="btn btn-default modal-dismiss" style="float:right;">Exit</span></h2>

                    <div class="panel-body">
                        <%
                            String hospSize = token.getCurrentGroupSize();
                            if(token.isAdmin()){
                                hospSize  = "L";
                            }
                            hospSize = hospSize.equals("H")? "L": hospSize;
                        %>
                        <script type="text/javascript">
                            var type='';
                            function setType(typeE, el){
                                $('.orsearch-form').find('.search-radio').removeClass('black-btn');
                                $(el).addClass('black-btn');
                                type=typeE;
                            }

                            function getUrl(ev) {
                                if(!type){
                                    alert('Please select the type you want search for (ORCatalog/ Announcement/Directory)');
                                }else {
                                    $('#openSearchModal').click();
                                    var test = "https://search.orlounge.com/index.html?src=Orlounge-Member-Site&type="+type+"&hospitalSize=<%=hospSize%>&term=" + ($('#q').val());
                                    //  alert(test);
                                    $('#searchResult').find('.panel-body').html(' <iframe width="100%" height="560px" src="' + test + '"></iframe>');
                                }

                            }
                        </script>


                    </div>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-md-12 text-right">

                                <button class="btn btn-default modal-dismiss">Exit</button>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>
</header>

<script type="text/javascript">
    function popupwin(url) {
        var finalUrl = url;
        newwindow = window.open(finalUrl, 'name', 'height=700,width=700');
        newwindow.moveTo(0, 0);
        //newwindow.resizeTo(700, 700);
        if (window.focus) {
            newwindow.focus()
        }
        return false;
    }
    $(document).ready(function(){
        $('.logo').html('<img style="width: 175px;height:50px" src="resources/other/logo_7.png" alt="ORLounge"/>');

    });

    function bookmark(){
        if (window.sidebar) { // Mozilla Firefox Bookmark
            window.sidebar.addPanel(location.href,document.title,"ORLounge");
        } else if(window.external) { // IE Favorite
            window.external.AddFavorite(location.href,document.title); }
        else if(window.opera && window.print) { // Opera Hotlist
            this.title=document.title;
            return true;
        }
    }

</script>


<script type="text/javascript">
    var silText ="A Special Interest Group is a group of members or hospitals that have a common professional interest. Doctors and nurses of different specialties," +
        " hospital networks or affiliated hospitals are examples.<br/>"+
        "These groups can form Special Interest Lounges to work together. " +
        "Special Interest Lounges have the same functionalities as an OR Lounge site, except that the Loungeworks are different.<br/>" +
        "Any member of a hospital OR lounge site can join a Special Interest Lounge based on their professional role.<br/>"+
        "Any hospital can work with another hospital or hospitals to create a Special Interest Lounge.<br/>" +
        "What you can do in Special Interest Lounge<br/>" +
        "<ul>" +
        "   <li>Join your colleagues in active discussion.</li>" +
        "   <li>Create special Loungeworks and discuss specialty-specific topics.</li>" +
        "   <li>Create or store clinical practice standards and protocols.</li>" +
        "   <li>Create practices related documents.</li>" +
        "   <li>Discuss business aspect of their practice.</li>" +
        "   <li>Copy relevant Loungeworks to your hospital OR lounge sites for your own use.</li>" +
        "   <li>Use Mobile Message App for private messaging.</li>" +
        "   <li>Network and/or Affiliated hospitals can work together.</li>" +
        "</ul>" +
        "<br/>" +
        "Special Interest Lounges allow members to focus on specialized interest, to gain useful insight and to further improve their professional lives.<br/>" +
        "Visit this site for updates and read details of its implementation.";


    var mobTxt= "A Mobile Messaging App is a messaging app for your smartphone.<br/>" +
        " A special messaging app for ORLOUNGE members has the following advantages:" +
        "   <ul>" +
        "   <li>The app is for members only. All members of ORLOUNGE are listed as contacts, unless the member chooses to opt out." +
        " The app does not allow non-members to be added.</li>" +
        "   <li>Use text, phone and images to communicate.</li>" +
        "   <li>There is no confusion and no chance accidental wrong messaging.</li>" +
        "   <li>A special audio alert let you know an incoming messaging is from this app.</li>" +
        "   <li>Incoming messages stay in the app and can be accessed at a convenient time. No need to pick up the phone immediately.</li>" +
        "   <li>Private messages can be sent to people in the same hospital or other members.</li>" +
        "   <li>Option to add office staff or answering service to the directory.</li>" +
        "   <li>You can receive notification and reminders.</li>" +
        "   <li>... and much more.</li>" +
        "    </ul>";

    var offTxt ="An OFF DUTY site is for our members' personal activities outside their work. <br/> " +
        "The Off Duty site is a social experience site where members can read News, listen to music or podcast," +
        " watch videos, play games, do their shopping, or discuss their hobbies, travel and investment.<br/>" +
        "With messaging, forums and the mobile messaging app, Members can thus socialize outside their work.";

    var orlsTxt = "This site is for everyone who works in the operating room.<br/>" +
        "It is for anaesthesiologists, nurses, residents and surgeons.<br/>" +
        "It is social.<br/>" +
        "It is a collaborative, productivity and management site.<br/>" +
        "Information is organized and structured for maximal efficiency.<br/>" +
        "It is developed by people who work in and understand the OR. <br/>" +
        "It is built with ease of use and navigation in mind.<br/>" +
        "It is built to respond to changes in the OR.<br/>" +
        "It is built to scale to include other working groups.<br/>" +
        "It uses the latest Internet technology.<br/>" +
        "It is not an electronic health record system.<br/>";


    var map = {"sil" : silText, "mob":mobTxt, "off": offTxt, "orls":orlsTxt};
    var mapHead = {"sil" : "What is Special Interest Group", "mob":"Mobile Messaging App",
        "off": "Off Duty", "orls":"What is different ABOUT this site?"};

    var cloudImg = {"sil" : "0", "mob":"1",
        "off": "2", "orls":"3"};


    function loadDesc(key){
        $('.whatisithead').html(mapHead[key]);
        $('.whatisit').html(map[key]);

        $('#whatisitreadmore').find('.whatisittext').html(map[key]);
        $('#whatisitreadmore').find('.panel-title').html(mapHead[key]);
        var img = '<img src="resources/other/tag_cloud_'+cloudImg[key]+'_1.png"/>';
        $('.tag-cloud-img').html(img);

        $('.whatisithead').on('click', function(){
            $('.what-it-is-read-more').click();
        });

        $('.whatisit').on('click', function(){
            $('.what-it-is-read-more').click();
        });

        $('#link-sil').removeClass('red-highlight');
        $('#link-mob').removeClass('red-highlight');
        $('#link-off').removeClass('red-highlight');
        $('#link-orls').removeClass('red-highlight');

        $('#link-'+key).addClass('red-highlight');

    }

    loadDesc('sil');


    function submitBug(){
        var type = $('#bugType').val();
        var txt = $('#bugTxt').val();
        /* var r1 = $('#userRating').val();
         var r2 = $('#secRating').val();*/
        $.ajax({
            url : "reportBug",
            type : "POST",
            //data : JSON.stringify({type : type+"|UserRating:"+r1+"|"+"SecurityRating:"+r2, text : txt}) ,
            data : JSON.stringify({type : type, text : txt}) ,
            contentType : "application/json"
        });
        $('.report-bug-exit').click();
    }







</script>

<!-- end: header -->

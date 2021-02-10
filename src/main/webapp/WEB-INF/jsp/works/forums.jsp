<%@ page import="org.orlounge.common.UserToken" %>
<script type="text/javascript">
    var role = "<%=((UserToken)session.getAttribute("USER_TOKEN")).getRole()%>";
    var showFor = '<%=request.getAttribute("role")%>';
    <% if(request.getParameter("json") != null){
%>
   var json = <%=request.getParameter("json")%>;
    <%
        }
        %>

</script>

<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String type = request.getParameter("role");
%>
<%
    if("Advisory Lounge".equals( token.getCurrentGroupName())){
        %>
<%
    if("get_well_soon_advisory".equalsIgnoreCase(type)){
        %><h3>Get Well Soon Advisory Board</h3><%
    }else if("asc_advisory".equalsIgnoreCase(type)){
        %><h3>ASC Advisory Board</h3><%
    } else if("hlounge_advisory".equalsIgnoreCase(type)){
        %><h3>Hospital Lounge Advisory Board</h3><%
    }


    }
%>

<section role="main" class="comment-body row col-sm-8">
    <section class="simple-compose-box mb-xlg post-msg">
        <form method="POST" id="postForum" action="postForumMsg">
            <textarea rows="2" name="text" data-plugin-textarea-autosize placeholder="Post your Forum Topic here." ></textarea>
            <input type="hidden" name="type" value="<%=request.getParameter("role")%>"/>
        </form>
        <div class="compose-box-footer">
        <%--    <ul class="compose-toolbar">
                <li>
                    <a href="#"><i class="fa fa-camera"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-map-marker"></i></a>
                </li>
            </ul>--%>
            <ul class="compose-btn">
                <li>
                    <% if(!token.isGuest()){
                    %>
                    <a class="btn btn-primary btn-xs" onclick="savePost()">Post</a>
                    <%
                    }
                    %>

                </li>
            </ul>
        </div>
    </section>

    <section class="panel" style="border: none;">
        <%--<header class="panel-heading">
            <div class="panel-actions">
                <a href="#" class="panel-action fa fa-refresh" ic-trigger-on="click" ic-append-from="/example-load-more" ic-target="#ExampleLoadMoreAppendTarget"></a>
                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
            </div>

            <h2 class="panel-title">Recent Forum Topics</h2>
        </header>--%>
        <div id="ExampleLoadMoreAppendTarget" style="border: none;">

            <section id="nodataPnl" class="panel" >
                <div class="panel-body" >
                    <div class="row">
                        <div class="col-md-12">
                            <section class="panel">
                                <div class="panel-footer">
                                    No More Thread Found.
                                </div>
                            </section>
                        </div>
                    </div>

                </div>

            </section>
        </div>
        <div class="loadMore-box">
            <%--<a href="#" id="loadMoreBtn" class="btn btn-primary btn-xs panel-action"  ic-trigger-on="click" ic-append-from="getMoreForums.html" ic-target="#ExampleLoadMoreAppendTarget">Load More</a>--%>
            <a href="#" id="loadMoreBtn" class="btn btn-primary btn-xs panel-action" onclick="loadMoreForums()">Load More</a>
        </div>
    </section>



<!-- end: page -->
</section>
<jsp:include page="right-side-option-bar.jsp"/>


<%--
<!-- Vendor -->
<script src="assets/vendor/jquery/jquery.js"></script>
<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
<script src="assets/vendor/intercooler/intercooler-0.4.8.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/jquery-mockjax/jquery.mockjax.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="assets/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="assets/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="assets/javascripts/theme.init.js"></script>

<!--
Examples - Actually there's just ajax mockups in the file below
All mockups are for demo purposes only. You don't need to include this in your application.
-->
<script src="assets/javascripts/extra/examples.ajax.made.easy.js"></script>--%>
<script src="resources/app/forums.js"></script>


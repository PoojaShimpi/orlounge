<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.util.DateUtility" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.ProjectInfoBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = null;
var success = true;

    <% if(request.getParameter("message") != null){
%>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success : success, message: msg}
<%
    }else if(request.getParameter("json") != null){
%>
var json =<%=request.getParameter("json")%> ;
<%
    }
    %>

</script>
<div class="row col-sm-8">
    <%
        UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

    %>
<section class="panel">

        <h2 class="panel-title clearfix">Manage a Project

            <%
                if(!token.isGuest()){
                    %>
            <a class="btn btn-primary pull-right"  href="create-project.html" >Create New Project <%--<i class="fa fa-plus"></i>--%></a>
            <%

                }
            %>
        </h2>



    <div class="panel-body">
        <p>
            <strong>How this is useful?</strong><Br/>
            Documents in LOUNGEWORKS or for any other purposes can be created by more than one member.
            After the document has been created, simply copy it to it's  final destination.
        </p>
        <p>
            Use this to produce one or more documents and allow more than one person to create and edit the documents.<br/>
            Step 1: Create a project name and give access rights to other users.<br/>
            Step 2: Create one or more documents.<br/>
            Step 3: Qualified members access the project to work together. Anyone can edit any documents.<br/>
            Step 4: All documents can be combined by copy and paste into one document.<br/>
        </p>
            <table class="table table-bordered table-striped mb-none" id="datatable-default" >
                <thead>
                <tr>
                    <th>Title</th>
                    <th>No. Of Docs</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ProjectInfoBean> beans= (List<ProjectInfoBean>) request.getAttribute("beans");
                    for(ProjectInfoBean u : beans){
                        %>
                <tr class="gradeX">
                    <td>
                        <a title="View" href="view-project.html?id=<%=u.getId()%>">
                            <%=u.getName()%>
                        </a>
                    </td>
                    <td>
                        <a title="View" href="view-project.html?id=<%=u.getId()%>">
                            <%=u.getDocuments() == null ? 0 : u.getDocuments().size() %>

                        </a>
                    </td>

                </tr>

                <%
                    }
                %>

                </tbody>
            </table>
    </div>
</section>
</div>

<jsp:include page="right-side-option-bar.jsp"/>
<!-- end: page -->




<div id="dialog" class="modal-block mfp-hide">
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title">Are you sure?</h2>
        </header>
        <div class="panel-body">
            <div class="modal-wrapper">
                <div class="modal-text">
                    <p>Are you sure that you want to delete this row?</p>
                </div>
            </div>
        </div>
        <footer class="panel-footer">
            <div class="row">
                <div class="col-md-12 text-right">
                    <button id="dialogConfirm" class="btn btn-primary">Confirm</button>
                    <button id="dialogCancel" class="btn btn-default">Cancel</button>
                </div>
            </div>
        </footer>
    </section>
</div>


<!-- Vendor -->
<%--
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
<script src="assets/javascripts/extra/examples.ajax.made.easy.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/select2/select2.js"></script>
<script src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
<script src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
--%>

<script src="resources/app/admin-user.js"></script>




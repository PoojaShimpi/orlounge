<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.bean.GroupBean" %>
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
    }
    %>

</script>


<section class="panel">
<div class="panel-body">
    <h3  class="mt-none bold">ORLS List</h3>

<table class="table table-bordered table-striped mb-none" id="datatable-default" >

<thead>
<tr>
    <th>#</th>
    <th>Group Name</th>
    <th>Status</th>
    <th>Actions</th>
</tr>
</thead>
<tbody>
<%
    List<GroupBean> groups = (List<GroupBean>) request.getAttribute("groups");
    for(GroupBean u : groups){
%>
<tr class="gradeX">
    <td><%=u.getGroupId()%></td>
    <td><%=u.getGroupName()%></td>
    <% if(u.getIsActive().equals(1)){
    %>
    <td  class="success">Approved</td>
    <td class="actions-hover actions-fade">
       <a href="selectOrl?id=<%=u.getGroupId()%>">Enter<i class="fa fa-check"></i></a>
    </td>
    <%
        } %>
    <%  if(u.getIsActive().equals(0)){
    %>
    <td  class="warning">Pending</td>
    <td class="actions-hover actions-fade">
    </td>
    <%
        } %>
    <% if(u.getIsActive().equals(-1)){
    %>
    <td  class="danger">Disapproved</td>
    <td class="actions-hover actions-fade">

    </td>
    <%
        } %>


</tr>
<%
    }
%>

</tbody>


</table>
</div>
</section>



<%--<section class="panel" style="left:100px">
    <header class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
            <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
        </div>

        <h2 class="panel-title">Users List</h2>
    </header>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover mb-none">
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>ORLS</th>
                    <th>Active</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
                    for(UserInfo u : users){
                        %>
                <tr >
                    <td><%=u.getUserId()%></td>
                    <td><%=u.getFirstName()%></td>
                    <td><%=u.getLastName()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.getRole()%></td>
                    <td><%=u.getCurrentGroupName()%></td>
                    <% if(u.getStatus().equalsIgnoreCase("PENDING")){
                        %>
                    <td  class="warning">Pending</td>
                    <td class="actions-hover actions-fade">
                        <a href="approveUser?userId=<%=u.getUserId()%>"><i class="fa fa-check"></i></a>
                        <a href="deleteUser?userId=<%=u.getUserId()%>" class="delete-row"><i class="fa fa-trash-o"></i></a>
                    </td>
                    <%
                    } %>
                    <%  if(u.getStatus().equalsIgnoreCase("ACTIVE")){
                    %>
                    <td  class="success">Active</td>
                    <td class="actions-hover actions-fade">
                        <a href="disapproveUser?userId=<%=u.getUserId()%>"><i class="fa fa-user-times"></i></a>
                        <a href="deleteUser?userId=<%=u.getUserId()%>" class="delete-row"><i class="fa fa-trash-o"></i></a>
                    </td>
                    <%
                    } %>
                    <% if(u.getStatus().equalsIgnoreCase("DELETED")){
                    %>
                    <td  class="danger">Deleted</td>
                    <td class="actions-hover actions-fade">
                        <a href="approveUser"><i class="fa fa-check"></i></a>

                    </td>
                    <%
                    } %>


                </tr>
                <%
                    }
                %>

                </tbody>
            </table>
        </div>
    </div>
</section>--%>
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
<script src="resources/vendor/jquery/jquery.js"></script>
<script src="resources/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.js"></script>
<script src="resources/vendor/nanoscroller/nanoscroller.js"></script>
<script src="resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/vendor/magnific-popup/magnific-popup.js"></script>
<script src="resources/vendor/jquery-placeholder/jquery.placeholder.js"></script>
<script src="resources/vendor/intercooler/intercooler-0.4.8.js"></script>

<!-- Specific Page Vendor -->
<script src="resources/vendor/jquery-mockjax/jquery.mockjax.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="resources/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="resources/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="resources/javascripts/theme.init.js"></script>

<!--
Examples - Actually there's just ajax mockups in the file below
All mockups are for demo purposes only. You don't need to include this in your application.
-->
<script src="resources/javascripts/extra/examples.ajax.made.easy.js"></script>

<!-- Specific Page Vendor -->
<script src="resources/vendor/select2/select2.js"></script>
<script src="resources/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
<script src="resources/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
--%>

<script src="resources/app/admin-user.js"></script>




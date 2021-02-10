<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.RoleNameEnum" %>
<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = null;
var success = true;

    <%
     UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

     boolean showLogout = false;
    if(session.getAttribute("showLogoutMessage")!= null){
        showLogout = Boolean.valueOf((Boolean) session.getAttribute("showLogoutMessage"));
    }

    if(request.getParameter("message") != null){
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
    <h3 class="mt-none bold">Users List</h3>
    <p>

    </p>

    <%
        if(showLogout){
    %>

    <div class="warn-header " style="padding-top: 5px;padding-bottom: 5px;margin-bottom: 15px;">
        <p >
            For Security Reasons, You may need to Login Again for reflecting changes. Please <a href="logout" style="color: #fff;text-decoration: underline;">Logout</a>
        </p>

    </div>
    <%
        }
    %>



<table class="table table-bordered table-striped mb-none" id="datatable-default" >

<thead>
<tr>
    <th>#</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Role</th>
    <th>LSA?</th>
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
<tr class="gradeX">
    <td><%=u.getUserId()%></td>
    <td><%=u.getFirstName()%></td>
    <td><%=u.getLastName()%></td>
    <td><%=u.getEmail()%></td>
    <td><%=RoleNameEnum.ROLE_NAME_MAP.get(u.getRole())%></td>
    <td>
        <span
            <%= (u.getLsa().equals(1) ?  " style=' font-size:10px;line-height:11px;font-weight:700;padding:2px 4px 2px 4px;border-radius:2px;color:#fff;background:green;'>Yes" : " style='font-size:10px;line-height:11px;font-weight:700;padding:2px 4px 2px 4px;border-radius:2px;color:#fff;background:red;'>No" )%>
        </span>
    </td>
    <td><%=u.getGroupName()%></td>
    <% if(u.getStatus().equalsIgnoreCase("ACCESS_PENDING")){
    %>
    <td  class="warning">Access Pending</td>
    <td class="actions-hover actions-fade">
        <a href="approveUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>"><i class="fa fa-check-circle"></i></a>
        <a href="deleteUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-trash-o"></i></a>
        <a href="profile.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-vcard"></i></a>

    <%
        } else if(u.getStatus().equalsIgnoreCase("PENDING")){
    %>
    <td  class="warning">Pending</td>
    <td class="actions-hover actions-fade">
        <a href="approveUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>"><i class="fa fa-check-circle"></i></a>
        <a href="deleteUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-trash-o"></i></a>
        <a href="profile.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-vcard"></i></a>

    <%
        } else if (u.getStatus().equalsIgnoreCase("ACTIVE")){
    %>
    <td  class="success">Active</td>
    <td class="actions-hover actions-fade">
        <a href="disapproveUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>"><i class="fa fa-user-times"></i></a>
        <a href="deleteUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-trash-o"></i></a>
        <a href="profile.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-vcard"></i></a>
    <%
        } else  if(u.getStatus().equalsIgnoreCase("DELETED")){
    %>
    <td  class="danger">Deleted</td>
    <td class="actions-hover actions-fade">
        <a href="approveUser?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>"><i class="fa fa-check-circle"></i></a>
        <a href="profile.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-vcard"></i></a>
    <%
        } %>
    <%
        if(token.isLSA() || token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)){

            if(u.getLsa().equals(1) && token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE) ){
                %>
                <a  title="Remove As LSA" href="removeLsa.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fas fa-users-slash"></i></a>
                <%
            } else if(!u.getUserId().equals(token.getUserId())) {
                %>
                <a title="Make As LSA" href="makeLsa.html?userId=<%=u.getUserId()%>&groupId=<%=u.getGrpId()%>" class="delete-row"><i class="fa fa-users"></i></a>
                <%
             }

        }

    %>
    </td>


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




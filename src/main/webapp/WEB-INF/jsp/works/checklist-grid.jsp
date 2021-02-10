<%@ page import="org.orlounge.bean.ChecklistBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="java.util.List" %>
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
<section class="panel">

    <%
        UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

    %>


        <h2 class="panel-title clearfix">Checklist

            <%--
                if(token.isLSA() || token.isAdmin() || token.isORM() || token.isSurgeon() || token.isAnaesthesiologist() || token.isOtherRole()){
                    --%>
                    <a class="btn btn-primary pull-right" href="viewchecklist.html?action=<%=AppConstants.ACTION_CREATE%>" >Create Checklist</a>
            <%--
                }
            --%>
            </h2>
    <div class="panel-body">
        <p>
            <strong>This is simply a reminder that can be quickly retrieved by the staff. Use this to create multiple steps for any procedure.</strong><br/>

        </p>
        <p >
            Create a checklist for any purpose by any member.
            Some examples are
        </p>
            <ul class="mb-lg">
                <li>
                    Time out checklist
                </li>
                <li>
                    Setting up a piece of equipment
                </li>
                <li>
                    Collecting special type of pathology specimen
                </li>
                <li>
                    Preoperative checklist of high risk patients
                </li>
                <li>
                    Steps during an emergency e.g. OR fire, unusual precautions, power failure
                </li>
                <li>
                    Reporting a needle prick
                </li>
                <li>
                    OR lock down procedure
                </li>
                <li>
                    Steps during special alert situation
                </li>

            </ul>
            <h4>Search</h4>
            <table class="table table-bordered table-striped mb-none" id="datatable-default" >
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ChecklistBean> scheduleBeans= (List<ChecklistBean>) request.getAttribute("checklist");
                    for(ChecklistBean u : scheduleBeans){
                        boolean allowedToUpdate = token.isLSA() || token.isAdmin() || token.isORM() ;
                        %>
                <tr class="gradeX">
                    <td>
                        <a title="View" href="viewchecklist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                            <%=u.getTopic()%>
                        </a>

                    </td>
                    <td>
                        <a title="View" href="viewchecklist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                            View
                        </a> |
                        <a title="View" href="viewchecklist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_UPDATE%>">
                            Edit
                        </a> |
                        <%
                        if(allowedToUpdate){%>


                        <a href="#"  data-toggle="modal" data-target="#confirm-delete-<%=u.getId()%>">Delete</a>
                        <div class="modal fade" id="confirm-delete-<%=u.getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
                                    </div>

                                    <div class="modal-body">
                                        <p>You are about to delete one record, this procedure is irreversible.</p>
                                        <p>Do you want to proceed?</p>
                                        <p class="debug-url"></p>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                        <a href="remove_check_list?id=<%=u.getId()%>" class="btn btn-danger btn-ok">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <%}
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




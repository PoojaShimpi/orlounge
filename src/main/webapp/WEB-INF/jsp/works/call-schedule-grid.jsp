<%@ page import="org.orlounge.bean.CallScheduleBean" %>
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

<section role="main" >
    <div class="comment-body col-sm-8 row">
        <div class="compose-box-footer"></div>
<section class="panel">

<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
	//out.println(token);
	

%>

    <h2 class="panel-title clearfix">Call Schedule List
        <%
            if(token.isAdmin() || token.isLSA() || token.isORM()){
        %>
        <a class="btn btn-primary pull-right" href="viewcallschedule.html?action=<%=AppConstants.ACTION_CREATE%>" >Create Call Schedule</a>
        <%
        }
        %>

    </h2>
    <div class="panel-body">
        <p>
            <strong>Use any format (eg a spreadsheet). For propriety software, use a pdf file.</strong><Br/>

        </p>
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
                    List<CallScheduleBean> scheduleBeans= (List<CallScheduleBean>) request.getAttribute("schedules");
                    for(CallScheduleBean u : scheduleBeans){
                        boolean isAllowToUpdate = token.isAdmin() || token.isLSA() || token.isORM();
                %>
                <tr class="gradeX">
                    <td>
                        <a title="Download" href="viewcallschedule.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                            <%=u.getTopic()%>
                        </a>
                    </td>
                    <td>
                        <a href="viewcallschedule.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">View</a> |
                    <%
                        if(isAllowToUpdate){
                    %>
                            <a href="viewcallschedule.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_UPDATE%>">Edit</a> |
                            <%--<a href="remove_call_schedule/<%=u.getId()%>">Remove</a>--%>
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
                                        <a href="remove_call_schedule/<%=u.getId()%>" class="btn btn-danger btn-ok">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
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
</div>

    <jsp:include page="right-side-option-bar.jsp"/>
    </section>
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



<script src="resources/app/admin-user.js"></script>




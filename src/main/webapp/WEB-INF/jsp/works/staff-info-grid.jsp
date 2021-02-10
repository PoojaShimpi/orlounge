<%@ page import="org.orlounge.bean.StaffInfoBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var msg = null;
    var success = true;

    <% if(request.getParameter("message") != null){
%>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success: success, message: msg};
    <%
        }else if(request.getParameter("json") != null){
%>
    var json =<%=request.getParameter("json")%>;
    <%
        }
            %>

</script>

<%

    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
    if (!ProcessData.isValid(token) || !ProcessData.isValid(token.getCurrentGroupId())) {
        response.sendRedirect("staffinfolist.html?message=You need to enter to some ORLS for vieweing Staff Infos");
    }
%>
<div class="row col-sm-8">
    <section class="panel">
        <%--  <header class="panel-heading">
              <div class="panel-actions">
                  <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>

                          <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>

            </div>
            </header> --%>
        <h2 class="panel-title clearfix">Staff Directory
            <a class="btn btn-primary pull-right" href="view_new_staff.html?action=<%=AppConstants.ACTION_CREATE%>">Create
                Staff
                Directory<%--<i class="fa fa-plus"></i>--%></a>
       
        </h2>


        <div class="panel-body">
            <p>
                <strong>Enter any information that is useful for the OR staff to have.</strong>
            </p>
			<h4>Search</h4> 
            <table class="table table-bordered table-striped mb-none" id="datatable-default">

                <thead>
                <tr>
                    <th>Name</th>
                    <th>Telephone No.</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<StaffInfoBean> users = (List<StaffInfoBean>) request.getAttribute("infos");
                    for (StaffInfoBean u : users) {
                        String action = AppConstants.ACTION_VIEW;
                %>
                <tr class="gradeX">
                    <td>
                        <a href="view_new_staff.html?id=<%=u.getId()%>&userId=<%=u.getUserId()%>&action=<%=action%>&groupId=<%=token.getCurrentGroupId()%>">
                            <%=u.getTitle() + " " + u.getFullName()%>
                        </a>
                    </td>
                    <td><%=u.getMobile()%>
                    </td>
                    <td>
                        <a href="view_new_staff.html?id=<%=u.getId()%>&userId=<%=u.getUserId()%>&action=<%=action%>&groupId=<%=token.getCurrentGroupId()%>">
                            View
                        </a>
                        |
                        <a href="view_new_staff.html?id=<%=u.getId()%>&userId=<%=u.getUserId()%>&action=<%=AppConstants.ACTION_UPDATE%>&groupId=<%=token.getCurrentGroupId()%>">Edit</a>
                         |
                        <a  title="Delete" href="#"  data-toggle="modal" data-target="#confirm-delete-<%=u.getId()%>">Delete</a>

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
                                        <a href="remove_staff_directory/<%=u.getId()%>" class="btn btn-danger btn-ok">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--
                            }
                        --%>


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






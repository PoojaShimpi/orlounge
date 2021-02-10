<%@ page import="org.orlounge.bean.PrefListBean" %>
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


        <h2 class="panel-title clearfix">Preference Setup
            <%
                if(token.isSurgeon()){
                    %>
                    <a class="btn btn-primary pull-right" href="viewpreflist.html?action=<%=AppConstants.ACTION_CREATE%>" >Add Preference Setup</a>
            <%

                }
            %>
        </h2>
    </header>
    <div class="panel-body">
<!--             <p>
                <strong>This consists of 2 parts - Operating Room setup and Preference List</strong><br/>

            </p>-->
            
            <h4>Search</h4> 
            <table class="table table-bordered table-striped mb-none" id="datatable-default" >
                <thead>
                <tr>
                    <th>Procedure</th>
                    <th>Speciality</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%

                    List<PrefListBean> beans= (List<PrefListBean>) request.getAttribute("beans");
                    boolean allowedToUpdate = token.isSurgeon();
                    for(PrefListBean u : beans){
                    %>
                <tr class="gradeX">
                    <td>
                        <a title="View" href="viewpreflist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                                <%=u.getProcedure()%>
                           </a>
                    </td>
                    <td>
                        <a title="View" href="viewpreflist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                            <%=u.getSpeciality()%>
                         </a>
                    </td>
                    <td>
                        <a title="View" href="viewpreflist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_VIEW%>">
                            View
                        </a> |
                        <%
                        if(allowedToUpdate){ %>
                        <a title="View" href="viewpreflist.html?id=<%=u.getId()%>&action=<%=AppConstants.ACTION_UPDATE%>">
                            Edit
                        </a> |

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
                                        <a href="new_delete_pref_list.html?id=<%=u.getId()%>" class="btn btn-danger btn-ok">Delete</a>
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



<script src="resources/app/admin-user.js"></script>




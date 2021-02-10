<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.util.DateUtility" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.bean.ProjectInfoBean" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
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

<%
    ProjectInfoBean bean = (ProjectInfoBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<section role="main" >

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">
       <%--         <header class="panel-heading">
                    <div class="panel-actions">
                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                        <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                    </div>
                </header>--%>

                    <h2 class="panel-title">Create My Project</h2>
                <div class="panel-body">
                    <p>
                        Collaborate to   produce a document with a group of members. All invited members can edit the document(s).<br/>
                        Step 1: Create a project name and give access rights to other users.<br/>
                        Step 2: Create one or more documents.<br/>
                        Step 3: Qualified members access the project to work together. Anyone can edit any documents.<br/>
                        Step 4: All documents can be combined by copy and paste into one document.<br/>

                    </p>
                    <form  class="form-horizontal" action="save-project.html" method="POST"  id="data-form" novalidate="novalidate">
                        <div class="form-group">
                            <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
                            <div class="col-md-6">
                            <input required id="name" name="name" class="form-control" />
                            <input type="hidden"  id="id" name="id" value="<%=bean.getId() != null? bean.getId() : ""  %>" class="col-md-4"/>
                        </div></div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Description</label>
                            <div class="col-md-6">
                            <textarea id="desc" name="desc" class="form-control"></textarea>
                            </div>
                        </div>
<%--
                        <div class="form-group">
                            <label class="col-md-3 control-label">Access Privileges</label>
                            <div class="col-md-6">
                            <select required id="accessType" name="accessType" class="form-control">
                                <option value="<%=AppConstants.PROJECT_ACCESS_TYPE_PRIVATE%>">Private (Visible Only to  Me)</option>
                                <option value="<%=token.getRole()%>">Role (Visible Only to <%=token.getRole()%>)</option>
                                <option value="<%=AppConstants.PROJECT_ACCESS_TYPE_PUBLIC%>">Public (Visible to All)</option>
                            </select>
                            </div>
                        </div>--%>
                        <input type="hidden"  name="accessType" value="<%=AppConstants.PROJECT_ACCESS_TYPE_PRIVATE%>"/>
                        <input type="hidden" id="userPreveliges" name="userPreveliges"/>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Choose member(s) to collaborate with: </label>
                            <div class="col-md-6">
                                <%
                                    List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
                                    Collections.sort(users, new Comparator<UserInfo>() {
                                        @Override
                                        public int compare(UserInfo o1, UserInfo o2) {
                                            StringBuilder builder1 = new StringBuilder(o1.getFirstName()).append(" ").append(o1.getLastName()).append(" (").append(o1.getUserCode()+")");
                                            StringBuilder builder2 = new StringBuilder(o2.getFirstName()).append(" ").append(o2.getLastName()).append(" (").append(o2.getUserCode()+")");

                                            return builder1.toString().compareToIgnoreCase(builder2.toString());
                                        }
                                    });
                                %>
                                <select id="usersToAdd"  multiple="multiple" class="form-control">
                                    <%
                                        for(UserInfo u : users){
                                            if(u.getUserId().equals(token.getUserId())){
                                                continue;
                                            }
                                            %>
                                    <option value="<%= u.getUserId() + "#_#"+ u.getFirstName()+" "+ u.getLastName() + " ("+u.getUserCode()+")"%>"><%=u.getFirstName()+" "+ u.getLastName() + "("+u.getUserCode()+")"%></option>
                                    <%
                                        }
                                    %>


                                </select>
                                <br/>

                                <label  class="btn btn-primary pull-right" onclick="add()">Add member(s)</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-9">
                                <div  id="usersLists" class="bootstrap-tagsinput " style="border:none;overflow-wrap: break-word;display: block;max-width: 400px;">

                                </div>
                            </div>

                        </div>
                        <script type="text/javascript">
                            var map = {};
                            function add(){
                                var users = $('#usersToAdd').val();
                                var len = users.length;
                                var i = -1;
                                var usersIds = [];
                                var usersDisp = [];
                                while(++i < len){
                                    var e = users[i];
                                    var id =  parseInt(e.split("#_#")[0]);
                                    var disp = e.split("#_#")[1];
                                    map[id] = disp;
                                }

                                resynctag(map);


                            }
                            function removeUser(userId){
                                delete map[userId];
                                resynctag(map);
                            }

                            function resynctag(map){
                                var html="";
                                for(var key in map){
                                    var v = map[key];
                                    html += '<span userid="'+key+'" class="tag label label-primary">'+v+'<span data-role="remove" onclick="removeUser('+key+')"></span></span>&nbsp';
                                }
                                $('#usersLists').html(html);
                            }

                            function getIdsForUser(){
                               var ids = [];
                               for(var key in map){
                                   ids.push(key);
                               }
                                return ids;
                            };

                        </script>

                        <div class="panel-footer clearfix">
                            <%
                                if(!token.isGuest()){
                                    %>
                            <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Save</a>
                            <%
                                }
                            %>

                        </div>


                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<script type="application/javascript">

    function dataSubmit(){
        var f = $('#data-form');
        if($('#name').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Name can not be empty.",
                type: 'error'
            });
            return;
        }
        var ids = getIdsForUser();
        $('#userPreveliges').val(ids.join(",").trim());
        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




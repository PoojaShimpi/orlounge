
<%@page import="org.orlounge.common.UserToken"%>
<%@page import="java.util.Collections"%>
<%@page import="org.orlounge.common.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Comparator" %>

<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>
<div class="form-group">
    <label class="col-md-3  control-label">Patient Name</label>
    <div class="col-md-6">
        <input id="pat" name="patientName" class="form-control "  />
        <span ><span class="red">*</span>Patient's Name is never saved into our system.</span>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Surgeon <span class="required">*</span></label>

    <div class="col-md-6">
        <input   required id="title" name="surgeon" class="form-control "  />
    </div>

</div>
<div class="form-group">
    <label class="col-md-3 control-label">Operation</label>
    <div class="col-md-6"><textarea  id="operation" name="operation" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Activity in the next 24 hours</label>
    <div class="col-md-6"><textarea id="activity" name="activity" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Contact number in case of an emergency</label>
    <div class="col-md-6"><input   id="emergencyContact" name="emergencyContact"  class="form-control"/></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">General Restriction, Diet, Activity and Others</label>

    <div class="col-md-6"><textarea   id="restriction" name="restriction" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Wound care</label>
    <div class="col-md-6"><textarea  id="woundCare" name="woundCare" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Medications</label>
    <div class="col-md-6"><textarea  id="medications" name="medications" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Supplies and equipment</label>
    <div class="col-md-6"><textarea id="supplies" name="supplies" class="form-control" ></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Postoperative Office Visit</label>


    <div class="col-md-6 form-inline" >
        <span class="input-group-addon">
            <input type="hidden"   id="officeVisit" name="officeVisit"  class="form-control"/>
            <i class="fa fa-calendar"></i><input data-plugin-datepicker required type="text"   id="officeVisitDate"   class="form-control"/>
            <i class="fa fa-clock-o"></i><input data-plugin-timepicker  required type="text"   id="officeVisitTime" class="form-control"/>
        </span>
    </div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Instructions or facilities</label>
    <div class="col-md-6"><textarea   id="instructions" name="instructions" class="form-control" ></textarea></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Others</label>
    <div class="col-md-6"><textarea id="other" name="other" class="form-control" ></textarea></div>
</div>

<input type="hidden" id="userPreveliges" name="userPreveliges"/>

<div class="form-group">
    <label class="col-md-3 control-label">Access Privileges To : </label>
    <div class="col-md-6">
        <%
            List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
            Collections.sort(users, Comparator.comparing(UserInfo::getFirstName));
        %>
        <select id="usersToAdd"  multiple="multiple" class="form-control">
            <%
                for (UserInfo u : users) {
                    if (u.getUserId().equals(token.getUserId())) {
                        continue;
                    }
            %>
            <option value="<%= u.getUserId() + "#_#" + u.getFirstName() + " " + u.getLastName() + "(" + u.getUserCode() + ")"%>"><%=u.getFirstName() + " " + u.getLastName() + "(" + u.getUserCode() + ")"%></option>
            <%
                }
            %>
        </select>
        <br/>
        <label  class="btn btn-primary" onclick="add()">Add</label>
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
    function add() {
        var users = $('#usersToAdd').val();
        var len = users.length;
        var i = -1;
        var usersIds = [];
        var usersDisp = [];
        while (++i < len) {
            var e = users[i];
            var id = parseInt(e.split("#_#")[0]);
            var disp = e.split("#_#")[1];
            map[id] = disp;
        }

        resynctag(map);


    }
    function removeUser(userId) {
        delete map[userId];
        resynctag(map);
    }

    function resynctag(map) {
        var html = "";
        for (var key in map) {
            var v = map[key];
            html += '<span userid="' + key + '" class="tag label label-primary">' + v + '<span data-role="remove" onclick="removeUser(' + key + ')"></span></span>&nbsp';
        }
        $('#usersLists').html(html);
    }

    function getIdsForUser() {
        var ids = [];
        for (var key in map) {
            ids.push(key);
        }
        return ids;
    }
    ;

</script>


<div class="panel-footer clearfix">
    <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Save</a>
</div>

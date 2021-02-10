<%@page import="org.orlounge.common.UserToken"%>
<%@page import="org.orlounge.bean.PostOpBean"%>
<%
    PostOpBean bean = (PostOpBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String surgeon = bean.getSurgeon() == null ? "" : bean.getSurgeon();
    String emCnt = bean.getEmergencyContact() == null ? "" : bean.getEmergencyContact();
    String activity = bean.getActivity() == null ? "" : bean.getActivity();
    String ins = bean.getInstructions() == null ? "" : bean.getInstructions();
    String med = bean.getMedications() == null ? "" : bean.getMedications();

    String operation = bean.getOperation() == null ? "" : bean.getOperation();
    String restrict = bean.getRestriction() == null ? "" : bean.getRestriction();
    String supp = bean.getSupplies() == null ? "" : bean.getSupplies();
    String ofcVisit = bean.getOfficeVisit() == null ? "" : bean.getOfficeVisit();
    String woundCare = bean.getWoundCare() == null ? "" : bean.getWoundCare();
    String oth = bean.getOther() == null ? "" : bean.getOther();
%>


<div class="form-group">
    <label class="col-md-3  control-label">Surgeon <span class="required">*</span></label>

    <div class="col-md-6">
        <input disabled  required id="title" name="surgeon" class="form-control " value="<%=surgeon%>" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Operation</label>
    <div class="col-md-6"><textarea disabled  id="operation" name="operation" class="form-control" ><%=operation%></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Activity in the next 24 hours</label>
    <div class="col-md-6"><textarea disabled id="activity" name="activity" class="form-control" ><%=activity%></textarea></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Contact number in case of an emergency</label>
    <div class="col-md-6"><input disabled  id="emergencyContact" name="emergencyContact" value="<%=emCnt%>" class="form-control"/></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">General Restriction, Diet, Activity and Others</label>

    <div class="col-md-6"><textarea disabled  id="restriction" name="restriction" class="form-control" ><%=restrict%></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Wound care</label>
    <div class="col-md-6"><textarea disabled  id="woundCare" name="woundCare" class="form-control" ><%=woundCare%></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Medications</label>
    <div class="col-md-6"><textarea disabled  id="medications" name="medications" class="form-control" ><%=med%></textarea></div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Supplies and equipment</label>
    <div class="col-md-6"><textarea disabled id="supplies" name="supplies" class="form-control" ><%=supp%></textarea></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Postoperative Office Visit</label>
    <div class="col-md-6 form-inline" >
        <span class="input-group-addon">
            <input type="hidden"  disabled  id="officeVisit" name="officeVisit" value="<%=ofcVisit%>" class="form-control"/>
            <i class="fa fa-calendar"></i><input data-plugin-datepicker required type="text"   id="officeVisitDate"   class="form-control"/>
            <i class="fa fa-clock-o"></i><input data-plugin-timepicker  required type="text"   id="officeVisitTime" class="form-control"/>

        </span>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Instructions or facilities</label>


    <div class="col-md-6"><textarea disabled  id="instructions" name="instructions" class="form-control" ><%=ins%></textarea></div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Others</label>
    <div class="col-md-6"><textarea disabled id="other" name="other" class="form-control" ><%=oth%></textarea></div>
</div>

<input type="hidden" disabled id="userPreveliges" name="userPreveliges"/>

<div class="form-group">
    <%--<label class="col-md-3 control-label">Access Privileges To : </label>--%>
    <div class="col-md-6">
        <%-- <%
             List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
             Collections.sort(users);
         %>
         <select id="usersToAdd"  multiple="multiple" class="form-control">
             <%
                 for(UserInfo u : users){
                     if(u.getUserId().equals(token.getUserId())){
                         continue;
                     }
             %>
             <option value="<%= u.getUserId() + "#_#"+ u.getFirstName()+" "+ u.getLastName() + "("+u.getUserCode()+")"%>"><%=u.getFirstName()+" "+ u.getLastName() + "("+u.getUserCode()+")"%></option>
             <%
                 }
             %>
         </select>--%>
        <%--<br/>--%>
        <%--<label  class="btn btn-primary" onclick="add()">Add</label>--%>
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



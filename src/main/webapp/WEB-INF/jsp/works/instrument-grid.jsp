<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.PostOpBean" %>
<%@ page import="org.orlounge.bean.PrefListBean" %>
<%@ page import="org.orlounge.bean.InstrumentPrefListBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = null;
var success = true;

    <%

    PrefListBean pref = (PrefListBean) request.getAttribute("pref");
    List<InstrumentPrefListBean> beans =   new ArrayList<>((java.util.Collection<? extends InstrumentPrefListBean>) request.getAttribute("beans"));


    if(request.getParameter("message") != null){
%>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success : success, message: msg};
<%
    }else if(request.getParameter("json") != null){
%>
var json =<%=request.getParameter("json")%> ;
<%
    }
    %>

</script>

<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

%>
<form enctype="multipart/form-data" id="ins-form" action="saveinstrument.html" method="POST">
    <input type="hidden" name="id" id="ins-id"/>
    <input type="hidden" name="prefId" id="ins-prefId" value="<%=pref.getId()%>"/>
<%--
    <input type="file" class="hidden" name="photoImagePath" id="ins-photoImagePath"/>
--%>


<!-- start: page -->
<section class="panel">
<header class="panel-heading">
    <div class="panel-actions">
        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
        <%--<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>--%>
    </div>
<%
    String editVal = request.getAttribute("edit") != null ? (""+request.getAttribute("edit")) : "false";
    boolean edit = Boolean.valueOf(editVal) && !token.isGuest();

%>
    <h2 class="panel-title">Instruments List</h2>
</header>
<div class="panel-body">
<div class="row">
    <div class="col-sm-6">
        <div class="mb-md">
            <%
                if(!token.isGuest()){
                    %>
            <button id="addToTable" class="btn btn-primary">Add a Instrument<%--<i class="fa fa-plus"></i>--%></button>
            <a href="viewpreflist.html?id=<%=pref.getId()%>&edit=<%=edit%>" class="btn btn-primary">Done</a>
            <%
                }
            %>

        </div>
    </div>
</div>
<table class="table table-bordered table-striped mb-none" id="datatable-editable">
<thead>
<tr>
    <th>Name</th>
    <th>Quantity</th>
    <th>Photo</th>
    <th>Bin</th>
    <th>Catalog #</th>
    <th>Actions</th>
</tr>
</thead>
<tbody>
<%
    for(InstrumentPrefListBean i : beans){
        %>
<tr class="gradeX" pref-id="<%=i.getPrefId()%>" ins-id="<%=i.getId()%>">
    <td><%=i.getName()%></td>
    <td>
        <%=i.getQuantity()%>
    </td>
    <td>
        <%
            if(ProcessData.isValid(i.getPhotoImagePath())){
        %>
        <img width="100" height="100" src="getImageDocument?filePath=<%=i.getPhotoImagePath()%>"/></td>
                <%
            }
        %>

    <td>
        <%=i.getBin()%>
    </td>
    <td>
        <%=i.getCatalog()%>
    </td>
    <td class="actions">
        <%
            if(!token.isGuest()){
                %>
        <a href="#"  class="hidden on-editing save-row"><i class="fa fa-save"></i> Save</a>
        <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i> Cancel</a>
        <a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i> Remove</a>
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
<!-- end: page -->

<%
    for(InstrumentPrefListBean i : beans) {
        %>

    <%
    }
%>

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

</form>
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


<!-- Vendor -->


<script src="resources/app/admin-user.js"></script>




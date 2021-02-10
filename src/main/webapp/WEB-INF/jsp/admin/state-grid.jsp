<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.bean.GroupBean" %>
<%@ page import="org.orlounge.bean.StateBean" %>
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
<header class="panel-heading">
    <div class="panel-actions">
        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>

    </div>

    <h2 class="panel-title">State List</h2>
</header>
<div class="panel-body">
    <form  class="form-horizontal" action="save-state.html" method="POST" enctype="multipart/form-data"  id="data-form" novalidate="novalidate">

        <div class="form-group">
            <label class="col-md-3  control-label">State Name<span class="required">*</span></label>

            <div class="col-md-6">
                <input required id="stateName" name="stateName" data-plugin-maxlength maxlength="20"  class="form-control "/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3  control-label">State Code<span class="required">*</span></label>

            <div class="col-md-6">
                <input required id="stateCode" name="stateCode" data-plugin-maxlength maxlength="5"  class="form-control "/>
            </div>
        </div>


        <div class="panel-footer clearfix">
            <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Save</a>
        </div>


    </form>

        <table class="table table-bordered table-striped mb-none" id="datatable-default" >

<thead>
<tr>
    <th>#</th>
    <th>State Name</th>
    <th>State Code</th>
</tr>
</thead>
<tbody>
<%
    List<StateBean> states = (List<StateBean>) request.getAttribute("states");
    for(StateBean u : states){
%>
<tr class="gradeX">
    <td><%=u.getStateId()%></td>
    <td><%=u.getStateName()%></td>
    <td><%=u.getStateCode()%></td>
</tr>
<%
    }
%>

</tbody>


</table>
</div>
</section>






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
<script src="assets/vendor/jquery/jquery.js"></script>
<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
<script src="assets/vendor/intercooler/intercooler-0.4.8.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/jquery-mockjax/jquery.mockjax.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="assets/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="assets/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="assets/javascripts/theme.init.js"></script>

<!--
Examples - Actually there's just ajax mockups in the file below
All mockups are for demo purposes only. You don't need to include this in your application.
-->
<script src="assets/javascripts/extra/examples.ajax.made.easy.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/select2/select2.js"></script>
<script src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
<script src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>


--%>

<script type="application/javascript">

    function dataSubmit(){
        var f = $('#data-form');
        if($('#stateName').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "State Name can not be empty.",
                type: 'error'
            });
            return;
        }
        if($('#stateCode').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "State Code can not be empty.",
                type: 'error'
            });
            return;
        }


        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"></script>
<script src="assets/javascripts/forms/examples.advanced.form.js"/>




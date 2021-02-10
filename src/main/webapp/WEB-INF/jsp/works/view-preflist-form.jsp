<%@ page import="org.orlounge.bean.InstrumentPrefListBean" %>
<%@ page import="org.orlounge.bean.PrefListBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
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

    jQuery(document).ready(
            function($) {
                $("#addToTable").click(function(event) {
                    if(prelistValidation()){
                        savePrefListData();

                    }
                });
            });

    function savePrefListData() {
        var data = prelistFormData();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "savePreflistAjax",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000,
                success: function (data) {
                    $('#ins-prefId').val(data.id);
                    //$('#ins-prefId').prop('disabled',true);
                },
                error: function (e) {
                    $("#btn-save").prop("disabled", false);
                }
            });

    }

    function downloadSample(){
        window.location.href = 'getSampleInstrument';
    }
    function savePreList(){
        if(prelistValidation()){
            if(!$('#ins-prefId').val()){
                var data = prelistFormData();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "savePreflistAjax",
                    data: JSON.stringify(data),
                    dataType: 'json',
                    timeout: 600000,
                    success: function (data) {
                        $('#ins-prefId').val(data.id);
                      //  $('#ins-prefId').prop('disabled',true);
                        uploadCSVFile(data.id);
                    },
                    error: function (e) {
                        $("#btn-save").prop("disabled", false);
                    }
                });
            }else{
                uploadCSVFile(document.getElementById('ins-prefId').value);
            }
        }
    }
    function uploadCSVFile(prefId){
        var formData = $('#uploadFile');
        $('#uploadPrefId').val(prefId);

       formData.submit();
    }
    function prelistFormData(){
        var data = {};
        prelistValidation();
        data["id"] = $("#id").val();
        data["procedure"] = $("#procedure").val();
        data["speciality"] = $("#speciality").val();
        data["spConsideration"] = $("#spConsideration").val();
        data["anConsideration"] = $("#anConsideration").val();
        data["roomSetup"] = $("#roomSetup").val();
        data["prepDrape"] = $("#prepDrape").val();
        data["equipment"] = $("#equipment").val();
        data["dressingDrain"] = $("#dressingDrain").val();
        data["others"] = $("#others").val();
        return data;
    }
    function dataSubmit(){
        if(!prelistValidation()){
            window.location.href = "pref-list.html";
        }
    }
    function prelistValidation(){
        if($('#procedure').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Procedure can not be empty.",
                type: 'error'
            });
            return false;
        }
        return true;
    }
</script>

<%
    PrefListBean bean = (PrefListBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String sp = bean.getSpeciality() == null ? "":bean.getSpeciality();
    String procedure = bean.getProcedure() == null ? "":bean.getProcedure();
    String spCons = bean.getSpConsideration();
    String anCons = bean.getAnConsideration();
    String roomSetup = bean.getRoomSetup();
    String prepDrape = bean.getPrepDrape();
    String eq = bean.getEquipment();
    String dressDrain = bean.getDressingDrain();
    String others = bean.getOthers();
    Set<InstrumentPrefListBean> ins = bean.getInstruments() == null ? new HashSet<InstrumentPrefListBean>() : bean.getInstruments();

    boolean readOnly = ProcessData.isValid(bean.getId()) || token.isGuest();
    boolean isView = ProcessData.isValid(bean.getId()) || token.isGuest();
    Boolean isEdit = !token.isGuest() &&  (request.getParameter("edit") != null ? (Boolean.valueOf(request.getParameter("edit"))) : false);
    if(!token.isGuest() && !isEdit ){
        Boolean t = (Boolean) request.getAttribute("edit");
        if(t != null){
                isEdit = t.booleanValue();
        }
    }
    if(isEdit ){
        if(bean.getId() != null){
            readOnly = !bean.getCreatedBy().equals(token.getUserId());
        }else {
            readOnly = false;
        }

    }
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>


<section role="main" >

    <div class="row">
        <div class="col-xs-12">
            <section class="panel">

                <!-- <h1 class="panel-title" align="center">Operating Room Setup</h1> -->
                
                <div class="panel-body">
                   <!--  <p class="bold">
                        Name of procedure
                    </p>  -->

                    <form enctype="multipart/form-data"  class="form-horizontal form-bordered" action="savePreflist.html" method="POST"  id="data-form" novalidate="novalidate">
                       <%
                       if(action.equalsIgnoreCase(AppConstants.ACTION_CREATE)){ %>
                       <jsp:include page="update-pref-list-form.jsp" />
                      <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)){ %>
                          <jsp:include page="update-pref-list-form.jsp" />
                       <%} else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>
						  <jsp:include page="view-pref-list-form.jsp" />
                       <%
                           }
                       %>
                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<script type="application/javascript">



    function dataSubmit(){
        var f = $('#data-form');
        if($('#procedure').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Procedure can not be empty.",
                type: 'error'
            });
            return;
        }

        f.submit();
    }
</script>
<script src="resources/vendor/select2/select2.js"></script>
<script src="resources/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
<script src="resources/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
<script src="resources/vendor/magnific-popup/magnific-popup.js"></script>

<script src="resources/app/instruments-grid.js"></script>
<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>
<script src="resources/javascripts/tables/examples.datatables.editable.js"></script>

<%
    if(!readOnly && (bean.getId() == null  || (bean.getId()!= null && (bean.getCreatedBy().equals(token.getUserId())))) ){

%>
<script type="text/javascript">
  //  $('#addToTable').click();
</script>

<%
    }
%>

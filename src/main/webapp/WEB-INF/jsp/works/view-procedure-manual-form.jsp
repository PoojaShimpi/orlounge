<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.InServiceBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.CallScheduleBean" %>
<%@ page import="org.orlounge.bean.ProcedureManualBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var msg = null;
    var success = true;

    <% if (request.getParameter("message") != null) {
    %>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success: success, message: msg}
    <%
    } else if (request.getParameter("json") != null) {
    %>
    var json =<%=request.getParameter("json")%>;
    <%
        }
    %>

</script>

<%
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>


<section role="main">

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">


                <h2 class="panel-title">OR Administration Manual</h2>
                <div class="panel-body">
                    <form enctype="multipart/form-data"  class="form-horizontal" action="manual/saveManual" method="POST"  id="data-form" novalidate="novalidate">
                        <%
                            if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) {%>
                        <jsp:include page="create-procedure-manual-form.jsp"/>
                        <%
                        } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) {%>
                        <jsp:include page="update-procedure-manual-form.jsp"/>

                        <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>

                        <jsp:include page="view-procedure-manual-only.jsp"/>


                        <%}
                        %>


                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<script type="application/javascript">


    var file1 ;
    $('#file').change(function () {
    file1 = this.files[0];
    });

    function dataSubmit(){
    var f = $('#data-form');
    if($('#title').val().toString().trim().length ==0){
    new PNotify({
    title: 'Validation',
    text:   "Title can not be empty.",
    type: 'error'
    });
    return;
    }

    var x = $('#descriptionB').parent().find('.note-editable').html();

    if(x.trim().length == 0){
    new PNotify({
    title: 'Validation',
    text:   "Description can not be empty.",
    type: 'error'
    });
    return;
    } else {
    $('#text').val(x.trim());
    }

    /*
    if(file1){

    } else {
    new PNotify({
    title: 'Validation',
    text:   "File needs to be uploaded.",
    type: 'error'
    });
    return;
    }
    */

    f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




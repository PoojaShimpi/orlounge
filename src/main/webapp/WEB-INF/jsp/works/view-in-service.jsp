<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.bean.CallScheduleBean" %>
<%@ page import="org.orlounge.common.util.DateUtility" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.InServiceBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
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
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>


<section role="main">

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">

                    <h2 class="panel-title">In Service</h2>
                <div class="panel-body">
                    <form  class="form-horizontal" action="inservice/saveInService" method="POST" enctype="multipart/form-data"  id="data-form" novalidate="novalidate">
                      <%
                      if(action.equalsIgnoreCase(AppConstants.ACTION_CREATE)){ %>
                      <jsp:include page="create-in-service-form.jsp"/>

                      <%} else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)){ %>
                      <jsp:include page="update-in-service-form.jsp"/>
                     <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)){ %>
                     <jsp:include page="view-in-service-only.jsp"/>
                      <%}
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
        if($('#title').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Title can not be empty.",
                type: 'error'
            });
            return;
        }
        if($('#date').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Date can not be empty.",
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

        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




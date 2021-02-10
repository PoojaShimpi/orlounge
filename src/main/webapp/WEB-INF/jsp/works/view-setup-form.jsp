<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.util.DateUtility" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.AnaethesiaSetupBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.AnaethesiaSetupNewBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var msg = null;
    var success = true;

    <% if (request.getParameter("message") != null) {
    %>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success: success, message: msg};
    <%
    } else if (request.getParameter("json") != null) {
    %>
    var json =<%=request.getParameter("json")%>;
    <%
        }
    %>

</script>

<%
    AnaethesiaSetupNewBean bean = (AnaethesiaSetupNewBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String action = (String) request.getAttribute(AppConstants.ACTION);
    boolean isView = token.isGuest();
%>


<section role="main">

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">

                <h2 class="panel-title">Anaesthesia Setup </h2>
                <div id="printForm" class="panel-body">

                    <form class="form-horizontal" action="new_save_an_setup.html" method="POST" id="data-form"
                          novalidate="novalidate">
                        <%
                            if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) {%>
                        <jsp:include page="create-an-step-form.jsp"/>
                        <%
                        } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) { %>
                        <jsp:include page="update-an-step-form.jsp"/>

                        <%
                            } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)){ %>
                            <jsp:include page="view-an-step-only.jsp"/>
                            <%}
                        %>




                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<script type="application/javascript">

    function printDiv(withPat){
    if(!withPat){
    $('#pat').hide();
    }
    var el ="printForm";
    var restorepage = $('body').html();
    var printcontent = $('#' + el).clone();
    $('body').empty().html(printcontent);
    window.print();
    $('body').html(restorepage);
    $('#pat').show();
    };

    function dataSubmit(){
    var f = $('#data-form');
    if($('#operation').val().toString().trim().length == 0){
    new PNotify({
    title: 'Validation',
    text:   "Operation can not be empty.",
    type: 'error'
    });
    return false;
    } else if ($('#type_of_anesthesia').val().toString().trim().length == 0) {
    new PNotify({
    title: 'Validation',
    text:   "Type of Anesthesia can not be empty.",
    type: 'error'
    });
    return false;
    } else if ($('#pre_induction').val().toString().trim().length == 0) {
    new PNotify({
    title: 'Validation',
    text:   "Pre-induction can not be empty.",
    type: 'error'
    });
    return false;
    } else if ($('#intra_operative').val().toString().trim().length == 0) {
    new PNotify({
    title: 'Validation',
    text:   "Intra-operative can not be empty.",
    type: 'error'
    });
    return false;
    } else if ($('#emergence').val().toString().trim().length == 0) {
    new PNotify({
    title: 'Validation',
    text:   "Emergence can not be empty.",
    type: 'error'
    });
    return false;
    }
    return true;

    var x = $('#descriptionB').parent().find('.note-editable').html();


    $('#body').val(x.trim());


    f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




<%@ page import="org.orlounge.bean.HandoverBean" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
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
    HandoverBean bean = (HandoverBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getName() == null ? "" : bean.getName();
    String desc = bean.getText();
    String fileName = bean.getName() == null ? "" : bean.getName();
    String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
    boolean isView = ProcessData.isValid(bean.getId()) || token.isGuest();
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>


<section role="main">
    <div class="row">
        <div class="col-sm-8">
            <section class="panel">

                <h2 class="panel-title">Handover (ISBAR)</h2>

                <div class="panel-body">
                    <p >
                        Create a handover.
                    </p>

<p>Test Search</p>
                    <form enctype="multipart/form-data"  class="form-horizontal" action="handover/saveHandover" method="POST"  id="data-form" novalidate="novalidate">

                            <%
                                if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) { %>
                            <jsp:include page="create-handover-form.jsp"/>
                            <%
                            } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) { %>
                            <jsp:include page="update-handover-form.jsp"/>
                            <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>
                            <jsp:include page="view-handover-only.jsp"/>
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
    if($('#name').val().toString().trim().length ==0){
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




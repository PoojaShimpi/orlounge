<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.*" %>
<%@ page import="org.orlounge.common.UserToken" %>
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
    ChecklistBean bean = (ChecklistBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getTopic() == null ? "" : bean.getTopic();
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
                <%--           <header class="panel-heading">
                           <div class="panel-actions">
                               <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>

                                                       <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>

                           </div>
                             </header>
                --%>
                <h2 class="panel-title">Checklist</h2>

                <div class="panel-body">
                    <p >
                        Create a checklist for any purpose by any member.
                        Some examples are
                    </p>
                    <ul class="mb-lg">
                        <li>
                            Time out checklist
                        </li>
                        <li>
                            Setting up a piece of equipment
                        </li>
                        <li>
                            Collecting special type of pathology specimen
                        </li>
                        <li>
                            Preoperative checklist of high risk patients
                        </li>
                        <li>
                            Steps during an emergency e.g. OR fire, unusual precautions, power failure
                        </li>
                        <li>
                            Reporting a needle prick
                        </li>
                        <li>
                            OR lock down procedure
                        </li>
                        <li>
                            Steps during special alert situation
                        </li>

                    </ul>

                    <form enctype="multipart/form-data"  class="form-horizontal" action="checklist/saveChecklist" method="POST"  id="data-form" novalidate="novalidate">

                            <%
                                if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) { %>
                            <jsp:include page="create-checklist-form.jsp"/>
                            <%
                            } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) { %>
                            <jsp:include page="update-checklist-form.jsp"/>
                            <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>
                            <jsp:include page="view-checklist-only.jsp"/>
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




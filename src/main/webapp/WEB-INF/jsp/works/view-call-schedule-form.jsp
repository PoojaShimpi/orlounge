<%@ page import="org.orlounge.common.AppConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>


<section role="main">

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">

                <h2 class="panel-title">Call Schedule</h2>
                <div class="panel-body">
                    <form enctype="multipart/form-data"  class="form-horizontal" action="call/saveSchedule" method="POST"  id="data-form" novalidate="novalidate">
                        <%   if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) { %>
                        <jsp:include page="create-call-schedule.jsp"/>
                        <%
                        } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) {%>
                        <jsp:include page="update-call-schedule-form.jsp"/>
                        <%
                            } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>
                            <jsp:include page="view-call-schedule-only.jsp"/>
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
    var x = $('#descriptionB').parent().find('.note-editable').html();
    $('#text').val(x);
    if($('#title').val().toString().trim().length ==0){
    new PNotify({
    title: 'Validation',
    text:   "Title can not be empty.",
    type: 'error'
    });
    return;
    }


    if(file1){

    } /*else {
    new PNotify({
    title: 'Validation',
    text:   "File needs to be uploaded.",
    type: 'error'
    });
    return;
    }*/

    f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




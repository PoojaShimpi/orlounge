<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.util.DateUtility" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
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
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    boolean isView = token.isGuest();
    String action = (String) request.getAttribute(AppConstants.ACTION);

%>


<section role="main" >

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">
                <h2 class="panel-title">Postoperative Care</h2>
                <div id="printForm" class="panel-body">
                    <h2 class="bold hide printHospital"><%=token.getCurrentGroupName()%></h2>
                    <p class="bold printHeader">
                        You are creating a template. Create a template and save. Modify for each patient.
                        There is no field for the patient's name because this is not a medical record. The document can be printed only for the patient.

                    </p>

                    <form  class="form-horizontal" action="postops/savePostOps" method="POST"  id="data-form" novalidate="novalidate">
                        <%
                            if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) { %>
                        <jsp:include page="create-post-ops-form.jsp" />
                        <%} else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) { %>

                        <jsp:include page="update-post-ops-form.jsp" />

                        <% } else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) { %>
                        <jsp:include page="view-post-ops-only.jsp"/>

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

    function printDiv(withPat){
    if(!withPat){
    $('#pat').hide();
    }
    $('.printHeader').hide();
    $('.printHospital').show();
    $('.printHospital').removeClass('hide');
    var btns = $(".printbtn");
    var len = btns.length;
    var i= -1;
    while(++i < len){
    $(btns[i]).hide();
    }
    var el ="printForm";
    var restorepage = $('body').html();
    var printcontent = $('#' + el).clone();
    $('body').empty().html(printcontent);
    window.print();
    $('body').html(restorepage);
    $('#pat').show();
    $('.printHeader').show();
    $('.printHospital').hide();
    $('.printHospital').addClass('hide');
    var btns = $(".printbtn");
    var len = btns.length;
    var i= -1;
    while(++i < len){
    $(btns[i]).show();
    }
    };

    function dataSubmit(){
    var f = $('#data-form');
    if($('#title').val().toString().trim().length ==0){
    new PNotify({
    title: 'Validation',
    text:   "Surgeon can not be empty.",
    type: 'error'
    });
    return;
    }


    if($('#officeVisitDate').val() && $('#officeVisitTime').val()){
    $('#officeVisit').val($('#officeVisitDate').val()+" "+$('#officeVisitTime').val());
    }



    var ids = getIdsForUser();
    $('#userPreveliges').val(ids.join(",").trim());

    f.submit();
    }


    $('#officeVisitDate').on('change', function(){
    var date = $('#officeVisitDate').val();
    var time = $('#officeVisitTime').val();

    if(date && time){
    $('#officeVisit').val(date+" "+time)
    }else if(date){
    $('#officeVisit').val(date+" "+time)
    }


    });
    $('#officeVisitTime').on('change', function(){
    var time = $('#officeVisitTime').val();
    })

</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




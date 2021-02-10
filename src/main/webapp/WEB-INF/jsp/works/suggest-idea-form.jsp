<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<section role="main" >

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">
                <h2 class="panel-title">Suggest An Idea</h2>

                <div class="panel-body">
                    <p class="col-sm-12">

                    </p>


                    <form enctype="multipart/form-data"  class="form-horizontal" action="save-idea.html" method="POST"  id="data-form" novalidate="novalidate">
                        <div class="form-group">
                            <label class="col-md-3  control-label">Title<span class="required">*</span></label>
                            <div class="col-md-6">
                            <input required id="title" name="title" class="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Short Description<span class="required">*</span></label>
                            <div class="col-md-6">
                                <textarea rows="3"  required id="desc" name="desc" class="form-control" ></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Explanation<span class="required">*</span></label>
                            <div class="col-md-6">
                                <textarea rows="10"  required id="content" name="content" class="form-control" ></textarea>
                            </div>
                        </div>












                        <div class="panel-footer clearfix">
                            <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Submit</a>
                        </div>

                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<script type="application/javascript">
    <%
        if(token.isAdmin()){
            %>

    $('#startDate').datepicker();
    $('#endDate').datepicker();

    <%
        }
    %>

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
        if($('#desc').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Description can not be empty.",
                type: 'error'
            });
            return;
        }
        if($('#content').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Explanation can not be empty.",
                type: 'error'
            });
            return;
        }




        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="assets/javascripts/forms/examples.advanced.form.js"/>




<%@ page import="org.orlounge.bean.ProjectDocBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    ProjectDocBean bean = (ProjectDocBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<section role="main" >

    <div class="row">
        <div class="col-xs-12">
            <section class="panel">
                <header class="panel-heading">
                    <div class="panel-actions">
                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                    </div>

                    <h2 class="panel-title"><%=bean.getId() != null ? bean.getTitle() : "Create Document"%></h2>

                </header>
                <div class="panel-body">

                    <form  class="form-horizontal form-bordered" action="save-project-doc.html" method="POST"  id="data-form" novalidate="novalidate">
                        <div class="form-group">
                            <input value="<%=bean.getProject().getId()%>" type="hidden" id="projectId" name="projectId" class="col-md-6 " />
                            <%
                                if(bean.getId() != null){
                                    %>
                            <input type="hidden" id="title" name="title" class="col-md-6 " value="<%=bean.getTitle()%>"/>
                            <%
                                }else {
                                    %>
                            <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
                            <input required id="title" name="title" class="col-md-6 " />
                            <%
                                }
                            %>
                            <input id="id" name="id" type="hidden" class="col-md-6 " value="<%=bean.getId() == null ? "": bean.getId()%>"/>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">Description</label>
                            <textarea   id="desc" name="desc" class="col-md-6" ><%=bean.getDesc() == null ? "":bean.getDesc()%></textarea>
                        </div>


                        <div class="form-group">
                            <input  type="hidden" id="body" name="body"/>
                            <div class="col-md-12">
                                <div  id="descriptionB" class="summernote" data-plugin-summernote
                                      data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'>
                                    <%=bean.getBody() == null ? "":bean.getBody()%>

                                      </div>
                            </div>
                        </div>



                        <div class="form-group">
                            <%
                                if(!token.isGuest()){
                                    %>
                            <a class="btn btn-primary" style="margin-left:15px;" href="#save" onclick="dataSubmit()" >Save</a>
                            <%
                                }
                            %>


                        </div>

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

        var x = $('#descriptionB').parent().find('.note-editable').html();

            $('#body').val(x.trim());
        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




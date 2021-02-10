<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.ProjectInfoBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.ProjectDocBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
    ProjectInfoBean bean = (ProjectInfoBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>


<section role="main" >

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">
                <header class="panel-heading">
                    <div class="panel-actions">
                    <%--    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>--%>
                        <%--
                                                <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                        --%>
                    </div>

                    <%
                        if(!token.isGuest() && bean.getCreatedById().equals(token.getUserId())){
                      %>
                    <h5 ><a class="button btn btn-primary" href="add-new-doc.html?id=<%=bean.getId()%>">Create New Document</a></h5>
                    <%
                        }
                        %>
                    <div class="panel-title">
                        <h2 ><%=bean.getName()%> - by <%=bean.getOwner().getFirstName()+" "+bean.getOwner().getLastName()%> </h2>
                        <script type="text/javascript">

                            function showDesc(){
                                var desc= $('.desc-text');
                                var visible = desc.is(":visible");
                                if(visible){
                                    $('.desc-link').html('View Description Of Project');
                                    desc.addClass('hide');
                                }else {
                                    $('.desc-link').html('Hide Description of Project');
                                    desc.removeClass('hide');
                                }
                            }

                            function showDocDesc(id){
                                var desc= $('.desc-text-'+id);
                                var visible = desc.is(":visible");
                                if(visible){
                                    $('.desc-link-'+id).html('View Description of Document');
                                    desc.addClass('hide');
                                }else {
                                    $('.desc-link-'+id).html('Hide Description of Document');
                                    desc.removeClass('hide');
                                }
                            }
                        </script>

                        <p style="font-size: 14px;">
                            <span href="#" class="desc-link" style="text-decoration: underline;color: #00a3f5;" onclick="showDesc()">View Description of Project</span>
                            <br/>
                            <span class="hide desc-text" >
                                 Description : <%=bean.getDesc()%>
                            </span>
                        </p>


                    </div>


                  </header>
                <div class="panel-body">
                    <form enctype="multipart/form-data"  class="form-horizontal form-bordered" action="#" method="POST"  id="data-form" novalidate="novalidate">
                        <div class="form-group">
                            <input type="hidden"  id="id" name="id" value="<%=bean.getId() != null? bean.getId() : ""  %>" class="col-md-4"/>
                        </div>

                        <%

                            if(bean.getDocuments() != null && !bean.getDocuments().isEmpty()){


                                for(ProjectDocBean d : bean.getDocuments()){
                        %>
                        <div class="form-group box">
                            <label class="col-md-12  bold" > Document Title : <%=d.getTitle()%></label>
                            <p style="margin-left:15px;">
                                <span href="#" style="text-decoration: underline;color:#00a3f5;;" class="desc-link-<%=d.getId()%>" onclick="showDocDesc(<%=d.getId()%>)">View Description of Document</span>
                                <br/>
                                <span class="hide desc-text-<%=d.getId()%>" >
                                 Description : <%=d.getDesc()%>
                            </span>
                            </p>

                            <label class="col-md-12">Last Updated By : <%=d.getLastUpdator().getFirstName()+ " " + d.getLastUpdator().getLastName() + " at "+ new SimpleDateFormat("MMM dd YYYY hh:mm a").format(d.getLastUpdatedDate()) %></label>
                            <div class="col-md-12">
                                <a class="modal-with-move-anim btn btn-primary" href="#docView<%=d.getId()%>" style="float:left;">Read Document</a>
                                <%
                                    if(!token.isGuest()){
                                %>
                                <a href="edit-doc.html?id=<%=d.getId()%>" class="btn btn-primary" style="float: right;">Edit </a>
                                <%

                                    }
                                %>
                            </div>



                        </div>


                        </hr>
                        <%
                                }

                            }else {
                               %>
                        <div class="form-group">
                            <div class="col-md-12">
                                No Documents
                            </div>
                        </div>

                        <%
                            }
                        %>



                        <%
                            if(!token.isGuest() && bean.getId() == null){
                                %>
                        <div class="form-group">
                            <a class="btn btn-primary" style="margin-left: 15px;" href="#save" onclick="dataSubmit()" >Save</a>
                        </div>
                        <%
                            }
                        %>


                    </form>
                </div>
            </section>
        </div>
    </div>


</section>


<%

    if(bean.getDocuments() != null && !bean.getDocuments().isEmpty()) {


        for (ProjectDocBean d : bean.getDocuments()) {
            %>
<div id="docView<%=d.getId()%>" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">

    <section class="panel">
        <%--- <header class="panel-heading"></header>--%>
        <h2 class="panel-title" style="color:000000;"><%=d.getTitle()%></h2>

        <div class="panel-body">

            <div class="modal-text">
                <div class="row mb-lg">

                        <%=d.getBody()%>

                </div>
            </div>
        </div>
        <footer class="panel-footer">
            <div class="row">
                <div class="col-md-12 text-right">

                    <button class="btn btn-default modal-dismiss">Cancel</button>
                </div>
            </div>
        </footer>
    </section>


</div>

<%
        }
    }
%>



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




<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<section class="panel">
    <header class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
            <%--<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>--%>
        </div>

        <h2 class="panel-title">Bulk Mail</h2>
    </header>
    <div class="panel-body">
        <form  class="form-horizontal form-bordered" action="mail/sendBulkMail" method="POST" enctype="multipart/form-data"  id="data-form" novalidate="novalidate">
            <div class="form-group form-group-invisible">
                <label for="to" class="control-label-invisible">To:</label>
                <div class="col-sm-offset-2 col-sm-12 col-md-offset-1 col-md-10">
                    <input id="to" name="bcc" type="text" class="form-control form-control-invisible" data-role="tagsinput" data-tag-class="label label-primary" value="">
                </div>
            </div>



            <div class="form-group form-group-invisible">
                <label for="subject" class="control-label-invisible">Subject:</label>
                <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                    <input id="subject"  name="subject" type="text" class="form-control form-control-invisible" value="">
                </div>
            </div>


            <div class="form-group">
                <input  type="hidden" id="text" name="body"/>
                <div class="col-md-12">
                    <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'></div>
                </div>
            </div>



            <div class="form-group">

                <label class="col-md-3 control-label"> <i class="fa fa-paperclip mr-sm"></i>Attachment</label>
                <input type="file" id="file" name="file" class="col-md-6"/>
            </div>

            <%--
                                    <div class="form-group form-group-invisible">
                                        <i class="fa fa-paperclip mr-sm"></i>
                                        <label for="subject" class="control-label-invisible">Attachment:</label>
                                        <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                                            <input id="attach"  name="file" type="file" class="form-control form-control-invisible" value="">
                                        </div>
                                    </div>--%>

            <div class="form-group">
                <a class="btn btn-primary" href="#save" onclick="dataSubmit()" >Send Mail</a>
                <a class="btn btn-primary" href="home.html"  >Cancel</a>
            </div>

        </form>
    </div>
</section>

<script type="application/javascript">

    function dataSubmit(){
        var f = $('#data-form');
        if($('#to').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "To Ids can not be empty.",
                type: 'warn'
            });
            return;
        }
        if($('#subject').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Subject can not be empty.",
                type: 'warn'
            });
            return;
        }


        var x = $('#descriptionB').parent().find('.note-editable').html();

        if(x.trim().length == 0){
            new PNotify({
                title: 'Validation',
                text:   "Body can not be empty.",
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
<script src="assets/javascripts/forms/examples.advanced.form.js"/>




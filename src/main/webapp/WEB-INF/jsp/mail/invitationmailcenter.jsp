<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="panel">
    <header class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
            <%--<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>--%>
        </div>

        <h2 class="panel-title">Invitation Bulk Mail</h2>
    </header>
    <div class="panel-body">
        <form  class="form-horizontal form-bordered" action="mail/sendInvitationBulkMail" method="POST" enctype="multipart/form-data"  id="data-form" novalidate="novalidate">
            <div class="form-group form-group-invisible">
                <label for="to" class="control-label-invisible">To:</label>
                <div class="col-sm-offset-2 col-sm-12 col-md-offset-1 col-md-10">
                    <textarea id="emailIds" name="emailIds"  class="form-control form-control-invisible" ></textarea>
                </div>
            </div>



            <div class="form-group form-group-invisible">
                <label for="subject" class="control-label-invisible">Subject:</label>
                <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                    <select id="msgType"  name="msgType" type="text" class="form-control form-control">
                        <option value="INVITATION_NORMAL">Normal Invitation</option>
                        <option value="INVITATION_NORMAL_WITH_PASSWORD">Normal Invitation with Password</option>
                        <option value="INVITATION_DECEPTIVE">Deceptive Error Invitation</option>
                        <option value="ADVERTISER_NORMAL">Normal Advertiser Invitation</option>
                    </select>

                </div>
            </div>



<%--
            <div class="form-group">

                <label class="col-md-3 control-label"> <i class="fa fa-paperclip mr-sm"></i>Attachment</label>
                <input type="file" id="file" name="file" class="col-md-6"/>
            </div>--%>



            <div class="form-group col-md-12">
                <a class="btn btn-primary" href="#"  onclick="return popupwin('mail/getTemplate/'+getMsgType())"  >Preview</a>
                <a class="btn btn-primary" href="#save" onclick="dataSubmit()" >Send Mail</a>
                <a class="btn btn-primary" href="home.html"  >Cancel</a>
            </div>

        </form>
    </div>
</section>

<script type="application/javascript">
    function getMsgType(){
        return $('#msgType').val();
    }
    function dataSubmit(){
        var f = $('#data-form');
        if($('#emailIds').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "To Ids can not be empty.",
                type: 'warn'
            });
            return;
        }
        if($('#msgType').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Message Type can not be empty.",
                type: 'warn'
            });
            return;
        }





        f.submit();
    }
</script>

<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="assets/javascripts/forms/examples.advanced.form.js"/>




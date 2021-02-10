<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.bean.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    EspsBillInfo info = (EspsBillInfo) request.getAttribute("info");
    String title = info.getTitle() == null ? "":info.getTitle();
    String accountName = info.getAccountName() == null ? "":info.getAccountName();
    String name = info.getName() == null ? "":info.getName();
    String telPh = info.getTelPh() == null ? "":info.getTelPh();
    String billMail = info.getBillMail() == null ? "":info.getBillMail();
    String startDate = info.getStartDate() == null ? "": new SimpleDateFormat("MMM dd, yyyy").format(info.getStartDate());
    String endDate = info.getEndDate() == null ? "": new SimpleDateFormat("MMM dd, yyyy").format(info.getEndDate());
    boolean isView = !(token.isLSA() || token.isAdmin());
%>


<section role="main" >

    <div class="row">
        <div class="col-sm-8">
            <section class="panel">
                <h2 class="panel-title">Buy an ESPS</h2>

                <div class="panel-body">
                    <p class="col-sm-12">
                        An Operating Room can enhance the Security and Privacy of its hospital lounge site by buying a geofencing
                        service (<b>ESPS</b>).<br/>
                        Geofencing allows login only if the device is inside the virtual perimeter of the hospital.
                        <br/>
                        <%
                            if(info.getId() == null){
                        %>
                        <b>
                            ESPS is available as <b>FREE</b> trial for 1 month.
                            To continue as a Paid Service after 1 month the pricing is as below :
                        </b>
                        <%
                            }else {
                                if(info.getStatus().equalsIgnoreCase("FREE_TRIAL")){
                         %>
                        You ESPS is available as <b>FREE</b> trial for 1 month. Your free Trial ends on
                        <b><%= new SimpleDateFormat("MMM dd, yyyy").format(info.getEndDate())%></b><br/>
                         To continue as a Paid Service after Free trial the pricing is as below :

                        <%

                                }else if(info.getStatus().equalsIgnoreCase("EXPIRED")){
                                %>
                        Your free Trial got expired  on <b><%= new SimpleDateFormat("MMM dd, yyyy").format(info.getEndDate())%></b>.
                        Please make the early payment arrangements to re-enable the service.
                        <%
                                }
                        }
                        %>

                    </p>
                    <div class="col-sm-12">

                        <div class="col-sm-10">
                            <table class="table table-striped table-bordered table-responsive">
                                <tbody>
                                <tr class="">
                                    <td>
                                        Large Operating room (>10 rooms)
                                    </td>
                                    <td>
                                        $30/month. Paid  Annually
                                    </td>
                                </tr>
                                <tr class="">
                                    <td>
                                        Medium size Operating room (5-10 rooms)
                                    </td>
                                    <td>
                                        $20/month. Paid Annually
                                    </td>
                                </tr>
                                <tr class="">
                                    <td>
                                        Small size Operating room (5-10 rooms)
                                    </td>
                                    <td>
                                        $10/month. Paid Annually
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>

                    <form enctype="multipart/form-data"  class="form-horizontal" action="save-esps.html" method="POST"  id="data-form" novalidate="novalidate">
                        <div class="form-group">
                            <label class="col-md-3  control-label">Account Name <span class="required">*</span></label>
                            <div class="col-md-6">
                            <input <%=isView? "disabled='disabled'" : ""%>  required id="accountName" name="accountName" class="form-control" value="<%=accountName%>" />
                            <input <%=isView? "disabled='disabled'" : ""%> type="hidden"  id="groupId" name="groupId" value="<%=token.getCurrentGroupId()%>" class="col-md-4"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Administrative Title <span class="required">*</span></label>
                            <div class="col-md-6">
                                <input <%=isView? "disabled='disabled'" : ""%>  required id="title" name="title" class="form-control" value="<%=title%>" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Name<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input <%=isView? "disabled='disabled'" : ""%>  required id="name" name="name" class="form-control" value="<%=name%>" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Telephone<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input <%=isView? "disabled='disabled'" : ""%>  required id="telPh" name="telPh" class="form-control" value="<%=telPh%>" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Billing Email<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input <%=isView? "disabled='disabled'" : ""%>  required id="billMail" name="billMail" class="form-control" value="<%=billMail%>" />
                            </div>
                        </div>

                        <%
                            if(token.isAdmin()){
                                %>

                        <div class="form-group">
                            <label class="col-md-3  control-label">Start Date<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input  <%=isView? "disabled='disabled'" : ""%>  required id="startDate" name="startDate" class="form-control" value="<%=startDate%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3  control-label">End Date<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input  <%=isView? "disabled='disabled'" : ""%>  required id="endDate" name="endDate" class="form-control" value="<%=endDate%>" />
                            </div>
                        </div>


                        <%
                            }
                        %>
                         <p>
                             Please visit "Manage GeoFence" under Administration from Left Navigation Menu to add Address you want to add as virtual perimeter(Geo Fence)
                         </p>







                        <%
                            if(!isView){
                        %>

                        <div class="panel-footer clearfix">
                            <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Submit</a>
                        </div>
                        <%
                        }else {
                        %>

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
        if($('#accountName').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Account Name can not be empty.",
                type: 'error'
            });
            return;
        }
        if($('#name').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Name can not be empty.",
                type: 'error'
            });
            return;
        }
        if($('#telPh').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Telephone can not be empty.",
                type: 'error'
            });
            return;
        }

        if($('#billMail').val().toString().trim().length ==0){
            new PNotify({
                title: 'Validation',
                text:   "Billing Mail can not be empty.",
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




<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.ProjectInfoBean" %>
<%@ page import="org.orlounge.bean.GeoLocation" %>
<%@ page import="org.orlounge.bean.EspsBillInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.amazonaws.util.DateUtils" %>
<%@ page import="org.joda.time.DateTimeUtils" %>
<%@ page import="org.joda.time.DateTimeComparator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row col-sm-8">
    <%
        UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

    %>
<section class="panel">

        <h2 class="panel-title clearfix">Manage ESPS


        </h2>



    <div class="panel-body">


            <table class="table table-bordered table-striped mb-none" id="datatable-default" >
                <thead>
                <tr>
                    <th>Hospital</th>
                    <th>Created By</th>
                    <th>Created Date</th>
                    <th>Status</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<EspsBillInfo> beans= (List<EspsBillInfo>) request.getAttribute("beans");

                    for(EspsBillInfo u : beans){
                        %>
                <tr class="gradeX">
                    <td>
                        <%=u.getGroup().getGroupName()%>
                    </td>
                    <td>
                        <%=u.getUser().getFirstName()+" "+ u.getUser().getLastName()%>
                    </td>
                    <td>
                        <%=new SimpleDateFormat("MMM dd, yyyy hh:mm a").format(u.getCreatorDate())%>
                    </td>
                    <td>
                        <%=u.getStatus()%>
                    </td>
                    <td>
                        <%=new SimpleDateFormat("MMM dd, yyyy").format(u.getStartDate())%>
                    </td>
                    <td>
                        <span style="text-decoration: underline;color:darkblue;" class="end-date" end-date="<%= new SimpleDateFormat("MMM dd, yyyy").format(u.getEndDate())%>"  group-id="<%=u.getGroupId()%>" start-date="<%= new SimpleDateFormat("MMM dd, yyyy").format(u.getStartDate())%>" onclick="onDateClick(this)">
                            <%=new SimpleDateFormat("MMM dd, yyyy").format(u.getEndDate())%>
                        </span>
                    </td>
                    <td class="dropdown">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                Action
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="mark-expired.html?groupId=<%=u.getGroupId()%>">Mark Expired</a></li>
                                <li><a href="mark-free-trial.html?groupId=<%=u.getGroupId()%>">Mark Free Trial</a></li>
                                <li><a href="send-reminder-email.html?groupId=<%=u.getGroupId()%>">Send Reminder Email</a></li>
                                <li><a href="#">Send Invoice Email</a></li>
                                <li><a href="#">Send Expiration Email</a></li>
                                <li><a href="#">Send Custom Email</a></li>
                            </ul>
                    </td>

                </tr>

                <%
                    }
                %>

                </tbody>
            </table>
    </div>
</section>
</div>

<jsp:include page="right-side-option-bar.jsp"/>
<!-- end: page -->




<div id="dialog" class="modal-block mfp-hide">
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title">Are you sure?</h2>
        </header>
        <div class="panel-body">
            <div class="modal-wrapper">
                <div class="modal-text">
                    <p>Are you sure that you want to delete this row?</p>
                </div>
            </div>
        </div>
        <footer class="panel-footer">
            <div class="row">
                <div class="col-md-12 text-right">
                    <button id="dialogConfirm" class="btn btn-primary">Confirm</button>
                    <button id="dialogCancel" class="btn btn-default">Cancel</button>
                </div>
            </div>
        </footer>
    </section>
</div>


<!-- Vendor -->
<%--
<script src="assets/vendor/jquery/jquery.js"></script>
<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
<script src="assets/vendor/intercooler/intercooler-0.4.8.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/jquery-mockjax/jquery.mockjax.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="assets/javascripts/theme.js"></script>

<!-- Theme Custom -->
<script src="assets/javascripts/theme.custom.js"></script>

<!-- Theme Initialization Files -->
<script src="assets/javascripts/theme.init.js"></script>

<!--
Examples - Actually there's just ajax mockups in the file below
All mockups are for demo purposes only. You don't need to include this in your application.
-->
<script src="assets/javascripts/extra/examples.ajax.made.easy.js"></script>

<!-- Specific Page Vendor -->
<script src="assets/vendor/select2/select2.js"></script>
<script src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
<script src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
--%>

<script src="resources/app/admin-user.js"></script>
<script type="text/javascript">

    function onDateClick(el){
        var stDt = $(el).attr('start-date');
        var endDt = $(el).attr('end-date');
        var groupId = $(el).attr('group-id');
        $(el).datepicker({
            todayHighlight : true,
            zIndexOffset: 20,
            format :'MMM dd, yyyy',
            startDate : stDt,
            defaultDate : endDt
        });
        $(el).on('changeDate', function(ev){
            var newDate = ev.date;
            var strtDate = new Date(Date.parse(stDt));
            if(newDate <= strtDate){
                alert('Invalid Dates. End date should be after start date');
            }else {
                location.href = 'change-date.html?groupId='+groupId+"&endDate="+ moment(newDate).format('MM/DD/YYYY')
                ;
            }

        })
    }
</script>




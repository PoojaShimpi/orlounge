<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %><%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
    if(ProcessData.isValid(token)){
        response.sendRedirect("/messageboard.html");
    }

%>
<div class="col-sm-3 panel faq-right-box" style="float:right;">

    <%-- <div class="panel-title">


     </div>--%>
    <div class="panel-body">
        <%--<a class="modal-with-move-anim" href="#securityModal"
           style="cursor: pointer;color: #c3c3c3;display: inline-block;font-size: 17px;margin: 0 0 0 10px;height: 50px;
                                   width: 50px;vertical-align: top;text-align: center;-webkit-transition: all 0.15s ease-in-out;
                                   -moz-transition: all 0.15s ease-in-out;transition: all 0.15s ease-in-out;line-height:35px;
                                    vertical-align: middle;margin-top: -5px;width: auto; color:#fff">
            <img src="assets/images/sec_alert_3.png" />
        </a
--%>
        <a class="modal-with-move-anim btn twelevefont bold red-highlight" href="#bugModal" style="float:left;" onclick="resetReport()">REPORT A BUG</a>
        <br/>
        <br/>
        <a class="btn twelevefont bold red-highlight" href="invite.html?hospitalFlag=on" style="float:left;">Tell a friend</a>
        <br/>
        <br/>
        <%--<a class="modal-with-move-anim btn  twelevefont bold red-highlight" href="#" style="float:left;">Recent Notices</a>--%>
        <a class="btn twelevefont bold red-highlight" href="notices.html" style="float:left;">Recent Notices</a>

    </div>
</div>

<script type="text/javascript">
    function resetReport(){

        $('#bugType').val('Registeration Process');
        $('#bugTxt').val('');
        $('#userRating').val('1');
        $('#secRating').val('1');

    }
</script>




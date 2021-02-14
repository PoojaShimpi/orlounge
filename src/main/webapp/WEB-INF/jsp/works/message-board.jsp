<%@ page import="org.orlounge.common.UserToken" %>
<%
    boolean isGuest = ((UserToken)session.getAttribute("USER_TOKEN")).isGuest();
%>
<script type="text/javascript">
    var GLOBAL_GRP_ID = '<%=((UserToken)session.getAttribute("USER_TOKEN")).getCurrentGroupId()%>';
    var role = "<%=((UserToken)session.getAttribute("USER_TOKEN")).getRole()%>";
    <% if(request.getParameter("json") != null){
%>
   var json = <%=request.getParameter("json")%>;
    <%
        }
        %>

</script>
<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

%>

<section role="main" >
   <!-- <div class="panel">
        <div class="panel-title">Message Board</div>
    </div> -->




    <iframe src="http://35.173.136.146/chat/?id=<%=token.getUserId()%>" title="W3Schools Free Online Web Tutorials" height="700" width="900">
    </iframe>
 <jsp:include page="right-side-option-bar.jsp"/>
</section>


<script src="resources/app/message-board.js"></script>


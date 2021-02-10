<%@ page import="org.orlounge.common.util.MessageUtils" %>
<script type="text/javascript">
    <%
     if (session.getAttribute("message")!= null){
     %>
    var message = '<%=MessageUtils.getMessage(session)%>';
    var success = <%=MessageUtils.isSuccess(session)%>;
    <%
       session.removeAttribute("message");
    }
    %>
</script>
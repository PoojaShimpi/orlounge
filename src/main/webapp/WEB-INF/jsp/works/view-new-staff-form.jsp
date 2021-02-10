<%@ page import="org.orlounge.common.AppConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var msg = null;
    var success = true;

    <% if (request.getParameter("message") != null) {
    %>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success: success, message: msg};
    <%
    } else if (request.getParameter("json") != null) {
    %>
    var json =<%=request.getParameter("json")%>;
    <%
        }
    %>

</script>

<%

    String action = (String) request.getAttribute(AppConstants.ACTION);
%>
<!--
data-toggle="tab"
style="visibility: hidden;"
-->

<%
    if (action.equalsIgnoreCase(AppConstants.ACTION_CREATE)) { %>
<jsp:include page="create-new-staff-form.jsp"/>
<% } else if (action.equalsIgnoreCase(AppConstants.ACTION_UPDATE)) { %>
<jsp:include page="update-new-staff-form.jsp"/>
<%
} else if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW)) {
%>

<jsp:include page="view-new-staff-only.jsp"/>
<%} %>


<script src="resources/app/admin-user.js"/>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"/>




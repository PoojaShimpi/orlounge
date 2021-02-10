<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
    if(!ProcessData.isValid(token)){
        session.invalidate();
        response.sendRedirect("/index.html");
    }

%>
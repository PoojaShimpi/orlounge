<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<!doctype html>
<html class="fixed">
<jsp:include page="../checkSession.jsp"/>
<jsp:include page="../../header.jsp"/>

    <%
    UserToken token = (UserToken) request.getSession().getAttribute("USER_TOKEN");
    if(ProcessData.isValid(token) && token.isHavingAccessRestriction()){
       %>
<jsp:include page="../check-geo.jsp"/>
    <%
    }
%>
<body style="min-height: 830px;">
<div class="se-pre-con">

</div>
<section class="body">
    <jsp:include page="../extract-notification.jsp"/>
    <jsp:include page="../../small-head-section.jsp"/>
    <div class="inner-wrapper">
        <table>
            <tr>
                <td class="sidebar-left">
                    <jsp:include page="../../nav.jsp"/>
                </td>
                <td  width="100%">
                    <section role="main" class="content-body">
                        <jsp:include page="../content-header-bar.jsp"/>


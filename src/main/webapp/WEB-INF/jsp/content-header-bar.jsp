<%@ page import="org.orlounge.common.UserToken" %>
<header class="page-header hospital-title-header" style="">
    <% UserToken token = (UserToken) session.getAttribute("USER_TOKEN");%>
    <div class="row">
        <div class="col-sm-8">
                <span class="hospital-title-header-h2" >
                                <%=session.getAttribute("HOSPITAL_NAME") == null ? "": session.getAttribute("HOSPITAL_NAME").toString().toUpperCase()%>
                            </span>
        </div>

     <div class="col-sm-3">
         <%
         if(session.getAttribute("HOSPITAL_NAME")!=null){%>
         <span class="beta-label" >This site is a beta testing mode</span>
         <%}

         %>

    </div>
    </div>

</header>
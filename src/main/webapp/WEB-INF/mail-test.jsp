<%@ page import="org.orlounge.common.mail.Mailing" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: satyam
  Date: 9/21/2015
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body style="min-height: 830px;">
    <%
        try{
        Mailing.sendMailGmail(Arrays.asList(new String[]{"sonisatyam92@gmail.com"}), new ArrayList<String>(), new ArrayList<String>(), "sonisatyam92@gmail.com", "test", "test");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
</body>
</html>

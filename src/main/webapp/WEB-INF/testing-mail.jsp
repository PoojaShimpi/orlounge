<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.InternetAddress" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Properties" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body style="min-height: 830px;">
 <%

     final String username = "invitation@orlounge.com";
     final String password = "orlounge@123";
     final String subject = "Test";
     final String body = "Test body";
     Properties props = new Properties();
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.ssl.enable", "true");
     props.put("mail.smtp.port", "465");

     List<String> toMails = new ArrayList<String>();
     toMails.add("sonisatyam92@gmail.com");

     Session mailSession = Session.getInstance(props,
             new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                 }
             });
    mailSession.setDebug(true);
     try {

         Message message = new MimeMessage(mailSession);
         message.setFrom(new InternetAddress(username));
         for(String to : toMails){
             message.setRecipients(Message.RecipientType.TO,
                     InternetAddress.parse(to.trim()));

         }


         message.setSubject(subject.trim());
         message.setText(body.trim());

         Transport.send(message);

     } catch (MessagingException e) {
         throw new RuntimeException(e);
     }

 %>
</body>
</html>

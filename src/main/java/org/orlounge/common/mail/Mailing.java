package org.orlounge.common.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by Satyam Soni on 9/13/2015.
 */
public class Mailing {


    private static class SMTPAuthenticator extends Authenticator {
        private final String emailId;
        private final String password;

        SMTPAuthenticator(final String emailId, final String password) {
            this.emailId = emailId;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailId, password);
        }
    }



    public static void sendMailGmail(final List<String> toMails,final List<String> ccs, final List<String> bccs, final String fromMails,
                                final String subject, final String body) {

        new Thread(){
           public void run(){
               final String username = "sonisatyam92@gmail.com";
               final String password = "soni9377326813";
               final Properties props = new Properties();
               props.put("mail.smtp.user", username);
               props.put("mail.smtp.host", "smtp.gmail.com");
               props.put("mail.smtp.port", 465);
               props.put("mail.smtp.starttls.enable", "true");
               props.put("mail.smtp.auth", "true");
               props.put("mail.smtp.debug", "true");
               props.put("mail.smtp.socketFactory.port", 465);
               props.put("mail.smtp.socketFactory.fallback", "false");
               try {
                   boolean debug = true;
                   final Authenticator auth = new SMTPAuthenticator(username, password);
                   final Session session = Session.getInstance(props, auth);
                   session.setDebug(debug);
                   final MimeMessage msg = new MimeMessage(session);
                   msg.setContent(body, "text/html");
                   msg.setSubject(subject);
                   msg.setFrom(new InternetAddress(username));
                   Message message = new MimeMessage(session);
                   message.setFrom(new InternetAddress(fromMails));
                   for(String to : toMails){
                       msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                   }
                   for(String to : ccs){
                       msg.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
                   }
                   for(String to : bccs){
                       msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(to));
                   }
                   Transport.send(msg);
                   boolean emailSent = true;
               } catch (MessagingException e) {
                   throw new RuntimeException(e);
               }
           }
        }.start();

    }

    public static void sendMail(final List<String> toMails,final List<String> ccs, final List<String> bccs, final String fromMails,
                                final String subject, final String body) {

        final String username = "satyam@encalibr.com";
        final String password = "soni9377326813";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.encalibr.com");
       props.put("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.socketFactory.class",
             "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMails));
            for(String to : toMails){
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to.trim()));

            }

            for(String to : ccs){
                message.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(to.trim()));

            }

            for(String to : bccs){
                message.setRecipients(Message.RecipientType.BCC,
                        InternetAddress.parse(to.trim()));

            }
            message.setSubject(subject.trim());
            message.setText(body.trim());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}

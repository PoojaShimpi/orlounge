
package org.orlounge.common.mail;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class MailTesting {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String emailMsgTxt = "Welcome to www.javaworkspace.com";
    private static final String emailSubjectTxt = "A test mail from www.javaworkspace.com";
    private static final String emailFromAddress = "sonisatyam92@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String[] sendTo = { "satyam@encalibr.com"};


/*
public static void main(String[] args) throws MessagingException {
    new MailTesting().sendSSLMessage(MailTesting.sendTo,"mail test","message",emailFromAddress);
}*/





    /**
     * @param recipients
     * @param subject
     * @param message
     * @param from
     * @throws javax.mail.MessagingException
     */
public void sendSSLMessage(String recipients[], String subject,
            String message, String from) throws MessagingException {
        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.class", SSL_FACTORY);


        Session session = Session.getDefaultInstance(props,new MyAuthenticator());

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

    public static void testMyMail() throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.orlounge.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", 25);


        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("satyam@orlounge.com","P@ssw0rd@123");
            }
        });

        String msgBody = "test";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("satyam@orlounge.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("sonisatyam92@gmail.com", "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        testMyMail();
    }

    public static void test() throws Exception{

        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;

        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "25");
        mailServerProperties.put("mail.smtp.auth", "false");
        mailServerProperties.put("mail.smtp.starttls.enable", "false");
        //mailServerProperties.put("mail.smtp.ssl.trust", "smtp.encalibr.com");

        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sonisatyam92@gmail.com"));
        generateMailMessage.setSubject("Greetings from Crunchify..");
        String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        //transport.connect("smtp.encalibr.com", "satyam@encalibr.com", "%Uny(bJ7");
        //transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        //transport.close();
    }
}  


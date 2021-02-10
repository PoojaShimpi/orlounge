package org.orlounge.common.mail;

import lombok.Getter;
import org.orlounge.common.util.ProcessData;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Satyam Soni on 11/8/2015.
 */
@Component
public class ORMail {


    @Getter
    public static class MailConfig {
        private final String host;
        private final String port;
        private final String fromMail;
        private final String fromPass;
        private final String accessKey;
        private final String secretKey;
        private final String usern;
        private final String password;

        public MailConfig(String fromPass, String fromMail, String port, String host, String accessKey, String secretKey) {
            this.fromPass = fromPass;
            this.fromMail = fromMail;
            this.port = port;
            this.host = host;
            this.accessKey = accessKey;
            this.secretKey = secretKey;
            this.usern= "AKIAVNL3YSNA6TG5XWOG";
            this.password="BE6+g8ItHDfmsjdBWwODwnLpK+47kljMwwPn8lmlSams";
        }


    }


    public void sendMail(final MailConfig mailConfig, final String sub, final String text,
                         final List<String> tos,
                         final List<String> ccs,
                         final List<String> bccs,
                         final File attachFile, final boolean deleteFileOnExit) {
        final Properties connectionProperties = init(mailConfig.getHost(), mailConfig.getPort());
/*
        new Thread(()->{
            try {
                AmazonSimpleEmailService client = new AmazonSimpleEmailServiceClient(new BasicAWSCredentials(mailConfig.getAccessKey(), mailConfig.getSecretKey()));
                client.setRegion(Region.getRegion(Regions.US_WEST_2));

                SendEmailRequest request = new SendEmailRequest()
                        .withSource(mailConfig.getFromMail())
                        .withDestination(
                                new Destination().withToAddresses(tos).withBccAddresses(bccs).withCcAddresses(ccs)
                        )
                        .withMessage(new Message()
                                .withBody(new Body()
                                        .withHtml(new Content()
                                                .withCharset("UTF-8").withData(text)
                                        )
                                )
                                .withSubject(new Content()
                                        .withCharset("UTF-8").withData(sub))
                        );

                client.sendEmail(request);
                System.out.println("Email sent!");
            } catch (Exception ex) {
                System.out.println("The email was not sent. Error message: "
                        + ex.getMessage());
            }
        }).start();
        */


   //     new Thread(() -> {
            try {

                // Create the session
                Session session = Session.getDefaultInstance(connectionProperties,
                        new Authenticator() {    // Define the authenticator
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("invitation@orlounge.com", "orlounge@123");
                            }
                        });

                // Create and send the message
                try {
                    // Create the message
                    Message message = new MimeMessage(session);
                    // Set sender
                    message.setFrom(new InternetAddress("invitation@orlounge.com"));
                    // Set the recipients
                    List<InternetAddress> toIA = new ArrayList<InternetAddress>();
                    for (String e : tos) {
                        toIA.addAll(Arrays.asList(InternetAddress.parse(e)));
                    }
                    if (ProcessData.isValidCollection(toIA)) {
                        message.setRecipients(Message.RecipientType.TO, toIA.toArray(new InternetAddress[0]));
                    }

                    List<InternetAddress> ccIA = new ArrayList<InternetAddress>();
                    for (String e : ccs) {
                        ccIA.addAll(Arrays.asList(InternetAddress.parse(e)));
                    }
                    if (ProcessData.isValidCollection(ccIA)) {
                        message.setRecipients(Message.RecipientType.CC, ccIA.toArray(new InternetAddress[0]));
                    }

                    List<InternetAddress> bccIA = new ArrayList<InternetAddress>();
                    for (String e : bccs) {
                        bccIA.addAll(Arrays.asList(InternetAddress.parse(e)));
                    }
                    if (ProcessData.isValidCollection(bccIA)) {
                        message.setRecipients(Message.RecipientType.BCC, bccIA.toArray(new InternetAddress[0]));
                    }
                    // Set message subject
                    message.setSubject(sub);
                    message.setContent(text, "text/html");


                    if (attachFile != null) {
                        Multipart multipart = new MimeMultipart();

                        BodyPart attachement = new MimeBodyPart();
                        DataSource source = new FileDataSource(attachFile);
                        attachement.setDataHandler(new DataHandler(source));
                        attachement.setFileName(attachFile.getName());
                        multipart.addBodyPart(attachement);
                        message.setContent(multipart);

                    }
                    System.out.println("Sending message...");
                    // Send the message
                    
                    Transport.send(message);


                    System.out.println("Mail Sent !");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (deleteFileOnExit && attachFile != null && attachFile.exists()) {
                        attachFile.deleteOnExit();
                    }
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
     //   }).start();
    }

    private Properties init(final String host, final String port) {

        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.transport.protocol", "smtp");

        connectionProperties.put("mail.smtp.host", host);
        connectionProperties.put("mail.smtp.port", port);
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is StartTLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        //connectionProperties.put("mail.smtp.socketFactory.port", "465");
        // SSL Socket Factory class
        //connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)


        System.out.print("Creating the session...");
        return connectionProperties;
    }


    /**
     * Send the email via SMTP using StartTLS and SSL
     */
/*    private static void sendEmail() {

        // Create the session
        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {    // Define the authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("satyam@orlounge.com","P@ssw0rd@123");
                    }
                });

        System.out.println("done!");

        // Create and send the message
        try {
            // Create the message
            Message message = new MimeMessage(session);
            // Set sender
            message.setFrom(new InternetAddress("satyam@orlounge.com"));
            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sonisatyam92@gmail.com"));
            // Set message subject
            message.setSubject("Hello from Team ITCuties");
            // Set message text
            message.setText("Java is easy when you watch our tutorials ;)");

            System.out.print("Sending message...");
            // Send the message
            Transport.send(message);


            System.out.println("done!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/
}

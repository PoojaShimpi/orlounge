package org.orlounge.controller.mail.mail;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.BulkMailProcess;
import org.orlounge.common.AppConstants;
import org.orlounge.common.mail.ORMail;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.orlounge.factory.BusinessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.orlounge.common.AppConstants.BASE_URL;
import static org.orlounge.common.AppConstants.STATIC_CONTENT_BASE_URL;


/**
 * Created by Satyam Soni on 9/13/2015.
 */
@Controller
@AuthCheck

public class MailController  extends BaseController {

    @Autowired
    private ORMail orMail;

    public ORMail getOrMail() {
        return orMail;
    }

    public void setOrMail(ORMail orMail) {
        this.orMail = orMail;
    }



    @RequestMapping("/bulkmail.html")
    public ModelAndView bulkmail(){
        return new ModelAndView("jsp/bulkmail");
    }


    @RequestMapping("/test-mail.html")
    public ModelAndView test(){
        return new ModelAndView("testing-mail");
    }


    @RequestMapping("/invitationbulkmail.html")
    public ModelAndView invitationbulkmail(){
        return new ModelAndView("jsp/invitationbulkmail");
    }


    public class BulkProcessor  extends Thread{
        private final List<String> emails;
        private final BulkMailProcess process;
        private final BusinessFactory businessFactory;
        private final String subject;
        private final String body;
        private final String fromEmail;
        private final String fromPass;
        private static final int SLEEP_TIME = 60;
        private static final int BATCH_SIZE = 20;

        public BulkProcessor(List<String> emails, BulkMailProcess process, BusinessFactory businessFactory, String subject, String body, String fromEmail, String fromPass) {
            this.emails = emails;
            this.process = process;
            this.businessFactory = businessFactory;
            this.subject = subject;
            this.body = body;
            this.fromEmail  = fromEmail;
            this.fromPass = fromPass;
        }

        @Override
        public void run()  {
            List<String> toSend = emails;
            int total =emails.size();
            while(total > 0 ){
                Integer id = this.process.getId();
                final BulkMailProcess process = businessFactory.getUserService().getBulkProcess(id);
                if(process.getSent() >= process.getTotal() || process.getStatus().equalsIgnoreCase("KILL")){
                    break;
                }

                if(total > BATCH_SIZE){
                   sendMail(toSend.subList(0,BATCH_SIZE-1), subject, body);
                    process.setSent(process.getSent()+BATCH_SIZE);
                    process.setLastSent(new Date());
                    businessFactory.getUserService().saveBulkProcess(process);
                    toSend = toSend.subList(BATCH_SIZE, toSend.size() - 1);
                    total = total - BATCH_SIZE;

                    try {
                        currentThread().sleep(SLEEP_TIME * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    sendMail(toSend, subject, body);
                    process.setSent(process.getSent()+total);
                    process.setLastSent(new Date());
                    process.setStatus("COMPLETED");
                    businessFactory.getUserService().saveBulkProcess(process);
                    total = 0;
                }
            }
        }

        public void sendMail(List<String> emails, String subject, String body){
            try{
                for(String email : emails){
                    businessFactory.getMailBusiness().sendMail(email, null, null, subject, body, null, fromEmail,fromPass);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


    @RequestMapping(value = "/mail/sendInvitationBulkMail", method = RequestMethod.POST)
    public ModelAndView sendInvitationBulkMail(@RequestParam(value = "emailIds") String emailIds,
                                               @RequestParam(value = "msgType")String msgType,
                                               HttpSession session
    ) {
        try{
            ModelAndView model = new ModelAndView("redirect:/invitationbulkmail.html");
            Map map = new HashMap();
            if(emailIds != null && !emailIds.trim().isEmpty()){
                final List<String> emails = Arrays.asList(emailIds.trim().split(","));
                final BulkMailProcess process = new BulkMailProcess();
                process.setBulkType("INVITATION");
                process.setMessageType(msgType);
                process.setCreated(new Date());
                process.setLastSent(new Date());
                process.setTotal(emails.size());
                process.setSent(0);
                process.setStatus("SENDING");
                getBusinessFactory().getUserService().saveBulkProcess(process);
                MessageType msgTypeEnum = MessageType.valueOf(msgType);
                String body = msgTypeEnum.content;
                String subject = msgTypeEnum.subject;
                String inviteeMail = getBusinessFactory().getMailBusiness().getConfigMap().get(AppConstants.FROM_MAIL);
                String inviteePass = getBusinessFactory().getMailBusiness().getConfigMap().get(AppConstants.FROM_PASS);

                try{
                    Thread run = new BulkProcessor(emails, process, getBusinessFactory(), subject, body, inviteeMail, inviteePass);
                    run.start();
                    MessageUtils.success(session, "Mail requested.");

                }catch (Exception ex){
                    MessageUtils.failure(session, AppConstants.GLOBAL_FAILURE_MSG);
                }


            }

            return model;

        } catch ( Exception ex){
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping(value = "/mail/getTemplate/{msgType}", method = RequestMethod.GET)
    public @ResponseBody
    String getTemplate(@PathVariable(value = "msgType") String msgType,
                       HttpSession session
    ){
        try {
            MessageType msgTypeEnum = MessageType.valueOf(msgType);
            return msgTypeEnum.content;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping(value = "/mail/sendBulkMail", method = RequestMethod.POST)
    public ModelAndView sendBulkMail(@RequestParam(value = "to",required = false) String to,
                                     @RequestParam(value = "bcc",required = false)String bcc,
                                     @RequestParam(value = "cc",required = false) String cc,
                                     @RequestParam(value = "subject", required = false) String subject,
                                     @RequestParam(value = "body", required = false) String body,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     HttpSession session
    ) {
        try{
            ModelAndView model = new ModelAndView("redirect:/bulkmail.html");
            Map map = new HashMap();
            try{
                getBusinessFactory().getMailBusiness().sendMail(to, bcc, cc, subject, body, file);
                MessageUtils.success(session, "Mail requested.");


            }catch (Exception ex){
                MessageUtils.failure(session, AppConstants.GLOBAL_FAILURE_MSG);
            }
            return model;

        } catch ( Exception ex){
            throw new ORException(ex, 0);
        }
    }


    public  enum MessageType {
        INVITATION_NORMAL(NORMAL_INVITATION, "A free private operating room lounge site for your hospital"),
        INVITATION_NORMAL_WITH_PASSWORD(NORMAL_INVITATION_WITH_PASSWORD, "A free private operating room lounge site for your hospital"),
        INVITATION_DECEPTIVE(DECEPTIVE_INVITATION,"A free private operating room lounge site for your hospital"),
        ADVERTISER_NORMAL(NORMAL_AD_INVITATION, "Online Social Marketing in the operating room lounge");
        private String content;
        private String subject;


        MessageType(String content, String subject) {
            this.content = content; this.subject = subject;
        }


        public String getContent() {
            return content;
        }

        public String getSubject() {
            return subject;
        }
    }


    public static String NORMAL_INVITATION = "<style>\n" +
            "    .contact-us{\n" +
            "        text-decoration: none;\n" +
            "    }\n" +
            "    .contact-us:hover{\n" +
            "        text-decoration: underline;\n" +
            "    }\n" +
            "</style>\n" +
            "<div class=\"\" style=\"color:#333333;font-family:Arial,sans-serif;font-size:14px;line-height:1.429\">\n" +
            "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse:collapse;background-color:#f5f5f5;border-collapse:collapse\" bgcolor=\"#f5f5f5\">\n" +
            "\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:10px 20px\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Invitation for\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>ORLOUNGE</strong>\n" +
            "           </a>\n" +
            "       </td>    </tr>\n" +
            "    </tbody>\n" +
            "</table> \n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                    <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"background-color:#f5f5f5\" height=\"10\" width=\"100%\"  >\n" +
            "\n" +
            "                            <a href=\""+ BASE_URL+"\" class=\"logo pull-left\">\n" +
            "                                <img src=\""+ AppConstants.STATIC_CONTENT_BASE_URL+"/other/email_banner_bar.png\" height=\"150\" width=\"100%\" alt=\"OR Lounge\" />\n" +
            "                            </a>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                     <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                        <strong>www.orlounge.com </strong>\n" +
            "                                    </a>\n" +
            "                                        is a new collaborative social site for the operating room community.<br/>\n" +
            "                                        The members are anaesthesiologists, nurses, residents, surgeons and surgical\n" +
            "                                        assistants.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        Visit\n" +
            "                                        <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                            <strong>www.orlounge.com </strong>\n" +
            "                                        </a>\n" +
            "                                        today to create a free private site for your hospital's\n" +
            "                                        operating room.<br/>\n" +
            "                                        See how a professional social site with communication, productivity and management tools\n" +
            "                                        can build a community that works together, enhance your professional life and improve operating room efficiency.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        <span style=\"color:#3b73af;color:#3b73af;text-decoration:none\" >\n" +
            "                                            <strong>The site is now ready for beta testing.</strong>\n" +
            "                                        </span>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">Feel free to forward this to a friend who works in the operating room.</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp;</td>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Best wishes,\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Dr. Anthony P. Cheng MD FRACS\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Diplomate American Board of Otolaryngology Head and Neck Surgery\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Founder and CEO\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/anthony.png\" />\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table> \n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:12px 20px;\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td width=\"100%\" style=\"padding:0;border-collapse:collapse;color:#999999;font-size:12px;line-height:18px;font-family:Arial,sans-serif\">\n" +
            "            <a href=\"mailto:contact@orlounge.com\"\n" +
            "               class=\"contact-us\"\n" +
            "               style=\"color: #707070;;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                <strong>Contact Us</strong>\n" +
            "            </a>\n" +
            "            <br/>\n" +
            "\n" +
            "            <span >\n" +
            "\n" +
            "                                <a href=\"https://orlounge.com\"\n" +
            "                                   class=\"contact-us\"\n" +
            "                                   style=\"color:#999;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                               www.orlounge.com\n" +
            "                            </a>\n" +
            "                &copy; 2017\n" +
            "                            <br/>\n" +
            "\n" +
            "                            4412 Wilson Avenue, Fresno<br/>\n" +
            "                            CA 93704. USA\n" +
            "                          </span>\n" +
            "        </td>\n" +
            "        <td valign=\"top\" style=\"padding:0;border-collapse:collapse;padding-left:20px;vertical-align:top\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;padding-top:3px\">\n" +
            "                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email-logo.jpg\" alt=\"ORLounge logo\" title=\"ORLounge logo\" width=\"90\" height=\"70\" > </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table><div ></div><div >\n" +
            "</div></div>";





    public static String NORMAL_INVITATION_WITH_PASSWORD = "<style>\n" +
            "    .contact-us{\n" +
            "        text-decoration: none;\n" +
            "    }\n" +
            "    .contact-us:hover{\n" +
            "        text-decoration: underline;\n" +
            "    }\n" +
            "</style>\n" +
            "<div class=\"\" style=\"color:#333333;font-family:Arial,sans-serif;font-size:14px;line-height:1.429\">\n" +
            "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse:collapse;background-color:#f5f5f5;border-collapse:collapse\" bgcolor=\"#f5f5f5\">\n" +
            "\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:10px 20px\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Invitation for\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>ORLOUNGE</strong>\n" +
            "           </a>\n" +
            "       </td>    </tr>\n" +
            "    </tbody>\n" +
            "</table> \n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                    <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"background-color:#f5f5f5\" height=\"10\" width=\"100%\"  >\n" +
            "\n" +
            "                            <a href=\""+BASE_URL+"\" class=\"logo pull-left\">\n" +
            "                                <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email_banner_bar.png\" height=\"150\" width=\"100%\" alt=\"OR Lounge\" />\n" +
            "                            </a>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                     <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                        <strong>www.orlounge.com </strong>\n" +
            "                                    </a>\n" +
            "                                        is a new collaborative social site for the operating room community.<br/>\n" +
            "                                        The members are anaesthesiologists, nurses, residents, surgeons and surgical\n" +
            "                                        assistants.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        Visit\n" +
            "                                        <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                            <strong>www.orlounge.com </strong>\n" +
            "                                        </a>\n" +
            "                                        today to create a free private site for your hospital's\n" +
            "                                        operating room.<br/>\n" +
            "                                        See how a professional social site with communication, productivity and management tools\n" +
            "                                        can build a community that works together, enhance your professional life and improve operating room efficiency.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        <span style=\"color:#3b73af;color:#3b73af;text-decoration:none\" >\n" +
            "                                            <strong>The site is now ready for beta testing.</strong>\n" +
            "                                        </span>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">Feel free to forward this to a friend who works in the operating room.</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "\n" +"                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        <span style=\"color:#000000;color:#3b73af;text-decoration:none\" >\n" +
            "                                            <i>To see a demo site, login in using your role (anesthesiologist, nurse, ormanager, resident or surgeon) as username, and \"demo\" as your password.</i>\n" +
            "                                        </span>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +

            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp;</td>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Best wishes,\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Dr. Anthony P. Cheng MD FRACS\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Diplomate American Board of Otolaryngology Head and Neck Surgery\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Founder and CEO\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/anthony.png\" />\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table> \n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:12px 20px;\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td width=\"100%\" style=\"padding:0;border-collapse:collapse;color:#999999;font-size:12px;line-height:18px;font-family:Arial,sans-serif\">\n" +
            "            <a href=\"mailto:contact@orlounge.com\"\n" +
            "               class=\"contact-us\"\n" +
            "               style=\"color: #707070;;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                <strong>Contact Us</strong>\n" +
            "            </a>\n" +
            "            <br/>\n" +
            "\n" +
            "            <span >\n" +
            "\n" +
            "                                <a href=\"https://orlounge.com\"\n" +
            "                                   class=\"contact-us\"\n" +
            "                                   style=\"color:#999;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                               www.orlounge.com\n" +
            "                            </a>\n" +
            "                &copy; 2017\n" +
            "                            <br/>\n" +
            "\n" +
            "                            4412 Wilson Avenue, Fresno<br/>\n" +
            "                            CA 93704. USA\n" +
            "                          </span>\n" +
            "        </td>\n" +
            "        <td valign=\"top\" style=\"padding:0;border-collapse:collapse;padding-left:20px;vertical-align:top\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;padding-top:3px\">\n" +
            "                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email-logo.jpg\" alt=\"ORLounge logo\" title=\"ORLounge logo\" width=\"90\" height=\"70\" > </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table><div ></div><div >\n" +
            "</div></div>";




    public static String NORMAL_AD_INVITATION = "<style>\n" +
            "    .contact-us {\n" +
            "        text-decoration: none;\n" +
            "    }\n" +
            "\n" +
            "    .contact-us:hover {\n" +
            "        text-decoration: underline;\n" +
            "    }\n" +
            "</style>\n" +
            "<div class=\"\" style=\"color:#333333;font-family:Arial,sans-serif;font-size:14px;line-height:1.429\">\n" +
            "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
            "           style=\"border-collapse:collapse;background-color:#f5f5f5;border-collapse:collapse\" bgcolor=\"#f5f5f5\">\n" +
            "\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:10px 20px\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                    <tbody>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td valign=\"middle\"\n" +
            "                            style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                            Invitation for\n" +
            "                            <a rel=\"achengorl\" href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\"\n" +
            "                               target=\"_blank\">\n" +
            "                                <strong>ORLounge</strong>\n" +
            "                            </a>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                       style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"background-color:#f5f5f5\" height=\"10\" width=\"100%\">\n" +
            "\n" +
            "                            <a href=\"https://www.advertiser.orlounge.com\" class=\"logo pull-left\">\n" +
            "                                <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email_adsite_banner_bar.png\" height=\"150\"\n" +
            "                                     width=\"100%\" alt=\"OR Lounge\"/>\n" +
            "                            </a>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\"\n" +
            "                            bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                                   style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;padding:10px 20px;border-left:1px solid lightgrey;border-right:1px solid lightgrey\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td valign=\"middle\"\n" +
            "                                        style=\"padding:0 0 0 0;border-collapse:collapse; align-items:center;text-align:center;vertical-align:middle;font-family:Arial,sans-serif;font-size:18px;line-height:24px\">\n" +
            "                                        <a rel=\"achengorl\" href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\"\n" +
            "                                           target=\"_blank\">\n" +
            "                                            <strong>Announcing a new professional social site for the operating room</strong>\n" +
            "                                        </a>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\"\n" +
            "                            bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        <a rel=\"achengorl\" href=\""+BASE_URL+"\"\n" +
            "                                           style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\">\n" +
            "                                            <strong>www.orlounge.com </strong>\n" +
            "                                        </a>\n" +
            "                                        is a new professional site for the Operating Room community.<br/>\n" +
            "                                        The members are anaesthesiologists, nurses, residents and surgeons, and surgical\n" +
            "                                        assistants. Hospitals create private operating room \"Lounges\" to work together.\n" +
            "                                        Each Lounge is a collaborative site with communication, productivity and\n" +
            "                                        management tools.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\"\n" +
            "                            bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "\n" +
            "                                        The site is free for any hospital to use.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\"\n" +
            "                            bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        Visit our site at\n" +
            "                                        <a rel=\"achengorl\" href=\"https://www.advertiser.orlounge.com\"\n" +
            "                                           style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\">\n" +
            "                                            <strong>www.advertiser.orlounge.com </strong>\n" +
            "                                        </a>\n" +
            "                                        today to see what professional social marketing can do for your company. Check out a"+
            "                                        new advertising paradigm which is non-interruptive, socially engaging and conversational.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top;border-left:1px solid lightgrey;border-right:1px solid lightgrey\">\n" +
            "                            &nbsp;</td>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                            &nbsp; </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\"\n" +
            "                            bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                                   style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Dr. Anthony P. Cheng MD FRACS\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Diplomate American Board of Otolaryngology Head and Neck Surgery\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Founder and CEO\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/anthony.png\"/>\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\"\n" +
            "                            height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:12px 20px;\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                    <tbody>\n" +
            "                    <tr>\n" +
            "                        <td width=\"100%\"\n" +
            "                            style=\"padding:0;border-collapse:collapse;color:#999999;font-size:12px;line-height:18px;font-family:Arial,sans-serif\">\n" +
            "                            <a href=\"mailto:contact@orlounge.com\"\n" +
            "                               class=\"contact-us\"\n" +
            "                               style=\"color: #707070;;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                <strong>Contact Us</strong>\n" +
            "                            </a>\n" +
            "                            <br/>\n" +
            "\n" +
            "                            <span>\n" +
            "\n" +
            "                                <a href=\"https://advertiser.orlounge.com\"\n" +
            "                                   class=\"contact-us\"\n" +
            "                                   style=\"color:#999;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                               www.advertiser.orlounge.com\n" +
            "                            </a>\n" +
            "                                &copy; 2017\n" +
            "                            <br/>\n" +
            "\n" +
            "                            4412 Wilson Avenue, Fresno<br/>\n" +
            "                            CA 93704. USA\n" +
            "                          </span>\n" +
            "                        </td>\n" +
            "                        <td valign=\"top\"\n" +
            "                            style=\"padding:0;border-collapse:collapse;padding-left:20px;vertical-align:top\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;padding-top:3px\">\n" +
            "                                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email-logo.jpg\" alt=\"ORLounge logo\"\n" +
            "                                             title=\"ORLounge logo\" width=\"90\" height=\"70\"></td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "    <div></div>\n" +
            "    <div>\n" +
            "    </div>\n" +
            "</div>";




    public static final String DECEPTIVE_INVITATION= "<style>\n" +
            "    .contact-us{\n" +
            "        text-decoration: none;\n" +
            "    }\n" +
            "    .contact-us:hover{\n" +
            "        text-decoration: underline;\n" +
            "    }\n" +
            "</style>\n" +
            "<div class=\"\" style=\"color:#333333;font-family:Arial,sans-serif;font-size:14px;line-height:1.429\">\n" +
            "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse:collapse;background-color:#f5f5f5;border-collapse:collapse\" bgcolor=\"#f5f5f5\">\n" +
            "\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:10px 20px\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Invitation for\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>ORLOUNGE</strong>\n" +
            "           </a>\n" +
            "       </td>    </tr>\n" +
            "    </tbody>\n" +
            "</table> \n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                    <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
            "\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"background-color:#f5f5f5\" height=\"10\" width=\"100%\"  >\n" +
            "\n" +
            "                            <a href=\""+BASE_URL+"\" class=\"logo pull-left\">\n" +
            "                                <img src=\""+STATIC_CONTENT_BASE_URL+"/other/email_banner_bar.png\" height=\"150\" width=\"100%\" alt=\"OR Lounge\" />\n" +
            "                            </a>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr >\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:bolder 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top;color:darkred; border-color: darkred;border: 2px solid\">\n" +
            "\n" +
            "                                        If you tried to register on July 4 and saw a standard warning notice about our site, please ignore it. Google and Firefox (using Google's information) mistakenly posted the notice.<br/>\n" +
            "                                        We have contacted Google and the notice has been removed. Google takes this precaution for many new websites.<br/>\n" +
            "                                        Rest be assured that <a href=\""+BASE_URL+"\">www.orlounge.com</a> is safe. On the contrary, we have implemented very strict security and privacy measures to prevent ineligible users from using the site.<br/>\n" +
            "                                        Contact me at\n" +
            "                                        <a href=\"mailto:acheng@orlounge.com\"\n" +
            "                                           class=\"contact-us\"\n" +
            "                                           style=\"text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        acheng@orlounge.com\n" +
            "                                            </a> if you need more information\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                     <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                        <strong>www.orlounge.com </strong>\n" +
            "                                    </a>\n" +
            "                                        is a new collaborative social site for the operating room community.<br/>\n" +
            "                                        The members are anaesthesiologists, nurses, residents, surgeons and surgical\n" +
            "                                        assistants.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        Visit\n" +
            "                                        <a rel=\"achengorl\"  href=\""+BASE_URL+"\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                            <strong>www.orlounge.com </strong>\n" +
            "                                        </a>\n" +
            "                                        today to create a free private site for your hospital's\n" +
            "                                        operating room.<br/>\n" +
            "                                        See how a professional social site with communication, productivity and management tools\n" +
            "                                        can build a community that works together, enhance your professional life and improve operating room efficiency.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "                            <table style=\"border-collapse:collapse\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "\n" +
            "                                        <span style=\"color:#3b73af;color:#3b73af;text-decoration:none\" >\n" +
            "                                            <strong>The site is now ready for beta testing.</strong>\n" +
            "                                        </span>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">Feel free to forward this to a friend who works in the operating room.</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp;</td>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Best wishes,\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Dr. Anthony P. Cheng MD FRACS\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Diplomate American Board of Otolaryngology Head and Neck Surgery\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        Founder and CEO\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                                        <img src=\""+STATIC_CONTENT_BASE_URL+"/other/anthony.png\" />\n" +
            "                                    </th>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
            "\n" +
            "                    <tr>\n" +
            "                        <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table> \n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:12px 20px;\">\n" +
            "                <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td width=\"100%\" style=\"padding:0;border-collapse:collapse;color:#999999;font-size:12px;line-height:18px;font-family:Arial,sans-serif\">\n" +
            "            <a href=\"mailto:contact@orlounge.com\"\n" +
            "               class=\"contact-us\"\n" +
            "               style=\"color: #707070;;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                <strong>Contact Us</strong>\n" +
            "            </a>\n" +
            "            <br/>\n" +
            "\n" +
            "            <span >\n" +
            "\n" +
            "                                <a href=\"https://orlounge.com\"\n" +
            "                                   class=\"contact-us\"\n" +
            "                                   style=\"color:#999;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                               www.orlounge.com\n" +
            "                            </a>\n" +
            "                &copy; 2017\n" +
            "                            <br/>\n" +
            "\n" +
            "                            4412 Wilson Avenue, Fresno<br/>\n" +
            "                            CA 93704. USA\n" +
            "                          </span>\n" +
            "        </td>\n" +
            "        <td valign=\"top\" style=\"padding:0;border-collapse:collapse;padding-left:20px;vertical-align:top\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;padding-top:3px\">\n" +
            "                        <img src=\""+STATIC_CONTENT_BASE_URL+"other/email-logo.jpg\" alt=\"ORLounge logo\" title=\"ORLounge logo\" width=\"90\" height=\"70\" > </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table><div ></div><div >\n" +
            "</div></div>";



}

class Main{
    public static void main(String[] args) throws IOException {
        File f = new File("D:\\ORLOUNGE\\orloungeweb\\web\\src\\main\\resources\\email\\INVITATION_AD_MAIL.html");
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        fw.write(MailController.NORMAL_AD_INVITATION);
        fw.close();
    }
}

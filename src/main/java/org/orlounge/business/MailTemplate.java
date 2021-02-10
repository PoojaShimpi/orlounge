package org.orlounge.business;

import org.orlounge.common.AppConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by satyam on 4/23/2017.
 */
public class MailTemplate {private String subject;



    private static final String REGISTER_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "        <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "            Welcome to\n" +
            "            <a rel=\"achengorl\"  href=\"orlouge.com\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                <strong>ORLounge</strong>\n" +
            "            </a>\n" +
            "\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String APPROVED_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "        <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "            Your request has been \n" +
            "            <a rel=\"achengorl\"  href=\"orlouge.com\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                <strong>Approved</strong>\n" +
            "            </a>\n" +
            "\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String APPROVER_NOTIFICATION_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "        <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "            Your have received ORLS joining request \n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String APPROVER__OR_TEAM_NOTIFICATION_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "        <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "            Your have received ORLS create request \n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";
    private static final String VERIFICATION_NOTIFICATION_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "        <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "            Your have received {hospitalName} ORLS create member verification response \n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String INVITATION_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Invitation letter from\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>{senderName}</strong>\n" +
            "           </a>\n" +
            "       </td>"+
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String PASSWORD_RECOVERY_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Password Recovery for\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>ORLounge</strong> account\n" +
            "           </a>\n" +
            "       </td>"+
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";
    private static final String ESPS_REMINDER_SUBJECT_TEMPLATE = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "\n" +
            "       <td valign=\"middle\" style=\"padding:0;border-collapse:collapse;vertical-align:middle;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "           Expiry Reminder for ESPS Services on\n" +
            "           <a rel=\"achengorl\"  href=\"#\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "              <strong>ORLounge</strong> account\n" +
            "           </a>\n" +
            "       </td>"+
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";

    private static final String MAIN_TEMPLATE = "<style>\n" +
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
            "                {{subject}} \n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                {{content}} \n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding:0;border-collapse:collapse;padding:12px 20px;\">\n" +
            "                {{disclaimer}}\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table><div ></div><div >\n" +
            "</div></div>";



    private static final String APPROVAL_OF_TEMP_LSA_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Your request to create an operating room lounge site for\n" +
            "                        <strong>{hospitalName}</strong> is approved.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                                style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #eee;\">\n" +
            "                            <tbody style=\"background: #eee;\">\n" +
            "                            <tr>\n" +
            "\n" +
            "                                <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#eee;padding-bottom:5px\" bgcolor=\"#eee\">\n" +
            "                                    <table style=\"border-collapse:collapse\">\n" +
            "                                        <tbody>\n" +
            "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                        </tr>\n" +
            "                                       <tr>"+
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
                    "                                                <p>\n" +
                    "                                                    Every Lounge site needs a <strong>Local Site Administrator </strong> and here's why:<br/>The LSA\n" +
                    "                                                <ul>\n" +
                    "                                                    <li>\n" +
                    "                                                        can approve, or reject all requests for membership, and even expel a member, because the LSA knows the situation best.\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        manages contents by deleting offensive, inappropriate or illegal contents  or HIPPA contents\n" +
                    "                                                    </li>\n" +
                    "                                                    can work with the hospital network administrator to limit site access\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        can appoint an assistant\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        can invite other OR members to join the site. post official notices and other documents so that everyone knows what's going on\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        A LSA increases security, ensures privacy and maximizes the benefits  of the site\n" +
                    "                                                    </li>\n" +
                    "                                                </ul>\n" +
                    "\n" +
                    "                                                </p>\n" +
                    "                                            </td>\n" +
                    "                                 </tr>\n" +
                    "\n" +
            "\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                                You are the <strong>Temporary Local Site Administrator(LSA)</strong>.\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                   <tr>"+
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
           "                                                who will approve all subsequent members who wants to join the site.\n"+
           "                                                See the FAQ to further details of what you can do.\n" +
           "                                            </td>\n" +
           "                                        </tr>"+
            "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                        </tr>\n" +
                                                        "<tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                        </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "\n" +
            "\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;\n" +
            "                                                background-color:#eee;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;\n" +
            "                                                border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "                        </table>\n" +
            "                   \n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Please login in at\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">Best wishes,</th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">The OR Lounge Team</th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";




    private static final String APPROVAL_OF_ORM_LSA_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                   <tr>"+
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
             "                        Your request to create an operating room lounge site for\n" +
             "                        <strong>{hospitalName}</strong> is approved.\n" +
             "                    </td>\n" +
             "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                                style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #eee;\">\n" +
            "                            <tbody style=\"background: #eee;\">\n" +
            "                            <tr>\n" +
            "\n" +
            "                                <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#eee;padding-bottom:5px\" bgcolor=\"#eee\">\n" +
            "                                    <table style=\"border-collapse:collapse\">\n" +
            "                                        <tbody>\n" +
            "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                        </tr>\n" +
            "                                        <tr>"+
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
                    "                                                <p>\n" +
                    "                                                    Every Lounge site needs a <strong>Local Site Administrator </strong> and here's why:<br/>The LSA\n" +
                    "                                                <ul>\n" +
                    "                                                    <li>\n" +
                    "                                                        can approve, or reject all requests for membership, and even expel a member, because the LSA knows the situation best.\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        manages contents by deleting offensive, inappropriate or illegal contents  or HIPPA contents\n" +
                    "                                                    </li>\n" +
                    "                                                    can work with the hospital network administrator to limit site access\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        can appoint an assistant\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        can invite other OR members to join the site. post official notices and other documents so that everyone knows what's going on\n" +
                    "                                                    </li>\n" +
                    "                                                    <li>\n" +
                    "                                                        A LSA increases security, ensures privacy and maximizes the benefits  of the site\n" +
                    "                                                    </li>\n" +
                    "                                                </ul>\n" +
                    "\n" +
                    "                                                </p>\n" +
                    "                                            </td>\n" +
                    "                                 </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                                You are the <strong>Official Local Site Administrator(LSA)</strong>.\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                   <tr>"+
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                                who will approve all subsequent members who wants to join the site.\n"+
            "                                                See the FAQ to further details of what you can do.\n" +
            "                                            </td>\n" +
            "                                        </tr>"+
                    "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "\n" +
            "                                            </th>\n" +
            "                                        </tr>\n" +
            "\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "\n" +
            "\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;\n" +
            "                                                background-color:#eee;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;\n" +
            "                                                border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "                        </table>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Please login in at\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        Best wishes,\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        The OR Lounge Team\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        {hospitalName}\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";






    private static final String APPROVAL_OF_MEMEBER_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Your request to join an operating room lounge site for\n" +
            "                        <strong>{hospitalName}</strong> is approved.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Please login in at\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        After login, please enter as much information as possible to the Staff directory to help the OR Manager "+
            "                    </td>\n" +
            "                </tr>\n" +
            "\n" +
            "\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        Congratulations and enjoy your membership,\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        {lsaName}<br/>\n" +
            "                        Local Site Manager\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        {hospitalName}\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";



    private static final String APPROVAL_OF_MEMEBER_ORM_BUT_NOT_LSA_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Your request to join an operating room lounge site for\n" +
            "                        <strong>{hospitalName}</strong> is approved.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                </tr>\n" +
            "\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                                style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #eee;\">\n" +
            "                            <tbody style=\"background: #eee;\">\n" +
            "                            <tr>\n" +
            "\n" +
            "                                <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#eee;padding-bottom:5px\" bgcolor=\"#eee\">\n" +
            "                                    <table style=\"border-collapse:collapse\">\n" +
            "                                        <tbody>\n" +
            "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                        </tr>\n" +
            "\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                                Since you are the OR Manager, you will replace {lsaName} to become <strong>Local Site Administrator</strong>.<br/>\n" +
            "                                                To do this, {lsaName} needs to go to the Manage Member menu, select your name " +
            "                                               and check ( <img src=\""+ AppConstants.STATIC_CONTENT_BASE_URL +"/other/gear.png\">) icon.<br/>" +
            "                                               This step will make you the <strong>Official  Local Site Administrator</strong>."+
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "\n" +
            "                                            </th>\n" +
            "                                        </tr>\n" +
            "\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "\n" +
            "\n" +
            "                            <tr>\n" +
            "                                <td style=\"border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;\n" +
            "                                                background-color:#eee;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;\n" +
            "                                                border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#eee\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "\n" +
            "                        </table>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Please login in at\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a> and Read the FAQ for further details of what you can do.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "\n" +
            "                </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        Congratulations and enjoy your membership,\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        {lsaName}<br/>\n" +
            "                        Local Site Manager\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        {hospitalName}\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";



    private static final String REGISTERATION_LSA_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Hello {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Thank you for registering to create an operating room lounge site for\n" +
            "                        <strong>{hospitalName}<strong>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        We take security seriously for anyone who wants to create an operating room lounge site.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        We need to verify that you are a working member of the <strong>{hospitalName}</strong> operating room before we approve your registration.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <font style=\"text-decoration: underline;\"><strong>What you must do next ?</strong></font>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        You must forward this  email to any working member of the  <strong>{hospitalName}</strong> operating room " +
            "                        who can verify your status by clicking <a href=\"orlounge.com/verification/{hospitalName}\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                        <strong>Verification Link</strong></a> or " +
            "                        <a href=\"orlounge.com/verification/{hospitalName}\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com/verification/{hospitalName}</strong></a> .\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">Best wishes,</th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">The OR Lounge Team</th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n";


    private static final String REGISTERATION_LSA_ORM_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Thank you for registering to create an operating room lounge site for\n" +
            "                        <strong>{hospitalName}<strong>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Once your information has been verified by the hospital security office, we will notify you and approve your request.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Once approved, you will automatically be able to access\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        You can expedite the verification process by informing the security office of the hospital to expect a request from us." +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">Best wishes,</th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">The OR Lounge Team</th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";


    private static final String REGISTERATION_MEMBER_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Hi {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Thank you for requesting to join <strong>{hospitalName}</strong> operating room lounge site." +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        When approved, the Local Site Administrator will notify you and you will automatically be able to access\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">Best wishes,</th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">The OR Lounge Team</th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";





    private static final String REGISTERATION_MEMBER_ORM_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Congratulations {userName},\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Thank you for your request to join <strong>{hospitalName}</strong> operating room lounge site." +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        For site administrative purpose, the member who creates the Lounge is automatically the <strong>Temporary Local Site Administrator</strong>." +
            "                       Since you are the OR Manager, that role must be transferred to you and you may transfer to anyone who may be eligible. " +
            "                        The  <strong>Local Site Administrator</strong> becomes the <strong>Official Site Administrator</strong>." +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        When approved, the Local Site Administrator will notify you and you will automatically be able to access\n" +
            "                        <a href=\"orlounge.com\" target=\"_blank\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\">\n" +
            "                            <strong>orlounge.com</strong></a>.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">Best wishes,</th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">The OR Lounge Team</th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";






    private static final String IMAGE_URL = AppConstants.STATIC_CONTENT_BASE_URL+"/other/email-logo.jpg";

    private static final String DISCLAIMER = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td width=\"100%\" style=\"padding:0;border-collapse:collapse;color:#999999;font-size:12px;line-height:18px;font-family:Arial,sans-serif\">\n" +
            "            <a href=\"orlounge.com\"\n" +
            "               class=\"contact-us\"\n" +
            "               style=\"color: #707070;;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                <strong>Contact Us</strong>\n" +
            "            </a>\n" +
            "            <br/>\n" +
            "\n" +
            "            <span >\n" +
            "\n" +
            "                                <a href=\"orlounge.com\"\n" +
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
            "                        <img src=\""+IMAGE_URL+"\" alt=\"ORLounge logo\" title=\"ORLounge logo\" width=\"90\" height=\"70\" > </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" ;


    private static final String APPROVER_NOTIFICATION_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Registration Notification,\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <strong>{userName}</strong> has requested to join <strong>{hospitalName}</strong>  operating room lounge site.\n" +
            "\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        Please approve based on your personal knowledge of the member's status." +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                         To approve, go to Manage Members, select member's name and check the ( <img src=\""+AppConstants.STATIC_CONTENT_BASE_URL+"/other/tick.png\">) icon."+
            "\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                       Best wishes,\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        The OR Lounge Team\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n";


    private static final String APPROVER_NOTIFICATION_OR_TEAM_BODY = "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "    <tbody style=\"background: #fff;\">\n" +
            "    <tr>\n" +
            "\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;\n" +
            "                        border-left:1px solid #cccccc;border-top:1px solid #cccccc;\n" +
            "                        border-right:1px solid #cccccc;border-bottom:0;border-top-right-radius:5px;border-top-left-radius:5px;\n" +
            "                        height:10px;line-height:10px;\" height=\"10\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Registration Notification,\n" +
            "                                        </span>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff;padding-top:10px;padding-bottom:5px\" bgcolor=\"#ffffff\">\n" +
            "            <table style=\"border-collapse:collapse\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                        <strong>{userName}</strong> has registered to create <strong>{hospitalName}</strong>  operating room lounge site.\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:0;border-bottom:0;padding:0 15px 0 16px;background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;font-family:Arial,sans-serif;font-size:14px;line-height:20px\">\n" +
            "                <tbody>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                       Best wishes,\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\">\n" +
            "                        The OR Lounge Team\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                </tbody>\n" +
            "            </table> </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr>\n" +
            "        <td style=\"padding:0;border-collapse:collapse;color:#ffffff;padding:0 15px 0 16px;height:5px;line-height:5px;background-color:#ffffff;border-top:0;border-left:1px solid #cccccc;border-bottom:1px solid #cccccc;border-right:1px solid #cccccc;border-bottom-right-radius:5px;border-bottom-left-radius:5px\" height=\"5\" bgcolor=\"#ffffff\">&nbsp;</td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n";


    private static final String VERIFICATION_RESPONSE_MAIL_BODY = "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse:collapse;background-color:#f5f5f5;border-collapse:collapse\" bgcolor=\"#f5f5f5\">\n" +
            "\n" +
            "        <tbody>\n" +
            "        <tr>\n" +
            "            <td style=\"border-collapse:collapse;padding:0 20px\">\n" +
            "\n" +
            "\n" +
            "                <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
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
            "                                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Dear {userName},\n" +
            "                                        </span>\n" +
            "                                    </td>\n" +
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
            "                                        You have received verification Response for <strong>{hospitalName}</strong> ORLS create request.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                        <strong>Approver Details</strong>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                        <strong>Name:</strong> {name} <br/>\n" +
            "                                        <strong>Role: </strong> {role} <br/>\n" +
            "                                        <strong>Email: </strong> {email} <br/>\n" +
            "                                        <strong>Phone Number: </strong> {phNo} <br/>\n" +
            "                                        <strong>Action: </strong> <strong>{action}</strong> <br/>\n" +
            "                                    </td>\n" +
            "\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
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
            "                                        {senderName}\n" +
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
            "                </table>\n" +
            "\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table><div ></div><div >";


    private static final String INVITATION_BODY ="                <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
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
            "                                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Dear friend,\n" +
            "                                        </span>\n" +
            "                                    </td>\n" +
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
            "                                       Check out a new site for the operating room - " +
            "\n" +
            "                                        <a rel=\"achengorl\"  href=\"www.orlounge.com\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                            <strong>OR Lounge</strong>\n" +
            "                                        </a>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">\n" +
            "                                       {body}" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <th style=\"color:#707070;font:normal 14px/20px Arial,sans-serif;text-align:left;vertical-align:top;padding:2px 0\"></th>\n" +
            "                                </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
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
            "                                        {senderName}\n" +
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
            "                </table>";

 private static final String PASSWORD_RECVOERY_BODY ="                <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
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
            "                                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Dear member,\n" +
            "                                        </span>\n" +
            "                                    </td>\n" +
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
            "                                       Your password for your orlounge account is  - " +
            "\n" +
            "                                        <a rel=\"achengorl\"  href=\"www.orlounge.com\" style=\"color:#3b73af;color:#3b73af;text-decoration:none\" target=\"_blank\" >\n" +
            "                                            <strong>{{password}}</strong>\n" +
            "                                        </a>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
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
            "                                        ORLounge Team\n" +
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
            "                </table>";


 private static final String ESPS_EXPIRY_REMINDER_BODY ="                <table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
            "                        style=\"border-collapse:collapse;border-spacing:0;border-collapse:separate;background: #fff;\">\n" +
            "                    <tbody style=\"background: #fff;\">\n" +
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
            "                                    <td style=\"vertical-align:top;padding:0;border-collapse:collapse;padding-right:5px;font-size:20px;line-height:30px\">\n" +
            "                                        <span style=\"font-family:Arial,sans-serif;padding:0;font-size:20px;line-height:30px;vertical-align:middle\">\n" +
            "                                            Dear member,\n" +
            "                                        </span>\n" +
            "                                    </td>\n" +
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
            "                                       Your hospital, <strong>{{hospitalName}}</strong> ESPS FREE Trial is expiring on <strong>{{expiryDate}}</strong>.<br/>" +
            "\n" +
            "                                       To continue to use the service please make arrangement to pay for the service. <br/>" +
         "                                          If you have already paid. Please contact us at " +
         "                                          <a href='mailto:sales@orlounge.com' >sales@orlounge.com</a>."+
            "\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "\n" +
            "                                <tr>\n" +
            "\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\"> &nbsp;</td>\n" +
            "                                    <td style=\"padding:0;border-collapse:collapse;font:normal 14px/20px Arial,sans-serif;padding:2px 0 2px 5px;vertical-align:top\">&nbsp; </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table> </td>\n" +
            "                    </tr>\n" +
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
            "                                        ORLounge Team\n" +
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
            "                </table>";



        public String REGISTER_MEMBER_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", REGISTER_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", REGISTERATION_MEMBER_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String REGISTER_MEMBER_ORM_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", REGISTER_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", REGISTERATION_MEMBER_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String REGISTER_LSA_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", REGISTER_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", REGISTERATION_LSA_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);
        public String REGISTER_LSA_ORM_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", REGISTER_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", REGISTERATION_LSA_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);


        public String APPROVAL_LSA_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", APPROVED_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", APPROVAL_OF_TEMP_LSA_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);


    public String APPROVAL_LSA_IS_ORM_MAIL =
            MAIN_TEMPLATE
                    .replaceAll("\\{\\{subject\\}\\}", APPROVED_SUBJECT_TEMPLATE)
                    .replaceAll("\\{\\{content\\}\\}", APPROVAL_OF_ORM_LSA_BODY)
                    .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String APPROVAL_MEMBER_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", APPROVED_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", APPROVAL_OF_MEMEBER_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);
        public String APPROVAL_MEMBER_ORM_BUT_NOT_LSA_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", APPROVED_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", APPROVAL_OF_MEMEBER_ORM_BUT_NOT_LSA_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String APPROVER_NOTIFYING_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", APPROVER_NOTIFICATION_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", APPROVER_NOTIFICATION_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);


        public String APPROVER__ORTEAM_NOTIFYING_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", APPROVER__OR_TEAM_NOTIFICATION_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", APPROVER_NOTIFICATION_OR_TEAM_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String INVITATION_ORTEAM_NOTIFYING_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", INVITATION_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", INVITATION_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);



        public String PASSWORD_RECOVERY=
            MAIN_TEMPLATE
                    .replaceAll("\\{\\{subject\\}\\}", PASSWORD_RECOVERY_SUBJECT_TEMPLATE)
                    .replaceAll("\\{\\{content\\}\\}", PASSWORD_RECVOERY_BODY)
                    .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String ESPS_REMINDER_MAIL=
            MAIN_TEMPLATE
                    .replaceAll("\\{\\{subject\\}\\}", ESPS_REMINDER_SUBJECT_TEMPLATE)
                    .replaceAll("\\{\\{content\\}\\}", ESPS_EXPIRY_REMINDER_BODY)
                    .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);

        public String MEMBER_VERIFICATION_STATUS_MAIL =
                MAIN_TEMPLATE
                        .replaceAll("\\{\\{subject\\}\\}", VERIFICATION_NOTIFICATION_SUBJECT_TEMPLATE)
                        .replaceAll("\\{\\{content\\}\\}", VERIFICATION_RESPONSE_MAIL_BODY)
                        .replaceAll("\\{\\{disclaimer\\}\\}", DISCLAIMER);



    public static void main(String[] args) throws IOException {
        MailTemplate t = new MailTemplate();
        Map<String,String> m = new HashMap();
        m.put("MEMBER_VERIFICATION_STATUS_MAIL", t.MEMBER_VERIFICATION_STATUS_MAIL );
        m.put("INVITATION_ORTEAM_NOTIFYING_MAIL", t.INVITATION_ORTEAM_NOTIFYING_MAIL );
        m.put("APPROVER__ORTEAM_NOTIFYING_MAIL", t.APPROVER__ORTEAM_NOTIFYING_MAIL );
        m.put("APPROVER_NOTIFYING_MAIL", t.APPROVER_NOTIFYING_MAIL );
        m.put("INVITATION_ORTEAM_NOTIFYING_MAIL", t.INVITATION_ORTEAM_NOTIFYING_MAIL );
        m.put("APPROVAL_MEMBER_ORM_BUT_NOT_LSA_MAIL", t.APPROVAL_MEMBER_ORM_BUT_NOT_LSA_MAIL );
        m.put("APPROVAL_MEMBER_MAIL", t.APPROVAL_MEMBER_MAIL );
        m.put("APPROVAL_LSA_IS_ORM_MAIL", t.APPROVAL_LSA_IS_ORM_MAIL );
        m.put("APPROVAL_LSA_MAIL", t.APPROVAL_LSA_MAIL );
        m.put("REGISTER_LSA_ORM_MAIL", t.REGISTER_LSA_ORM_MAIL );
        m.put("REGISTER_LSA_MAIL", t.REGISTER_LSA_MAIL );
        m.put("REGISTER_MEMBER_ORM_MAIL", t.REGISTER_MEMBER_ORM_MAIL );
        m.put("REGISTER_MEMBER_MAIL", t.REGISTER_MEMBER_MAIL );
        m.put("PASSWORD_RECOVERY_MAIL", t.PASSWORD_RECOVERY );

        for(Map.Entry<String,String> e : m.entrySet()){
            File f = new File("D:\\ORLOUNGE\\orloungeweb\\web\\src\\main\\resources\\email\\"+e.getKey()+".html");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write(e.getValue());
            fw.close();
        }

    }

}

package org.orlounge.business;

import org.orlounge.bean.ApproverResponse;
import org.orlounge.bean.EspsBillInfo;

import java.text.SimpleDateFormat;

/**
 * Created by satyam on 4/23/2017.
 */
public class RegisterationEmailer {

    public String registerLSAEmail(final String userName, final String hospitalName){
        return new MailTemplate().REGISTER_LSA_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }

    public String registerMemberEmail(final String userName, final String hospitalName){
        return new MailTemplate().REGISTER_MEMBER_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }
    public String registerORMMemberEmail(final String userName, final String hospitalName){
        return new MailTemplate().REGISTER_MEMBER_ORM_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }

    public String registerLSAisORMEmail(final String userName, final String hospitalName){
        return new MailTemplate().REGISTER_LSA_ORM_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }

    public String approvalLSAEmail(final String userName, final String hospitalName){
        return new MailTemplate().APPROVAL_LSA_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }
    public String approvalMemberEmail(final String userName, final String hospitalName, final String lsaName){
        return new MailTemplate().APPROVAL_MEMBER_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName)
                                .replaceAll("\\{lsaName\\}", lsaName);
    }
    public String approvalMemberEmailIsORMButNotLSA(final String userName, final String hospitalName, final String lsaName){
        return new MailTemplate().APPROVAL_MEMBER_ORM_BUT_NOT_LSA_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName)
                                .replaceAll("\\{lsaName\\}", lsaName);
    }
    public String approvalLSAIsORMEmail(final String userName, final String hospitalName){
        return new MailTemplate().APPROVAL_LSA_IS_ORM_MAIL
                                .replaceAll("\\{userName\\}",userName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }

    public String approverNotificationEmail(final String joinerName, final String hospitalName){
        return new MailTemplate().APPROVER_NOTIFYING_MAIL
                                .replaceAll("\\{userName\\}",joinerName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }
    public String approverNotificationForORTeamEmail(final String joinerName, final String hospitalName){
        return new MailTemplate().APPROVER__ORTEAM_NOTIFYING_MAIL
                                .replaceAll("\\{userName\\}",joinerName)
                                .replaceAll("\\{hospitalName\\}", hospitalName);
    }

    public String invitationEmail(final String senderName, final String message, final String recieverEmail){
        return new MailTemplate().INVITATION_ORTEAM_NOTIFYING_MAIL
                                .replaceAll("\\{senderName\\}",senderName)
                                .replaceAll("\\{userName\\}",recieverEmail)
                                .replaceAll("\\{body\\}", message);
    }

    public String verificationStatusEmail(final String senderName, final String hospitalName, final String recieverEmail,
                                          final ApproverResponse response){
        return new MailTemplate().MEMBER_VERIFICATION_STATUS_MAIL
                                .replaceAll("\\{senderName\\}",senderName)
                                .replaceAll("\\{userName\\}",recieverEmail)
                                .replaceAll("\\{hospitalName\\}",hospitalName)
                                .replaceAll("\\{name\\}", response.getName())
                                .replaceAll("\\{role\\}", response.getRole())
                                .replaceAll("\\{phNo\\}", response.getPhNo())
                                .replaceAll("\\{action\\}", response.getAction())
                                .replaceAll("\\{email\\}", response.getEmail());
    }
    public String passwordRecoveryMail(final String password){
        return new MailTemplate().PASSWORD_RECOVERY
                                .replaceAll("\\{\\{password\\}\\}",password);
    }
    public String espsReminder(final String hospitalName, EspsBillInfo billInfo){
        return new MailTemplate().ESPS_REMINDER_MAIL
                                .replaceAll("\\{\\{hospitalName\\}\\}",hospitalName)
                                .replaceAll("\\{\\{expiryDate\\}\\}", new SimpleDateFormat("dd MMM, yyyy").format(billInfo.getEndDate()));
    }



}

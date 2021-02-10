package org.orlounge.business;

import org.orlounge.bean.GroupBean;
import org.orlounge.bean.UserBean;

/**
 * Created by satyam on 3/12/2017.
 */
public class EmailTemplateResolver {

    private UserBean userBean;

    private GroupBean groupBean;

    private boolean isLSAUser;

    private boolean isFirstUser;

    public EmailTemplateResolver(UserBean userBean, GroupBean groupBean, boolean isLSAUser, boolean isFirstUser) {
        this.userBean = userBean;
        this.groupBean = groupBean;
        this.isLSAUser = isLSAUser;
        this.isFirstUser = isFirstUser;
    }

    public void sendMail(MailBusiness mailBusiness){

    }
}

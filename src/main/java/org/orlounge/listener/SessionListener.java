package org.orlounge.listener;

import org.orlounge.bean.EventLogBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.UserToken;
import org.orlounge.factory.BusinessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Component
public class SessionListener implements HttpSessionListener {

    @Autowired
    private BusinessFactory businessFactory;

    @Autowired
    private ApplicationContext applicationContext;

    public BusinessFactory getBusinessFactory() {
        if (businessFactory == null) {
            businessFactory = applicationContext.getBean(BusinessFactory.class);
        }
        return businessFactory;
    }

    public void setBusinessFactory(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if(session != null){
            UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
            if(token != null && token.getEventId() != null){
                Integer id = token.getEventId();
                EventLogBean event = getBusinessFactory().getEventBusiness().getEventLogByEventId(id);
                event.setEventName(AppConstants.LOGOUT_EVENT);
                event.setIsActive(0);
                event.setLastDts(new Date());
                getBusinessFactory().getEventBusiness().saveEventLog(event);
            } else if(token != null){
                EventLogBean event = new EventLogBean();
                event.setEventName(AppConstants.LOGOUT_EVENT);
                event.setIsActive(0);
                event.setDts(new Date());
                event.setLastDts(event.getDts());
                event.setEventMsg("User : [" + token.getUserCode() + "] got Logout");
                event.setUserId(token.getUserId());
                getBusinessFactory().getEventBusiness().saveEventLog(event);
            }

        }

    }
}

package org.orlounge.business;

import org.orlounge.bean.EventLogBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Component
public class EventBusiness extends AbstractBaseBusiness{



    public List<EventLogBean> getAllEventLogs(){
        return   getServiceFactory().getEventService().getAllEventLogs();
    }

    public EventLogBean getEventLogByEventId(Integer id){
        return getServiceFactory().getEventService().getEventLogByEventId(id);
    }

    public List<EventLogBean> getEventLogsByGroup(Integer groupId){
        return getServiceFactory().getEventService().getEventLogsByGroup(groupId);
    }

    public List<EventLogBean> getEventLogByUser(Integer userId){
        return getServiceFactory().getEventService().getEventLogByUser(userId);
    }

    public List<EventLogBean> getEventLogByEvent(String eventName){
        return getServiceFactory().getEventService().getEventLogByEvent(eventName);
    }


    public EventLogBean saveEventLog(EventLogBean bean){
        return getServiceFactory().getEventService().saveEventLog(bean);
    }

}

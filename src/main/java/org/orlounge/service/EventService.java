package org.orlounge.service;

import org.orlounge.bean.EventLogBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Service
@Transactional
public class EventService extends AbstractBaseService {


    public List<EventLogBean> getAllEventLogs(){
            return   getDaoFactory().getEventLogDAO().findAll();
    }

    public List<EventLogBean> getEventLogsByGroup(Integer groupId){
        return getDaoFactory().getEventLogDAO().getEventLogsByGroup(groupId);
    }

    public List<EventLogBean> getEventLogByUser(Integer userId){
        return getDaoFactory().getEventLogDAO().findAllByUserId(userId);
    }

    public List<EventLogBean> getEventLogByEvent(String eventName){
        return getDaoFactory().getEventLogDAO().findAllByEventName(eventName);
    }


    public EventLogBean saveEventLog(EventLogBean bean){
        return getDaoFactory().getEventLogDAO().saveAndFlush(bean);
    }


    public EventLogBean getEventLogByEventId(Integer id) {
        return getDaoFactory().getEventLogDAO().findById(id).get();
    }
}

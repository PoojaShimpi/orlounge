package org.orlounge.service;

import org.orlounge.bean.CallScheduleBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class CallScheduleService extends AbstractBaseService{


    public List<CallScheduleBean> getCallSchedules(){
            return getDaoFactory().getCallScheduleDAO().getCallSchedules(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public CallScheduleBean getCallSchedule(Integer id){
            return getDaoFactory().getCallScheduleDAO().findById(id).get();
    }

    public CallScheduleBean saveCallSchedule(CallScheduleBean bean){
        return getDaoFactory().getCallScheduleDAO().saveAndFlush(bean);
    }
    
    public int deleteCallSchedule(Integer id){
        return getDaoFactory().getCallScheduleDAO().deleteCallSchedule(id);
    }
//
//    public CallScheduleBean deleteCallSchedule(CallScheduleBean bean){
//        return getDaoFactory().getCallScheduleDAO().deleteCallSchedule(bean);
//    }
}

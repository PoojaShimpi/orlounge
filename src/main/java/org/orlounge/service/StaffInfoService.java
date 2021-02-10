package org.orlounge.service;

import org.orlounge.bean.NoticeBean;
import org.orlounge.bean.StaffInfoBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/22/2015.
 */
@Service
@Transactional
public class StaffInfoService extends AbstractBaseService {

    public StaffInfoBean getStaffInfo(Integer userId){
        return getDaoFactory().getStaffInfoDAO().getStaffInfo(userId);
    }

    public StaffInfoBean saveStaffInfo(StaffInfoBean bean){
        return getDaoFactory().getStaffInfoDAO().save(bean);
    }

    public List<StaffInfoBean> getAllGrpStaffInfo() {
        return getDaoFactory().getStaffInfoDAO().getAllGrpStaffInfo(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public StaffInfoBean getStaffByIdAndUserId(Integer id, Integer userId) {
    	//return getDaoFactory().getStaffInfoDAO().getStaffByIdAndUserId(id, userId);
    	return getDaoFactory().getStaffInfoDAO().getStaffByIdAndUserId(id, userId, AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }
    
    public int deleteStaff(Integer id){
        return getDaoFactory().getStaffInfoDAO().deleteStaff(id);
    }
}

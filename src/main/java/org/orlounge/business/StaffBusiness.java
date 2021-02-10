package org.orlounge.business;

import org.orlounge.bean.StaffInfoBean;
import org.orlounge.bean.UserGroupMap;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public class StaffBusiness extends AbstractBaseBusiness {
    public List<StaffInfoBean> getAllGrpStaffInfo() {
        return getServiceFactory().getStaffInfoService().getAllGrpStaffInfo();
    }

    public StaffInfoBean getStaffInfo(Integer id) {
        return getServiceFactory().getStaffInfoService().getStaffInfo(id);
    }


    public int deactivatedStaff(Integer id) {
    	return getServiceFactory().getStaffInfoService().deleteStaff(id);
//        if (userId != null && groupId != null) {
//            UserGroupMap userGroupMap = getServiceFactory().getGroupService().getGroupUserMap(Integer.parseInt(userId), Integer.parseInt(groupId));
//            userGroupMap.setIsActive(0);
//            getServiceFactory().getGroupService().saveUserGroupMap(userGroupMap);
//            flag = true;
//        }
//        return flag;
    }
}

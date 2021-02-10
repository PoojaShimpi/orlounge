package org.orlounge.business;

import org.orlounge.bean.GroupBean;
import org.orlounge.bean.UserGroupMap;
import org.orlounge.common.util.ProcessData;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Component
public class GroupBusiness extends AbstractBaseBusiness {

    public List<GroupBean> getAllGroups(){
        try{
            return getServiceFactory().getGroupService().getAllGroups();
        }catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }

    public GroupBean getGroup(Integer id){
        try{
            return getServiceFactory().getGroupService().getGroupByGrpId(id);
        }catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }
    public GroupBean getGroupByName(String name){
        try{
            return getServiceFactory().getGroupService().getGroupByGrpName(name);
        }catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }

    public Map checkEmailIdByRoleAndHospitalId(String emailId, String role, Integer hospitalId) {
        Map result = new HashMap();
        try {
            UserGroupMap userGroupMap = getServiceFactory().getGroupService().checkEmailIdByRoleAndHospitalId(emailId, role, hospitalId);
            if (ProcessData.isValid(userGroupMap)) {
                result.put("success", false);
                result.put("message", "Your are already registered user with ORM");
            } else {
                result.put("success", true);
                result.put("message", "");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ORException(ex, 0);
        }
        return result;
    }
}

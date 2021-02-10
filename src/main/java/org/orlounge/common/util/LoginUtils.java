package org.orlounge.common.util;

import org.orlounge.bean.*;
import org.orlounge.common.AppConstants;
import org.orlounge.common.UserToken;
import org.orlounge.factory.BusinessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public  class LoginUtils {

    @Autowired
    private BusinessFactory businessFactory;

    public   UserToken prepareToken(LoginBean loginBean){
        try{
            final UserToken token = new UserToken();
            //token.setFirstName();
            UserBean user = businessFactory.getUserService().getUserBeanByUserCode(loginBean.getUserCode());
            token.setFirstName(user.getFirstName());
            token.setLastName(user.getLastName());
            token.setTimeZone(user.getTimezone());
            token.setIsPassExpired(loginBean.getPass_Reset() > 0);
            token.setLoginId(loginBean.getUserLoginId());
            token.setEmail(user.getEmail());

            token.setUserCode(loginBean.getUserCode());
            token.setUserId(user.getUserId());
            token.setUserImage(user.getHospitalBadgeId());


            StaffInfoBean staff = businessFactory.getStaffBusiness().getStaffInfo(token.getUserId());
            token.setHasStaffInfo(ProcessData.isValid(staff));

            if(user.getUserFlag().equals(1)){
                token.setRole(AppConstants.ADMIN_ROLE);
                token.setLSA(true);
            }else {

                List<UserGroupMap> userGroupMapList = businessFactory.getUserService().getGroupMapsBeanByUser(user.getUserId());
                UserToken.GroupInfo groupInfo = null;
                UserToken.GroupAccessMap accessMap = null;
                List<UserToken.GroupAccessMap> list = new ArrayList<>();
                Integer lastAccessGrp = businessFactory.getUserService().getLastAccessGroupIdByUser(user.getUserId());
                GroupBean lastAccessGroup = null;
                String lastAccessRole = null;
                boolean isLastAccessLSA = false;
                for(UserGroupMap ug : userGroupMapList){

                    GroupBean g  = ug.getGroup();
                    if(!ProcessData.isValid(lastAccessGrp)){
                        lastAccessGrp = g.getGroupId();
                    }
                    if(lastAccessGroup == null){
                        lastAccessGroup = g;
                        lastAccessRole = ug.getRole();
                        isLastAccessLSA = ug.getIsLSA() == 1;
                    }
                    groupInfo = new UserToken.GroupInfo(g.getGroupId(), g.getGroupName(),g.getTrialFlag().equals(1), g.getTrialFlag().equals(-1));
                    accessMap = new UserToken.GroupAccessMap(groupInfo, ug.getRole(), ug.getIsLSA().equals(1), lastAccessGrp.equals(g.getGroupId()));
                    if(lastAccessGrp.equals(g.getGroupId())){
                        lastAccessGroup = g;
                        lastAccessRole = ug.getRole();
                        isLastAccessLSA = ug.getIsLSA() == 1;
                    }
                    list.add(accessMap);
                }
                token.setGroupAccessList(list);


                if(ProcessData.isValid(lastAccessGroup)){
                    token.setCurrentGroupName(lastAccessGroup.getGroupName());
                    token.setCurrentGroupId(lastAccessGroup.getGroupId());
                    token.setRole(lastAccessRole);
                    token.setLSA(isLastAccessLSA);

                } else{
                    throw new AuthenticationException("Invalid Group");
                }
            }

            boolean groupHasLsaAsORM = (!Objects.nonNull(token.getCurrentGroupId())) || businessFactory.getUserService().hasGroupLSAAsRole(AppConstants.ORM_ROLE, token.getCurrentGroupId());
            token.setHasOrmAsLSA(groupHasLsaAsORM);
            return token;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}

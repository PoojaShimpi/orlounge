package org.orlounge.handler;

import org.orlounge.bean.*;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.JwtUtil;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.factory.BusinessFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Satyam Soni on 9/13/2015.
 */
@Component
public class  SuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SuccessHandler.class);

    @Autowired
    private BusinessFactory businessFactory;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserToken token = prepareToken((LoginBean)authentication.getPrincipal());
        AppThreadLocal.setTemp(token);
        if(AppThreadLocal.getTemp() == null){
            request.getSession().invalidate();
        }
        response.addCookie(new Cookie("jwt", createJWT(StringUtil.getUUID(), token)));
        super.onAuthenticationSuccess(request, response, authentication);
        request.getSession().setAttribute("HOSPITAL_NAME", token.getCurrentGroupName());
        request.getSession().setAttribute("USER_TOKEN", token);

        try{
            EventLogBean event = new EventLogBean();
            event.setEventName(AppConstants.LOGIN_EVENT);
            event.setIsActive(1);
            event.setDts(new Date());
            event.setEventMsg("User : [" + token.getUserCode() + "] got Login");
            event.setUserId(token.getUserId());
            event.setIp(ControllerUtils.getClientIpAddr(request));
            businessFactory.getEventBusiness().saveEventLog(event);
            token.setEventId(event.getId());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * Builds the target URL according to the logic defined in the main class Javadoc.
     */
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        setDefaultTargetUrl("/redirect.html");

        return super.determineTargetUrl(request, response);
    }

    //Sample method to construct a JWT
    private String createJWT(String id,  UserToken userToken) {
        return JwtUtil.createJwtToken(userToken);
    }


    private UserToken prepareToken(LoginBean loginBean){
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

                if(user.getRole().equals(AppConstants.ADMIN_ROLE)){
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
                        token.setCurrentGroupSize(lastAccessGroup.getGroupSize());
                        token.setRole(lastAccessRole);
                        token.setLSA(isLastAccessLSA);

                    } else{
                        throw new AuthenticationException("Invalid Group");
                    }
                    boolean groupHasLsaAsORM = (!Objects.nonNull(token.getCurrentGroupId())) || businessFactory.getUserService().hasGroupLSAAsRole(AppConstants.ORM_ROLE, token.getCurrentGroupId());
                    token.setHasOrmAsLSA(groupHasLsaAsORM);
                }



                return token;
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
    }
}

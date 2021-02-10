package org.orlounge.controller.user;

import org.orlounge.bean.GroupBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Controller
public class GroupController extends BaseController {

    @RequestMapping("/selectOrl")
    public ModelAndView select(@RequestParam(value = "id") Integer groupId, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("redirect:home.html");
        try{
            UserToken token = AppThreadLocal.getTokenLocal();
            if(AppConstants.ADMIN_ROLE.equalsIgnoreCase(token.getRole())){

                GroupBean groupBean  = getBusinessFactory().getGroupBusiness().getGroup(groupId);
                if(ProcessData.isValid(groupBean)){
                    token.setCurrentGroupId(groupId);
                    token.setCurrentGroupName(groupBean.getGroupName());
                    request.getSession().setAttribute("HOSPITAL_NAME", token.getCurrentGroupName());

                    return modelAndView;
                }

            }else {
                List<UserToken.GroupAccessMap> access = token.getGroupAccessList();
                for(UserToken.GroupAccessMap e : access){
                    if(groupId.equals(e.getGroupInfo().getGroupId())){
                        GroupBean groupBean  = getBusinessFactory().getGroupBusiness().getGroup(groupId);
                        if(ProcessData.isValid(groupBean)){
                            token.setCurrentGroupId(groupId);
                            token.setCurrentGroupName(groupBean.getGroupName());
                            token.setHasOrmAsLSA(getBusinessFactory().getUserService().hasGroupLSAAsRole(AppConstants.ORM_ROLE, token.getCurrentGroupId()));
                            request.getSession().setAttribute("HOSPITAL_NAME", token.getCurrentGroupName());
                            getBusinessFactory().getUserService().updateLastAccessGroupIdByUser(token.getUserId(), token.getCurrentGroupId());
                             return modelAndView;
                        }
                        break;
                    }
                }
                return modelAndView;
            }
                request.getSession().invalidate();
                return new ModelAndView("redirect:index.html");
        }catch (Exception ex){

            request.getSession().invalidate();
            return new ModelAndView("redirect:index.html");
        }
    }
}

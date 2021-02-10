package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.StaffInfoBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserInfo;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 10/13/2015.
 */
@Controller
@AuthCheck
public class StaffController extends BaseController {


    @RequestMapping("/saveStaffInfo")
    public ModelAndView saveStaffInfo(@ModelAttribute(value = "staffInfo") StaffInfoBean staffInfo, BindingResult result, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("redirect:profile.html");
        getBusinessFactory().getUserService().saveStaffInfo(staffInfo);
        UserToken token = AppThreadLocal.getTemp();
        if (token == null || !token.isHasStaffInfo()) {
            httpSession.setAttribute("showLogoutMessage", true);
        }
        return model;
    }


    @RequestMapping("/staffinfolist.html")
    public ModelAndView staffinfolist(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/staff-info-main");
            List<StaffInfoBean> scheduleBeans = getBusinessFactory().getStaffBusiness().getAllGrpStaffInfo();
            model.addObject("infos", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }


    @RequestMapping(value = "/staff/view", method = RequestMethod.GET)
    public ModelAndView viewStaffInfo(@RequestParam(value = "userId", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            ModelAndView model = new ModelAndView("redirect:profile.html?userId=" + id);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }


    @RequestMapping(value = "/view_new_staff.html", method = RequestMethod.GET)
    public ModelAndView getStaffViewModel(@RequestParam(value = "id", required = false) Integer id,
                                          @RequestParam(value = "userId", required = false) Integer userId,
                                          @RequestParam(value = "action") String action,
                                          @RequestParam(value = "groupId", required = false) Integer groupId) {
        ModelAndView modelAndView = new ModelAndView("jsp/view-new-staff");
        try {
            UserInfo userInfo = null;
            StaffInfoBean bean = null;
            if (ProcessData.isValid(id) && ProcessData.isValid(userId)) {

                bean = getBusinessFactory().getUserService().getStaffInfoBean(id, userId);
                userInfo = getBusinessFactory().getUserService().getUserAsPerRoleForStaff(userId, groupId);
            } else {
                bean = new StaffInfoBean();
                userInfo = new UserInfo (){

                    @Override
                    public Date getDate() {
                        return null;
                    }

                    @Override
                    public Integer getUserId() {
                        return null;
                    }

                    @Override
                    public Integer getUserLoginId() {
                        return null;
                    }

                    @Override
                    public String getUserCode() {
                        return null;
                    }

                    @Override
                    public String getEmail() {
                        return null;
                    }

                    @Override
                    public String getRole() {
                        return null;
                    }

                    @Override
                    public String getGroupName() {
                        return null;
                    }

                    @Override
                    public Integer getGrpId() {
                        return null;
                    }

                    @Override
                    public Integer getStatusId() {
                        return null;
                    }

                    @Override
                    public String getStatus() {
                        return null;
                    }

                    @Override
                    public String getFirstName() {
                        return null;
                    }

                    @Override
                    public String getLastName() {
                        return null;
                    }

                    public Integer getLsa(){return 0;}
                };
            }
            modelAndView.addObject("staffInfo", bean);
            modelAndView.addObject("userInfo", userInfo);
            modelAndView.addObject(AppConstants.ACTION, action);

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/remove_staff_directory/{id}", method = RequestMethod.GET)
    public ModelAndView removeStaff(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/staffinfolist.html");
        try {
        	boolean success=false;
        	Integer inputId = Integer.parseInt(id);
        	int ans= getBusinessFactory().getStaffBusiness().deactivatedStaff(inputId);
        	if (ans<=0)
        	{
        		success=false;
        	}
        	else {
        		success=true;
        	}
            //boolean success = getBusinessFactory().getNoticeBusiness().deleteNotice(id);
            if (success) {
                MessageUtils.success(request.getSession(), "Removed Successfully.");
            } else {
                MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;
    }
    
    
//    @RequestMapping(value = "/remove_staff_directory", method = RequestMethod.GET)
//    public ModelAndView removeStaff(@RequestParam(value = "userId") String userId, @RequestParam(value = "groupId") String groupId, HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("redirect:staffinfolist.html");
//        try {
//            UserToken user = AppThreadLocal.getTokenLocal();
////            if (user.isLSA()) {
//                boolean flag = getBusinessFactory().getStaffBusiness().deactivatedStaff(userId, groupId);
//                if (flag) {
//                    MessageUtils.failure(request.getSession(), "Remove successfully");
//                } else {
//                    MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
//                }
//                
////            } else {
////                MessageUtils.failure(request.getSession(), "UnAuthorize Access");
////            }
//
//
//        } catch (Exception e) {
//            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
//            e.printStackTrace();
//        }
//        return model;
//    }

    @RequestMapping("/saveOrUpdateStaffDirectory")
    public ModelAndView saveOrUpdateStaffDirectory(@ModelAttribute(value = "staffInfo") StaffInfoBean staffInfo, BindingResult result, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("redirect:staffinfolist.html");
        try {
            boolean flag = getBusinessFactory().getUserService().saveOrUpdateStaffDirectory(staffInfo);
            if (flag) {
                MessageUtils.failure(request.getSession(), "Staff saved successfully");
            } else {
                MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            }
        } catch (Exception e) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            e.printStackTrace();
        }
        return model;
    }
}

package org.orlounge.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.*;
import org.orlounge.common.*;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.exception.ORException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 9/13/2015.
 */
@Controller
public class MainController extends BaseController{

	@RequestMapping("/healthcheck")
	public @ResponseBody String healthcheck()
	{
		return "server strated done.";
	}

    @RequestMapping({"/","/index.html"})
    public ModelAndView index() {
        ModelAndView m = new ModelAndView("jsp/main");
         List<GroupedHospital> hos = getBusinessFactory().getUserService().getAllGroupedHospital();
        m.addObject("hospitals", hos);
        return m;
    }

    @RequestMapping("/register-holder.html")
    public ModelAndView register(HttpServletRequest request, HttpSession httpSession) throws AuthException {
        ModelAndView model = new ModelAndView("jsp/register-holder");
        List<GroupedHospital> hos = getBusinessFactory().getUserService().getAllGroupedHospital();
        model.addObject("hospitals", hos);
        return model;
    }

    @RequestMapping("/login.html")
    public ModelAndView login(HttpServletRequest request, HttpSession httpSession) throws AuthException {
        System.out.println("Helllaooooajdf");
        ModelAndView model = new ModelAndView("jsp/login");
        return model;
    }
    @RequestMapping("/geo.html")
    @AuthCheck
    public ModelAndView geo(){
        ModelAndView m = new ModelAndView("jsp/admin/geo");

        return m;
    }
    @RequestMapping("/geo-error.html")
    @AuthCheck
    public ModelAndView geoError(){
        ModelAndView m = new ModelAndView("/geo-error");
        return m;
    }
   /* @RequestMapping("/gws-app.html")
    public ModelAndView gwsApp(){
        Map<String,String> contentMap = getBusinessFactory().getUserService().getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getStaticContent();
        ModelAndView m = new ModelAndView("/jsp/gws-main");
        m.addObject("contentMap", contentMap);
        return m;
    }

    @RequestMapping(value = "/save-gws-app/{contentId}" ,method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map saveGwsApp(@PathVariable String contentId,@RequestBody String  content){
        getBusinessFactory().getUserService().getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().saveStaticContent(contentId, content);
        Map<String,String> m = new HashMap<>();
        m.put("success", "true");
        return m;
    }*/
    @RequestMapping("/geo-info.html")
    @AuthCheck
    public ModelAndView geoinfo(){
        ModelAndView m = new ModelAndView("geo-info");
        return m;
    }



    @RequestMapping("/aboutus.html")
    public ModelAndView aboutus() {
        return new ModelAndView("jsp/about-us");
    }

    @RequestMapping("/terms.html")
    public ModelAndView terms() {
        return new ModelAndView("jsp/terms");
    }

    @RequestMapping("/contactus.html")
    public ModelAndView contactus() {
        return new ModelAndView("jsp/contact-us");
    }

    @RequestMapping("/general-instructions.html")
    public ModelAndView genins() {
        return new ModelAndView("jsp/gen-ins");
    }

    @RequestMapping("/copyrightnotice.html")
    public ModelAndView copyrightnotice() {
        return new ModelAndView("jsp/copy-right");
    }


    @RequestMapping("/home.html")
    @AuthCheck
    public ModelAndView home() {
        //  return new ModelAndView("jsp/home");
        return new ModelAndView("redirect:messageboard.html");
    }


    @RequestMapping("/invite.html")
    @AuthCheck
    public ModelAndView invite(@RequestParam(required = false) String hospitalFlag, @RequestParam(required = false) String ormFlag) {
        boolean added = false;
        ModelAndView view = new ModelAndView("jsp/invite");
        if (hospitalFlag != null) {
            view.addObject("hospitalFlag", hospitalFlag);
        }
        if (ormFlag != null) {
            view.addObject("ormFlag", ormFlag);
        }
        return view;
    }

    @RequestMapping("/inviteUser")
    @AuthCheck
    public ModelAndView inviteUser(@RequestParam(value = "email") String email,
                                   @RequestParam(required = false, value = "emailMessage") String emailMessage,
                                   HttpServletRequest request, HttpServletResponse response){

        getBusinessFactory().getUserService().sendInviteMail(email, emailMessage);
        request.setAttribute("success", "true");
        return new ModelAndView("redirect:invite.html");
    }



    @RequestMapping("/loungeworks.html")
    @AuthCheck
    public ModelAndView loungeworks(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "role", required =  false) String role, HttpServletRequest request, HttpSession httpSession){
        ModelAndView model = new ModelAndView("jsp/loungeworks");
        model.addObject("role",role);
        if(page == null || page == 0){
            //model
        } else {

        }
        return model;
    }


    @RequestMapping("/redirect.html")
    @AuthCheck
    public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView;
        if(AppThreadLocal.getTemp() != null){
            UserToken token = AppThreadLocal.getTemp();
            token.setIpAddress(ControllerUtils.getClientIpAddr(request));
            if(token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE) && token.getCurrentGroupId() == null){
                modelAndView = new ModelAndView("redirect:orltoenter.html");
            }
            else if(!token.isHasStaffInfo()){
                modelAndView = new ModelAndView("redirect:profile.html");
            }else {
                modelAndView = new ModelAndView("redirect:messageboard.html");
            }
            AppThreadLocal.setTemp(null);
        }else {
            modelAndView = new ModelAndView("redirect:messageboard.html");
        }
        return modelAndView;
    }




    @RequestMapping(value = "/getImageDocument", method = RequestMethod.GET)
    @AuthCheck
    public ResponseEntity<byte[]> getImageDocument(@RequestParam("filePath") final String filePath, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
        ResponseEntity<byte[]> result =null;
        File file = null;
        try {
            file = getBusinessFactory().getPrefListBusiness().getFileFromAWS(filePath, StringUtil.getUUID()+filePath.substring(filePath.lastIndexOf(".")));
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            result = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
            file.deleteOnExit();
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }

        return result;
    }



    @RequestMapping("/profile.html")
    @AuthCheck
    public ModelAndView profile(@RequestParam(value = "userId", required = false)Integer userId ,@RequestParam(value = "groupId", required = false)Integer groupId, HttpServletRequest request, HttpSession httpSession){
        ModelAndView model = new ModelAndView("jsp/staff-directory");
        UserToken token = AppThreadLocal.getTokenLocal();
        Integer id ;
        if(userId != null){
            id = userId;
        } else {
            id = token.getUserId();
        }
        if(groupId == null){
            groupId = token.getCurrentGroupId();
        }
        model.addObject("userId", id);
        UserBean user = getBusinessFactory().getUserService().getUserBeanByUserId( id);
        if(user != null){
            model.addObject("userObj", user );
            model.addObject("userObj", user );
            UserInfo userInfo = getBusinessFactory().getUserService().getUserInfo(id, groupId);
            model.addObject("userInfo", userInfo );
            StaffInfoBean staffInfoBean = getBusinessFactory().getUserService().getStaffInfo(id);

            model.addObject("staffInfo", staffInfoBean == null ? new StaffInfoBean() : staffInfoBean);

        }else {
            throw new ORException(5);
        }

        return model;
    }



    @RequestMapping("/groups.html")
    @AuthCheck
    public ModelAndView groups(HttpServletRequest request, HttpServletResponse response){
        try{

            ModelAndView model = new ModelAndView("jsp/manage-groups");
            List<GroupBean> grps = getBusinessFactory().getGroupBusiness().getAllGroups();
            model.addObject("groups" ,grps);
            return model;

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping("/states.html")
    @AuthCheck
    public ModelAndView states(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                ModelAndView model = new ModelAndView("jsp/manage-states");
                List<StateBean> grps = getBusinessFactory().getUserService().getStates();
                model.addObject("states", grps);
                return model;
            } else {
                return new ModelAndView("redirect:home.html");
            }
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @RequestMapping("/hospitals.html")
    @AuthCheck
    public ModelAndView hospitals(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                ModelAndView model = new ModelAndView("jsp/manage-hospital");
                List<HospitalBean> grps = getBusinessFactory().getHospitalBusiness().getAllHospitals();
                model.addObject("hospitals", grps);
                List<StateBean> states = getBusinessFactory().getUserService().getStates();
                model.addObject("states", states);
                return model;
            } else {
                return new ModelAndView("redirect:home.html");
            }
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping("/save-state.html")
    @AuthCheck
    public ModelAndView saveState(
            @RequestParam String stateName,
            @RequestParam String stateCode,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                ModelAndView model = new ModelAndView("redirect:states.html");
                StateBean stateBean = new StateBean();
                stateBean.setStateCode(stateCode);
                stateBean.setStateName(stateName);
                getBusinessFactory().getUserService().saveState(stateBean);
                return model;
            } else {
                return new ModelAndView("redirect:home.html");
            }
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping("/save-hospital.html")
    @AuthCheck
    public ModelAndView saveHospital(
            @RequestParam String hospitalName,
            @RequestParam String timezone,
            @RequestParam String type,
            @RequestParam Integer stateId,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                ModelAndView model = new ModelAndView("redirect:hospitals.html");
                HospitalBean stateBean = new HospitalBean();
                stateBean.setHospitalName(hospitalName);
                stateBean.setIsActive(1);
                StateBean state = new StateBean();
                state.setStateId(stateId);
                stateBean.setState(state);
                stateBean.setTimezone(timezone);
                stateBean.setType(HospitalType.valueOf(type));

                getBusinessFactory().getHospitalBusiness().saveHospital(stateBean);
                return model;
            } else {
                return new ModelAndView("redirect:home.html");
            }
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping("/orltoenter.html")
    @AuthCheck
    public ModelAndView orltoenter(HttpServletRequest request, HttpServletResponse response){
        try{

            UserToken token  = AppThreadLocal.getTokenLocal();
            if(AppConstants.ADMIN_ROLE.equalsIgnoreCase(token.getRole())){
                ModelAndView model = new ModelAndView("jsp/select-group");
                List<GroupBean> grps = getBusinessFactory().getGroupBusiness().getAllGroups();
                model.addObject("groups" ,grps);
                return model;
            } else {
                //ModelAndView model = new ModelAndView("redirect:home.html");
                ModelAndView model = new ModelAndView("jsp/select-group");
                List<GroupBean> grp = new ArrayList<>();
                List<UserGroupMap> map = getBusinessFactory().getUserService().getGroupMapsBeanByUser(token.getUserId());
                for(UserGroupMap e : map){
                    grp.add(e.getGroup());
                }
                model.addObject("groups" ,grp);
                return model;
            }


        }catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }

    @RequestMapping("/forgotPassword")
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("jsp/forgot-password");
        return modelAndView;
    }

    @RequestMapping("/recoveryPassword")
    public ModelAndView recoverPass(@RequestParam String email, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("jsp/forgot-password");
        if (ProcessData.isValid(email)) {


            if (getBusinessFactory().getUserService().recoverPassword(email)) {
                modelAndView.addObject("success", true);
            } else {
                modelAndView.addObject("success", false);
                modelAndView.addObject("message", "No active user account found for entered email address.");
            }

        } else {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "Please enter valid email address.");
        }

        return modelAndView;
    }

    @RequestMapping("/verification/{hospitalName}")
    @AuthCheck
    public ModelAndView verification(@PathVariable(value = "hospitalName") String hospitalName, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("jsp/verification");
        try {
            hospitalName = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            hospitalName = hospitalName.substring(14);
            GroupBean groupBean = getBusinessFactory().getGroupBusiness().getGroupByName(hospitalName);
            List<UserInfo> userBeanList = getBusinessFactory().getUserService().getLsaUserForGroup(groupBean.getGroupId(), 0);

            if (userBeanList == null || userBeanList.isEmpty()) {
                modelAndView.addObject("success", true);
                modelAndView.addObject("message", "This Hospital Member is already verified.");

            } else {
                modelAndView.addObject("user", userBeanList.get(0));
                modelAndView.addObject("group", groupBean);
            }

        } catch (Exception ex) {
            new ModelAndView("redirect:../index.html");
            throw new ORException(ex, 0);
        }
        return modelAndView;
    }

    @RequestMapping("/verification/verifyUser")
    @AuthCheck
    public ModelAndView verify(
            ApproverResponseDTO dto, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("jsp/verification");
        try {
            GroupBean groupBean = getBusinessFactory().getGroupBusiness().getGroup(dto.getGroupId());
            List<UserInfo> userBeanList = getBusinessFactory().getUserService().getLsaUserForGroup(groupBean.getGroupId(), 0);

            ApproverResponse toSave = new ApproverResponse();
            toSave.setName(dto.getFirstName() + " " + dto.getLastName());
            toSave.setRole(dto.getRole());
            toSave.setAction(dto.getAction().value);
            toSave.setCreatedDate(new Date());
            toSave.setEmail(dto.getEmail());
            toSave.setPhNo(dto.getPhNo());
            toSave.setUserId(userBeanList.get(0).getUserId());
            toSave.setGroupId(groupBean.getGroupId());

            ApproverResponse approverResponse = getBusinessFactory().getUserService().addApproverResponse(toSave);
            getBusinessFactory().getUserService().verificationMailNotification(groupBean.getGroupName(), approverResponse);
            modelAndView.addObject("success", true);

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return modelAndView;
    }


    enum ACTION {
        APPROVE("APPROVE"),
        REJECT("REJECT");

        private String value;

        ACTION(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Getter
    @Setter
    static class ApproverResponseDTO {
        private String firstName;
        private String lastName;
        private String email;
        private String phNo;
        private Integer groupId;
        private Integer userId;
        private String role;
        private ACTION action;

    }

}

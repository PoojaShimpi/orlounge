package org.orlounge.controller.user;

import org.orlounge.bean.LoginBean;
import org.orlounge.bean.UserBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserInfo;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.orlounge.service.GoogleRecaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.orlounge.common.AppConstants.GLOBAL_FAILURE_MSG;

/**
 * Created by Satyam Soni on 9/15/2015.
 *
 */
@Controller
public class UserController extends BaseController {




    @RequestMapping("/manageUsers.html")
    public ModelAndView manageUsers(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession){
        try{

            ModelAndView model = new ModelAndView("jsp/manage-users");
            List<UserInfo> users  = getBusinessFactory().getUserService().getUserInfoList();
            model.addObject("users" ,users);
            return model;

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/makeLsa.html")
    public String makeLsa(Integer userId, Integer groupId, HttpServletRequest request, HttpSession httpSession) {
        getBusinessFactory().getUserService().changeLsaFlag(userId, groupId, 1);
        UserToken token = AppThreadLocal.getTokenLocal();
        if (!token.getRole().equals(AppConstants.ADMIN_ROLE)) {
            getBusinessFactory().getUserService().changeLsaFlag(token.getUserId(), groupId, 0);
            httpSession.setAttribute("showLogoutMessage", true);
            token.setLSA(false);
            request.getSession().setAttribute("USER_TOKEN", token);
        }
        return "redirect:manageUsers.html";
    }

    @RequestMapping("/removeLsa.html")
    public String makeLsa(Integer userId, Integer groupId) {
        getBusinessFactory().getUserService().changeLsaFlag(userId, groupId, 0);
        return "redirect:manageUsers.html";
    }


    @RequestMapping(value = "/checkUserNameExists", produces = "application/json")
    public
    @ResponseBody
    Boolean userExists(String username) {
        try {
            return getBusinessFactory().getUserService().loadUserByUsername(username) == null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "/approveUser", produces = "application/json")
    public ModelAndView approveUser(Integer userId, Integer groupId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:manageUsers.html");
        try {

            UserToken user = AppThreadLocal.getTokenLocal();
            if (user.getUserId().equals(userId)) {
                throw new ORException(4);
            } else if (user.isLSA() || user.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                boolean done = getBusinessFactory().getUserService().doUserApproval(userId, groupId);
                if (!done) {
                    MessageUtils.failure(session, GLOBAL_FAILURE_MSG);

                } else {
                    MessageUtils.success(session, "User Approvess Successfully.");

                }
            } else {
                throw new ORException(3);
            }

        } catch (ORException ex) {
            MessageUtils.failure(session, ex.getMessage());
            // throw new ORException(2);
        } catch (Exception ex) {
            MessageUtils.failure(session, GLOBAL_FAILURE_MSG);
        }
        return modelAndView;

    }

    @RequestMapping(value = "/disapproveUser", produces = "application/json")
    public ModelAndView disapproveUser(Integer userId, Integer groupId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:manageUsers.html");
        try {

            UserToken user = AppThreadLocal.getTokenLocal();
            if (user.getUserId().equals(userId)) {
                throw new ORException(4);
            } else if (user.isLSA() || user.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                boolean done = getBusinessFactory().getUserService().doUserDeActive(userId, groupId);
                if (!done) {
                    MessageUtils.failure(session, GLOBAL_FAILURE_MSG);

                } else {
                    MessageUtils.success(session, "User Deactivated Successfully.");

                }
            } else {
                throw new ORException(3);
            }

        } catch (ORException ex) {
            MessageUtils.failure(session, ex.getMessage());
            // throw new ORException(2);
        } catch (Exception ex) {
            MessageUtils.failure(session, GLOBAL_FAILURE_MSG);
        }

        return modelAndView;

    }

    @RequestMapping(value = "/deleteUser", produces = "application/json")
    public ModelAndView deleteUser(Integer userId, Integer groupId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:manageUsers.html");
        try {

            UserToken user = AppThreadLocal.getTokenLocal();
            if (user.getUserId().equals(userId)) {
                throw new ORException(4);
            } else if (user.isLSA() || user.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                boolean done = getBusinessFactory().getUserService().doUserDelete(userId, groupId);
                if (!done) {
                    MessageUtils.failure(session, GLOBAL_FAILURE_MSG);

                } else {
                    MessageUtils.success(session, "User Deleted Successfully.");

                }
            } else {
                throw new ORException(3);
            }

        } catch (ORException ex) {
            MessageUtils.failure(session, ex.getMessage());
            // throw new ORException(2);
        } catch (Exception ex) {
            MessageUtils.failure(session, GLOBAL_FAILURE_MSG);
        }
        return modelAndView;

    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(
            @RequestParam("role") String role,
            @RequestParam(value = "hospitalPhNo", required = false) String hospitalPhNo,
            @RequestParam(value = "hospitalExt", required = false) String hospitalExt,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam(value = "hospitalId", required = false) Integer hospitalId,
            @RequestParam("numOfBeds") Integer numOfBeds,
            @RequestParam("hospitalCreateOrJoin") String hospitalCreateOrJoin,
            @RequestParam("hospitalBadge") MultipartFile hospitalBadge,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String radioEvent = request.getParameter("radio");
        String hospitalName =  request.getParameter("hospitalId");
        ModelAndView result = new ModelAndView("/index");
        if (hospitalBadge != null && !hospitalBadge.isEmpty()) {
            if (!ControllerUtils.isValidImageFile(hospitalBadge.getOriginalFilename())) {
                MessageUtils.failure(request.getSession(), "Invalid Image file. Please upload valid image file. ( eg: .jpg/.jpeg/.png/.bmp/.tiff)");
                return result;
            }
        }

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            MessageUtils.failure(request.getSession(), "Please validate Captcha. Tick mark on I'm not a robot");
            return result;
        } else {
            GoogleRecaptcha.verifyUserToken(gRecaptchaResponse);
        }

        final LoginBean loginBean = new LoginBean();
        loginBean.setIsActive(0);
        loginBean.setPass_Reset(1);
        loginBean.setEmail(email);
        loginBean.setPassword(password);
        loginBean.setUserCode(email);
        loginBean.setLastPassChange(new Date());

        UserBean user = new UserBean();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserCode(email);
        user.setCreatedDate(new Date());
        user.setHospitalTelExt(hospitalExt);
        user.setHospitalTelPh(hospitalPhNo);
        user.setRole(role);
        try {
            Map m = getBusinessFactory().getUserService().saveUserNewLSA(loginBean, user, radioEvent, hospitalName, hospitalId, numOfBeds, hospitalBadge, hospitalCreateOrJoin);
            if (m.get("success").equals(Boolean.TRUE)) {
                MessageUtils.success(request.getSession(), m.get("message").toString());
            } else {
                MessageUtils.failure(request.getSession(), m.get("message").toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return result;
    }


    @RequestMapping(value = "/changePassword.html", method = RequestMethod.POST)
    public ModelAndView changePass(Integer userId, String userCode, String oldPass, String newPass, String confirmPass, HttpServletRequest request, HttpServletResponse response) {


        String message = "";
        boolean success = false;
        try {
            success =
                    ProcessData.isValid(userId) &&
                            ProcessData.isValid(userCode) &&
                            ProcessData.isValid(oldPass) &&
                            ProcessData.isValid(newPass) &&
                            ProcessData.isValid(confirmPass) &&
                            confirmPass.equals(newPass) &&
                            getBusinessFactory().getUserService().changePass(userId, userCode, oldPass, newPass);
            message = success ? "Password Changed." : "Password Change Failed. Seems something wrong.";
            if (success) {
                MessageUtils.success(request.getSession(), message);
            } else {
                MessageUtils.failure(request.getSession(), message);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        ModelAndView result = new ModelAndView("redirect:profile.html");
        return result;
    }

}

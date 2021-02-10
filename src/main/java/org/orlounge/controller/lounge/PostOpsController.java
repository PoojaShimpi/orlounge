package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.PostOpBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Controller
@AuthCheck

public class PostOpsController extends BaseController {


    @RequestMapping("/viewpostops.html")
    public ModelAndView viewpostops(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-postop");
        try {
            PostOpBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getPostOpBusiness().getPostOp(id);
            } else {
                bean = new PostOpBean();
            }
            model.addObject("bean", bean);
            model.addObject("users", getBusinessFactory().getUserService().getUsersForGroup(AppThreadLocal.getTokenLocal().getCurrentGroupId()));
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping("/postop.html")
    public ModelAndView postop(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/post-op-main");
            List<PostOpBean> scheduleBeans = getBusinessFactory().getPostOpBusiness().getAccessiblePostOps();
            model.addObject("ops", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }



    @RequestMapping(value = "/postops/savePostOps", method = RequestMethod.POST)
    public ModelAndView save(PostOpBean bean, HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView("redirect:/postop.html");
        boolean success = false;
        Map map = new HashMap();
        try{
            success = getBusinessFactory().getPostOpBusiness().saveOrUpdate(bean);
            if (success) {
                MessageUtils.success(request.getSession(), "Post Operative Care Saved Successfully.");
            } else {
                throw new ORException(0);
            }


        }catch (Exception ex){
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }
    
    @RequestMapping(value = "/remove_postop", method = RequestMethod.GET)
    public ModelAndView removePostop(@RequestParam(value = "id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/postop.html");
        try {
            boolean flag = getBusinessFactory().getPostOpBusiness().removePostOpAccess(id);
            if (flag) {
                MessageUtils.failure(request.getSession(), "Remove Successfully");
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

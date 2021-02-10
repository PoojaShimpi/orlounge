package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.HandoverBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Controller
@AuthCheck

public class HandoverController extends BaseController {


    @RequestMapping(value = "/handover/viewFile/id/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            HandoverBean bean = getBusinessFactory().getHandoverBusiness().getHandover(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getHandoverBusiness().getHandoverFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                result = ControllerUtils.makeFileForView(response, f, bean.getName(), fileType);
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
        return result;
    }


    @RequestMapping(value = "/handover/downloadFile")
    public void download(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            HandoverBean bean = getBusinessFactory().getHandoverBusiness().getHandover(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getHandoverBusiness().getHandoverFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                ControllerUtils.downloadFile(response, f, bean.getName(), fileType);
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    @RequestMapping("/viewhandover.html")
    public ModelAndView viewchecklist(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-handover");
        try {
            HandoverBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getHandoverBusiness().getHandover(id);
            } else {
                bean = new HandoverBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping("/handovers.html")
    public ModelAndView checklists(HttpServletRequest request, HttpServletResponse response) {
        try {

            ModelAndView model = new ModelAndView("jsp/handover-main");
            List<HandoverBean> grps = getBusinessFactory().getHandoverBusiness().getHandovers();
            model.addObject("handovers", grps);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping(value = "/handover/saveHandover", method = RequestMethod.POST)
    public ModelAndView save(HandoverBean bean, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/handovers.html");
        boolean success = false;
        Map map = new HashMap();
        try {


            if (file != null && !file.isEmpty()) {
                if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                    MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                    return model;
                }
            }

            success = getBusinessFactory().getHandoverBusiness().saveHandover(bean, file);
            if (success) {
                MessageUtils.success(request.getSession(), "Handover Saved Successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/remove_handover", method = RequestMethod.GET)
    public ModelAndView removeCheckList(@RequestParam(value = "id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/handovers.html");
        try {
            boolean flag = getBusinessFactory().getHandoverBusiness().removeHandover(id);
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

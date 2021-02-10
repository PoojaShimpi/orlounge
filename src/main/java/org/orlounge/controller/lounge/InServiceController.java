package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.InServiceBean;
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
import java.util.List;

/**
 * Created by Satyam Soni on 10/18/2015.
 */
@Controller
@AuthCheck

public class InServiceController extends BaseController {

    @RequestMapping("/inservice.html")
    public ModelAndView inservice(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession){
        try{

            ModelAndView model = new ModelAndView("jsp/in-service-main");
            List<InServiceBean> scheduleBeans  = getBusinessFactory().getInServiceBusiness().getInServices();
            model.addObject("services" ,scheduleBeans);
            return model;

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }

    }



    @RequestMapping("/viewinservice.html")
    public ModelAndView viewinservice(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-in-service");
        try {
            InServiceBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getInServiceBusiness().getInService(id);
            } else {
                bean = new InServiceBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping(value = "/inservice/downloadFile")
    public void downloadCallSchedule(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            InServiceBean bean = getBusinessFactory().getInServiceBusiness().getInService(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getInServiceBusiness().getInServiceFile(bean, id);
                ControllerUtils.downloadFile(response, f, bean.getName(), bean.getFileType());
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    @RequestMapping(value = "/inservice/viewFile/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> redirect(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            InServiceBean bean = getBusinessFactory().getInServiceBusiness().getInService(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getInServiceBusiness().getInServiceFile(bean, id);
                result = ControllerUtils.makeFileForView(response, f, bean.getName(), bean.getFileType());
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

        return result;
    }


    @RequestMapping(value = "/inservice/saveInService", method = RequestMethod.POST)
    public ModelAndView save(InServiceBean inServiceBean, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/inservice.html");
        boolean success;
        try {

            if (file != null && !file.isEmpty()) {
                if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                    MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                    return model;
                }
            }

            success = getBusinessFactory().getInServiceBusiness().saveUpdate(inServiceBean, file);
            if (success) {
                MessageUtils.success(request.getSession(), "Service Saved Successfully.");
            } else {
                throw new ORException(0);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/remove_in_service", method = RequestMethod.GET)
    public ModelAndView removeInServiceController(@RequestParam(value = "id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/inservice.html");
        try {
            boolean flag = getBusinessFactory().getInServiceBusiness().removeInservice(id);
            if (flag) {
                MessageUtils.success(request.getSession(), "Removed Successfully.");
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

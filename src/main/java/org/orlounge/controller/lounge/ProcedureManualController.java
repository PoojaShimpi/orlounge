package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.ProcedureManualBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

public class ProcedureManualController extends BaseController {



    @RequestMapping("/procedure-manuals.html")
    public ModelAndView manuals(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/procedure-manual-main");
            List<ProcedureManualBean> scheduleBeans = getBusinessFactory().getProcedureManualBusiness().getProcedureManuals();
            model.addObject("manuals", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }



    @RequestMapping("/viewprmanual.html")
    public ModelAndView viewprmanual(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-procedure-manual");
        try {
            ProcedureManualBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getProcedureManualBusiness().getProcedureManual(id);
            } else {
                bean = new ProcedureManualBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping("/proceduremanual.html")
    public ModelAndView proceduremanual(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/procedure-manual-main");
        if (page == null || page == 0) {
            //model
        } else {

        }
        return model;
    }

    @RequestMapping(value = "/manual/downloadFile")
    public void downloadCallSchedule(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            ProcedureManualBean callScheduleBean = getBusinessFactory().getProcedureManualBusiness().getProcedureManual(id);
            if (ProcessData.isValid(callScheduleBean)) {
                File f = getBusinessFactory().getProcedureManualBusiness().getProcedureManualFile(callScheduleBean, id);
                ControllerUtils.downloadFile(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    @RequestMapping(value = "/manual/viewFile/id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<byte[]> viewCallScheduleFile(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {

            ProcedureManualBean callScheduleBean = getBusinessFactory().getProcedureManualBusiness().getProcedureManual(id);
            if (ProcessData.isValid(callScheduleBean)) {
                File f = getBusinessFactory().getProcedureManualBusiness().getProcedureManualFile(callScheduleBean, id);
                result = ControllerUtils.makeFileForView(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
            }
            response.setStatus(200);
        } catch (Exception ex) {
            throw new ORException(0);
        }
        return result;
    }


    @RequestMapping(value = "/manual/saveManual", method = RequestMethod.POST)
    public ModelAndView save(String topic, String id, String text, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/procedure-manuals.html");
        boolean success = false;
        Map map = new HashMap();
        if (file != null && !file.isEmpty()) {
            if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                return model;
            }
        }
        try {
            success = getBusinessFactory().getProcedureManualBusiness().saveManual(topic, id, text, file);
            if (success) {
                MessageUtils.success(request.getSession(), "Manual Saved Successfully");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/remove_manual.html", method = RequestMethod.GET)
    public ModelAndView removeprmanual(@RequestParam("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/procedure-manuals.html");
        try {
            boolean flag = getBusinessFactory().getProcedureManualBusiness().removeProcedureManualBean(id);
            if (flag) {
                MessageUtils.failure(request.getSession(), "Remove Successfully");
            } else {
                MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            throw new ORException(ex, 0);
        }
        return model;
    }
}

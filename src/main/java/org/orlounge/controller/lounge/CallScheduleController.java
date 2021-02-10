package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.CallScheduleBean;
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

public class CallScheduleController extends BaseController {




    @RequestMapping("/viewcallschedule.html")
    public ModelAndView viewcallschedule(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession){
        try{

            ModelAndView model = new ModelAndView("jsp/view-call-schedule");
            CallScheduleBean bean = null;
            if(ProcessData.isValid(id)){
                bean = getBusinessFactory().getCallScheduleBusiness().getCallSchedule(id);
            } else {
                bean = new CallScheduleBean();
            }
            model.addObject("bean" ,bean);
            model.addObject(AppConstants.ACTION,action);

            return model;

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }

    }



    @RequestMapping("/call-schedule.html")
    public ModelAndView callSchedule(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession){
        try{

            ModelAndView model = new ModelAndView("jsp/call-schedule-main");
            List<CallScheduleBean> scheduleBeans  = getBusinessFactory().getCallScheduleBusiness().getCallSchedules();
            model.addObject("schedules" ,scheduleBeans);
            return model;

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }

    }



    @RequestMapping(value = "/call/downloadFile")
    public void downloadCallSchedule(@RequestParam(value = "id", required = true)Integer id, HttpServletRequest request, HttpServletResponse response){
        try{

            CallScheduleBean callScheduleBean = getBusinessFactory().getCallScheduleBusiness().getCallSchedule(id);
            if(ProcessData.isValid(callScheduleBean)){
                File f = getBusinessFactory().getCallScheduleBusiness().getCallScheduleFile(callScheduleBean,id);
                ControllerUtils.downloadFile(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
                f.deleteOnExit();
            }

        }catch (Exception ex){
            throw new ORException(0);
        }
    }



    @RequestMapping(value = "/call/saveSchedule", method = RequestMethod.POST)
    public ModelAndView save(String topic, @RequestParam(value = "text", required = false) String text, String id, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/call-schedule.html");
        boolean success = false;
        Map map = new HashMap();
        try {
            if (file != null && !file.isEmpty()) {
                if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                    MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                    return model;
                }
            }
            success = getBusinessFactory().getCallScheduleBusiness().saveSchedule(topic, id, text, file);
            if (success) {
                MessageUtils.success(request.getSession(), "Schedule Saved Successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }


    @RequestMapping(value = "/call/viewFile/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> redirect(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            CallScheduleBean callScheduleBean = getBusinessFactory().getCallScheduleBusiness().getCallSchedule(id);
            if (ProcessData.isValid(callScheduleBean)) {
                File f = getBusinessFactory().getCallScheduleBusiness().getCallScheduleFile(callScheduleBean, id);
                result = ControllerUtils.makeFileForView(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

        return result;
    }

    @RequestMapping(value = "/remove_call_schedule/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCallSchedule(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/call-schedule.html");
        try {
            boolean success = getBusinessFactory().getCallScheduleBusiness().deleteCallSchedule(id);
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

}

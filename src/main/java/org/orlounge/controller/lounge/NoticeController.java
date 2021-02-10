package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.NoticeBean;
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


@Controller
@AuthCheck

public class NoticeController extends BaseController {


    @RequestMapping("/notices.html")
    public ModelAndView notices(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/notice-main");
            List<NoticeBean> scheduleBeans = getBusinessFactory().getNoticeBusiness().getNotices();
            model.addObject("notices", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping("/viewnotice.html")
    public ModelAndView viewnotice(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-notice");
        try {


            NoticeBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getNoticeBusiness().getNotice(id);
            } else {
                bean = new NoticeBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping(value = "/notice/viewFile/id/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            NoticeBean callScheduleBean = getBusinessFactory().getNoticeBusiness().getNotice(id);
            if (ProcessData.isValid(callScheduleBean)) {
                File f = getBusinessFactory().getNoticeBusiness().getNoticeFile(callScheduleBean, id);
                result = ControllerUtils.makeFileForView(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
        return result;
    }


    @RequestMapping(value = "/notice/downloadFile")
    public void download(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            NoticeBean callScheduleBean = getBusinessFactory().getNoticeBusiness().getNotice(id);
            if (ProcessData.isValid(callScheduleBean)) {
                File f = getBusinessFactory().getNoticeBusiness().getNoticeFile(callScheduleBean, id);
                ControllerUtils.downloadFile(response, f, callScheduleBean.getName(), callScheduleBean.getFileType());
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    @RequestMapping(value = "/notice/saveNotice", method = RequestMethod.POST)
    public ModelAndView save(String topic, Integer id, String text, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/notices.html");
        boolean success = false;
        Map map = new HashMap();
        if (file != null && !file.isEmpty()) {
            if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                return model;
            }
        }
        try {
            success = getBusinessFactory().getNoticeBusiness().saveNotice(topic, id, text, file);
            if (success)
                MessageUtils.success(request.getSession(), "Notice Saved Successfully.");
            else
                throw new ORException(0);

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/remove_notice/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCallSchedule(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/notices.html");
        try {
            boolean success = getBusinessFactory().getNoticeBusiness().deleteNotice(id);
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

package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.ChecklistBean;
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

public class ChecklistController extends BaseController {


    @RequestMapping(value = "/checklist/viewFile/id/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            ChecklistBean bean = getBusinessFactory().getChecklistBusiness().getChecklist(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getChecklistBusiness().getChecklistFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                result = ControllerUtils.makeFileForView(response, f, bean.getName(), fileType);
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
        return result;
    }


    @RequestMapping(value = "/checklist/downloadFile")
    public void download(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            ChecklistBean bean = getBusinessFactory().getChecklistBusiness().getChecklist(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getChecklistBusiness().getChecklistFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                ControllerUtils.downloadFile(response, f, bean.getName(), fileType);
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    @RequestMapping("/viewchecklist.html")
    public ModelAndView viewchecklist(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-checklist");
        try {
            ChecklistBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getChecklistBusiness().getChecklist(id);
            } else {
                bean = new ChecklistBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }


    @RequestMapping("/checklists.html")
    public ModelAndView checklists(HttpServletRequest request, HttpServletResponse response) {
        try {

            ModelAndView model = new ModelAndView("jsp/checklist-main");
            List<ChecklistBean> grps = getBusinessFactory().getChecklistBusiness().getChecklists();
            model.addObject("checklist", grps);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @RequestMapping(value = "/checklist/saveChecklist", method = RequestMethod.POST)
    public ModelAndView save(ChecklistBean bean, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:/checklists.html");
        boolean success = false;
        Map map = new HashMap();
        try {


            if (file != null && !file.isEmpty()) {
                if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageOrDocFile(file)) {
                    MessageUtils.failure(request.getSession(), "Invalid File. The file should be less than 2 mb and should be of specified file format.");
                    return model;
                }
            }

            success = getBusinessFactory().getChecklistBusiness().saveChecklist(bean, file);
            if (success) {
                MessageUtils.success(request.getSession(), "Checklist Saved Successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/remove_check_list", method = RequestMethod.GET)
    public ModelAndView removeCheckList(@RequestParam(value = "id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/checklists.html");
        try {
            boolean flag = getBusinessFactory().getChecklistBusiness().remvoeCheckList(id);
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

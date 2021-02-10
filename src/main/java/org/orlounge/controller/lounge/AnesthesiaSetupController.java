package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.AnaethesiaSetupBean;
import org.orlounge.bean.AnaethesiaSetupNewBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ControllerUtils;
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
import java.io.File;
import java.util.List;

import static org.orlounge.common.AppConstants.GLOBAL_FAILURE_MSG;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Controller
@AuthCheck

public class AnesthesiaSetupController extends BaseController {


   /* @RequestMapping("/setup-list.html")
    public ModelAndView prefs(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/an-setup-main");
            List<AnaethesiaSetupBean> scheduleBeans = getBusinessFactory().getAnaesthesiaSetupBusiness().getAllSetups();
            model.addObject("beans", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }
*/
   /* @RequestMapping(value = "/setup/viewFile/id/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> result = null;

        try {
            AnaethesiaSetupBean bean = getBusinessFactory().getAnaesthesiaSetupBusiness().getSetupById(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getAnaesthesiaSetupBusiness().getSetupFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                result = ControllerUtils.makeFileForView(response, f, bean.getName(), fileType);
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
        return result;
    }*/


   /* @RequestMapping(value = "/save-an-setup.html")
    public ModelAndView saveIns(@ModelAttribute AnaethesiaSetupBean bean, HttpServletRequest request, HttpSession httpSession) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            bean.setCreatorId(token.getUserId());
            bean.setCreatedDate(new Date());
            bean.setGroupId(token.getCurrentGroupId());
            bean.setActive(1);
            getBusinessFactory().getAnaesthesiaSetupBusiness().saveSetup(bean);
            return new ModelAndView("redirect:setup-list.html");
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }*/



    @RequestMapping(value = "/setup/downloadFile")
    public void download(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {

            AnaethesiaSetupBean bean = getBusinessFactory().getAnaesthesiaSetupBusiness().getSetupById(id);
            if (ProcessData.isValid(bean)) {
                File f = getBusinessFactory().getAnaesthesiaSetupBusiness().getSetupFile(bean, id);
                String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);
                ControllerUtils.downloadFile(response, f, bean.getName(), fileType);
                f.deleteOnExit();
            }

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


   /* @RequestMapping("/viewsetup.html")
    public ModelAndView view(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/view-setup");
            AnaethesiaSetupBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getAnaesthesiaSetupBusiness().getSetupById(id);
            } else {
                bean = new AnaethesiaSetupBean();
            }
            model.addObject("bean", bean);

            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }*/

    @RequestMapping(value = "/new_save_an_setup.html", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateAnaethesiaSetupNewBean(
                                                            @RequestParam(required = false) Integer id,
                                                            @RequestParam(required = false) String operation,
                                                            @RequestParam(required = false) String type_of_anesthesia,
                                                            @RequestParam(required = false) String pre_induction,
                                                            @RequestParam(required = false) String intra_operative,
                                                            @RequestParam(required = false) String emergence,
                                                           HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("redirect:setup-list.html");
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            boolean isUpdate = false;
            AnaethesiaSetupNewBean bean = new AnaethesiaSetupNewBean();
            if (id != null) {
                isUpdate = true;
                AnaethesiaSetupNewBean schedule = getBusinessFactory().getAnaesthesiaSetupBusiness().getAnaethesiaSetupNewBeanById(id);
                schedule.setEmergence(emergence);
                schedule.setOperation(operation);
                schedule.setTypeOfAnesthesia(type_of_anesthesia);
                schedule.setPreInduction(pre_induction);
                schedule.setIntraOperative(intra_operative);
                bean =  schedule;
            }
            else {
            bean.setActive(1);
            bean.setEmergence(emergence);
            bean.setOperation(operation);
            bean.setTypeOfAnesthesia(type_of_anesthesia);
            bean.setPreInduction(pre_induction);
            bean.setIntraOperative(intra_operative);
            bean.setUserId(token.getUserId());
            bean.setGroupId(token.getCurrentGroupId());

            }
            boolean isCreated = getBusinessFactory().getAnaesthesiaSetupBusiness().saveOrUpdateAnaethesiaSetupNewBean(bean, isUpdate);
            if (isCreated) {
                MessageUtils.success(request.getSession(), "Anesthesia Setup added successfully.");
            } else {
                MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
            }
        } catch (Exception e) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
            throw new ORException(e, 0);
        }
        return modelAndView;
    }

    @RequestMapping("/setup-list.html")
    public ModelAndView prefs() {
        try {
            ModelAndView model = new ModelAndView("jsp/an-setup-main");
            List<AnaethesiaSetupNewBean> allAnaethesiaSetupNewBean = getBusinessFactory().getAnaesthesiaSetupBusiness().getAllAnaethesiaSetupNewBean();
            model.addObject("beans", allAnaethesiaSetupNewBean);
            return model;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @RequestMapping("/viewsetup.html")
    public ModelAndView view(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action) {
        ModelAndView model = new ModelAndView("jsp/view-setup");
        try {
            AnaethesiaSetupNewBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getAnaesthesiaSetupBusiness().getAnaethesiaSetupNewBeanById(id);
            } else {
                bean = new AnaethesiaSetupNewBean();
            }
            model.addObject("bean", bean);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }

    @RequestMapping("/remove.html")
    public ModelAndView removeAnaethesiaSetupNewBean(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:setup-list.html");
        try {
            boolean isCreated = getBusinessFactory().getAnaesthesiaSetupBusiness().removeAnaethesiaSetupNewBean(id);
            if (isCreated) {
                MessageUtils.success(request.getSession(), "Remove successfully.");
            } else {
                MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
            }
        } catch (Exception e) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
            throw new ORException(e, 0);
        }

        return modelAndView;
    }

}

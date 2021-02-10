package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.ProjectDocBean;
import org.orlounge.bean.ProjectInfoBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Controller
@AuthCheck

public class ProjectController extends BaseController {


    @RequestMapping("/project-list.html")
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/projects-main");
            List<ProjectInfoBean> beans = getBusinessFactory().getProjectBusiness().getMyAccessibleProjects();
            model.addObject("beans", beans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }


    @RequestMapping("/view-project.html")
    public ModelAndView view(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/view-project");
            model.addObject("bean", getBusinessFactory().getProjectBusiness()
                    .getProject(id));
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }


    @RequestMapping("/create-project.html")
    public ModelAndView create(HttpServletRequest request, HttpSession httpSession) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            ModelAndView model = new ModelAndView("jsp/create-project-main");
            model.addObject("bean", new ProjectInfoBean());
            model.addObject("users", getBusinessFactory().getUserService().getUsersForGroup(token.getCurrentGroupId()));
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/add-new-doc.html")
    public ModelAndView createDoc(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/create-project-doc-main");
            ProjectDocBean bean = new ProjectDocBean();
            bean.setProject(getBusinessFactory().getProjectBusiness().getProject(id));
            model.addObject("bean", bean);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/edit-doc.html")
    public ModelAndView editDoc(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/create-project-doc-main");
            ProjectDocBean bean = getBusinessFactory().getProjectBusiness().getProjectDoc(id);
            if (bean.getIsLock().equals(1)) {
                MessageUtils.failure(request.getSession(), "You cannot edit this document, As it is in already in use.");
                model = new ModelAndView("redirect:view-project.html?id=" + bean.getProjectId() + "&msg=You cannot edit this document, As it is in already in use.&success=false");


            } else {
                bean.setIsLock(1);

                getBusinessFactory().getProjectBusiness().saveDoc(bean);

            }

            model.addObject("bean", bean);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/save-project.html")
    public ModelAndView saveProject(@ModelAttribute ProjectInfoBean bean, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("redirect:project-list.html");
            UserToken token = AppThreadLocal.getTokenLocal();

            bean.setGroupId(token.getCurrentGroupId());
            bean.setCreatedById(token.getUserId());
            bean.setOwnerId(token.getUserId());
            bean.setCreatedDate(new Date());
            bean.setIsActive(1);

            getBusinessFactory().getProjectBusiness().saveProject(bean);


            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/save-project-doc.html")
    public ModelAndView saveProject(@ModelAttribute ProjectDocBean b, HttpServletRequest request, HttpSession httpSession) {
        try {


            UserToken token = AppThreadLocal.getTokenLocal();

            ProjectDocBean bean = b;
            if (bean.getId() != null) {
                bean = getBusinessFactory().getProjectBusiness().getProjectDoc(bean.getId());
                bean.setDesc(b.getDesc());
                bean.setBody(b.getBody());

            } else {
                bean.setCreatorUserId(token.getUserId());
                bean.setCreatedDate(new Date());
                bean.setActive(1);
            }
            bean.setIsLock(0);
            bean.setLastUpdatedUserId(token.getUserId());
            bean.setLastUpdatedDate(new Date());
            ModelAndView model = new ModelAndView("redirect:view-project.html?id=" + bean.getProjectId());

            getBusinessFactory().getProjectBusiness().saveDoc(bean);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }


}

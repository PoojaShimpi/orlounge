package org.orlounge.business;

import org.orlounge.bean.DocTemplateBean;
import org.orlounge.bean.ProjectAccessBean;
import org.orlounge.bean.ProjectDocBean;
import org.orlounge.bean.ProjectInfoBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Component
public class ProjectBusiness extends AbstractBaseBusiness {

    public List<ProjectInfoBean> getMyAccessibleProjects() {
        return getServiceFactory().getProjectService().getMyAccessibleProjects();
    }


    public ProjectInfoBean getProject(Integer id) {
        return getServiceFactory().getProjectService().getProject(id);
    }
    public ProjectDocBean getProjectDoc(Integer id) {
        return getServiceFactory().getProjectService().getProjectDoc(id);
    }


    public ProjectInfoBean saveProject(ProjectInfoBean bean) {
        getServiceFactory().getProjectService().saveProject(bean);

        String[] access = bean.getUserPreveliges().split(",");

        ProjectAccessBean accessBean = new ProjectAccessBean();
        accessBean.setActive(1);
        accessBean.setProject(bean);
        accessBean.setProjectId(bean.getId());
        accessBean.setUserId(AppThreadLocal.getTokenLocal().getUserId());
        saveProjectAccess(accessBean);
        for(String e : access){
            if(StringUtil.isEmptyString(e)){
                continue;
            }
            Integer userId = Integer.parseInt(e);
            accessBean = new ProjectAccessBean();
            accessBean.setActive(1);
            accessBean.setProject(bean);
            accessBean.setProjectId(bean.getId());
            accessBean.setUserId(userId);
            saveProjectAccess(accessBean);

        }
        return bean;
    }
    public ProjectAccessBean saveProjectAccess(ProjectAccessBean bean) {
        return getServiceFactory().getProjectService().saveProject(bean);
    }

    public List<DocTemplateBean> getAllDocTemplates() {
        return getServiceFactory().getProjectService().getAllDocTemplates();
    }

    public ProjectDocBean saveDoc(ProjectDocBean bean) {
        return getServiceFactory().getProjectService().saveDoc(bean);
    }
}
package org.orlounge.service;

import org.orlounge.bean.DocTemplateBean;
import org.orlounge.bean.ProjectAccessBean;
import org.orlounge.bean.ProjectDocBean;
import org.orlounge.bean.ProjectInfoBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Service
@Transactional
public class ProjectService extends AbstractBaseService {

    public List<ProjectInfoBean> getMyAccessibleProjects() {
        return getDaoFactory().getProjectDAO().getMyAccessibleProjects();
    }


    public List<DocTemplateBean> getAllDocTemplates() {
        return getDaoFactory().getProjectDAO().getAllDocTemplates();
    }

    public ProjectInfoBean saveProject(ProjectInfoBean bean) {

        return getDaoFactory().getProjectDAO().save(bean);
    }

    public ProjectAccessBean saveProject(ProjectAccessBean bean) {
        return getDaoFactory().getProjectAccessDAO().save(bean);
    }

    public ProjectInfoBean getProject(Integer id) {
        return getDaoFactory().getProjectDAO().getProjectById(id);
    }

    public ProjectDocBean getProjectDoc(Integer id) {
        return getDaoFactory().getProjectDocDAO().getOne(id);

    }

    public ProjectDocBean saveDoc(ProjectDocBean bean) {
        return getDaoFactory().getProjectDocDAO().save(bean);
    }
}

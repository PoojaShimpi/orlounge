package org.orlounge.dao;

import org.orlounge.bean.DocTemplateBean;
import org.orlounge.bean.ProjectInfoBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Repository
public interface ProjectDAO extends JpaRepository<ProjectInfoBean, Integer> {

    default List<ProjectInfoBean> getMyAccessibleProjects() {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (AppConstants.ADMIN_ROLE.equalsIgnoreCase(token.getRole())) {
                return getAllActive();
            } else {
                return findAllByAccessLevel(token.getUserId(), token.getRole());
            }

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    @Query("from DocTemplateBean  where active = 1")
    public List<DocTemplateBean> getAllDocTemplates();

    @Query("from ProjectInfoBean  where isActive = 1")
    public List<ProjectInfoBean> getAllActive();

    default ProjectInfoBean getProjectById(Integer id) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (AppConstants.ADMIN_ROLE.equalsIgnoreCase(token.getRole())) {
                return findById(id).get();
            } else {
                return findByAccessLevel(token.getUserId(), id, token.getRole());
            }

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @Query(" from ProjectInfoBean where  ( accessType = 'PUBLIC' or (accessType = 'PRIVATE' and ownerId = :userId)" +
            " or accessType = :role)  order by createdDate DESC ")
    public List<ProjectInfoBean> findAllByAccessLevel(@Param("userId") Integer ownerId, @Param("role") String role);


    @Query(" from ProjectInfoBean where  ( accessType = 'PUBLIC' or (accessType = 'PRIVATE' and ownerId = :userId)" +
            " or accessType = :role) and id = :id   ")
    public ProjectInfoBean findByAccessLevel(@Param("userId") Integer ownerId, @Param("id") Integer id, @Param("role") String role);


}

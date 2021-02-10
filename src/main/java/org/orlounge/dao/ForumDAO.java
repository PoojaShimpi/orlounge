package org.orlounge.dao;

import org.orlounge.bean.ForumBean;
import org.orlounge.bean.ForumCommentBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.exception.ORException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public interface ForumDAO extends PagingAndSortingRepository<ForumBean, Integer> {

    default List<ForumBean> getForums(Integer groupId, int start, int limit, String role) {
        try {
            String userrole = AppThreadLocal.getTokenLocal().getRole();
            if ("all".equalsIgnoreCase(role)) {

            }else if(
                    "get_well_soon_advisory".equalsIgnoreCase(role) ||
                    "asc_advisory".equalsIgnoreCase(role) ||
                    "hlounge_advisory".equalsIgnoreCase(role)
                    ){

            }else if(!(userrole.equalsIgnoreCase(AppConstants.ORM_ROLE) || userrole.equalsIgnoreCase(AppConstants.ADMIN_ROLE)) && !role.equalsIgnoreCase(userrole) ){
                role = userrole;
            }

            if (role.equalsIgnoreCase(AppConstants.ORM_ROLE) || role.equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                return findAllByGroupIdOrderByCreatedDateDesc(groupId, PageRequest.of(start, limit, Sort.by(Sort.Direction.DESC, "createdDate")));
            } else {
                return findAllByGroupIdAndRoleOrderByCreatedDateDesc(groupId, role, PageRequest.of(start, limit, Sort.by(Sort.Direction.DESC, "createdDate")));
            }

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    List<ForumBean> findAllByGroupIdOrderByCreatedDateDesc(@Param("grpId") Integer groupId, Pageable pageable);

    List<ForumBean> findAllByGroupIdAndRoleOrderByCreatedDateDesc(@Param("grpId") Integer groupId, @Param("role") String role, Pageable pageable);

    @Query(" from ForumCommentBean where forumId= :forumId and isActive = 1 order by  createdDate asc")
    public List<ForumCommentBean> getForumComments(@Param("forumId") Integer forumId);

}


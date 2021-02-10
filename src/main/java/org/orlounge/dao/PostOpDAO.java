package org.orlounge.dao;

import org.orlounge.bean.PostOpBean;
import org.orlounge.common.AppConstants;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/22/2015.
 */
@Component
public interface PostOpDAO extends JpaRepository<PostOpBean, Integer> {

    @Query("from PostOpBean where id = :id and groupId = :grpId")
    public PostOpBean getPostOp(Integer id, Integer grpId);

    @Query("from PostOpBean where groupId = :grpId and isActive = 1")
    public List<PostOpBean> getPostOps(Integer grpId);

    @Query("from PostOpBean p join PostOpAccessBean a on a.postOp = p and a.userId = :userId and a.active = 1 and p.groupId = :groupId and p.isActive = 1")
    List<PostOpBean> getAccessedPostOps(Integer userId, Integer groupId);

    default List<PostOpBean> getAccessiblePostOps(Integer userId, Integer grpId, String role) {
        if (AppConstants.ADMIN_ROLE.equalsIgnoreCase(role) || AppConstants.ORM_ROLE.equalsIgnoreCase(role)) {
            return getPostOps(grpId);
        } else {
            return getAccessedPostOps(userId, grpId);
        }
    }
    
    
//    @Transactional
//    @Modifying
//    @Query("DELETE from PostOpAccessBean where id in (select t.id from (select id from PostOpAccessBean a where a.postOpId= :id) as t)")
//    public int deletePostOpAccess(@Param("id") Integer id);
//     
//    @Transactional
//    @Modifying
//    @Query("delete from PostOpBean where id= :id")
//    public int deletePostOp(@Param("id") Integer id);
//
    default PostOpBean deletePostOp(PostOpBean bean) {
        try {
            bean.setIsActive(0);
            return save(bean);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }
}

package org.orlounge.dao;

import org.orlounge.bean.StaffInfoBean;
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
public interface StaffInfoDAO extends JpaRepository<StaffInfoBean, Integer> {

    @Query("from StaffInfoBean where userId = :id")
    public StaffInfoBean getStaffInfo(@Param("id") Integer userId);


    @Query(value = " from StaffInfoBean s inner join UserGroupMap g on g.userId = s.userId and g.groupId = :grpId ")
    public List<StaffInfoBean> getAllGrpStaffInfo(@Param("grpId") Integer grpId);


    @Query("from  StaffInfoBean s join UserBean u on s.userId=u.userId join" +
            " UserGroupMap ug on ug.userId =s.userId " +
            "where s.id = :infoId and ug.groupId = :groupId and s.userId = :userId")
    public StaffInfoBean getStaffByIdAndUserId(Integer infoId, Integer userId, Integer groupId);
    
    @Transactional
    @Modifying
    @Query("delete from StaffInfoBean where id= :id")
    public int deleteStaff(@Param("id") Integer id);
    
}

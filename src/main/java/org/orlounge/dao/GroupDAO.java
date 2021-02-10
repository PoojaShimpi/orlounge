package org.orlounge.dao;

import org.orlounge.bean.GroupBean;
import org.orlounge.bean.LastAccessGroupBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public interface GroupDAO extends JpaRepository<GroupBean, Integer> {


    GroupBean findByHospitalId(@Param("id") Integer id);


    @Query("from LastAccessGroupBean where userId = :id")
    LastAccessGroupBean getLastAccessGroupIdByUser(@Param("id") Integer id);

    @Query(value = "select g.group_id as groupId, g.group_name as groupName from group_tbl g" +
            " inner join  group_user_map  ug on ug.group_id = g.group_id and ug.user_id = :id ",
            nativeQuery = true)
    public List<GroupBean> getGroupByUserForAction(@Param("id") Integer id);

    @Query(" from GroupBean where trialFlag = -1 and isActive = 1")
    public List<GroupBean> fetchCommonGroup();


    GroupBean findByGroupName(String groupName);


}

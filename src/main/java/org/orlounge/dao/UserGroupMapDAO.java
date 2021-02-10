package org.orlounge.dao;

import org.orlounge.bean.UserGroupMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Repository
public interface UserGroupMapDAO extends JpaRepository<UserGroupMap, Integer> {


    @Query("from UserGroupMap ug where ug.group.isActive  = 1 and ug.isActive = 1 and ug.userId = :id")
    List<UserGroupMap> getGroupsByUser(@Param("id") Integer id);

    @Query("from UserGroupMap ug join GroupBean g on ug.group = g and g.isActive  = 1 and ug.isActive = 1 and ug.userId = :id")
    List<UserGroupMap> getGroupsByUserIncludingInActive(@Param("id") Integer id);


    @Query("from UserGroupMap ug join GroupBean g on ug.group = g and g.isActive  = 1  and ug.groupId = :id and ug.role = :role and ug.isLSA = 1 ")
    List<UserGroupMap> getGroupLsaAsRole(@Param("role") String role, @Param("id") Integer grpid);


    public UserGroupMap findByUserIdAndGroupId(Integer userId, Integer grpId);

    @Query("from UserGroupMap gum" +
            " inner join UserBean u  on u.userId = gum.userId  and u.email = :emailId and gum.role = 'orm' " +
            " join GroupBean g on gum.groupId = g.groupId " +
            " join HospitalBean h on g.hospitalId = h.hospitalId and h.hospitalId = :hospitalId ")
    public UserGroupMap checkEmailIdByRoleAndHospitalId(@Param("emailId") String emailId,
                                                        @Param("hospitalId") Integer hospitalId);

    @Query("update UserGroupMap set isLSA = :lsaFlag " +
            " where userId = :userId and groupId = :groupId")
    @Modifying
    public void changeLsaFlag(Integer userId, Integer groupId, Integer lsaFlag) ;
}

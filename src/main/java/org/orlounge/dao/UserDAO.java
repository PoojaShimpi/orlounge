package org.orlounge.dao;

import org.orlounge.bean.UserBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserInfo;
import org.orlounge.common.UserToken;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Component
public interface UserDAO extends JpaRepository<UserBean, Integer> {


    @Query("from UserBean where userCode = :userCode")
    UserBean getUserByUserCode(@Param("userCode") String userCode);



    default List<UserInfo> getUserAsPerRole() {
        try {
            UserToken user = AppThreadLocal.getTokenLocal();
            if (user.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE)) {
                return getUserForAllGroup();
            } else {
                return getUserForGroup(user.getCurrentGroupId());
            }

        } catch (Exception ex) {
            throw new ORException(0);

        }
    }


    @Query(value = "select u.user_id as userId, l.user_login_id as userLoginId , l.user_code as userCode, u.email as email, " +
            "                    gm.role as role, g.group_name as groupName, g.group_id as grpId, " +
            "                    IF (l.is_active = 0 ,'ACCESS_PENDING', if(gm.is_active = 0, 'PENDING', IF(l.is_active = -1 , 'DELETED', 'ACTIVE'))) as status, " +
            "                    u.created_date as date, " +
            "                    u.first_name as firstName, " +
            "                    u.last_name as lastName," +
            "                    gm.is_lsa as lsa " +
            "                     from user u inner join login l on u.user_code = l.user_code  " +
            "                    inner join group_user_map gm on gm.user_id = u.user_id " +
            "                    inner join group_tbl g on g.group_id = gm.group_id ",
            nativeQuery = true)
    public List<UserInfo> getUserForAllGroup();

    @Query(value = "select" +
            " u.user_id as userId, " +
            " l.user_login_id as userLoginId , " +
            " l.user_code as userCode, " +
            "u.email as email, " +
            " gm.role as role," +
            " g.group_name as groupName," +
            " g.group_id as grpId, " +
            " IF (l.is_active = 0 ,'ACCESS_PENDING', if(gm.is_active = 0, 'PENDING', IF(l.is_active = -1 , 'DELETED', 'ACTIVE'))) as status, " +
            " u.created_date as date, " +
            "  u.first_name as firstName, " +
            " u.last_name as lastName," +
            " gm.is_lsa as lsa " +
            "                     from user u inner join login l on u.user_code = l.user_code  " +
            "                    inner join group_user_map gm on gm.user_id = u.user_id " +
            "                    inner join group_tbl g on g.group_id = gm.group_id  and g.group_id = :id ",
            nativeQuery = true)
    public List<UserInfo> getUserForGroup(@Param("id") Integer groupid);

    @Query(value = "select u.user_id as userId, " +
            "l.user_login_id as userLoginId ," +
            " l.user_code as userCode," +
            " u.email as email, " +
            "                    gm.role as role, g.group_name as groupName, g.group_id as grpId, " +
            "                    IF (l.is_active = 0 ,'ACCESS_PENDING', if(gm.is_active = 0, 'PENDING', IF(l.is_active = -1 , 'DELETED', 'ACTIVE'))) as status, " +
            "                    u.created_date as date, " +
            "                    u.first_name as firstName, " +
            "                    u.last_name as lastName, " +
            "                       gm.is_lsa as lsa " +
            "                     from user u inner join login l on u.user_code = l.user_code  " +
            "                    inner join group_user_map gm on gm.user_id = u.user_id  and u.user_id = :userId" +
            "                    inner join group_tbl g on g.group_id = gm.group_id  and g.group_id = :grpId ",
            nativeQuery = true)
    public UserInfo getUserForGroup(@Param("userId") Integer userId, @Param("grpId") Integer groupId);

    @Query(value = "select u.user_id as userId, u.hospital_badge_id as image," +
            " u.first_name as firstName, " +
            "u.last_name as lastName," +
            " u.email as email, " +
            "ug.role as role," +
            " u.user_code as userCode " +
            " from group_user_map ug " +
            "inner join group_tbl g on g.group_id = ug.group_id and g.is_active =1 and ug.is_active = 1 " +
            "inner join user u on  u.user_id = ug.user_id  " +
            "inner join login l on l.user_code = u.user_code and l.is_active = 1 " +
            "and g.group_id = :groupId and ug.is_lsa = 1 order by u.created_date desc",
    nativeQuery = true)
    public List<UserInfo> getLsaUsersForGroup(Integer groupId);

    @Query(value = "select u.user_id as userId, u.hospital_badge_id as image, u.first_name as firstName, u.last_name as lastName, " +
            " u.email as email, ug.role as role, u.user_code as userCode " +
            " from group_user_map ug " +
            "inner join group_tbl g on g.group_id = ug.group_id and g.is_active =1 and ug.is_active = :active " +
            "inner join user u on  u.user_id = ug.user_id " +
            "inner join login l on l.user_code = u.user_code and l.is_active = :active " +
            "and g.group_id = :groupId and ug.is_lsa = 1 order by u.created_date desc",
    nativeQuery = true)
    public List<UserInfo> getLsaUsersForGroup(Integer groupId, Integer active) ;

    @Query(value = "select u.user_id as userId,  u.first_name as firstName, u.last_name as lastName, u.email as email, u.user_code as userCode " +
            " from user u " +
            "where u.user_code = 'superadmin' ", nativeQuery = true)
    public List<UserInfo> getSuperUser();






    @Query(value = "select u.user_id as userId,u.user_code as userCode, u.email as email, " +
            "    gm.role as role, g.group_name as groupName, g.group_id as grpId, " +
            "    IF (g.is_active = 0,'ACCESS_PENDING', if(gm.is_active = 0, 'PENDING', IF(g.is_active = -1 , 'DELETED', 'ACTIVE'))) as status," +
            "    u.created_date as date," +
            "    u.first_name as firstName," +
            "    u.last_name as lastName " +
            "    from user u  " +
            "    inner join group_user_map gm on gm.user_id = u.user_id " +
            "    and u.user_id = :userId " +
            "    inner join group_tbl g on g.group_id = gm.group_id " +
            "    and g.group_id = :groupId ", nativeQuery = true)
    public UserInfo getUserAsPerRoleForStaff(Integer userId, Integer groupId);

}

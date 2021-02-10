package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name ="group_user_map")
public class UserGroupMap {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "map_id")
    private Integer mapId;

    @Column(name = "group_id")
    private Integer groupId;


    @JoinColumn(insertable = false, updatable = false, name = "group_id")
    @OneToOne(fetch = FetchType.EAGER)
    private GroupBean group;

    @Column(name = "user_id")
    private Integer userId;


    @JoinColumn(insertable = false, updatable = false, name = "user_id")
    @OneToOne
    private UserBean user;


    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "role")
    private String role;


    @Column(name = "is_lsa")
    private Integer isLSA = 0 ;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIsLSA() {
        return isLSA;
    }

    public void setIsLSA(Integer isLSA) {
        this.isLSA = isLSA;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}

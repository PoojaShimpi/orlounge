package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Entity
@Table(name = "last_access_group_info")
public class LastAccessGroupBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "group_id")
    private Integer groupId;


    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean user;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }
}

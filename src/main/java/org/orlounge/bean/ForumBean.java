package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name = "forum")
public class ForumBean {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "forum_id")
    private Integer forumId;



    @Column(name = "group_id")
    private Integer groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean groupBean;



    @Column(name = "created_by")
    private Integer userId;

    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @OneToOne
    private UserBean user;


    @Column(name = "topic")
    private String topic;


    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GroupBean groupBean) {
        this.groupBean = groupBean;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}

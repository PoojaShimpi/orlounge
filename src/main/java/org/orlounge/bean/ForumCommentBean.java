package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name = "forum_comment")
public class ForumCommentBean {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "forum_comment_id")
    private Integer forumCommentId;


    @Column(name = "forum_id")
    private Integer forumId;

    @JoinColumn(name = "forum_id", insertable = false, updatable = false)
    @OneToOne
    private ForumBean forum;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean user;

    @Column(name = "created_date")
    private Date createdDate;


    @Column(name = "comment")
    private String comment;


    @Column(name = "is_active")
    private Integer isActive;


    public Integer getForumCommentId() {
        return forumCommentId;
    }

    public void setForumCommentId(Integer forumCommentId) {
        this.forumCommentId = forumCommentId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public ForumBean getForum() {
        return forum;
    }

    public void setForum(ForumBean forum) {
        this.forum = forum;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}

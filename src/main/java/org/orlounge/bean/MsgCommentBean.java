package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name = "msg_board_comment")
public class MsgCommentBean {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "msg_comment_id")
    private Integer msgCommentId;


    @Column(name = "msg_id")
    private Integer msgId;

    @JoinColumn(name = "msg_id", insertable = false, updatable = false)
    @OneToOne
    private MsgBoardBean message;

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

    public Integer getMsgCommentId() {
        return msgCommentId;
    }

    public void setMsgCommentId(Integer msgCommentId) {
        this.msgCommentId = msgCommentId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public MsgBoardBean getMessage() {
        return message;
    }

    public void setMessage(MsgBoardBean message) {
        this.message = message;
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

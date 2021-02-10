package org.orlounge.common;

import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public class MsgBoardThread {
    private Integer msgId;
    private String  msgTitle;
    private String topic;
    private String createdBy;
    private Integer createdById;
    private String createdUserRole;
    private String createdDateStr;
    private Date createdDate;
    private String image;
    private List<MsgCommentThread> threadList;

    public String getCreatedUserRole() {
        return createdUserRole;
    }

    public void setCreatedUserRole(String createdUserRole) {
        this.createdUserRole = createdUserRole;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<MsgCommentThread> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<MsgCommentThread> threadList) {
        this.threadList = threadList;
    }
}

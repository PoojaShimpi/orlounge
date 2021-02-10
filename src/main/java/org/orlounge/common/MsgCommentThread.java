package org.orlounge.common;

import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public class MsgCommentThread {

    private String comment;
    private Integer commentId;
    private String createdDateStr;
    private Date createdDate;
    private String createdUserRole;
    private String createdBy;
    private Integer createById;
    private String image;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }
}

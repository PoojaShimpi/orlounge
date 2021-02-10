package org.orlounge.bean;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ideas")
public class IdeaBean {

    @GeneratedValue
    @Id
    @Column(name = "idea_id")
    private Integer ideaId;


    @Column(name = "idea_title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "votes")
    private Integer votes = 0;


    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @OneToOne
    private UserBean createdUser;

    @Column(name = "created_by")
    private Integer createdId;


    @Column(name ="created_date_time")
    private Date createdDate;




    @Transient
    private IdeaContent data;
    @Transient
    private final Gson gson = new Gson();


    public IdeaContent getData() {
        return data = gson.fromJson(content, IdeaContent.class);
    }
    public void setData(IdeaContent data) {
        this.data = data;
        this.content  = gson.toJson(data);
    }

    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public UserBean getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(UserBean createdUser) {
        this.createdUser = createdUser;
    }

    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static class IdeaContent{
        private String description;
        private String content;
        private List<IdeasField> fields = new ArrayList<>();
        private List<IdeasComment> comments = new ArrayList<>();

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<IdeasField> getFields() {
            return fields;
        }

        public void setFields(List<IdeasField> fields) {
            this.fields = fields;
        }

        public List<IdeasComment> getComments() {
            return comments;
        }

        public void setComments(List<IdeasComment> comments) {
            this.comments = comments;
        }
    }

    public static class IdeasField{
        private String field;
        private String fieldType;
        private String desc;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class IdeasComment{
        private String comment;
        private Date commentTime;
        private String commentBy;
        private Integer userId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Date getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(Date commentTime) {
            this.commentTime = commentTime;
        }

        public String getCommentBy() {
            return commentBy;
        }

        public void setCommentBy(String commentBy) {
            this.commentBy = commentBy;
        }
    }
}

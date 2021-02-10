package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Entity
@Table(name = "in_service")
public class InServiceBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "topic")
    private String topic;

    @Column(name = "date")
    private String date;

    @Column
    private String presenter;

    @Column(name ="created_date")
    private Date createdDate;

    @Column(name = "created_id")
    private Integer createdId;

    @Column(name = "group_id")
    private Integer groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;


    @JoinColumn(name = "created_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean createdUser;

    @Column(name = "file_name")
    private String name;


    @Column(name = "file_id")
    private String fileId;


    @Column(name =  "file_path")
    private String filePath;

    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "file_type")
    private String fileType;

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

    public UserBean getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(UserBean createdUser) {
        this.createdUser = createdUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

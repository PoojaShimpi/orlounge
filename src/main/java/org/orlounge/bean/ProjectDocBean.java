package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "project_doc")
public class ProjectDocBean {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column
    private String title;

    @Column(name ="description")
    private String desc;


    @Column
    private String body;

    @Column(name = "created_by")
    private Integer creatorUserId;

    @JoinColumn(name = "created_by", updatable = false, insertable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean creator;


    @Column(name  = "created_date")
    private Date createdDate;


    @Column(name = "last_updated_by")
    private Integer lastUpdatedUserId;

    @JoinColumn(name = "last_updated_by", updatable = false, insertable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean lastUpdator;


    @Column(name  = "last_updated_date")
    private Date lastUpdatedDate;


    @Column(name = "project_id")
    private Integer projectId;

    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    @ManyToOne
    private ProjectInfoBean project;


    @Column(name = "is_lock")
    private Integer isLock  = 0;

    @Column(name = "is_active")
    private Integer active  = 1;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public UserBean getCreator() {
        return creator;
    }

    public void setCreator(UserBean creator) {
        this.creator = creator;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastUpdatedUserId() {
        return lastUpdatedUserId;
    }

    public void setLastUpdatedUserId(Integer lastUpdatedUserId) {
        this.lastUpdatedUserId = lastUpdatedUserId;
    }

    public UserBean getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(UserBean lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectInfoBean getProject() {
        return project;
    }

    public void setProject(ProjectInfoBean project) {
        this.project = project;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

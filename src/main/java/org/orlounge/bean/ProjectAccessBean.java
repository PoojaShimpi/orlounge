package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "project_access")
public class ProjectAccessBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;


    @Column(name = "user_id")
    private Integer userId;


    @JoinColumn(name = "user_id", updatable = false , insertable = false)
    @OneToOne
    private UserBean user;

    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    @ManyToOne
    private ProjectInfoBean project;


    @Column(name = "is_active")
    private Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public ProjectInfoBean getProject() {
        return project;
    }

    public void setProject(ProjectInfoBean project) {
        this.project = project;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

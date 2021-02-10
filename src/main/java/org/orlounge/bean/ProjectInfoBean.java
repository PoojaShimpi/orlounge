package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "project_tbl")
@Getter
@Setter
public class ProjectInfoBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "description")
    private String desc;


    @Column(name = "owner_id")
    private Integer ownerId;


    @JoinColumn(name = "owner_id", updatable = false, insertable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean owner;


    @Column(name ="created_date")
    private Date createdDate;


    @Column(name = "created_by")
    private Integer createdById;

    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean creator;


    @Column(name = "is_active")
    private Integer isActive = 1;

    @Column(name = "access_type")
    private String accessType;

    @Transient
    private String userPreveliges;


    @Column(name = "group_id")
    private Integer groupId;


    @JoinColumn(name = "group_id", updatable = false, insertable = false)
    @OneToOne
    private GroupBean group;


    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<ProjectDocBean> documents;



    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<ProjectAccessBean> access;

}

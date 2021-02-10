package org.orlounge.bean;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Satyam Soni on 10/13/2015.
 */
@Entity
@Table(name = "post_op_ins")
@Getter
@Setter
public class PostOpBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String surgeon;

    @Column
    private String operation;

    @Column
    private String activity;

    @Column(name = "emergency_contact")
    private String emergencyContact;


    @Column
    private String restriction;

    @Column(name = "wound_care")
    private String woundCare;

    @Column(name = "medications")
    private String medications;

    @Column
    private String supplies;

    @Column(name = "office_visit")
    private String officeVisit;

    @Transient
    private String officeDate;

    @Transient
    private String officeTime;

    @Column
    private String instructions;

    @Column
    private String other;


    @Column(name = "user_id", insertable = true, updatable = true)
    private Integer userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean user;


    @Column(name = "created_dts")
    private Date createdDate;

    @Column(name = "update_id", insertable = true, updatable = true)
    private Integer updateUserId;

    @JoinColumn(name = "update_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean updateUser;


    @Column(name = "update_dts")
    private Date updatedDate;

    @Column(name = "group_id", insertable = true, updatable = true)
    private Integer groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;


    @Column(name = "is_active")
    private Integer isActive;


    @Column(name = "with_patient")
    private Boolean withPatientName = false;

    @OneToMany(mappedBy = "postOp", fetch = FetchType.EAGER)
    private Set<PostOpAccessBean> access;


    @Transient
    private String userPreveliges;

}

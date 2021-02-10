package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Entity
@Table(name = "approver_response")
@Getter
@Setter
public class ApproverResponse {

    @Id
    @Column(name= "approver_resp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "user_id")
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserBean user;

    @Column(name = "group_id")
    private Integer groupId;

    @OneToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private GroupBean group;


    @Column
    private String action;

    @Column
    private String name;

    @Column
    private String phNo;

    @Column
    private String email;

    @Column
    private String role;

    @Column(name ="created_date")
    private Date createdDate;


}

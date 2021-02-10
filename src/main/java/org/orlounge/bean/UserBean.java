package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class UserBean {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_code")
    private String userCode;

    @Column(name ="email")
    private String email;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;



    @Column(name="hosp_tel_ph")
    private String hospitalTelPh;


    @Column(name="hosp_tel_ext")
    private String hospitalTelExt;


    @Column(name="hospital_badge_id")
    private String hospitalBadgeId;


    @Column(name="created_date")
    private Date createdDate;


    @Column(name="updated_date")
    private Date updatedDate;


    @Column(name="expire_date")
    private Date expireDate;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "user_flag")
    private Integer userFlag = 0;


    @Column(name = "role")
    private String role;

}

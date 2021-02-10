package org.orlounge.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 9/22/2015.
 */
@Entity
@Table(name = "staff_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfoBean {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "staff_info_id")
    private Integer id;


    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean user;

    @Column(name = "title")
    private String title = "";

    @Column(name ="mobile")
    private String mobile = "";

    @Column(name = "office_ph_no")
    private String officePhNo = "";

    @Column(name = "address")
    private String address = "";

    @Column(name = "city")
    private String city= "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "sur_sp")
    private String surgicalSpeciality = "";

    @Column(name ="sp_acc")
    private String specialAccrediation= "";

    @Column(name ="website")
    private String website= "";

    @Column(name ="office_mgr")
    private String officeManager= "";

    @Column(name ="office_hrs")
    private String officeHrs= "";

    @Column(name ="npi")
    private String npi= "";

    @Column(name ="cov_phy")
    private String coveringPhy= "";

    @Column(name ="additional_info")
    private String additionalInfo= "";

    @Transient
    private String fullName;

    public String getFullName(){
        return getUser().getFirstName()+" " + getUser().getLastName();
    }

}

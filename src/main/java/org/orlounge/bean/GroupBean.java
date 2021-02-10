package org.orlounge.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Entity
@Table(name="group_tbl")
public class GroupBean {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_created_by", updatable = true, insertable = true)
    private Integer creatorId;


    @JoinColumn(insertable = false, updatable = false, name = "group_created_by")
    @OneToOne
    private UserBean user;


    @Column(name="group_created_date")
    private Date createdDate;


    @Column(name="is_active")
    private Integer isActive;


    @Column(name="hospital_id")
    private Integer hospitalId;

    @Column(name="no_of_beds")
    private Integer noOfBeds;

    @Column(name= "is_trial")
    private Integer trialFlag = 1;

    @Transient
    private List<GeoLocation> locations = new ArrayList<>();

    public List<GeoLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<GeoLocation> locations) {
        this.locations = locations;
    }

    public Integer getTrialFlag() {
        return trialFlag;
    }

    public void setTrialFlag(Integer trialFlag) {
        this.trialFlag = trialFlag;
    }

    public Integer getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(Integer noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Transient
    public String getGroupSize(){
        if(this.getNoOfBeds() == null){
            return null;
        }else if(this.getNoOfBeds() > 100){
            return "L";
        }else if(this.getNoOfBeds() > 50){
            return "M";
        }else{
            return "S";
        }
    }
}

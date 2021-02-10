package org.orlounge.bean;

import org.orlounge.common.HospitalType;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Entity
@Table(name = "hospital")
public class HospitalBean {

    @GeneratedValue
    @Id
    @Column(name = "hospital_id")
    private Integer hospitalId;


    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "is_active")
    private Integer isActive;

    @JoinColumn(name = "state_id")
    @OneToOne(fetch = FetchType.EAGER)
    private StateBean state;


    @Column
    private String timezone;

    @Column
    @Enumerated(EnumType.STRING)
    private HospitalType type;

    public HospitalType getType() {
        return type;
    }

    public void setType(HospitalType type) {
        this.type = type;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }
}

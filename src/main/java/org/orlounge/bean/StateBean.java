package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Entity
@Table(name="state")
public class StateBean {


    @GeneratedValue
    @Id
    @Column(name = "state_id")
    private Integer stateId;

    @Column(name="state_name")
    private String stateName;

    @Column(name="state_code")
    private String stateCode;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}

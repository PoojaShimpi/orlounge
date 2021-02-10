package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created on 11/22/2016.
 */
@Entity
@Table(name ="voting")
public class VotingBean {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;


    @Column(name = "vote_for")
    private String voteFor;


    @Column(name=  "vote_num")
    private Integer voteNumber;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "active")
    private Integer active;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoteFor() {
        return voteFor;
    }

    public void setVoteFor(String voteFor) {
        this.voteFor = voteFor;
    }

    public Integer getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(Integer voteNumber) {
        this.voteNumber = voteNumber;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}

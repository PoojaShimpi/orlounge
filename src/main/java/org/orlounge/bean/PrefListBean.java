package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Entity
@Table(name = "pref_list")
public class PrefListBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String speciality;

    @Column(name = "procedures")
    private String procedure;

    @Column(name = "sp_consideration")
    private String spConsideration;

    @Column(name = "an_consideration")
    private String anConsideration;

    @Column(name = "room_setup")
    private String roomSetup;

    @Column(name = "prep_drape")
    private String prepDrape;

    @Column(name = "equipment")
    private String equipment;

    @Column(name ="dressing_drain")
    private String dressingDrain;

    @Column(name ="others")
    private String others;

    @Column(name = "group_id")
    private Integer groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;

    @Column(name = "created_by")
    private Integer createdBy;

    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean createdUser;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name ="is_active")
    private Integer isActive;



    @OneToMany(mappedBy = "preference", fetch = FetchType.EAGER)
    private Set<InstrumentPrefListBean> instruments;


    @OneToMany(mappedBy = "preference", fetch = FetchType.EAGER)
    private Set<PrefListAccessBean> access;


    public Set<PrefListAccessBean> getAccess() {
        return access;
    }

    public void setAccess(Set<PrefListAccessBean> access) {
        this.access = access;
    }

    public Set<InstrumentPrefListBean> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<InstrumentPrefListBean> instruments) {
        this.instruments = instruments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getSpConsideration() {
        return spConsideration;
    }

    public void setSpConsideration(String spConsideration) {
        this.spConsideration = spConsideration;
    }

    public String getAnConsideration() {
        return anConsideration;
    }

    public void setAnConsideration(String anConsideration) {
        this.anConsideration = anConsideration;
    }

    public String getRoomSetup() {
        return roomSetup;
    }

    public void setRoomSetup(String roomSetup) {
        this.roomSetup = roomSetup;
    }

    public String getPrepDrape() {
        return prepDrape;
    }

    public void setPrepDrape(String prepDrape) {
        this.prepDrape = prepDrape;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDressingDrain() {
        return dressingDrain;
    }

    public void setDressingDrain(String dressingDrain) {
        this.dressingDrain = dressingDrain;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public UserBean getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(UserBean createdUser) {
        this.createdUser = createdUser;
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
}

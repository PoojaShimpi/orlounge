package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "geo_loc")
public class GeoLocation {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;


    @Column(name ="group_id")
    private Integer groupId;

    @JoinColumn(name = "group_id", updatable = false, insertable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private GroupBean group;


    @Column(name = "type")
    private String type;

    @Column(name = "key")
    private String key;

    @Column(name = "json")
    private String json;

    @Column(name = "active")
    private Integer active;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    @OneToOne
    private UserBean updatedUser;


    @Column(name ="updated_date")
    private Date updatedDate;

    @Column(name ="comment")
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public UserBean getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(UserBean updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

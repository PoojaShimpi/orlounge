package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "esps_bill_info")
public class EspsBillInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @JoinColumn(updatable = false, insertable = false, name = "group_id")
    @OneToOne
    private GroupBean group;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "tel_ph")
    private String telPh;

    @Column(name = "bill_mail")
    private String billMail;


    @Column(name = "status")
    private String status;




    @JoinColumn(updatable = false, insertable = false, name = "created_by")
    @OneToOne(fetch = FetchType.LAZY)
    private UserBean user;

    @Column(name = "created_by")
    private Integer userId;

    @Column(name = "created_date")
    private Date creatorDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPh() {
        return telPh;
    }

    public void setTelPh(String telPh) {
        this.telPh = telPh;
    }

    public String getBillMail() {
        return billMail;
    }

    public void setBillMail(String billMail) {
        this.billMail = billMail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(Date creatorDate) {
        this.creatorDate = creatorDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

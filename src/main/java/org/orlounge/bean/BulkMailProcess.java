package org.orlounge.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by satyam on 7/8/2017.
 */
@Entity
@Table(name = "bulk_mail_process")
public class BulkMailProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bulk_type")
    private String bulkType;

    @Column(name = "msg_type")
    private String messageType;

    @Column(name = "total")
    private Integer total;

    @Column(name = "sent")
    private Integer sent;

    @Column(name = "last_sent")
    private Date lastSent;

    @Column(name = "created")
    private Date created;

    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBulkType() {
        return bulkType;
    }

    public void setBulkType(String bulkType) {
        this.bulkType = bulkType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Date getLastSent() {
        return lastSent;
    }

    public void setLastSent(Date lastSent) {
        this.lastSent = lastSent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

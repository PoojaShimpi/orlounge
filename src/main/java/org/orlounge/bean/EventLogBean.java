package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Entity
@Table(name = "event_log")
@Getter
@Setter
public class EventLogBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name ="user_id", insertable = true, updatable = true)
    private Integer userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean user;

    @Column(name = "event_msg")
    private String eventMsg;

    @Column(name ="dts")
    private Date dts;

    @Column(name ="last_dts")
    private Date lastDts;

    @Column
    private String ip;

    @Column(name = "is_active")
    private Integer isActive;


}

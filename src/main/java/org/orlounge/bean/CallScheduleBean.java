package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Entity
@Table(name = "call_schedule")
@Getter
@Setter
@SelectBeforeUpdate()
public class CallScheduleBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "topic")
    private String topic;


    @Column(name = "text")
    private String text;

    @Column(name ="created_date")
    private Date createdDate;

    @Column(name = "created_id")
    private Integer createdId;

    @Column(name = "group_id")
    private Integer groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;


    @JoinColumn(name = "created_id", insertable = false, updatable = false)
    @OneToOne
    private UserBean createdUser;

    @Column(name = "file_name")
    private String name;


    @Column(name = "file_id")
    private String fileId;


    @Column(name =  "file_path")
    private String filePath;

    @Column(name = "role")
    private String role;

//    @Column(name = "is_active")
//    private Integer isActive;

    @Column(name = "file_type")
    private String fileType;


}

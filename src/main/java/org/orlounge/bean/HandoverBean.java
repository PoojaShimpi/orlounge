package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Entity
@Table(name = "handover")
@Getter
@Setter
public class HandoverBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type")
    private String type;


    @Column(name = "text")
    private String text;

    private String identification;

    private String situation;
    private String background;

    @Column(name = "assessed_by")
    private String assessedBy;
    private String recommendation;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "file_id")
    private String fileId;

    @Column(name =  "file_path")
    private String filePath;


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

    @Column(name = "is_active")
    private Integer isActive;


}

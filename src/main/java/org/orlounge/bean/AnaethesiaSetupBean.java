package org.orlounge.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Satyam Soni on 1/4/2016.
 */
@Entity
@Getter
@Setter
@Table(name = "anaesthesia_setup")
public class AnaethesiaSetupBean {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String speciality;

    @Column(name = "procedure_setup")
    private String procedure;

    @Column
    private String body;

    @Column(name = "created_by")
    private Integer creatorId;

    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean creator;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_active")
    private Integer active = 1;


    @Column(name = "group_id")
    private Integer groupId;


    @Column(name = "file_name")
    private String name;

    @Column(name = "file_id")
    private String fileId;


    @Column(name = "file_path")
    private String filePath;


    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @OneToOne
    private GroupBean group;

}

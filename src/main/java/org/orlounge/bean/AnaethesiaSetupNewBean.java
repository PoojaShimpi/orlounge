package org.orlounge.bean;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "anesthesia_new_setup")
@Getter
@Setter
public class AnaethesiaSetupNewBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "operation", length = 255)
    private String operation;

    @Column(name = "type_of_anesthesia", length = 255)
    private String typeOfAnesthesia;

    @Column(name = "pre_induction")
    @Lob
    private String preInduction;

    @Column(name = "intra_operative")
    @Lob
    private String intraOperative;

    @Column(name = "emergence")
    @Lob
    private String emergence;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private UserBean userBean;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "is_active")
    private Integer active = 1;



}

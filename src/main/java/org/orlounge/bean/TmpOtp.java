package org.orlounge.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nilay on 19/9/18.
 */
@Entity
@Table(name = "tmp_otp")
public class TmpOtp implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "tmp_otp_id")
    private Long tmpOtpId;


    @Column(name = "email")
    private String email;

    @Column(name = "hosp_tel_ext")
    private String hospitalTelPh;

    @Column(name = "otp_number", length = 7)
    private Integer otpNumber;

    @Column(name = "message_id")
    private String messgae_id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_ts")
    private Date createTs;

    public Long getTmpOtpId() {
        return tmpOtpId;
    }

    public void setTmpOtpId(Long tmpOtpId) {
        this.tmpOtpId = tmpOtpId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHospitalTelPh() {
        return hospitalTelPh;
    }

    public void setHospitalTelPh(String hospitalTelPh) {
        this.hospitalTelPh = hospitalTelPh;
    }

    public Integer getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(Integer otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getMessgae_id() {
        return messgae_id;
    }

    public void setMessgae_id(String messgae_id) {
        this.messgae_id = messgae_id;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }
}
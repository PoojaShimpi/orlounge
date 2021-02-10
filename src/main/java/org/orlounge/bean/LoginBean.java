package org.orlounge.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Entity
@Table(name = "login")
public class LoginBean implements UserDetails{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "user_login_id")
    private Integer userLoginId;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "email_id")
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "last_pass_change")
    private Date lastPassChange;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "pass_reset")
    private Integer pass_Reset;

    public Integer getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Integer userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(0);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getIsActive() == 1;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(Date lastPassChange) {
        this.lastPassChange = lastPassChange;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getPass_Reset() {
        return pass_Reset;
    }

    public void setPass_Reset(Integer pass_Reset) {
        this.pass_Reset = pass_Reset;
    }
}

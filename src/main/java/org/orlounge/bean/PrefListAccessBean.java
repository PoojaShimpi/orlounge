package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "pref_list_access")
public class PrefListAccessBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "pref_id")
    private Integer prefId;


    @Column(name = "user_id")
    private Integer userId;


    @JoinColumn(name = "user_id", updatable = false , insertable = false)
    @OneToOne
    private UserBean user;

    @JoinColumn(name = "pref_id", insertable = false, updatable = false)
    @ManyToOne
    private PrefListBean preference;


    @Column(name = "active")
    private Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrefId() {
        return prefId;
    }

    public void setPrefId(Integer prefId) {
        this.prefId = prefId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public PrefListBean getPreference() {
        return preference;
    }

    public void setPreference(PrefListBean preference) {
        this.preference = preference;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

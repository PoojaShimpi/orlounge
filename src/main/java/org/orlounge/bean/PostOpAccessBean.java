package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "post_ops_ins_access")
public class PostOpAccessBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "post_ops_ins_id")
    private Integer postOpId;


    @Column(name = "user_id")
    private Integer userId;


    @JoinColumn(name = "user_id", updatable = false , insertable = false)
    @OneToOne
    private UserBean user;

    @JoinColumn(name = "post_ops_ins_id", insertable = false, updatable = false)
    @ManyToOne
    private PostOpBean postOp;


    @Column(name = "active")
    private Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostOpId() {
        return postOpId;
    }

    public void setPostOpId(Integer postOpId) {
        this.postOpId = postOpId;
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

    public PostOpBean getPostOp() {
        return postOp;
    }

    public void setPostOp(PostOpBean postOp) {
        this.postOp = postOp;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created on 7/3/2016.
 */
@Entity
@Table(name = "tags")
public class TagsBean {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name= "active")
    private Boolean active;

    @Column(name = "wt")
    private Double wt;

    public Double getWt() {
        return wt;
    }

    public void setWt(Double wt) {
        this.wt = wt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Entity
@Table(name = "config")
public class ConfigBean {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column
    private String key;

    @Column
    private String value;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

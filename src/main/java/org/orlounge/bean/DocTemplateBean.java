package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Entity
@Table(name = "doc_template")
public class DocTemplateBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "template_name")
    private String name ;

    @Column
    private String body;

    @Column(name = "is_active")
    private Integer active = 1;


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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

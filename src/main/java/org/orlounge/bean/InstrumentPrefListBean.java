package org.orlounge.bean;

import javax.persistence.*;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Entity
@Table(name = "instrument_pref_list")
public class InstrumentPrefListBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "pref_id")
    private Integer prefId;



    @ManyToOne
    @JoinColumn(name = "pref_id", insertable = false, updatable = false)
    private PrefListBean preference;


    @Column
    private String name;

    @Column
    private String quantity;

    @Column(name = "photo")
    private String photoImagePath;


    @Column
    private String bin;


    @Column
    private String catalog;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhotoImagePath() {
        return photoImagePath;
    }

    public void setPhotoImagePath(String photoImagePath) {
        this.photoImagePath = photoImagePath;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

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

    public PrefListBean getPreference() {
        return preference;
    }

    public void setPreference(PrefListBean preference) {
        this.preference = preference;
    }
}

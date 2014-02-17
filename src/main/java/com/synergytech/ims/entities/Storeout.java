/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ujjwal
 */
@Entity
@Table(name = "storeout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storeout.findAll", query = "SELECT s FROM Storeout s"),
    @NamedQuery(name = "Storeout.findByStoreoutId", query = "SELECT s FROM Storeout s WHERE s.storeoutId = :storeoutId"),
    @NamedQuery(name = "Storeout.findByStoreoutSerialno", query = "SELECT s FROM Storeout s WHERE s.storeoutSerialno = :storeoutSerialno"),
    @NamedQuery(name = "Storeout.findByStoreoutQuantity", query = "SELECT s FROM Storeout s WHERE s.storeoutQuantity = :storeoutQuantity"),
    @NamedQuery(name = "Storeout.findByStoreoutMeasure", query = "SELECT s FROM Storeout s WHERE s.storeoutMeasure = :storeoutMeasure"),
    @NamedQuery(name = "Storeout.findByStoreoutCreatedDate", query = "SELECT s FROM Storeout s WHERE s.storeoutCreatedDate = :storeoutCreatedDate"),
    @NamedQuery(name = "Store.findByStoreoutItemcode", query = "SELECT s FROM Storeout s WHERE s.storeoutItemItemcode = :storeoutItemItemcode")})

public class Storeout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "storeout_id")
    private Integer storeoutId;
    @Size(max = 45)
    @Column(name = "storeout_serialno")
    private String storeoutSerialno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "storeout_quantity")
    private String storeoutQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "storeout_measure")
    private String storeoutMeasure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeout_created_date")
    @Temporal(TemporalType.DATE)
    private Date storeoutCreatedDate;
    @JoinColumn(name = "storeout_createdby", referencedColumnName = "user_username")
    @ManyToOne
    private User storeoutCreatedby;
    @JoinColumn(name = "storeout_item_office_officeid", referencedColumnName = "office_officeid")
    @ManyToOne
    private Office storeoutItemOfficeOfficeid;
    @JoinColumn(name = "storeout_item_itemcode", referencedColumnName = "item_itemcode")
    @ManyToOne
    private Item storeoutItemItemcode;

    public Storeout() {
    }

    public Storeout(Integer storeoutId) {
        this.storeoutId = storeoutId;
    }

    public Storeout(Integer storeoutId, String storeoutQuantity, String storeoutMeasure, Date storeoutCreatedDate) {
        this.storeoutId = storeoutId;
        this.storeoutQuantity = storeoutQuantity;
        this.storeoutMeasure = storeoutMeasure;
        this.storeoutCreatedDate = storeoutCreatedDate;
    }

    public Integer getStoreoutId() {
        return storeoutId;
    }

    public void setStoreoutId(Integer storeoutId) {
        this.storeoutId = storeoutId;
    }

    public String getStoreoutSerialno() {
        return storeoutSerialno;
    }

    public void setStoreoutSerialno(String storeoutSerialno) {
        this.storeoutSerialno = storeoutSerialno;
    }

    public String getStoreoutQuantity() {
        return storeoutQuantity;
    }

    public void setStoreoutQuantity(String storeoutQuantity) {
        this.storeoutQuantity = storeoutQuantity;
    }

    public String getStoreoutMeasure() {
        return storeoutMeasure;
    }

    public void setStoreoutMeasure(String storeoutMeasure) {
        this.storeoutMeasure = storeoutMeasure;
    }

    public Date getStoreoutCreatedDate() {
        return storeoutCreatedDate;
    }

    public void setStoreoutCreatedDate(Date storeoutCreatedDate) {
        this.storeoutCreatedDate = storeoutCreatedDate;
    }

    public User getStoreoutCreatedby() {
        return storeoutCreatedby;
    }

    public void setStoreoutCreatedby(User storeoutCreatedby) {
        this.storeoutCreatedby = storeoutCreatedby;
    }

    public Office getStoreoutItemOfficeOfficeid() {
        return storeoutItemOfficeOfficeid;
    }

    public void setStoreoutItemOfficeOfficeid(Office storeoutItemOfficeOfficeid) {
        this.storeoutItemOfficeOfficeid = storeoutItemOfficeOfficeid;
    }

    public Item getStoreoutItemItemcode() {
        return storeoutItemItemcode;
    }

    public void setStoreoutItemItemcode(Item storeoutItemItemcode) {
        this.storeoutItemItemcode = storeoutItemItemcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeoutId != null ? storeoutId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storeout)) {
            return false;
        }
        Storeout other = (Storeout) object;
        if ((this.storeoutId == null && other.storeoutId != null) || (this.storeoutId != null && !this.storeoutId.equals(other.storeoutId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Storeout[ storeoutId=" + storeoutId + " ]";
    }

}

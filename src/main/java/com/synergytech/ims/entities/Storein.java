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
@Table(name = "storein")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storein.findAll", query = "SELECT s FROM Storein s"),
    @NamedQuery(name = "Storein.findByStoreinId", query = "SELECT s FROM Storein s WHERE s.storeinId = :storeinId"),
    @NamedQuery(name = "Storein.findByStoreinSerialno", query = "SELECT s FROM Storein s WHERE s.storeinSerialno = :storeinSerialno"),
    @NamedQuery(name = "Storein.findByStoreinQuantity", query = "SELECT s FROM Storein s WHERE s.storeinQuantity = :storeinQuantity"),
    @NamedQuery(name = "Storein.findByStoreinMeasure", query = "SELECT s FROM Storein s WHERE s.storeinMeasure = :storeinMeasure"),
    @NamedQuery(name = "Storein.findByStoreinCreatedDate", query = "SELECT s FROM Storein s WHERE s.storeinCreatedDate = :storeinCreatedDate"),
    @NamedQuery(name = "Storein.findByStoreinOfficeid", query = "SELECT s FROM Storein s WHERE s.storeinOfficeOfficeid = :storeinOfficeid"),
    @NamedQuery(name = "Storein.findByStoreinItemcode", query = "SELECT s FROM Storein s WHERE s.storeinItemItemcode = :storeinItemItemcode")})
public class Storein implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "storein_id")
    private Integer storeinId;
    @Size(max = 45)
    @Column(name = "storein_serialno")
    private String storeinSerialno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "storein_quantity")
    private String storeinQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "storein_measure")
    private String storeinMeasure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storein_created_date")
    @Temporal(TemporalType.DATE)
    private Date storeinCreatedDate;
    @JoinColumn(name = "storein_createdby", referencedColumnName = "user_username")
    @ManyToOne
    private User storeinCreatedby;
    @JoinColumn(name = "storein_office_officeid", referencedColumnName = "office_officeid")
    @ManyToOne
    private Office storeinOfficeOfficeid;
    @JoinColumn(name = "storein_item_itemcode", referencedColumnName = "item_itemcode")
    @ManyToOne
    private Item storeinItemItemcode;

    public Storein() {
    }

    public Storein(Integer storeinId) {
        this.storeinId = storeinId;
    }

    public Storein(Integer storeinId, String storeinQuantity, String storeinMeasure, Date storeinCreatedDate) {
        this.storeinId = storeinId;
        this.storeinQuantity = storeinQuantity;
        this.storeinMeasure = storeinMeasure;
        this.storeinCreatedDate = storeinCreatedDate;
    }

    public Integer getStoreinId() {
        return storeinId;
    }

    public void setStoreinId(Integer storeinId) {
        this.storeinId = storeinId;
    }

    public String getStoreinSerialno() {
        return storeinSerialno;
    }

    public void setStoreinSerialno(String storeinSerialno) {
        this.storeinSerialno = storeinSerialno;
    }

    public String getStoreinQuantity() {
        return storeinQuantity;
    }

    public void setStoreinQuantity(String storeinQuantity) {
        this.storeinQuantity = storeinQuantity;
    }

    public String getStoreinMeasure() {
        return storeinMeasure;
    }

    public void setStoreinMeasure(String storeinMeasure) {
        this.storeinMeasure = storeinMeasure;
    }

    public Date getStoreinCreatedDate() {
        return storeinCreatedDate;
    }

    public void setStoreinCreatedDate(Date storeinCreatedDate) {
        this.storeinCreatedDate = storeinCreatedDate;
    }

    public User getStoreinCreatedby() {
        return storeinCreatedby;
    }

    public void setStoreinCreatedby(User storeinCreatedby) {
        this.storeinCreatedby = storeinCreatedby;
    }

    public Office getStoreinOfficeOfficeid() {
        return storeinOfficeOfficeid;
    }

    public void setStoreinOfficeOfficeid(Office storeinOfficeOfficeid) {
        this.storeinOfficeOfficeid = storeinOfficeOfficeid;
    }

    public Item getStoreinItemItemcode() {
        return storeinItemItemcode;
    }

    public void setStoreinItemItemcode(Item storeinItemItemcode) {
        this.storeinItemItemcode = storeinItemItemcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeinId != null ? storeinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storein)) {
            return false;
        }
        Storein other = (Storein) object;
        if ((this.storeinId == null && other.storeinId != null) || (this.storeinId != null && !this.storeinId.equals(other.storeinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Storein[ storeinId=" + storeinId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ujjwal
 */
@Entity
@Table(name = "office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o"),
    @NamedQuery(name = "Office.findByOfficeOfficeid", query = "SELECT o FROM Office o WHERE o.officeOfficeid = :officeOfficeid"),
    @NamedQuery(name = "Office.findByOfficeName", query = "SELECT o FROM Office o WHERE o.officeName = :officeName"),
    @NamedQuery(name = "Office.findByOfficeAddress", query = "SELECT o FROM Office o WHERE o.officeAddress = :officeAddress"),
    @NamedQuery(name = "Office.findByOfficeContact", query = "SELECT o FROM Office o WHERE o.officeContact = :officeContact"),
    @NamedQuery(name = "Office.findByOfficeStatus", query = "SELECT o FROM Office o WHERE o.officeStatus = :officeStatus"),
    @NamedQuery(name="Office.findByOfficeStatusActive", query="SELECT o FROM Office o WHERE o.officeStatus='Active'")})
public class Office implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "office_officeid")
    private Integer officeOfficeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "office_name")
    private String officeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "office_address")
    private String officeAddress;
    @Size(max = 20)
    @Column(name = "office_contact")
    private String officeContact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "office_status")
    private String officeStatus;
    @JoinTable(name = "officesupplier", joinColumns = {
        @JoinColumn(name = "officesupplier_office_officeid", referencedColumnName = "office_officeid")}, inverseJoinColumns = {
        @JoinColumn(name = "officesupplier_supplier_supplierid", referencedColumnName = "supplier_supplierid")})
    @ManyToMany
    private Collection<Supplier> supplierCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office")
    private Collection<Store> storeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOfficeOfficeid")
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeoutItemOfficeOfficeid")
    private Collection<Storeout> storeoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeinOfficeOfficeid")
    private Collection<Storein> storeinCollection;

    public Office() {
    }

    public Office(Integer officeOfficeid) {
        this.officeOfficeid = officeOfficeid;
    }

    public Office(Integer officeOfficeid, String officeName, String officeAddress, String officeStatus) {
        this.officeOfficeid = officeOfficeid;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.officeStatus = officeStatus;
    }

    public Integer getOfficeOfficeid() {
        return officeOfficeid;
    }

    public void setOfficeOfficeid(Integer officeOfficeid) {
        this.officeOfficeid = officeOfficeid;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeContact() {
        return officeContact;
    }

    public void setOfficeContact(String officeContact) {
        this.officeContact = officeContact;
    }

    public String getOfficeStatus() {
        return officeStatus;
    }

    public void setOfficeStatus(String officeStatus) {
        this.officeStatus = officeStatus;
    }

    @XmlTransient
    public Collection<Supplier> getSupplierCollection() {
        return supplierCollection;
    }

    public void setSupplierCollection(Collection<Supplier> supplierCollection) {
        this.supplierCollection = supplierCollection;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Storeout> getStoreoutCollection() {
        return storeoutCollection;
    }

    public void setStoreoutCollection(Collection<Storeout> storeoutCollection) {
        this.storeoutCollection = storeoutCollection;
    }

    @XmlTransient
    public Collection<Storein> getStoreinCollection() {
        return storeinCollection;
    }

    public void setStoreinCollection(Collection<Storein> storeinCollection) {
        this.storeinCollection = storeinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officeOfficeid != null ? officeOfficeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.officeOfficeid == null && other.officeOfficeid != null) || (this.officeOfficeid != null && !this.officeOfficeid.equals(other.officeOfficeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Office[ officeOfficeid=" + officeOfficeid + " ]";
    }
    
}

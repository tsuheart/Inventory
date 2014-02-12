/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ujjwal
 */
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findBySupplierSupplierid", query = "SELECT s FROM Supplier s WHERE s.supplierSupplierid = :supplierSupplierid"),
    @NamedQuery(name = "Supplier.findBySupplierName", query = "SELECT s FROM Supplier s WHERE s.supplierName = :supplierName"),
    @NamedQuery(name = "Supplier.findBySupplierAddress", query = "SELECT s FROM Supplier s WHERE s.supplierAddress = :supplierAddress"),
    @NamedQuery(name = "Supplier.findBySupplierContact", query = "SELECT s FROM Supplier s WHERE s.supplierContact = :supplierContact"),
    @NamedQuery(name = "Supplier.findBySupplierStatus", query = "SELECT s FROM Supplier s WHERE s.supplierStatus = :supplierStatus")})
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "supplier_supplierid")
    private String supplierSupplierid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "supplier_name")
    private String supplierName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "supplier_address")
    private String supplierAddress;
    @Size(max = 20)
    @Column(name = "supplier_contact")
    private String supplierContact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "supplier_status")
    private String supplierStatus;   
    @ManyToMany(mappedBy = "supplierCollection", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Office> officeCollection;

    public Supplier() {
        officeCollection= new ArrayList<Office>();
    }

    public Supplier(String supplierSupplierid) {
        this();
        this.supplierSupplierid = supplierSupplierid;
    }

    public Supplier(String supplierSupplierid, String supplierName, String supplierAddress, String supplierStatus) {
        this();
        this.supplierSupplierid = supplierSupplierid;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierSupplierid() {
        return supplierSupplierid;
    }

    public void setSupplierSupplierid(String supplierSupplierid) {
        this.supplierSupplierid = supplierSupplierid;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    @XmlTransient
    public Collection<Office> getOfficeCollection() {
        return officeCollection;
    }

    public void setOfficeCollection(Collection<Office> officeCollection) {
        this.officeCollection = officeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierSupplierid != null ? supplierSupplierid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supplierSupplierid == null && other.supplierSupplierid != null) || (this.supplierSupplierid != null && !this.supplierSupplierid.equals(other.supplierSupplierid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Supplier[ supplierSupplierid=" + supplierSupplierid + " ]";
    }
    
}

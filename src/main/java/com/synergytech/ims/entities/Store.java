/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ujjwal
 */
@Entity
@Table(name = "store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s"),
    @NamedQuery(name = "Store.findByStoreItemItemcode", query = "SELECT s FROM Store s WHERE s.storePK.storeItemItemcode = :storeItemItemcode"),
    @NamedQuery(name = "Store.findByStoreOfficeOfficeid", query = "SELECT s FROM Store s WHERE s.storePK.storeOfficeOfficeid = :storeOfficeOfficeid"),
    @NamedQuery(name = "Store.findByStoreOfficeOfficeidAndItemItemcode", query = "SELECT s FROM Store s WHERE s.storePK.storeOfficeOfficeid = :storeOfficeOfficeid AND s.item.itemItemcode = :storeItemItemcode"),
    @NamedQuery(name = "Store.findByStoreQuantity", query = "SELECT s FROM Store s WHERE s.storeQuantity = :storeQuantity"),
    @NamedQuery(name = "Store.findByStoreItemCategory", query = "SELECT s FROM Store s WHERE s.item.itemCategoryCategoryid = :category"),
    @NamedQuery(name = "Store.findByStoreItemCategoryAndOfficeid", query = "SELECT s FROM Store s WHERE s.item.itemCategoryCategoryid = :category AND s.storePK.storeOfficeOfficeid=:storeOfficeOfficeid"),
    @NamedQuery(name = "Store.findByStoreUnit", query = "SELECT s FROM Store s WHERE s.storeUnit = :storeUnit")})
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StorePK storePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "store_quantity")
    private String storeQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "store_unit")
    private String storeUnit;
    @Size(max = 100)
    @Column(name = "store_remarks")
    private String storeRemarks;
    @JoinColumn(name = "store_office_officeid", referencedColumnName = "office_officeid", insertable = false, updatable = false)
    @ManyToOne
    private Office office;
    @JoinColumn(name = "store_item_itemcode", referencedColumnName = "item_itemcode", insertable = false, updatable = false)
    @ManyToOne
    private Item item;

    public Store() {
    }

    public Store(StorePK storePK) {
        this.storePK = storePK;
    }

    public Store(StorePK storePK, String storeQuantity, String storeUnit) {
        this.storePK = storePK;
        this.storeQuantity = storeQuantity;
        this.storeUnit = storeUnit;
    }

    public Store(String storeItemItemcode, int storeOfficeOfficeid) {
        this.storePK = new StorePK(storeItemItemcode, storeOfficeOfficeid);
    }

    public StorePK getStorePK() {
        return storePK;
    }

    public void setStorePK(StorePK storePK) {
        this.storePK = storePK;
    }

    public String getStoreRemarks() {
        return storeRemarks;
    }

    public void setStoreRemarks(String storeRemarks) {
        this.storeRemarks = storeRemarks;
    }

    public String getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(String storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public String getStoreUnit() {
        return storeUnit;
    }

    public void setStoreUnit(String storeUnit) {
        this.storeUnit = storeUnit;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storePK != null ? storePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.storePK == null && other.storePK != null) || (this.storePK != null && !this.storePK.equals(other.storePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Store[ storePK=" + storePK + " ]";
    }

}

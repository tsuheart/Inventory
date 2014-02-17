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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemItemcode", query = "SELECT i FROM Item i WHERE i.itemItemcode = :itemItemcode"),
    @NamedQuery(name = "Item.findByItemName", query = "SELECT i FROM Item i WHERE i.itemName = :itemName"),
    @NamedQuery(name = "Item.findByItemRemark", query = "SELECT i FROM Item i WHERE i.itemRemark = :itemRemark"),
    @NamedQuery(name = "Item.findByCategoryId", query = "SELECT i FROM Item i WHERE i.itemCategoryCategoryid = :itemCategoryCategoryid"),
    @NamedQuery(name = "Item.findByItemStatus", query = "SELECT i FROM Item i WHERE i.itemStatus = :itemStatus")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "item_itemcode")
    private String itemItemcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 50)
    @Column(name = "item_remark")
    private String itemRemark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "item_status")
    private String itemStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Store> storeCollection;
    @JoinColumn(name = "item_measurebases_measureid", referencedColumnName = "measurebases_measureid")
    @ManyToOne
    private Measurebases itemMeasurebasesMeasureid;
    @JoinColumn(name = "item_category_categoryid", referencedColumnName = "category_categoryid")
    @ManyToOne
    private Category itemCategoryCategoryid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeoutItemItemcode")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Storeout> storeoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeinItemItemcode")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Storein> storeinCollection;

    public Item() {
    }

    public Item(String itemItemcode) {
        this.itemItemcode = itemItemcode;
    }

    public Item(String itemItemcode, String itemName, String itemStatus) {
        this.itemItemcode = itemItemcode;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
    }

    public String getItemItemcode() {
        return itemItemcode;
    }

    public void setItemItemcode(String itemItemcode) {
        this.itemItemcode = itemItemcode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    public Measurebases getItemMeasurebasesMeasureid() {
        return itemMeasurebasesMeasureid;
    }

    public void setItemMeasurebasesMeasureid(Measurebases itemMeasurebasesMeasureid) {
        this.itemMeasurebasesMeasureid = itemMeasurebasesMeasureid;
    }

    public Category getItemCategoryCategoryid() {
        return itemCategoryCategoryid;
    }

    public void setItemCategoryCategoryid(Category itemCategoryCategoryid) {
        this.itemCategoryCategoryid = itemCategoryCategoryid;
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
        hash += (itemItemcode != null ? itemItemcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemItemcode == null && other.itemItemcode != null) || (this.itemItemcode != null && !this.itemItemcode.equals(other.itemItemcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemName;
    }

}

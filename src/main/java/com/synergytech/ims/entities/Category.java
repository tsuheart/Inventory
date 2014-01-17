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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Administrator
 */
@Entity
@Table(name = "category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryCategoryid", query = "SELECT c FROM Category c WHERE c.categoryCategoryid = :categoryCategoryid"),
    @NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "Category.findByCategoryParentid", query = "SELECT c FROM Category c WHERE c.categoryParentid = :categoryParentid")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_categoryid")
    private Integer categoryCategoryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_parentid")
    private Integer categoryParentid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemCategoryCategoryid")
    private Collection<Item> itemCollection;

    public Category() {
    }

    public Category(Integer categoryCategoryid) {
        this.categoryCategoryid = categoryCategoryid;
    }

    public Category(Integer categoryCategoryid, String categoryName) {
        this.categoryCategoryid = categoryCategoryid;
        this.categoryName = categoryName;
    }

    public Integer getCategoryCategoryid() {
        return categoryCategoryid;
    }

    public void setCategoryCategoryid(Integer categoryCategoryid) {
        this.categoryCategoryid = categoryCategoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryParentid() {
        return categoryParentid;
    }

    public void setCategoryParentid(Integer categoryParentid) {
        this.categoryParentid = categoryParentid;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryCategoryid != null ? categoryCategoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryCategoryid == null && other.categoryCategoryid != null) || (this.categoryCategoryid != null && !this.categoryCategoryid.equals(other.categoryCategoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.Category[ categoryCategoryid=" + categoryCategoryid + " ]";
    }
    
}

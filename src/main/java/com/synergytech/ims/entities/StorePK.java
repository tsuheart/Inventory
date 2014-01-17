/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Embeddable
public class StorePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "store_item_itemcode")
    private String storeItemItemcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "store_office_officeid")
    private int storeOfficeOfficeid;

    public StorePK() {
    }

    public StorePK(String storeItemItemcode, int storeOfficeOfficeid) {
        this.storeItemItemcode = storeItemItemcode;
        this.storeOfficeOfficeid = storeOfficeOfficeid;
    }

    public String getStoreItemItemcode() {
        return storeItemItemcode;
    }

    public void setStoreItemItemcode(String storeItemItemcode) {
        this.storeItemItemcode = storeItemItemcode;
    }

    public int getStoreOfficeOfficeid() {
        return storeOfficeOfficeid;
    }

    public void setStoreOfficeOfficeid(int storeOfficeOfficeid) {
        this.storeOfficeOfficeid = storeOfficeOfficeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeItemItemcode != null ? storeItemItemcode.hashCode() : 0);
        hash += (int) storeOfficeOfficeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StorePK)) {
            return false;
        }
        StorePK other = (StorePK) object;
        if ((this.storeItemItemcode == null && other.storeItemItemcode != null) || (this.storeItemItemcode != null && !this.storeItemItemcode.equals(other.storeItemItemcode))) {
            return false;
        }
        if (this.storeOfficeOfficeid != other.storeOfficeOfficeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.StorePK[ storeItemItemcode=" + storeItemItemcode + ", storeOfficeOfficeid=" + storeOfficeOfficeid + " ]";
    }
    
}

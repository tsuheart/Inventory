/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Supplier;
import com.synergytech.ims.facade.SupplierFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author tsuheart
 */
@ManagedBean
@SessionScoped
public class SupplierController {

    @EJB
    SupplierFacade supplierfacade;
    Supplier current;

    List<Supplier> supplierlist;
    /**
     * Creates a new instance of SupplierController
     */
    public Supplier getCurrent() {
        return current;
    }

    public void setCurrent(Supplier current) {
        this.current = current;
    }

    public SupplierFacade getSupplierfacade() {
        return supplierfacade;
    }
    
    public SupplierController() {
    }

    public List<Supplier> getSupplierlist() {
        return supplierlist;
    }

    public void setSupplierlist(List<Supplier> supplierlist) {
        this.supplierlist = supplierlist;
    }
    
}

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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
    
    public void prepareCreate(){
        if(current==null)
            current=new Supplier();
    }

    public SupplierFacade getSupplierfacade() {
        return supplierfacade;
    }
    
    public SupplierController() {
    }

    public List<Supplier> getSupplierlist() {
        supplierlist=getSupplierfacade().findAll();
        return supplierlist;
    }

    public void setSupplierlist(List<Supplier> supplierlist) {
        this.supplierlist = supplierlist;
    }
    
    public void createSupplier() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getSupplierfacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Supplier Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Supplier Not Created"));
            setCurrent(null);
        }
    }

    public void editSupplier() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getSupplierfacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Supplier Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Supplier Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteSupplier() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getSupplierfacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Supplier Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Supplier Not Deleted"));
            setCurrent(null);

        }
    }
    
}

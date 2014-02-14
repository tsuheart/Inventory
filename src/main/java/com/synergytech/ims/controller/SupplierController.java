/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Supplier;
import com.synergytech.ims.facade.OfficeFacade;
import com.synergytech.ims.facade.SupplierFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tsuheart
 */
@ManagedBean
@SessionScoped
public class SupplierController implements Serializable {

    @ManagedProperty("#{loginController}")
    LoginController loginController;
    @EJB
    SupplierFacade supplierfacade;
    @EJB
    OfficeFacade officeFacade;
    Supplier current;

    List<Supplier> supplierlist;

    /**
     * Creates a new instance of SupplierController
     */
    public Supplier getCurrent() {
        return current;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public List<Supplier> findOfficeSupplier() {
        return (List<Supplier>) loginController.getCurrent().getUserOfficeOfficeid().getSupplierCollection();
    }

    public void setCurrent(Supplier current) {
        this.current = current;
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Supplier();
        }
    }

    public OfficeFacade getOfficeFacade() {
        return officeFacade;
    }

    public void setOfficeFacade(OfficeFacade officeFacade) {
        this.officeFacade = officeFacade;
    }

    public SupplierFacade getSupplierfacade() {
        return supplierfacade;
    }

    public SupplierController() {
    }

    public List<Supplier> findAllSupplier() {
        return getSupplierfacade().findAll();
    }

    public List<Supplier> getSupplierlist() {
        return supplierlist;
    }

    public void setSupplierlist(List<Supplier> supplierlist) {
        this.supplierlist = supplierlist;
    }

    public void createSupplier() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            current.getOfficeCollection().add(loginController.current.getUserOfficeOfficeid());
            loginController.current.getUserOfficeOfficeid().getSupplierCollection().add(current);
            getSupplierfacade().edit(current);
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
            getCurrent().getOfficeCollection().remove(loginController.current.getUserOfficeOfficeid());
            loginController.current.getUserOfficeOfficeid().getSupplierCollection().remove(current);            
            getOfficeFacade().edit(loginController.getCurrent().getUserOfficeOfficeid());
            getSupplierfacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Supplier Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Supplier Not Deleted"));
            setCurrent(null);

        }
    }

}

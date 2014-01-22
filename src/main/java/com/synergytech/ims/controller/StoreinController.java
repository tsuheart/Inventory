/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Storein;
import com.synergytech.ims.entities.User;
import com.synergytech.ims.facade.StoreinFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class StoreinController {

    /**
     * Creates a new instance of StoreinController
     */
    @EJB
    StoreinFacade storeinFacade;
    Storein current;
    List<Storein> storeinlist;

    public StoreinFacade getStoreinFacade() {
        return storeinFacade;
    }

    public Storein getCurrent() {
        return current;
    }

    public void setCurrent(Storein current) {
        this.current = current;
    }

    public List<Storein> getStoreinlist() {
        storeinlist=storeinFacade.findAll();
        return storeinlist;
    }

    public void setStoreinlist(List<Storein> storeinlist) {
        this.storeinlist = storeinlist;
    }
    
    public void createStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Stored"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Stored"));
            setCurrent(null);
        }
    }

    public void editStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "User Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "User Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Deleted"));
            setCurrent(null);

        }
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Storein();
        }
    }

    public StoreinController() {
    }

}

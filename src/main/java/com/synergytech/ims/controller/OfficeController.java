/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Office;
import com.synergytech.ims.facade.OfficeFacade;
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
public class OfficeController {

    /**
     * Creates a new instance of OfficeController
     */
    public OfficeController() {
    }

    @EJB
    OfficeFacade officeFacade;
    Office current;
    List<Office> officelist;

    public OfficeFacade getOfficeFacade() {
        return officeFacade;
    }

    public List<Office> getOfficelist() {
        officelist = getOfficeFacade().findAll();
        return officelist;
    }

    public void setOfficelist(List<Office> officelist) {
        this.officelist = officelist;
    }

    public Office getCurrent() {
        return current;
    }

    public void setCurrent(Office current) {
        this.current = current;
    }

    public void createOffice() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getOfficeFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful", "Office Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Office not Created"));
            setCurrent(null);
        }
    }

    public void editOffice() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getOfficeFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful", "Office Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Office not Edited"));
            setCurrent(null);
        }
    }

    public void deleteOffice() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getOfficeFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful", "Office Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Office not Edited"));
            setCurrent(null);

        }
    }
}
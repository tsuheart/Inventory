/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Office;
import com.synergytech.ims.entities.User;
import com.synergytech.ims.facade.OfficeFacade;
import com.synergytech.ims.facade.UserFacade;
import java.io.Serializable;
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
public class OfficeController implements Serializable {

    /**
     * Creates a new instance of OfficeController
     */
    public OfficeController() {
    }

    @EJB
    OfficeFacade officeFacade;

    @EJB
    UserFacade userFacade;

    Office current;
    List<Office> officelist;

    List<User> userlist;

    public OfficeFacade getOfficeFacade() {
        return officeFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
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
            context.addMessage(null, new FacesMessage("Successful!", "Office Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Office Not Created"));
            setCurrent(null);
        }
    }

    public void editOffice() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getOfficeFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Office Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Office Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteOffice() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            current.setOfficeStatus("Inactive");
            getOfficeFacade().edit(current);
//            User user=null;
//            userlist = getUserFacade().getByUserOfficeId(current);
//            for (Iterator<User> it = userlist.iterator(); it.hasNext();) {
//                user = it.next();
//                user.setUserStatus("Inactive");
//                getUserFacade().edit(user);
//            }
            setCurrent(null);

            context.addMessage(null, new FacesMessage("Successful!", "Office Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Office Not Edited"));
            setCurrent(null);

        }
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Office();
        }
    }
}

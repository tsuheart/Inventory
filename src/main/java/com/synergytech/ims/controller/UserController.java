/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.User;
import com.synergytech.ims.facade.UserFacade;
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
public class UserController {

    /**
     * Creates a new instance of UserController
     */
    @EJB
    UserFacade userFacade;
    User current;
    List<User> userlist;

    public UserController() {
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public List<User> getUserlist() {
        userlist = getUserFacade().getByUserOfficeStatus();
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public void createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getUserFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "User Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "User Not Created"));
            setCurrent(null);
        }
    }

    public void editUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getUserFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "User Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "User Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            current.setUserStatus("Inactive");
            getUserFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "User Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "User Not Deleted"));
            setCurrent(null);

        }
    }

    public void prepareCreate() {
        if (current == null) {
            current = new User();
        }
    }
}

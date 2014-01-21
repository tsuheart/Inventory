/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.User;
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
 * @author tsuheart
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    UserFacade userfacade;
    User current;
    List<User> userlist;

    boolean loggedIn;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public User getCurrent() {
        if (current == null) {
            current = new User();
        }
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public UserFacade getUserfacade() {
        return userfacade;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String login() {
        User loginusr;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            loginusr = getUserfacade().getByUserName(current.getUserUsername());
            if (loginusr.getUserUserpassword().equals(current.getUserUserpassword())) {

                setLoggedIn(true);
                setCurrent(loginusr);
                context.addMessage(null, new FacesMessage("Logged In!", "Log In Successfull"));
                return "/home.xhtml?faces-redirect=true";
            } else {
                context.addMessage(null, new FacesMessage("Error!", "Invalid Log In! Try Again!"));
                setLoggedIn(false);
                return null;
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Error!", "Invalid Log In! Try Again!"));
            setLoggedIn(false);
            return null;
        }
    }

    public String logout() {
        setLoggedIn(false);
        return "/index.xhtml?faces-redirect=true";
    }
}

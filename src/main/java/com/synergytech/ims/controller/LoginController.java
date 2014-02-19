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
import org.primefaces.context.RequestContext;

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
    int count;

    boolean loggedIn;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        count = 0;
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
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext request = RequestContext.getCurrentInstance();
        if (count < 3) {
            User loginusr;
            try {
                loginusr = getUserfacade().getByUserName(current.getUserUsername());
                if (loginusr.getUserUserpassword().equals(current.getUserUserpassword())) {
                    setLoggedIn(true);
                    count = 1;
                    setCurrent(loginusr);
                    context.addMessage(null, new FacesMessage("Logged In!", "Log In Successfull"));
                    return "/home.xhtml?faces-redirect=true";
                } else {
                    count++;
                    context.addMessage(null, new FacesMessage("Invalid Log In! Try Again! ", " You have "+(3-count)+" try left"));                                        
                    getCurrent().setUserUserpassword(null);
                    setLoggedIn(false);
                    return null;
                }
            } catch (Exception ex) {
                count++;
                context.addMessage(null, new FacesMessage("Invalid Log In! Try Again! ", " You have "+(3-count)+" try left"));
                setLoggedIn(false);
                getCurrent().setUserUserpassword(null);
                return null;
            }
        } else {            
            context.addMessage(null, new FacesMessage("Invalid Log in Try for 3 times","Try after 5 Minutes."));
            return null;
        }

    }

    public String logout() {
        setLoggedIn(false);
        return "/index.xhtml?faces-redirect=true";
    }
}

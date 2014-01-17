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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

    boolean loggedIn, msgFlag;
    String message;

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

    public boolean isMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(boolean msgFlag) {
        this.msgFlag = msgFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {
        User loginusr;
        String mess = null;
        try {
            loginusr = getUserfacade().getByUserName(current.getUserUsername());
            if (loginusr.getUserUserpassword().equals(current.getUserUserpassword())) {
                setLoggedIn(true);
                setMsgFlag(false);
                setMessage(null);
                return "/home.xhtml?faces-redirect=true";
            } else {
                mess = "Invalid login try";
                setLoggedIn(false);
                setMsgFlag(true);
                setMessage(mess);
                return null;
            }
        } catch (Exception ex) {
            setLoggedIn(false);
            setMsgFlag(true);
            mess = "Invalid login try";
            setMessage(mess);
            return null;
        }
    }

    public String logout() {
        setLoggedIn(false);
        return "/index.xhtml?faces-redirect=true";
    }
}

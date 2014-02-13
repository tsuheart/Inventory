/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Storeout;
import com.synergytech.ims.facade.StoreoutFacade;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class StoreoutController {

    /**
     * Creates a new instance of StoreinController
     */
    @EJB
    StoreoutFacade storeoutFacade;
    Storeout current;
    List<Storeout> storeoutlist;

    public StoreoutFacade getStoreoutFacade() {
        return storeoutFacade;
    }

    public Storeout getCurrent() {
        return current;
    }

    public void setCurrent(Storeout current) {
        this.current = current;
    }

    public List<Storeout> getStoreoutlist() {
        storeoutlist = getStoreoutFacade().findAll();
        return storeoutlist;
    }

    public void setStoreoutlist(List<Storeout> storeoutlist) {
        this.storeoutlist = storeoutlist;
    }

    public void itemStoreout() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreoutFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Stored Out"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Stored Out"));
            setCurrent(null);
        }
    }

    public String getCurrentDate() {
        String dateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dateString = sdf.format(date);
        return dateString;
    }

    public StoreoutController() {
    }

}

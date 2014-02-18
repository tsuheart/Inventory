/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Storeout;
import com.synergytech.ims.facade.StoreoutFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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

    @ManagedProperty("#{loginController}")
    LoginController loginController;
    @ManagedProperty("#{itemsController}")
    ItemsController itemsController;
    @ManagedProperty("#{storeController}")
    StoreController storeController;

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

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }

    public StoreoutController() {
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Storeout();
        }
    }

    public void itemStoreout() {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(date);
            getCurrent().setStoreoutCreatedDate(new java.sql.Date(date.getTime()));
            getCurrent().setStoreoutMeasure(storeController.current.getItem().getItemMeasurebasesMeasureid().getMeasurebasesName());
            getCurrent().setStoreoutCreatedby(loginController.getCurrent());
            getCurrent().setStoreoutItemItemcode(itemsController.getCurrent());
            getCurrent().setStoreoutItemOfficeOfficeid(loginController.getCurrent().getUserOfficeOfficeid());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

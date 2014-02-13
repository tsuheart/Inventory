/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Store;
import com.synergytech.ims.facade.StoreFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class StoreController {

    /**
     * Creates a new instance of StoreinController
     */
    @EJB
    StoreFacade storeFacade;
    Store current;
    List<Store> storelist;

    public StoreFacade getStoreFacade() {
        return storeFacade;
    }

    public Store getCurrent() {
        return current;
    }

    public void setCurrent(Store current) {
        this.current = current;
    }

    public List<Store> getStorelist() {
        storelist = getStoreFacade().findAll();
        return storelist;
    }

    public void setStorelist(List<Store> storeinlist) {
        this.storelist = storeinlist;
    }

    public StoreController() {
    }

}

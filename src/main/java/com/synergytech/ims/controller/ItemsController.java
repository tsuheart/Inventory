/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Item;
import com.synergytech.ims.facade.ItemFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "itemsController")
@SessionScoped
public class ItemsController implements Serializable {

    /**
     * Creates a new instance of ItemsController
     */
    @EJB
    ItemFacade itemFacade;
    Item current;
    List<Item> itemlist;   
    
    public ItemsController() {
    }

    public ItemFacade getItemFacade() {
        return itemFacade;
    }

    public Item getCurrent() {
        return current;
    }

    public void setCurrent(Item current) {
        this.current = current;
    }

    public List<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<Item> itemlist) {
        this.itemlist = itemlist;
    }
    
    public void prepareCreate() {
        if (current == null) {
            current = new Item();
        }
    }
    
    public List<Item> getAllItem(){
        return getItemFacade().findAll();
    }

    public void createItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getItemFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Created"));
            setCurrent(null);
        }
    }

    public void editItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getItemFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getItemFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Deleted"));
            setCurrent(null);

        }  
    }
    
    public List<Item> itemByCategory(Category cat){
        return getItemFacade().getByCategoryId(cat);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Store;
import com.synergytech.ims.entities.StorePK;
import com.synergytech.ims.entities.Storein;
import com.synergytech.ims.facade.StoreFacade;
import com.synergytech.ims.facade.StoreinFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class StoreinController {

    /**
     * Creates a new instance of StoreinController
     */
    @EJB
    StoreinFacade storeinFacade;
    @EJB
    StoreFacade storeFacade;

    @Inject
    CategoryController categoryController;

    @ManagedProperty("#{loginController}")
    LoginController loginController;
    @ManagedProperty("#{itemsController}")
    ItemsController itemsController;

    Storein current;
    List<Storein> storeinlist;

    public StoreinFacade getStoreinFacade() {
        return storeinFacade;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public Storein getCurrent() {
        return current;
    }

    public void setCurrent(Storein current) {
        this.current = current;
    }

    public List<Storein> getStoreinlist() {
        storeinlist = storeinFacade.findAll();
        return storeinlist;
    }

    public void setStoreinlist(List<Storein> storeinlist) {
        this.storeinlist = storeinlist;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public StoreFacade getStoreFacade() {
        return storeFacade;
    }

    public void setStoreFacade(StoreFacade storeFacade) {
        this.storeFacade = storeFacade;
    }

    public void createStorein() throws NoResultException {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            List<Store> newstore = getStoreFacade().getByStoreItemCode(getCurrent().getStoreinItemItemcode().getItemItemcode());
            if (newstore.isEmpty()) {
                Store newStoreItem = new Store();
                newStoreItem.setItem(getCurrent().getStoreinItemItemcode());
                newStoreItem.setOffice(loginController.getCurrent().getUserOfficeOfficeid());
                newStoreItem.setStoreQuantity(getCurrent().getStoreinQuantity());
                StorePK s=new StorePK();
                s.setStoreItemItemcode(getCurrent().getStoreinItemItemcode().getItemItemcode());
                s.setStoreOfficeOfficeid(loginController.getCurrent().getUserOfficeOfficeid().getOfficeOfficeid());
                newStoreItem.setStorePK(s);
                newStoreItem.setStoreUnit(getCurrent().getStoreinItemItemcode().getItemMeasurebasesMeasureid().getMeasurebasesName());
                getStoreFacade().create(newStoreItem);
                getStoreinFacade().edit(current);
            } else {
                Store currentStoreItem = newstore.get(0);
                int sum;
                sum = Integer.valueOf(currentStoreItem.getStoreQuantity()) + Integer.valueOf(getCurrent().getStoreinQuantity());
                currentStoreItem.setStoreQuantity(String.valueOf(sum));
                getStoreFacade().edit(currentStoreItem);
                getStoreinFacade().edit(current);
            }
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Stored"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Stored"));
            setCurrent(null);
        }
    }

    public void editStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "User Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "User Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Deleted"));
            setCurrent(null);

        }
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Storein();
        }
    }

    public void firstStoreInCreate() {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(date);
            getCurrent().setStoreinCreatedDate(new java.sql.Date(date.getTime()));
            getCurrent().setStoreinMeasure(itemsController.getCurrent().getItemMeasurebasesMeasureid().getMeasurebasesName());
            getCurrent().setStoreinCreatedby(loginController.getCurrent());
            getCurrent().setStoreinItemItemcode(itemsController.getCurrent());
            getCurrent().setStoreinOfficeOfficeid(loginController.getCurrent().getUserOfficeOfficeid());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private TreeNode selectedNode;

    public StoreinController() {
    }
}

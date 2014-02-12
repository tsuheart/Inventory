/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Item;
import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.facade.ItemFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

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
    private TreeNode selectedcatNode;
    private TreeNode selectedmeaNode;
    boolean selectCat;
    boolean selectMea;

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

    public TreeNode getSelectedcatNode() {
        return selectedcatNode;
    }

    public void setSelectedcatNode(TreeNode selectedcatNode) {
        this.selectedcatNode = selectedcatNode;
    }

    public TreeNode getSelectedmeaNode() {
        return selectedmeaNode;
    }

    public void setSelectedmeaNode(TreeNode selectedmeaNode) {
        this.selectedmeaNode = selectedmeaNode;
    }
  

    public boolean isSelectCat() {
        return selectCat;
    }

    public void setSelectCat(boolean selectCat) {
        this.selectCat = selectCat;
    }

    public boolean isSelectMea() {
        return selectMea;
    }

    public void setSelectMea(boolean selectMea) {
        this.selectMea = selectMea;
    }

    public Category getSelectcat() {
        return selectcat;
    }

    public void setSelectcat(Category selectcat) {
        this.selectcat = selectcat;
    }

    public Measurebases getSelectmea() {
        return selectmea;
    }

    public void setSelectmea(Measurebases selectmea) {
        this.selectmea = selectmea;
    }

    public List<Item> getAllItem() {
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
    Category selectcat = null;
    Measurebases selectmea = null;

    public void onNodeSelect(NodeSelectEvent event) {
        setSelectCat(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        Category cat;
        cat = (Category) selectedcatNode.getData();
        selectcat = cat;
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        setSelectCat(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectcat = null;
    }

    public void onNodeSelectM(NodeSelectEvent event) {
        setSelectMea(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        Measurebases mea;
        mea = (Measurebases) selectedmeaNode.getData();
        selectmea = mea;
    }

    public void onNodeUnselectM(NodeUnselectEvent event) {
        setSelectMea(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectmea = null;
    }

    public List<Item> itemByCategory(Category cat) {
        return getItemFacade().getByCategoryId(cat);
    }

}

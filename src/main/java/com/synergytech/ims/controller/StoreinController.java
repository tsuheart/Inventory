/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Storein;
import com.synergytech.ims.facade.StoreinFacade;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
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
    @Inject
    ItemsController itemController;
    @Inject
    CategoryController categoryController;
    Storein current;
    List<Storein> storeinlist;
    boolean showItemList;

    public boolean isShowItemList() {
        return showItemList;
    }

    public void setShowItemList(boolean showItemList) {
        this.showItemList = showItemList;
    }

    public StoreinFacade getStoreinFacade() {
        return storeinFacade;
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

    public void createStorein() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getStoreinFacade().create(current);
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

    public String getCurrentDate() {
        String dateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dateString = sdf.format(date);
        return dateString;
    }

    private TreeNode selectedNode;

    public StoreinController() {
    }

    public void onNodeSelect(NodeSelectEvent event) {
        setShowItemList(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        Category cat=new Category();
        cat=(Category) selectedNode.getData();
        categoryController.setCurrent(cat);
        itemController.setItemlist(itemController.itemByCategory(categoryController.current));
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("createDialog.show();");
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        setShowItemList(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

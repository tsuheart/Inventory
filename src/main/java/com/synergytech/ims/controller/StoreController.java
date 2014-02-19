/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Store;
import com.synergytech.ims.facade.CategoryFacade;
import com.synergytech.ims.facade.StoreFacade;
import com.synergytech.ims.facade.StoreoutFacade;
import java.util.Iterator;
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
public class StoreController {

    /**
     * Creates a new instance of StoreinController
     */
    @EJB
    StoreFacade storeFacade;
    @EJB
    CategoryFacade categoryFacade;
    @EJB
    StoreoutFacade storeoutFacade;
    @Inject
    CategoryController categoryController;

    boolean showItemList;
    private TreeNode selectedNode;

    Store current;
    List<Store> storelist, tempList;

    public StoreFacade getStoreFacade() {
        return storeFacade;
    }

    public void setStoreFacade(StoreFacade storeFacade) {
        this.storeFacade = storeFacade;
    }

    public StoreoutFacade getStoreoutFacade() {
        return storeoutFacade;
    }

    public void setStoreoutFacade(StoreoutFacade storeoutFacade) {
        this.storeoutFacade = storeoutFacade;
    }

    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public boolean isShowItemList() {
        return showItemList;
    }

    public void setShowItemList(boolean showItemList) {
        this.showItemList = showItemList;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Store getCurrent() {
        return current;
    }

    public void setCurrent(Store current) {
        this.current = current;
    }

    public List<Store> getStorelist() {
        return storelist;
    }

    public void setStorelist(List<Store> storeinlist) {
        this.storelist = storeinlist;
    }

    public StoreController() {
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Store();
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        setShowItemList(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        Category cat = (Category) selectedNode.getData();
        categoryController.setCurrent(cat);
        setStorelist(storeItemByCategory(categoryController.current));
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        setShowItemList(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Store> storeItemByCategory(Category cat) {
        tempList = null;
        tempList = getStoreFacade().getStoreItemByItemCategory(cat);
        findChild(cat);
        return tempList;
    }

    public void findChild(Category category) {
        List<Category> childList = getCategoryFacade().getByParentID(category);
        for (Iterator<Category> it = childList.iterator(); it.hasNext();) {
            Category categoryTemp = it.next();
            tempList.addAll(getStoreFacade().getStoreItemByItemCategory(categoryTemp));
            findChild(categoryTemp);
        }
    }

    public void storeoutItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                List<Store> newstore = getStoreFacade().getByStoreItemCode(getCurrent().getStorePK().getStoreItemItemcode());
                if (newstore.isEmpty() == false) {
                    Store currentStoreItem = newstore.get(0);
                    float res;
                    res = Float.valueOf(currentStoreItem.getStoreQuantity()) - Float.valueOf(getCurrent().getStoreQuantity());
                    if (res == 0.0) {
                        getStoreFacade().remove(current);
                    } else {
                        currentStoreItem.setStoreQuantity(String.valueOf(res));
                        getStoreFacade().edit(current);
                    }
//                    getStoreoutFacade().edit();
                }
                setCurrent(null);
                context.addMessage(null, new FacesMessage("Successful!", "Item Stored"));
            } catch (Exception ex) {
                context.addMessage(null, new FacesMessage("Failed!", "Item Not Stored"));
                setCurrent(null);
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Stored Out"));
            setCurrent(null);
        }
    }
}

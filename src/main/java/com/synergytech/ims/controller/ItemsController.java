/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Item;
import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.entities.Store;
import com.synergytech.ims.entities.Storein;
import com.synergytech.ims.entities.Storeout;
import com.synergytech.ims.facade.CategoryFacade;
import com.synergytech.ims.facade.ItemFacade;
import com.synergytech.ims.facade.StoreFacade;
import com.synergytech.ims.facade.StoreinFacade;
import com.synergytech.ims.facade.StoreoutFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
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
    @EJB
    CategoryFacade categoryFacade;
    @EJB
    StoreinFacade storeinFacade;
    @EJB
    StoreoutFacade storeoutFacade;
    @EJB
    StoreFacade storeFacade;
    @Inject
    CategoryController categoryController;
    
    Item current;
    List<Item> itemlist, tempList;
    private TreeNode selectedcatNode,selectedNode;
    private TreeNode selectedmeaNode;
    boolean selectCat,showItemList;
    boolean selectMea;

    @ManagedProperty("#{storeinController}")
    StoreinController storeinController;

    public ItemsController() {
        tempList = new ArrayList<Item>();
    }

    public ItemFacade getItemFacade() {
        return itemFacade;
    }

    public StoreinFacade getStoreinFacade() {
        return storeinFacade;
    }

    public StoreoutFacade getStoreoutFacade() {
        return storeoutFacade;
    }

    public StoreFacade getStoreFacade() {
        return storeFacade;
    }

    public Item getCurrent() {
        return current;
    }

    public void setCurrent(Item current) {
        this.current = current;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public boolean isShowItemList() {
        return showItemList;
    }

    public void setShowItemList(boolean showItemList) {
        this.showItemList = showItemList;
    }

    public List<Item> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<Item> itemlist) {
        this.itemlist = itemlist;
    }

    public void prepareCreate() {
        selectedcatNode=null;
        setCurrent(null);
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

    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public TreeNode getSelectedmeaNode() {
        return selectedmeaNode;
    }

    public void setSelectedmeaNode(TreeNode selectedmeaNode) {
        this.selectedmeaNode = selectedmeaNode;
    }

    public List<Item> getTempList() {
        return tempList;
    }

    public void setTempList(List<Item> tempList) {
        this.tempList = tempList;
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

    public Category getSelectcategory() {
        return selectcategory;
    }

    public void setSelectcategory(Category selectcategory) {
        this.selectcategory = selectcategory;
    }

    public Measurebases getSelectmeasurebases() {
        return selectmeasurebases;
    }

    public void setSelectmeasurebases(Measurebases selectmeasurebases) {
        this.selectmeasurebases = selectmeasurebases;
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
            getCurrent().getStoreinCollection().removeAll(getByItemCodeStorein(current));
            
//            storeinController.current.getStoreinItemItemcode().getStoreinCollection().remove(current);
            getCurrent().getStoreoutCollection().removeAll(getByItemCodeStoreout(current));
            
            getCurrent().getStoreCollection().removeAll(getByItemCodeStore(current.getItemItemcode()));
            
            current.setItemStatus("Inactive");
//            getItemFacade().edit(current);
            getItemFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Item Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Item Not Deleted"));
            setCurrent(null);

        }
    }
    Category selectcategory = null;
    Measurebases selectmeasurebases = null;

    public void onNodeSelect(NodeSelectEvent event) {
        setSelectCat(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectcategory = (Category) selectedcatNode.getData();
        current.setItemCategoryCategoryid(selectcategory);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        setSelectCat(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectcategory = null;
    }

    public void onNodeSelectM(NodeSelectEvent event) {
        setSelectMea(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectmeasurebases = (Measurebases) selectedmeaNode.getData();
        current.setItemMeasurebasesMeasureid(selectmeasurebases);
    }

    public void onNodeUnselectM(NodeUnselectEvent event) {
        setSelectMea(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectmeasurebases = null;
    }

    public void onNodeSelectList(NodeSelectEvent event) {
//        setShowItemList(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        Category cat = new Category();
        cat = (Category) selectedNode.getData();
        categoryController.setCurrent(cat);
        setItemlist(itemByCategory(categoryController.current));
    }

    public void onNodeUnselectList(NodeUnselectEvent event) {
//        setShowItemList(false);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectcategory = null;
    }

    public List<Item> itemByCategory(Category cat) {
        tempList = getItemFacade().getByCategoryId(cat);
        findChild(cat);
        return tempList;
    }

    public void findChild(Category category) {
        List<Category> childList = getCategoryFacade().getByParentID(category);
        for (Iterator<Category> it = childList.iterator(); it.hasNext();) {
            Category categoryTemp = it.next();
            tempList.addAll(getItemFacade().getByCategoryId(categoryTemp));
            findChild(categoryTemp);
        }
    }

    public List<Storein> getByItemCodeStorein(Item item) {
        return getStoreinFacade().getByStoreinItemCode(item);
    }

    public List<Storeout> getByItemCodeStoreout(Item item) {
        return getStoreoutFacade().getByStoreoutItemCode(item);
    }

    public List<Store> getByItemCodeStore(String itemcode) {
        return getStoreFacade().getByStoreItemCode(itemcode);
    }
    
    public void showAll(){
        setItemlist(getItemFacade().findAll());
        setShowItemList(true);
    }
}

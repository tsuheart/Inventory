/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.facade.CategoryFacade;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tsuheart
 */
@ManagedBean
@ViewScoped
public class CategoryController implements Serializable {

    @EJB
    CategoryFacade categoryFacade;
    Category current;
    List<Category> categorylist;
    private TreeNode root;
    private TreeNode actualRoot, tempRoot;
    private TreeNode selectedNode;

    /**
     * Creates a new instance of CategoryController
     */
    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public Category getCurrent() {
        return current;
    }

    public void setCurrent(Category current) {
        this.current = current;
    }

    public CategoryController() {
    }

    public List<Category> getCategorylist() {
        categorylist = getCategoryFacade().findAll();
        return categorylist;
    }

    public void setCategorylist(List<Category> categorylist) {
        this.categorylist = categorylist;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Category();
        }
    }

    public void createCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Created"));
            setCurrent(null);
        }
    }

    public void editCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            List<Category> tobeDeletedlist;
            tobeDeletedlist=getCategoryFacade().getByParentID(current.getCategoryParentid());
            for (Iterator<Category> it = tobeDeletedlist.iterator(); it.hasNext();) {
                Category category = it.next();
                getCategoryFacade().remove(category);                
            }
            getCategoryFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Deleted"));
            setCurrent(null);

        }
    }

    public List<Category> All() {
        return getCategoryFacade().findAll();
    }

    public TreeNode makeTree() {
        root = new DefaultTreeNode("Root", null);
        actualRoot = new DefaultTreeNode("Root", null);
        categorylist = getCategoryFacade().getByParentNullID();
        for (Iterator<Category> it = categorylist.iterator(); it.hasNext();) {
            Category category = it.next();
            actualRoot = new DefaultTreeNode(category, root);
            createTree(category.getCategoryCategoryid(), category.getCategoryName(), actualRoot);
            actualRoot.setParent(root);
        }
        return root;
    }

    public void createTree(Integer Pid, String categoryName, TreeNode subRoot) {
        List<Category> subRootList;
        subRootList = getCategoryFacade().getByParentID(Pid);
        if (!subRootList.isEmpty()) {
            for (Iterator<Category> it = subRootList.iterator(); it.hasNext();) {
                Category category = it.next();
                TreeNode node = new DefaultTreeNode(category, subRoot);
                createTree(category.getCategoryCategoryid(), category.getCategoryName(), node);
            }
        }
    }
    
    public void onNodeSelect(NodeSelectEvent event) {
        String categoryName = event.getTreeNode().toString();
//        Integer pid = getCategoryFacade().getByCategoryName(categoryName);
//        prepareCreate();
//        getCurrent().setCategoryParentid(pid);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", categoryName);        
        FacesContext.getCurrentInstance().addMessage(null, message);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("createDialog.show();");
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void viewButton(){
        setCurrent(getCategoryFacade().getByCategoryID(getCurrent().getCategoryParentid()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.facade.CategoryFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tsuheart
 */
@Named(value = "dataBean")
@SessionScoped
public class DataBean implements Serializable {

    @EJB
    CategoryFacade categoryfacade;
    List<Category> categorylist;

    /**
     * Creates a new instance of DataBean
     */
    private TreeNode root;
    private TreeNode actualRoot, tempRoot;
    private TreeNode selectedNode;

    public CategoryFacade getCategoryfacade() {
        return categoryfacade;
    }

    public void setCategoryfacade(CategoryFacade categoryfacade) {
        this.categoryfacade = categoryfacade;
    }

    public List<Category> getCategorylist() {
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

    public DataBean() {
    }

    public TreeNode makeTree() {
        root = new DefaultTreeNode("Root", null);
        actualRoot = new DefaultTreeNode("Root", null);
        categorylist = getCategoryfacade().getByParentNullID();
        for (Iterator<Category> it = categorylist.iterator(); it.hasNext();) {
            Category category = it.next();
            actualRoot = new DefaultTreeNode(category.getCategoryName(), root);
            createTree(category.getCategoryCategoryid(), category.getCategoryName(), actualRoot);
            actualRoot.setParent(root);
        }
        return root;
    }

    public void createTree(Integer Pid, String categoryName, TreeNode subRoot) {
        List<Category> subRootList;
        subRootList = getCategoryfacade().getByParentID(Pid);
        if (!subRootList.isEmpty()) {
            for (Iterator<Category> it = subRootList.iterator(); it.hasNext();) {
                Category category = it.next();
                TreeNode node = new DefaultTreeNode(category.getCategoryName(), subRoot);
                createTree(category.getCategoryCategoryid(), category.getCategoryName(), node);
            }
        }
    }

    public void onNodeExpand(NodeExpandEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeSelect(NodeSelectEvent event) {
        TreeNode node=event.getTreeNode();
        
        while(!node.getParent().toString().equals("Root")){
            //parent=node.toString();
            node=node.getParent();
        } 
        String parent=node.toString();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", parent);
        FacesContext.getCurrentInstance().addMessage(null, message);
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.execute("createDialog.show();");
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.facade.CategoryFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tsuheart
 */
@ManagedBean
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    CategoryFacade categoryFacade;
    Category current, treeObject;
    List<Category> categorylist;
    List<Category> parentcategorylist;
    private TreeNode root;
    private TreeNode actualRoot;
    private TreeNode selectedNode;
    private Date fromDate;

    /**
     * Creates a new instance of CategoryController
     * @return 
     */
    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Category getCurrent() {
        return current;
    }

    public void setCurrent(Category current) {
        this.current = current;
    }

    public CategoryController() {
    }
    
    public List<Category> getParentcategorylist() {
        parentcategorylist = getCategoryFacade().getByParentNullID();
        return parentcategorylist;
    }

    public void setParentcategorylist(List<Category> parentcategorylist) {
        this.parentcategorylist = parentcategorylist;
    }

    private List<Category> selectall(){
        return(getCategoryFacade().findAll());
    }
    public List<Category> getCategorylist() {
        categorylist = selectall();
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

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public Category getTreeObject() {
        return treeObject;
    }

    public void setTreeObject(Category treeObject) {
        this.treeObject = treeObject;
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Category();
        }
    }

    public void prepareTreeObject() {
        if (treeObject == null) {
            treeObject = new Category();
        }
    }

    public void createCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().create(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Created"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Created"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }

    public void editCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().edit(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Edited"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Edited"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }
    
    public void deleteTree(Category cat) {
        List<Category> subRootList;
        subRootList = getCategoryFacade().getByParentID(cat);
        if (!subRootList.isEmpty()) {
            for (Iterator<Category> it = subRootList.iterator(); it.hasNext();) {
                Category category = it.next();
                deleteTree(category);
            }
        }
        getCategoryFacade().remove(cat);
    }
    
    public void deleteCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            List<Category> tobeDeletedlist = getCategoryFacade().getByParentID(current);
            for (Iterator<Category> it = tobeDeletedlist.iterator(); it.hasNext();) {
                Category category = it.next();
                deleteTree(category);
                getCategoryFacade().remove(category);
            }
            getCategoryFacade().remove(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Category Deleted"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Category Not Deleted"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }

    public List<Category> All() {
        return getCategoryFacade().findAll();
    }

    private TreeNode rootNode;

    @PostConstruct
    public void init() {
        rootNode = makeTree();
    }

    public TreeNode makeTree() {
        root = new DefaultTreeNode("Root", null);
        actualRoot = new DefaultTreeNode("Root", null);
        categorylist = getCategoryFacade().getByParentNullID();
        for (Iterator<Category> it = categorylist.iterator(); it.hasNext();) {
            Category category = it.next();
            actualRoot = new DefaultTreeNode(category, root);
            createTree(category, actualRoot);
            actualRoot.setParent(root);
        }
        return root;
    }

    public void createTree(Category cat, TreeNode subRoot) {
        List<Category> subRootList;
        subRootList = getCategoryFacade().getByParentID(cat);
        if (!subRootList.isEmpty()) {
            for (Iterator<Category> it = subRootList.iterator(); it.hasNext();) {
                Category category = it.next();
                TreeNode node = new DefaultTreeNode(category, subRoot);
                createTree(category, node);
            }
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        prepareTreeObject();
        treeObject = (Category) event.getTreeNode().getData();
        otherClick();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", treeObject.getCategoryName());
        FacesContext.getCurrentInstance().addMessage(null, message);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("createDialog.show();");
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        prepareTreeObject();
        treeObject = (Category) event.getTreeNode().getData();
        setCurrent(null);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", treeObject.getCategoryName());
        treeObject=null;
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createClick() {
        setCurrent(null);
        prepareCreate();
        if (treeObject != null) {
            getCurrent().setCategoryParentid(treeObject);
        }
    }

    public void otherClick() {
        if (treeObject != null) {
            setCurrent(treeObject);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.facade.MeasurebasesFacade;
import java.io.Serializable;
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
public class MeasurebasesController implements Serializable {

    @EJB
    MeasurebasesFacade measurebasesFacade;
    Measurebases current, treeObject;
    List<Measurebases> measurebaseslist;
    List<Measurebases> parentcategorylist;
    private TreeNode root;
    private TreeNode actualRoot;
    private TreeNode selectedNode;

    /**
     * Creates a new instance of MeasurebasesController
     */
    public MeasurebasesFacade getMeasurebasesFacade() {
        return measurebasesFacade;
    }

    public Measurebases getCurrent() {
        return current;
    }

    public void setCurrent(Measurebases current) {
        this.current = current;
    }

    public MeasurebasesController() {
    }

    public List<Measurebases> getParentcategorylist() {
        parentcategorylist = getMeasurebasesFacade().getByParentNullID();
        return parentcategorylist;
    }

    public void setParentcategorylist(List<Measurebases> parentcategorylist) {
        this.parentcategorylist = parentcategorylist;
    }

    public List<Measurebases> getMeasurebaseslist() {
        measurebaseslist = getMeasurebasesFacade().findAll();
        return measurebaseslist;
    }

    public void setMeasurebaseslist(List<Measurebases> measurebaseslist) {
        this.measurebaseslist = measurebaseslist;
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

    public Measurebases getTreeObject() {
        return treeObject;
    }

    public void setTreeObject(Measurebases treeObject) {
        this.treeObject = treeObject;
    }

    public void prepareCreate() {
        if (current == null) {
            current = new Measurebases();
        }
    }

    public void prepareTreeObject() {
        if (treeObject == null) {
            treeObject = new Measurebases();
        }
    }

    public void createMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getMeasurebasesFacade().create(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measurebases Created"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measurebases Not Created"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }

    public void editMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getMeasurebasesFacade().edit(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measurebases Edited"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measurebases Not Edited"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }

    public void deleteTree(Measurebases measurebase) {
        List<Measurebases> subRootList;
        subRootList = getMeasurebasesFacade().getByParentID(measurebase);
        if (!subRootList.isEmpty()) {
            for (Iterator<Measurebases> it = subRootList.iterator(); it.hasNext();) {
                Measurebases measure = it.next();
                deleteTree(measure);
            }
        }
        getMeasurebasesFacade().remove(measurebase);
    }

    public void deleteMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            List<Measurebases> tobeDeletedlist = getMeasurebasesFacade().getByParentID(current);
            for (Iterator<Measurebases> it = tobeDeletedlist.iterator(); it.hasNext();) {
                Measurebases measure = it.next();
                deleteTree(measure);
                getMeasurebasesFacade().remove(measure);
            }
            getMeasurebasesFacade().remove(current);
            setCurrent(null);
            setTreeObject(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measurebases Deleted"));
            init();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measurebases Not Deleted"));
            setCurrent(null);
            setTreeObject(null);
            init();
        }
    }

    public List<Measurebases> All() {
        return getMeasurebasesFacade().findAll();
    }

    private TreeNode rootNode;

    @PostConstruct
    public void init() {
        rootNode = makeTree();
    }

    public TreeNode makeTree() {
        root = new DefaultTreeNode("Root", null);
        actualRoot = new DefaultTreeNode("Root", null);
        measurebaseslist = getMeasurebasesFacade().getByParentNullID();
        for (Iterator<Measurebases> it = measurebaseslist.iterator(); it.hasNext();) {
            Measurebases measure = it.next();
            actualRoot = new DefaultTreeNode(measure, root);
            createTree(measure, actualRoot);
            actualRoot.setParent(root);
        }
        return root;
    }

    public void createTree(Measurebases measure, TreeNode subRoot) {
        List<Measurebases> subRootList;
        subRootList = getMeasurebasesFacade().getByParentID(measure);
        if (!subRootList.isEmpty()) {
            for (Iterator<Measurebases> it = subRootList.iterator(); it.hasNext();) {
                Measurebases measurebase = it.next();
                TreeNode node = new DefaultTreeNode(measurebase, subRoot);
                createTree(measurebase, node);
            }
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        prepareTreeObject();
        treeObject = (Measurebases) event.getTreeNode().getData();
        otherClick();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", treeObject.getMeasurebasesName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        prepareTreeObject();
        treeObject = (Measurebases) event.getTreeNode().getData();
        setCurrent(null);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", treeObject.getMeasurebasesName());
        treeObject = null;
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createClick() {
        setCurrent(null);
        prepareCreate();
        if (treeObject != null) {
            getCurrent().setMeasurebasesParentid(treeObject);
        }
    }

    public void otherClick() {
        if (treeObject != null) {
            setCurrent(treeObject);
        }
    }
}

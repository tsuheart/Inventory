/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.facade.MeasurebasesFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class MeasurebasesController implements Serializable {

    /**
     * Creates a new instance of MeasurebasesController
     */
    @EJB
    MeasurebasesFacade measurebasesFacade;
    Measurebases current, treeObject;
    List<Measurebases> measurebaseslist;
    private TreeNode root;
    private TreeNode actualRoot;
    private TreeNode selectedNode;

    public MeasurebasesFacade getMeasurebasesFacade() {
        return measurebasesFacade;
    }

    public Measurebases getCurrent() {
        return current;
    }

    public void setCurrent(Measurebases current) {
        this.current = current;
    }

    public List<Measurebases> getMeasurebaseslist() {
        measurebaseslist = measurebasesFacade.findAll();
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

    public TreeNode getActualRoot() {
        return actualRoot;
    }

    public void setActualRoot(TreeNode actualRoot) {
        this.actualRoot = actualRoot;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
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

    public void createMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getMeasurebasesFacade().create(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measure Bases Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measure Bases Not Created"));
            setCurrent(null);
        }
    }

    public void editMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getMeasurebasesFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measure Bases Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measure Bases Not Edited"));
            setCurrent(null);
        }
    }

    public void deleteMeasurebases() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            List<Measurebases> tobeDeletedlist = getMeasurebasesFacade().getByParentID(current.getMeasurebasesMeasureid());
            for (Iterator<Measurebases> it = tobeDeletedlist.iterator(); it.hasNext();) {
                Measurebases measure = it.next();
                getMeasurebasesFacade().remove(measure);
            }
            getMeasurebasesFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measure Bases Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measure Bases Not Deleted"));
            setCurrent(null);

        }
    }

    public List<Measurebases> All() {
        return getMeasurebasesFacade().findAll();
    }

    public TreeNode makeTree() {
        root = new DefaultTreeNode("Root", null);
        actualRoot = new DefaultTreeNode("Root", null);
        measurebaseslist = getMeasurebasesFacade().getByParentNullID();
        for (Iterator<Measurebases> it = measurebaseslist.iterator(); it.hasNext();) {
            Measurebases measure = it.next();
            actualRoot = new DefaultTreeNode(measure, root);
            createTree(measure.getMeasurebasesMeasureid(), measure.getMeasurebasesName(), actualRoot);
            actualRoot.setParent(root);
        }
        return root;
    }

    public void createTree(Integer Pid, String measureName, TreeNode subRoot) {
        List<Measurebases> subRootList;
        subRootList = getMeasurebasesFacade().getByParentID(Pid);
        if (!subRootList.isEmpty()) {
            for (Iterator<Measurebases> it = subRootList.iterator(); it.hasNext();) {
                Measurebases measure = it.next();
                TreeNode node = new DefaultTreeNode(measure, subRoot);
                createTree(measure.getMeasurebasesMeasureid(), measure.getMeasurebasesName(), node);
            }
        }
    }

    public void prepareTreeObject() {
        if (treeObject == null) {
            treeObject = new Measurebases();
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        prepareTreeObject();
        treeObject = (Measurebases) event.getTreeNode().getData();
        otherClick();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", treeObject.getMeasurebasesName());
        FacesContext.getCurrentInstance().addMessage(null, message);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("createDialog.show();");
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        prepareTreeObject();
        treeObject = (Measurebases) event.getTreeNode().getData();
        setCurrent(null);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", treeObject.getMeasurebasesName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createClick() {
        setCurrent(null);
        prepareCreate();
        if (treeObject != null) {
            getCurrent().setMeasurebasesMeasureid(treeObject.getMeasurebasesMeasureid());
        }
    }

    public void otherClick() {
        if (treeObject != null) {
            setCurrent(treeObject);
        }
    }

    public MeasurebasesController() {
    }

}

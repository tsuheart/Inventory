/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Supplier;
import com.synergytech.ims.facade.CategoryFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tsuheart
 */
@ManagedBean
@SessionScoped
public class CategoryController {

    @EJB
    CategoryFacade categoryFacade;
    Category current;
    List<Category> categorylist;

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
        categorylist=getCategoryFacade().findAll();
        return categorylist;
    }

    public void setCategorylist(List<Category> categorylist) {
        this.categorylist = categorylist;
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
            context.addMessage(null, new FacesMessage("Successful", "Supplier Created"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Supplier not Created"));
            setCurrent(null);
        }
    }

    public void editCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().edit(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Supplier Edited", "Supplier Edited"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Supplier not Edited"));
            setCurrent(null);
        }
    }

    public void deleteCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            getCategoryFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful", "Supplier Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed", "Supplier not Edited"));
            setCurrent(null);

        }
    }
    
    public List<Category> All(){
        return getCategoryFacade().findAll();
    }
}

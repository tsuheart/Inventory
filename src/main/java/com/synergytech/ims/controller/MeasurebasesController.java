/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.controller;

import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.facade.MeasurebasesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@Named(value = "measurebasesController")
@SessionScoped
public class MeasurebasesController implements Serializable {

    /**
     * Creates a new instance of MeasurebasesController
     */
    @EJB
    MeasurebasesFacade measurebasesFacade;
    Measurebases current;
    List<Measurebases> measurebaseslist;

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
            getMeasurebasesFacade().remove(current);
            setCurrent(null);
            context.addMessage(null, new FacesMessage("Successful!", "Measure Bases Deleted"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Failed!", "Measure Bases Not Deleted"));
            setCurrent(null);

        }  
    }

    public MeasurebasesController() {
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.converter;

import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.facade.MeasurebasesFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class MeasurebasesConverter implements Converter {

    /**
     *
     * @author Administrator
     */
    @EJB
    private MeasurebasesFacade measurebasesFacade;

    public MeasurebasesFacade getMeasurebasesFacade() {
        return measurebasesFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException {

        if (newValue == null || newValue.isEmpty()) {
            return null;
        }
        return (measurebasesFacade.getByMeasurebasesId(Integer.valueOf(newValue)));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null; // Or an empty string, can also.
        }

        if (!(value instanceof Measurebases)) {
            throw new ConverterException("The value is not a valid Measurebase: " + value);
        }
        Integer measurebasesid = ((Measurebases) value).getMeasurebasesMeasureid();
        return (measurebasesid != null) ? String.valueOf(measurebasesid) : null;
    }

    public MeasurebasesConverter() {
    }

}

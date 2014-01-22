/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.converter;

import com.synergytech.ims.entities.Storein;
import com.synergytech.ims.facade.StoreinFacade;
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
public class StoreinConverter implements Converter {

    /**
     *
     * @author Administrator
     */
    @EJB
    private StoreinFacade storeinFacade;

    public StoreinFacade getStoreinFacade() {
        return storeinFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException {

        if (newValue == null || newValue.isEmpty()) {
            return null;
        }
        return (storeinFacade.getByStoreinId(Integer.valueOf(newValue)));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null; // Or an empty string, can also.
        }

        if (!(value instanceof Storein)) {
            throw new ConverterException("The value is not a valid Office: " + value);
        }
        Integer storeinid = ((Storein) value).getStoreinId();
        return (storeinid != null) ? String.valueOf(storeinid) : null;
    }

    public StoreinConverter() {
    }

}

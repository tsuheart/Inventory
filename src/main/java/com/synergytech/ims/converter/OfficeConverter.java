/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.inventory_ims.converter;

import com.synergytech.ims.entities.Office;
import com.synergytech.ims.facade.OfficeFacade;
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
public class OfficeConverter implements Converter {

    /**
     *
     * @author Administrator
     */
    @EJB
    private OfficeFacade officeFacade;

    public OfficeFacade getOfficeFacade() {
        return officeFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException {
        if (newValue == null || newValue.isEmpty()) {
            return null;
        }
        return (officeFacade.getByOfficeId(newValue));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null; // Or an empty string, can also.
        }

        if (!(value instanceof Office)) {
            throw new ConverterException("The value is not a valid Office: " + value);
        }
        Integer officeid = ((Office) value).getOfficeOfficeid();
        return (officeid != null) ? String.valueOf(officeid) : null;
    }

    public OfficeConverter() {
    }

}

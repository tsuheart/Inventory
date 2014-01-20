/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.converter;

import com.synergytech.ims.entities.Supplier;
import com.synergytech.ims.facade.SupplierFacade;
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
public class SupplierConverter implements Converter{

    @EJB
    private SupplierFacade supplierFacade;

    public SupplierFacade getFacade() {
        return supplierFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException {
        if (newValue == null || newValue.isEmpty()) {
            return null;
        }
        return (supplierFacade.getBySupplierCode(newValue));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null; // Or an empty string, can also.
        }

        if (!(value instanceof Supplier)) {
            throw new ConverterException("The value is not a valid Supplier: " + value);
        }
        String suppliercode = ((Supplier)value).getSupplierSupplierid();
        return (suppliercode != null) ? String.valueOf(suppliercode) : null;
    }
    public SupplierConverter() {
    }
    
}

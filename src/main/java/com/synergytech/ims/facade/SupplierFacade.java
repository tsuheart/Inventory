/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Supplier;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class SupplierFacade extends AbstractFacade<Supplier> {

    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplierFacade() {
        super(Supplier.class);
    }

    public Supplier getBySupplierCode(String supplier) {
        Supplier sup = getEntityManager().createNamedQuery("Supplier.findBySupplierSupplierid", Supplier.class).setParameter("supplierSupplierid", supplier).getSingleResult();
        return sup;
    }

}

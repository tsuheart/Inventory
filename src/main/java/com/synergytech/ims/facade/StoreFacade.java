/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Store;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class StoreFacade extends AbstractFacade<Store> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StoreFacade() {
        super(Store.class);
    }
    
    public List<Store> getStoreItemByItemCategory(Category category){
        return getEntityManager().createNamedQuery("Store.findByStoreItemCategory",Store.class).setParameter("category", category).getResultList();
    }
    
     public List<Store> getByStoreItemCode(String itemcode){
        return getEntityManager().createNamedQuery("Store.findByStoreItemItemcode",Store.class).setParameter("storeItemItemcode", itemcode).getResultList();
 
    }
}
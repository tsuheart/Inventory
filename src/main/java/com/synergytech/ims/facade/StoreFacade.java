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
    
    public List<Store> getStoreItemByItemCategoryAndOfficeid(Category category,int officeID){
        return getEntityManager().createNamedQuery("Store.findByStoreItemCategoryAndOfficeid",Store.class).setParameter("category", category).setParameter("storeOfficeOfficeid", officeID).getResultList();
    }
    
     public List<Store> getByStoreItemCode(String itemcode){
        return getEntityManager().createNamedQuery("Store.findByStoreItemItemcode",Store.class).setParameter("storeItemItemcode", itemcode).getResultList(); 
    }
     
     public List<Store> getStoreItemByOfficeid(int officeID){
         return getEntityManager().createNamedQuery("Store.findByStoreOfficeOfficeid",Store.class).setParameter("storeOfficeOfficeid", officeID).getResultList(); 
     }
     
      public List<Store> getByStoreItemCodeAndOfficeid(String itemcode,int officeID){
        return getEntityManager().createNamedQuery("Store.findByStoreOfficeOfficeidAndItemItemcode",Store.class).setParameter("storeOfficeOfficeid", officeID).setParameter("storeItemItemcode", itemcode).getResultList(); 
    }
}
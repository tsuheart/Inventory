/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Category;
import com.synergytech.ims.entities.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }
    
    public Item getByItemcode(String itemcode){
        Item itm=getEntityManager().createNamedQuery("Item.findByItemItemcode",Item.class).setParameter("itemItemcode",itemcode).getSingleResult();
        return itm;
    }
    
    public List<Item> getByCategoryId(Category cat){
        return getEntityManager().createNamedQuery("Item.findByCategoryId",Item.class).setParameter("itemCategoryCategoryid", cat).getResultList();
    }
}

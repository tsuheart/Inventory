/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Item;
import com.synergytech.ims.entities.Storein;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class StoreinFacade extends AbstractFacade<Storein> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StoreinFacade() {
        super(Storein.class);
    }
    
    public List<Storein> getByStoreinItemCode(Item item){
        return getEntityManager().createNamedQuery("Storein.findByStoreinItemcode",Storein.class).setParameter("storeinItemItemcode",item).getResultList();
 
    }
    
    public Storein getByStoreinId(Integer id){
        return getEntityManager().createNamedQuery("Storein.findByStoreinId",Storein.class).setParameter("storeinId",id).getSingleResult();
 
    }
}

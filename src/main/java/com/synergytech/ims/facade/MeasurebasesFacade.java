/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Measurebases;
import com.synergytech.ims.entities.Office;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class MeasurebasesFacade extends AbstractFacade<Measurebases> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeasurebasesFacade() {
        super(Measurebases.class);
    }
    
    public Measurebases getByMeasurebasesId(Integer measurebases){
        Measurebases msrb=getEntityManager().createNamedQuery("Measurebases.findByMeasurebasesMeasureid",Measurebases.class).setParameter("measurebasesMeasureid",measurebases).getSingleResult();
        return msrb;
    }
    
}

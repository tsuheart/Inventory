/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Office;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class OfficeFacade extends AbstractFacade<Office> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfficeFacade() {
        super(Office.class);
    }
    
    public Office getByOfficeId(Integer office){
        Office off=getEntityManager().createNamedQuery("Office.findByOfficeOfficeid",Office.class).setParameter("officeOfficeid",office).getSingleResult();
        return off;
    }
    
    public List<Office> getByOfficeStatusActive(){
        return(getEntityManager().createNamedQuery("Office.findByOfficeStatusActive", Office.class).getResultList());
    }
}

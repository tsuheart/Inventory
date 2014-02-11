/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Office;
import com.synergytech.ims.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User getByUserName(String username){
        return(getEntityManager().createNamedQuery("User.findByUserUsername", User.class).setParameter("userUsername",username).getSingleResult());
    }
    
    public List<User> getByUserStatusActive(){
        return(getEntityManager().createNamedQuery("User.findByUserStatusActive", User.class).getResultList());
    }
    
    public List<User> getByUserOfficeId(Office office){
        return(getEntityManager().createNamedQuery("User.findByUserOfficeId", User.class).setParameter("userOfficeOfficeid",office).getResultList());
    }
    
    public List<User> getByUserOfficeStatus(){
        return(getEntityManager().createNamedQuery("User.findByOfficeStatus", User.class).getResultList());
    }
}

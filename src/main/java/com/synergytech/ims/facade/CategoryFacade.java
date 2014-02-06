/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.facade;

import com.synergytech.ims.entities.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "com.synergytech.ims_Project_Inventory_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
    
    public Category getByCategoryId(Integer id){
        Category cat=getEntityManager().createNamedQuery("Category.findByCategoryCategoryid",Category.class).setParameter("categoryCategoryid", id).getSingleResult();
        return cat;
    }
    
    public List<Category> getByParentID(Category cat){
        return getEntityManager().createNamedQuery("Category.findByCategoryParentid", Category.class).setParameter("categoryParentid", cat).getResultList();
    }
    
    public List<Category> getByParentNullID(){
        return getEntityManager().createNamedQuery("Category.findByParentNullID", Category.class).getResultList();
    }
    
    
}

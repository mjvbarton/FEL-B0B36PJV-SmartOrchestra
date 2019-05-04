/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import javax.persistence.EntityManager;

/**
 *
 * @author Matěj Bartoň
 */
public class UserWriter {
    private EntityManager em;
    
    public UserWriter() {
        em = SmartOrchestra.getEntityManager();
    }
    
    public void write(Users user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}

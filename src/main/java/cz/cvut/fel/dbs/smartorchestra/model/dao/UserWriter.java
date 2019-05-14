/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserManagerException;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 *
 * @author Matěj Bartoň
 */
public class UserWriter {
    private EntityManager em;
    
    public UserWriter() {
        em = SmartOrchestra.getInstance().getEntityManager();
    }
    
    public synchronized void create(Users user){
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            Logger.getLogger(UserWriter.class.getName()).log(Level.INFO, "Created user {0}", user);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            Logger.getLogger(UserWriter.class.getName()).log(Level.SEVERE, "Cannot create user " + user, ex);
        }
    }
    
    public synchronized void write(Users user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public synchronized void removeUser(Users user) throws UserManagerException, Exception {
        try{
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            
        } catch (NoResultException ex){
            em.getTransaction().rollback();
            throw new UserManagerException("Nelze odstranit již smazaného uživatele.");
            
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
        
        
    }
}

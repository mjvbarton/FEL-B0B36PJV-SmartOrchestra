/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.exceptions.UserManagerException;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 * Class for reading information about {@link Users} entities from the database.
 * <b>WARNING: This class cannot be used in multiple threads simultaneously!</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserWriter extends DAO{
    
    /**
     * Creates new UserWriter
     */
    public UserWriter() {
        super();
    }
    
    /**
     * Creates new user entity in database for given user entity.
     * @param user - a {@link Users} entity
     */
    public void create(Users user){
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
    
    /**
     * Updates given user to the database
     * @param user -  a {@link Users entity}
     */
    public synchronized void write(Users user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    
    /**
     * Removes the given user from the database.
     * @param user - a {@link Users} entity
     * @throws UserManagerException if there is no user in database
     * @throws Exception if the process fails
     */
    public void removeUser(Users user) throws UserManagerException, Exception {
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

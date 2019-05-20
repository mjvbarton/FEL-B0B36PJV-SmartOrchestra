/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Administrators;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 * This class access {@link Adminstrators} entities in the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class AdministratorHandler extends DAO{
    
    /**
     * Checks if given user has relation to {@link Administrators} entity
     * @param user - a {@link Users} entity
     * @return {@code true} if given user has relation to {@link Administrators}, {@code false} if not
     */
    public boolean getIsAdministrator(Users user){
        try{
            Administrators admin =
                    em.createNamedQuery("Administrators.findByUid", Administrators.class)
                        .setParameter("uid", user.getUid())
                        .getSingleResult();
            
            return admin != null;
        } catch (NoResultException ex){
            
            return false;
        } catch (Exception ex){
            
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.SEVERE, "Cannot read Administrators.", ex);
            return false;
        }
    }

    /**
     * Removes {@link Administrators} entity with relation to the given user.
     * @param user - a {@link Users} entity
     * @throws Exception if the process fails
     */
    public synchronized void remove(Users user) throws Exception{
        try{
            em.getTransaction().begin();
            Administrators admin =
                        em.createNamedQuery("Administrators.findByUid", Administrators.class)
                            .setParameter("uid", user.getUid())
                            .getSingleResult();
            em.remove(admin);
            em.getTransaction().commit();
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.INFO, "User {0} removed from administrators.", user);
        } catch (NoResultException ex){
            em.getTransaction().rollback();
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.WARNING, "User " + user + " is not administrator.", ex);
            
        } catch (Exception ex){
            em.getTransaction().rollback();
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.SEVERE, "Cannot remove " + user + " from administrators.", ex);
            throw ex;
        }
    }
    
    /**
     * Creates new {@link Administrators} entity with relation to the given user.
     * @param user - a {@link Users} entity
     * @throws Exception when the process fails
     */
    public synchronized void newAdmin(Users user) throws Exception{
        try{
            em.getTransaction().begin();
            Administrators admin = new Administrators(user.getUid());
            em.persist(admin);
            em.getTransaction().commit();
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.INFO, "User {0} added to administrators.", user);            
        } catch(Exception ex){
            em.getTransaction().rollback();
            Logger.getLogger(AdministratorHandler.class.getName()).log(Level.SEVERE, "Cannot add " + user + " to administrators.", ex);
            throw ex;
        }
    }
}
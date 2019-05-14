/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Administrators;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 *
 * @author Matěj Bartoň
 */
public class AdministratorHandler extends DAO{
    
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

    public synchronized void remove(Users user) throws UserAdminException{
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
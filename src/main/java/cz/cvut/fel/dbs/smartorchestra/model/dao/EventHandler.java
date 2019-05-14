/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class EventHandler extends DAO {

    public void saveEvent(Events event) {
        try{
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.clear();
        }
    }  

    public List<Events> getNextEvents() throws NoResultException, Exception{
        Date now = new Date();
        synchronized(em){
            em.getTransaction().begin();
            List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins > :now AND e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)
                    .setParameter("now", now).getResultList();
            em.getTransaction().commit();
            return e;
        }
    }
    
    public synchronized List<Events> getNextEvents(Users user) throws NoResultException, Exception{
        Date now = new Date();
        synchronized(em){
            em.getTransaction().begin();
            List<Integer> userEvids = em.createNamedQuery("Participants.getEvidsByUid", Integer.class)
                    .setParameter("uid", user.getUid().intValue()).getResultList();
            if(userEvids.isEmpty()){
                throw new NoResultException("User " + user + " has no events");
            }
            List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins > :now AND e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                    .setParameter("now", now).setParameter("evids", userEvids).getResultList();
            em.getTransaction().commit();
            return e;
        }
    }
}

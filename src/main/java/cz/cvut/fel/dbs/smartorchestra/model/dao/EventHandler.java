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
import java.util.HashMap;
import java.util.List;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author Matěj Bartoň
 */
public class EventHandler extends DAOThreadSafe {

    public EventHandler(EntityManager em) {
        super(em);
    }

    public void saveEvent(Events event) {
        em.clear();
        
        try{
            em.getTransaction().begin();
            em.merge(event);
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
            
    }
    
    public Events getEvent(Integer evid) throws NoResultException{
        return em.find(Events.class, evid);
    }

    // Loads new list of events including selected date and the events after this date, for administrator
    public List<Events> getNextEvents(Date date) throws NoResultException, Exception{
        em.clear();
        em.getTransaction().begin();
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins >= :date AND e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)                    
                .setParameter("date", date).getResultList();
        em.getTransaction().commit();
        em.clear();
        return e;        
    }
        
    // Loads new list of events including selected date and the events after this date if user is participant
    public List<Events> getNextEvents(Date date, Users user) throws NoResultException, Exception{
        em.clear();
        em.getTransaction().begin();
        List<Integer> userEvids = em.createNamedQuery("Participants.getEvidsByUid", Integer.class)
                .setParameter("uid", user.getUid().intValue()).getResultList();
        if(userEvids.isEmpty()){
            throw new NoResultException("User " + user + " has no events");
        }
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins >= :date AND e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("date", date).setParameter("evids", userEvids).getResultList();
        em.getTransaction().commit();
        return e;
        
    } 
}

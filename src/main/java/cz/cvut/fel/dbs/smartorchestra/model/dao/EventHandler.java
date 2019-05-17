/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class EventHandler extends DAOThreadSafe {

    public EventHandler(EntityManager em) {
        super(em);
    }

    public void saveEvent(Events event) {        
        try{
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
            
    }
    
    public Events getEvent(Integer evid) throws NoResultException{
        em.clear();
        em.getTransaction().begin();
        Events e = em.find(Events.class, evid);
        em.getTransaction().commit();
        return e;
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
            em.getTransaction().rollback();
            throw new NoResultException("User " + user + " has no events");
        }
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins >= :date AND e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("date", date).setParameter("evids", userEvids).getResultList();
        em.getTransaction().commit();
        return e;
    } 
    
    // Loads new list of events including selected date and the events after this date and section of the participant
    public List<Events> getNextEvents(Date date, Sections section) throws NoResultException, Exception{
        em.clear();
        em.getTransaction().begin();
        List<Integer> userEvids = em.createNamedQuery("Participants.getEvidsBySection", Integer.class)
                .setParameter("seid", section).getResultList();
        if(userEvids.isEmpty()){
            em.getTransaction().commit();
            return new ArrayList();
        }
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins >= :date AND e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("date", date).setParameter("evids", userEvids).getResultList();
        em.getTransaction().commit();
        return e;
    }
}

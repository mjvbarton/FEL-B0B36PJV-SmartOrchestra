/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
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
 * This is a DAO for manipulating {@link Events} entities.
 * This class can be use in multiple threads.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventHandler extends DAOThreadSafe {
    
    /**
     * Creates new EventHandler with {@code EntityManager} given
     * @param em - an {@code EntityManager} instance
     */
    public EventHandler(EntityManager em) {
        super(em);
    }

    /**
     * Saves the given in the database.
     * @param event - an {@link Events} entity
     */
    public void saveEvent(Events event) {
        
        try{
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
            em.clear();
        } catch(Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
            
    }
    
    /**
     * Finds {@link Events} entity in the database based on its primary key.
     * @param evid - {@code Integer} value of {@link Events} primary key
     * @return an {@link Events} entity
     * @throws NoResultException if no entity was found
     */
    public Events getEvent(Integer evid) throws NoResultException{
        em.clear();
        em.getTransaction().begin();
        Events e = em.find(Events.class, evid);
        em.getTransaction().commit();
        return e;
    }

    /**
     * Loads new list of active events including selected date and the events after this date. Suitable main for administrator.
     * @param date - a {@code Date} object
     * @return a {@code List} of {@link Events} entities
     * @throws NoResultException if no result was found
     * @throws Exception if the process fails
     */
    public List<Events> getNextEvents(Date date) throws NoResultException, Exception{
        em.clear();
        em.getTransaction().begin();
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins >= :date AND e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)                    
                .setParameter("date", date).getResultList();
        em.getTransaction().commit();
        return e;        
    }
        
    /**
     * Loads new list of active events including given date and the events after this date if the given user is participant.
     * @param date - a {@code Date} object
     * @param user - an {@link Users} entity
     * @return a {@code List} of {@link Events} entities
     * @throws NoResultException if no result was found
     * @throws Exception if the process fails
     */
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
    
    /**
     * Loads list of events where there is relation to given section and the events date is equal or greater than given date.
     * @param date - a {@code Date} object
     * @param section - a {@link Sections} entity
     * @return a {@code List} of {@link Events} entities
     * @throws NoResultException if no result was found
     * @throws Exception if the process fails
     */
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
    
    /**
     * Loads all active events from the database.
     * @return a {@code List} of {@link Events} entities
     * @throws Exception if the process fails
     */
    public List<Events> getAllEvents() throws Exception{
        em.clear();
        List<Events> e = new ArrayList();
        try {
            em.getTransaction().begin();
            e = em.createQuery("SELECT e FROM Events e WHERE e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }        
        return e;
    }
    
    /**
     * Loads all active events from the database related to given user.
     * @param user - a {@link Users} entity
     * @return a {@code List} of {@link Events} entities
     * @throws NoResultException if no result was found
     * @throws Exception if the process fails 
     */
    public List<Events> getAllEvents(Users user) throws NoResultException, Exception{
        em.clear();
        em.getTransaction().begin();
        List<Integer> userEvids = em.createNamedQuery("Participants.getEvidsByUid", Integer.class)
                .setParameter("uid", user.getUid().intValue()).getResultList();
        if(userEvids.isEmpty()){
            em.getTransaction().rollback();
            throw new NoResultException("User " + user + " has no events");
        }
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("evids", userEvids).getResultList();
        em.getTransaction().commit();
        return e;
    }
    
    /**
     * Loads events from database where the date of the event is lower or equal to the date given.
     * @param date - a {@code Date} object
     * @return a {@code List} of {@link Events} entities
     * @throws Exception if the process fails 
     */
    public List<Events> getPastEvents(Date date) throws Exception{
        em.clear();
        em.getTransaction().begin();
        List<Events> e = em.createQuery("SELECT e FROM Events e WHERE e.begins < :date AND e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)                    
                .setParameter("date", date).getResultList();
        em.getTransaction().commit();
        return e;
    }
    
    /**
     * Loads events from database related to given user where the date of the event is lower or equal to the date given.
     * @param date - a {@code Date} object
     * @return a {@code List} of {@link Events} entities
     * @throws NoResultException if no result was found
     * @throws Exception if the process fails 
     */
    public List<Events> getPastEvents(Date date, Users user) throws NoResultException, Exception{
        em.clear();
        List<Events> e = null; 
        em.getTransaction().begin();
        List<Integer> userEvids = em.createNamedQuery("Participants.getEvidsByUid", Integer.class)
                .setParameter("uid", user.getUid().intValue()).getResultList();
        if (userEvids.isEmpty()) {
            em.getTransaction().rollback();
            throw new NoResultException("User " + user + " has no events");
        }
        e = em.createQuery("SELECT e FROM Events e WHERE e.begins < :date AND e.active = TRUE AND e.evid IN :evids ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("date", date).setParameter("evids", userEvids).getResultList();
        em.getTransaction().commit();
        return e;
    } 
    
    /**
     * Deletes given event and all dependencies from the database.
     * @param event - an {@link Events} entity
     */
    public void deleteEvent(Events event){
        em.clear();
        try {
            em.getTransaction().begin();
            Events deleteEvent = em.find(Events.class, event.getEvid());
            em.remove(deleteEvent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
}

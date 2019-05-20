/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantsPK;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * This is class to access {@link Participants} entities in the database.
 * This class can be use in multiple threads.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class ParticipantManager extends DAOThreadSafe{
    
    /**
     * Creates new ParticipantManager with {@code EntityManager} given.
     * @param em - an {@code EntityManager} instance
     */
    public ParticipantManager(EntityManager em) {
        super(em);
    }
    
    /**
     * Creates relation between the given user, the given section and the given event.
     * @param section - a {@link Sections} entity
     * @param event - an {@link Events} entity
     * @param user - a {@link Users} entity
     * @throws Exception when the process fails.
     */
    public void inviteSingleUser(Sections section, Events event, Users user) throws Exception{
        Participants part = new Participants();
        part.setParticipantsPK(new ParticipantsPK(user.getUid().intValue(), event.getEvid()));
        part.setSeid(section);
        try{
            em.getTransaction().begin();
            em.persist(part);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().commit();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.SEVERE, "Cannot invite User: " + user + " from section: " + section, ex);                    
            throw ex;
        }
    }
    
    /**
     * Creates/deletes new relation between {@link Players} entities in {@link Sections} entity given and the given event
     * @param section - a {@link Sections} entity
     * @param event - an {@link Events} entity
     * @param addInvitation - a {@code boolean} flag, if {@code true} the relation is created/updated, otherwise the relation is removed
     */
    public void processNewInvitation(Sections section, Events event, boolean addInvitation){
        try {
            em.getTransaction().begin();
            
            // Getting players in section
            List<Player> players = em.createNamedQuery("Player.findBySeid", Player.class)
                    .setParameter("seid", section).getResultList();
            for (Player player : players) {
                if(addInvitation){
                    Participants participant;
                    ParticipantsPK parPK = new ParticipantsPK(
                            player.getUid().intValue(), event.getEvid());
                    
                    // Trying to get participant entity for the player values
                    try{
                        participant = em.find(Participants.class, parPK);
                        if(participant == null){
                            throw new NoResultException();
                        }
                        participant.setEvents(event);
                        participant.setSeid(section); 
                        em.persist(participant);
                        Logger.getLogger(ParticipantManager.class.getName()).log(
                                Level.INFO, "Player {0} invited to event {1}", new Object[]{player, event});   
                    
                    // Creating new participant entity with the player values
                    } catch (NoResultException ex){
                        participant = new Participants();
                        participant.setParticipantsPK(parPK);
                        participant.setEvents(event);
                        participant.setSeid(section); 
                        em.persist(participant);
                        Logger.getLogger(ParticipantManager.class.getName()).log(
                                Level.INFO, "Player {0} invited to event {1}", new Object[]{player, event});   
                    } 
                
                // Removing participant entity
                } else {
                    Participants participant = em.find(Participants.class, new ParticipantsPK(
                            player.getUid().intValue(), event.getEvid()));
                    if(participant != null){
                        em.remove(participant);
                    }                    
                    Logger.getLogger(ParticipantManager.class.getName()).log(Level.INFO, "Player {0} - invitation to event {1} deleted", new Object[]{player, event});
                }
            }
            em.getTransaction().commit();
        
        // Sanitization of the case when section does not contain any players
        } catch (NoResultException ex) {
            em.getTransaction().rollback();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.WARNING, "Section {0} does not conatin any player.", section);
        
        // Other fails during the process
        } catch (Exception ex){
            em.getTransaction().rollback();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.SEVERE, "Cannot send invitations for Section " + section, ex);                    
            throw ex;
        }
    }
    
    /**
     * Gets participating section for given event.
     * @param event - an {@link Events} entity
     * @return a {@code List} of {@link Sections} enitites
     */
    public List<Sections> getEventSections(Events event) {
        return em.createQuery("SELECT DISTINCT p.seid FROM Participants p WHERE p.events = :event ", Sections.class)
                .setParameter("event", event).getResultList();  
    }
    
    /**
     * Gets participants for given event.
     * @param event - an {@link Events} entity
     * @return a {@code List} of {@link Participants} entities, or empty {@code List} if nothing found
     */
    public List<Participants> getParticipants(Events event){
        em.clear();
        try{
            return em.createNamedQuery("Participants.findByEvid", Participants.class)
                .setParameter("evid", event.getEvid()).getResultList();
        } catch (NoResultException ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.INFO, "No participants found for event {0}", event);
            return new ArrayList();
        } catch (Exception ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.WARNING, "Cannot read Participants for event: " + event , ex);
            return new ArrayList();
        }        
    }
    
    /**
     * Gets participants where the given user participates the given events.
     * @param user - a {@link Users} entity
     * @param events - a {@code List} of {@link Events} entities
     * @return a {@code List} of {@link Participants} entities, or empty {@code List} if nothing found
     */
    public List<Participants> getParticipants(Users user, List<Events> events){
        try {
            return em.createQuery("SELECT p FROM Participants p WHERE p.events IN :events "
                    + "AND p.users = :user ORDER BY p.events.begins, p.events.eventname", Participants.class)
                    .setParameter("events", events).setParameter("user", user).getResultList();
        } catch (NoResultException ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.INFO, "User {0} does not participate any event.", user);
            return new ArrayList();
        } catch (Exception ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.WARNING, "Cannot read Participants for user: " + user , ex);
            return new ArrayList();
        }
    }
    
    /**
     * Gets participants where user participates an event whose dater is greater or equal to the given date.
     * @param user - a {@link Users} entity
     * @param afterDate - a {@code Date} object
     * @return a {@code List} of {@link Participants} entities, or empty {@code List} if nothing found
     */
    public List<Participants> getParticipants(Users user, Date afterDate){
        try {
            return em.createQuery("SELECT p FROM Participants p WHERE p.events.begins >= :date AND "
                    + "p.users = :user ORDER BY p.events.begins, p.events.eventname", Participants.class)
                    .setParameter("date", afterDate).setParameter("user", user).getResultList();
        } catch (NoResultException ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.INFO, "User {0} does not participate any event.", user);
            return new ArrayList();
        } catch (Exception ex){
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.SEVERE, "Cannot read Participants for user: #" + user , ex);
            return new ArrayList();
        }
    }
    
    /**
     * Delete every participant from list of participants given from the database.
     * @param participants - a {@code List} of {@link Participants} entities
     */
    public void deleteParticipant(List<Participants> participants){
        try {
            em.getTransaction().begin();
            for(Participants part : participants){
                em.remove(part);
            }
            em.getTransaction().commit();            
        } catch (Exception ex){
            em.getTransaction().rollback();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.SEVERE, "Cannot remove participants. Nothing removed.", ex);
            throw ex;
        }
    }
    
    /**
     * Get single participant based on its primary key - {@link Users} entity and {@link Events} entity
     * @param user - a {@link Users} entity
     * @param event - a {@link Events} entity
     * @return a {@link Participants} entity from the database
     */
    public Participants getParticipant(Users user, Events event) {
        em.clear();
        return em.find(Participants.class, new ParticipantsPK(user, event));
    }

    /**
     * Update given participant to the database.
     * @param part - a {@link Participants} entity
     */
    public void updateParticipant(Participants part) {
        try{
            em.getTransaction().begin();
            em.persist(part);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
    }
}

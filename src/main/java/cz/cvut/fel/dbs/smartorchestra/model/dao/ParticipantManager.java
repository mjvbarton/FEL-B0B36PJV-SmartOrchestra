/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantsPK;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Matěj Bartoň
 */
public class ParticipantManager extends DAOThreadSafe{

    public ParticipantManager(EntityManager em) {
        super(em);
    }
    public synchronized void processNewInvitation(Sections section, Events event, boolean addInvitation){
        try {
            em.getTransaction().begin();
            List<Player> players = em.createNamedQuery("Player.findBySeid", Player.class)
                    .setParameter("seid", section).getResultList();
            for (Player player : players) {
                if(addInvitation){
                    Participants participant;
                    ParticipantsPK parPK = new ParticipantsPK(
                            player.getUid().intValue(), event.getEvid());
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
                    } catch (NoResultException ex){
                        participant = new Participants();
                        participant.setParticipantsPK(parPK);
                        participant.setEvents(event);
                        participant.setSeid(section); 
                        em.persist(participant);
                        Logger.getLogger(ParticipantManager.class.getName()).log(
                                Level.INFO, "Player {0} invited to event {1}", new Object[]{player, event});   
                    } 
                } else {
                    Participants participant = em.find(Participants.class, new ParticipantsPK(
                            player.getUid().intValue(), event.getEvid()));
                    em.remove(participant);
                    
                    Logger.getLogger(ParticipantManager.class.getName()).log(Level.INFO, "Player {0} - invitation to event {1} deleted", new Object[]{player, event});
                }
            }
            em.getTransaction().commit();
            
        } catch (NoResultException ex) {
            em.getTransaction().rollback();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.WARNING, "Section {0} does not conatin any player.", section);
        } catch (Exception ex){
            em.getTransaction().rollback();
            Logger.getLogger(ParticipantManager.class.getName()).log(Level.SEVERE, "Cannot send invitations for Section " + section, ex);                    
            throw ex;
        }
    }

    public synchronized List<Sections> getEventSections(Events event) {
        return em.createQuery("SELECT p.seid FROM Participants p ", Sections.class)
                .getResultList();   
    }
    
    public List<Participants> getParticipantByUserAndEventList(Users user, List<Events> events){
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
        
}

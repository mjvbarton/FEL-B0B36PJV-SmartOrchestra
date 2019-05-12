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
import java.util.List;
import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matěj Bartoň
 */
public class ParticipantManager extends DAO{
    public void processNewInvitation(Sections section, Events event, boolean addInvitation){
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

    public List<Sections> getEventSections(Events event) {
        return em.createQuery("SELECT p.seid FROM Participants p ", Sections.class)
                .getResultList();   
    }
}

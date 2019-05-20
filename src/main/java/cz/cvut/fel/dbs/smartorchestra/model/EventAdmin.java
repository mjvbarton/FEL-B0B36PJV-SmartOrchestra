/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.ThreadEntityManager;
import cz.cvut.fel.dbs.smartorchestra.exceptions.EventAdminException;
import cz.cvut.fel.dbs.smartorchestra.model.dao.EventHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.ParticipantManager;
import cz.cvut.fel.dbs.smartorchestra.model.dao.SectionReader;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JCheckBox;

/**
 * This class represents a model for manipulating with {@link Events} and {@link Participants}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventAdmin {
    private ThreadEntityManager tem;
    
    /**
     * Creates new EventAdmin
     * @param tem - pointer to the {@code EntityManager}
     */
    public EventAdmin(ThreadEntityManager tem){
        this.tem = tem;
    }
    
    /**
     * Loads sections into {@link SectionType} enum
     * @throws Exception when loading fails
     */
    public void loadSections() throws Exception{
        SectionReader sr = new SectionReader();
        
        SectionType.STRINGS.setSections(sr.getSectionsBySectionType(SectionType.STRINGS));
        SectionType.WINDS.setSections(sr.getSectionsBySectionType(SectionType.WINDS));
        SectionType.OTHER.setSections(sr.getSectionsBySectionType(SectionType.OTHER));
    }

    /**
     * Saves the given event to the database.
     * @param event - an {@link Events} entity
     */
    public void saveEvent(Events event) {
        EventHandler eh = new EventHandler(tem.getEntityManager());
        eh.saveEvent(event);
    }
    
    /**
     * Loads events which undergo the filter condition given for date value of now.
     * @param filter - a enum {@link EventDateFilter} value
     * @return {@code List} of {@link Events} entities
     * @throws Exception when the operation fails
     */
    public List<Events> loadEvents(EventDateFilter filter) throws Exception{
        return loadEvents(filter, new Date());
    }

    /**
     * Loads events which undergo the filter condition compared to the date given.
     * @param filter - a enum {@link EventDateFilter} value
     * @param date - a {@code Date} object
     * @return {@code List} of {@link Events} entities
     * @throws Exception when the operation fails
     */
    public List<Events> loadEvents(EventDateFilter filter, Date date) throws Exception{
        EventHandler eh = new EventHandler(tem.getEntityManager());
        try{
            if(SmartOrchestra.getInstance().isAdministrationActive()){
                switch(filter){
                    case NEXT:
                        return eh.getNextEvents(date);
                    case PAST:
                        return eh.getPastEvents(date);
                    case ALL:
                        return eh.getAllEvents();
                    default:
                        throw new InputMismatchException("Invalid enum value");
                }
            } else {
                switch(filter){
                    case NEXT:
                        return eh.getNextEvents(date, SmartOrchestra.getInstance().getActiveUser());
                    case PAST:
                        return eh.getPastEvents(date, SmartOrchestra.getInstance().getActiveUser());
                    case ALL:
                        return eh.getAllEvents(SmartOrchestra.getInstance().getActiveUser());
                    default:
                        throw new InputMismatchException("Invalid enum value");
                }                
            }
        } catch(NoResultException ex){
            return new ArrayList();
        }
    }
    
    /**
     * Sends the invitation to players based on the checked checkbox stored in the hash map.
     * @param event - a {@link Events} entity
     * @param eventSections - {@code HashMap} where {@link Sections} entity is the key an {@code JCheckBox} object the value
     * @param type - an enum value of {@link SectionType}
     */
    public void sendInvitations(Events event, HashMap<Sections, JCheckBox> eventSections, SectionType type){
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        eventSections.forEach((section, checkBox) -> {
            pm.processNewInvitation(section, event, checkBox.isSelected());
        });
        
    }   
    
    /**
     * Reads invited section for {@link Events} entity given.
     * @param event - an {@link Events} entity
     * @return - {@code List} of {@link Sections} entities
     * @throws EventAdminException when there are no sections for given event
     */
    public List<Sections> getInvitedSectionsForEvent(Events event) throws EventAdminException {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        List<Sections> sections = pm.getEventSections(event);
        if(sections.isEmpty()){
            throw new EventAdminException("Sekci pro událost " + event + " nebyly nalezeny.");
        }
        return sections;
    }
    
    /**
     * Invites the {@link Player} entity given to events related to his/hers section.
     * @param player - a {@link Player} entity
     * @throws Exception when the process fails
     */
    public void invitePlayerToEvents(Player player) throws Exception{
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        UserReader ur = new UserReader();
        EventHandler eh = new EventHandler(tem.getEntityManager());
        Users user = ur.getUserFromUid(player.getUid());
        List<Participants> delParts = pm.getParticipants(user, new Date());
        if(!delParts.isEmpty()){
           pm.deleteParticipant(delParts);
        }
        List<Events> events = eh.getNextEvents(new Date(), player.getSeid());
        for(Events event : events){
            pm.inviteSingleUser(player.getSeid(), event, user);
        }
    }
    
    /**
     * Creates a participatioin hash map of the {@link ParticipantState} for given user and events.
     * @param user - an {@link Users} entity
     * @param events - a {@code List} of {@link Events} entities
     * @return a {@code HashMap} where {@link Events} entity is the key and {@link ParticipationState} is the value
     */
    public HashMap<Events, ParticipantState> getParticipationMap(Users user, List<Events> events) {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        // Getting participants for given event
        List<Participants> participants;
        if(events.isEmpty()){
            participants = new ArrayList();
        } else {
            participants = pm.getParticipants(user, events);
        }
        
        // Building the HashMap
        HashMap<Events, ParticipantState> map =  new HashMap();
        boolean partAdded;
        for(Events event : events){
            partAdded = false;
            for(Participants part : participants){
                if(event.getEvid() == part.getParticipantsPK().getEvid()){
                    if(part.getActive() == null){
                        map.put(event, ParticipantState.NOT_FILLED);
                    } else if(!part.getActive()){
                        map.put(event, ParticipantState.NOT_COMING);
                    } else {
                        map.put(event, ParticipantState.COMING);
                    }
                    partAdded = true;
                }
            }
            if(!partAdded){
                map.put(event, ParticipantState.NOT_INVITED);
            }          
        }
        return map;
    }

    /**
     * Updates participation of active user for given event, state and message. 
     * @param event - a {@link Events} entity
     * @param state - a {@link ParticipantState} enum value
     * @param message -  a {@code String} message connected to the participation (mainly the reason why the player is not coming)
     */
    public void updateParticipation(Events event, ParticipantState state, String message) {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        Participants part = pm.getParticipant(SmartOrchestra.getInstance().getActiveUser(), event);
        part.setActive(state.toBoolean());
        part.setMessage(message);
        pm.updateParticipant(part);
    }
    
    /**
     * Gets participants for given event.
     * @param event - an {@link Events} entity
     * @return a {@code List} of {@link Participatns} entities
     */
    public List<Participants> getParticipants(Events event){
        return new ParticipantManager(tem.getEntityManager()).getParticipants(event);        
    }
    
    /**
     * Deletes the given event from the database.
     * @param event - an {@link Events} entity
     */
    public void deleteEvent(Events event){
        new EventHandler(tem.getEntityManager()).deleteEvent(event);
    }
}

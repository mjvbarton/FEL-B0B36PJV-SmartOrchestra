/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.EventUpdater;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.swing.JCheckBox;

/**
 *
 * @author Matěj Bartoň
 */
public class EventAdmin {
    private ThreadEntityManager tem;
        
    public EventAdmin(ThreadEntityManager tem){
        this.tem = tem;
    }
    
    public void loadSections() throws Exception{
        SectionReader sr = new SectionReader();
        
        SectionType.STRINGS.setSections(sr.getSectionsBySectionType(SectionType.STRINGS));
        SectionType.WINDS.setSections(sr.getSectionsBySectionType(SectionType.WINDS));
        SectionType.OTHER.setSections(sr.getSectionsBySectionType(SectionType.OTHER));
    }

    public void saveEvent(Events event) {
        EventHandler eh = new EventHandler(tem.getEntityManager());
        eh.saveEvent(event);
    }
    
    public List<Events> loadEvents() throws Exception{
        return loadEvents(new Date());
    }

    public List<Events> loadEvents(Date date) throws Exception{
        EventHandler eh = new EventHandler(tem.getEntityManager());
        try{
            if(SmartOrchestra.getInstance().isAdministrationActive()){
                return eh.getNextEvents(date);
            } else {
                return eh.getNextEvents(date, SmartOrchestra.getInstance().getActiveUser());
            }
        } catch(NoResultException ex){
            return new ArrayList();
        }
    }
        
    public void sendInvitations(Events event, HashMap<Sections, JCheckBox> eventSections, SectionType type){
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        eventSections.forEach((section, checkBox) -> {
            pm.processNewInvitation(section, event, checkBox.isSelected());
        });
        
    }   

    public List<Sections> getInvitedSectionsForEvent(Events event) throws EventAdminException {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        List<Sections> sections = pm.getEventSections(event);
        if(sections.isEmpty()){
            throw new EventAdminException("Sekci pro událost " + event + " nebyly nalezeny.");
        }
        return sections;
    }
    
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

    public HashMap<Events, ParticipantState> getParticipationMap(Users user, List<Events> events) {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        List<Participants> participants;
        if(events.isEmpty()){
            participants = new ArrayList();
        } else {
            participants = pm.getParticipants(user, events);
        }
        
        
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
}

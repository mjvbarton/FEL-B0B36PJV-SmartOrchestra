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
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
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
        
    public void sendInvitations(Events event, List<JCheckBox> checkedSections, SectionType type){
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        for(int i = 0; i < type.getSections().size(); i++){
            pm.processNewInvitation(type.getSections().get(i), event, checkedSections.get(i).isSelected());            
        }   
    }   

    public void checkSections(Events event, List<JCheckBox> sections, SectionType type) {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        List<Sections> eventSections = pm.getEventSections(event);
        for(int i = 0; i < type.getSections().size(); i++){
            sections.get(i).setSelected(
                    eventSections.contains(type.getSections().get(i))
            );
        }        
    }

    public HashMap<Events, ParticipantState> getParticipationMap(Users user, List<Events> events) {
        ParticipantManager pm = new ParticipantManager(tem.getEntityManager());
        List<Participants> participants = pm.getParticipantByUserAndEventList(user, events);
        
        HashMap<Events, ParticipantState> map =  new HashMap();
        for(int i = 0; i < events.size(); i++){
            if(i < participants.size() 
                    && events.get(i).getEvid() == participants.get(i).getParticipantsPK().getEvid()){
                Participants part = participants.get(i);
                if(part.getActive() == null){
                    map.put(events.get(i), ParticipantState.NOT_FILLED);
                } else if (part.getActive()){
                    map.put(events.get(i), ParticipantState.COMING);
                } else {
                    map.put(events.get(i), ParticipantState.NOT_COMING);
                }
            } else {
                map.put(events.get(i), ParticipantState.NOT_INVITED);
            }
        }
        return map;
    }
}

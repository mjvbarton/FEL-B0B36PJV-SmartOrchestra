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
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import java.util.ArrayList;
import java.util.Date;
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
        ParticipantManager pm = new ParticipantManager();
        for(int i = 0; i < type.getSections().size(); i++){
            pm.processNewInvitation(type.getSections().get(i), event, checkedSections.get(i).isSelected());            
        }   
    }   

    public void checkSections(Events event, List<JCheckBox> sections, SectionType type) {
        ParticipantManager pm = new ParticipantManager();
        List<Sections> eventSections = pm.getEventSections(event);
        for(int i = 0; i < type.getSections().size(); i++){
            sections.get(i).setSelected(
                    eventSections.contains(type.getSections().get(i))
            );
        }        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.exceptions.EventAdminException;
import cz.cvut.fel.dbs.smartorchestra.model.dao.EventHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.SectionReader;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class EventAdmin {
    
    public void loadSections() throws Exception{
        SectionReader sr = new SectionReader();
        
        SectionType.STRINGS.setSections(sr.getSectionsBySectionType(SectionType.STRINGS));
        SectionType.WINDS.setSections(sr.getSectionsBySectionType(SectionType.WINDS));
        SectionType.OTHER.setSections(sr.getSectionsBySectionType(SectionType.OTHER));
    }

    public void saveEvent(Events event) {
        EventHandler eh = new EventHandler();
        eh.saveEvent(event);
    }

    public List<Events> loadEvents() throws NoResultException, Exception{
        EventHandler eh = new EventHandler();
        return eh.getActiveEvents();
    }
}

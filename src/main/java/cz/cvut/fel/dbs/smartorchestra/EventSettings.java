/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import cz.cvut.fel.dbs.smartorchestra.gui.EventDetails;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj Bartoň
 */
public class EventSettings implements UIController<EventDetails>{

    private EventDetails controled;
    private Events event;
    
    public EventSettings(EventDetails controled) {
        setControlled(controled);
    }

    @Override
    public void setControlled(EventDetails controled) {
        this.controled = controled;
    }
    
    public void loadEvent(int evid){
        
    }
    
    public void loadEvent(Events event){
        this.event = event;
        controled.getFieldName().setText(event.getEventname());
        DateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        controled.getFieldBeginsDate().setText(f.format(event.getBegins()));
        controled.getFieldEndsDate().setText(f.format(event.getEnds()));
        
        controled.getFieldAddrInstitution().setText(event.getAddrinstitution());
        controled.getFieldAddrStreet().setText(event.getAddrstreet());
        controled.getFieldAddrHouseNumber().setText(event.getAddrhousenumber());
        controled.getFieldAddrTown().setText(event.getAddrtown());
        controled.getFieldAddrZipCode().setText(event.getAddrzipcode().toString());
        
        EventAdmin ea = new EventAdmin();
        try {
            ea.loadSections();
            controled.getGroupStrings().loadSections(SectionType.STRINGS.getSections());
            controled.getGroupWinds().loadSections(SectionType.WINDS.getSections());
            controled.getGroupOther().loadSections(SectionType.OTHER.getSections());
            Logger.getLogger(EventSettings.class.getName()).log(Level.INFO, "Loaded sections to SectionType enum.");
            ea.checkSections(event, controled.getGroupStrings().getSections(), SectionType.STRINGS);
            ea.checkSections(event, controled.getGroupWinds().getSections(), SectionType.WINDS);
            ea.checkSections(event, controled.getGroupOther().getSections(), SectionType.OTHER);
            
        } catch (Exception ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.SEVERE, "Cannot read sections.", ex);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(),
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
           
    public void saveEvent(){
        saveEvent(event);
    }
    
    public void saveEvent(Events event){
        controled.clearInfos();
        boolean noFail = true;
        try {
            event.setEventname(controled.getFieldName().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.name failed: {0}", ex.getMessage());
            controled.getInfoName().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setBegins(controled.getFieldBeginsDate().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.begins failed: {0}", ex.getMessage());
            controled.getInfoBeginsDate().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setEnds(controled.getFieldEndsDate().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.ends failed: {0}", ex.getMessage());
            controled.getInfoEndsDate().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setAddrinstitution(controled.getFieldAddrInstitution().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.addrInstitution failed: {0}", ex.getMessage());
            controled.getInfoAddrInstitution().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setAddrstreet(controled.getFieldAddrStreet().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.addrStreet failed: {0}", ex.getMessage());
            controled.getInfoAddrStreet().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setAddrtown(controled.getFieldAddrTown().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.addrTown failed: {0}", ex.getMessage());
            controled.getInfoAddrTown().setText(ex.getMessage());
            noFail = false;
        }
        
        try {
            event.setAddrZipCode(controled.getFieldAddrZipCode().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.addrZipCode failed: {0}", ex.getMessage());
            controled.getInfoAddrZipCode().setText(ex.getMessage());
            noFail = false;
        }
        
        if(!noFail){
            JOptionPane.showMessageDialog(controled, "Ukládání události se nezdařilo.", controled.getTitle(),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        EventAdmin ea = new EventAdmin();
        try{
            ea.saveEvent(event);
            Logger.getLogger(EventSettings.class.getName()).log(Level.INFO, "Event {0} was created.", event);
            ea.sendInvitations(event, controled.getGroupStrings().getSections(), SectionType.STRINGS);
            //ea.sendInvitations(event, controled.getGroupWinds().getSections(), SectionType.WINDS);
            //ea.sendInvitations(event, controled.getGroupOther().getSections(), SectionType.OTHER);
            JOptionPane.showMessageDialog(controled, "Událost byla uložena.", 
                    controled.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception ex){
            Logger.getLogger(EventSettings.class.getName()).log(Level.SEVERE, "Unable to create event: " + event, ex);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(), 
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
        } finally {
            controled.dispose();
        }
    }
}

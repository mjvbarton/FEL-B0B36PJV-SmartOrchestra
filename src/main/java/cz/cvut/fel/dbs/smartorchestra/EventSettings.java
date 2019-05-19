/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 *
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import cz.cvut.fel.dbs.smartorchestra.gui.EventDetails;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.dao.EventHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.ParticipantManager;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This class implements {@link UIController} for {@link EventDetails}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventSettings implements UIController<EventDetails>{

    private EventDetails controled;
    private Events event;
    
    /**
     * Initalize <code>EventSettings</code> with {@link EventDetails} implementing {@link UIControlled}
     * @param controled - instance of {@link EventDetails} implementing {@link UIControlled}
     */
    public EventSettings(EventDetails controled) {
        setControlled(controled);
    }
    
    /**
     * See {@link UIController}
     * @param controled 
     */
    @Override
    public void setControlled(EventDetails controled) {
        this.controled = controled;
    }
    
    /**
     * Runs dialog for creating new events
     */
    public void loadEvent(){
        Events e = new Events();
        loadEvent(new Events());
        controled.getBtnDeleteEvent().setVisible(false);
    }
    
    /**
     * Loads information as {@link Events} from database. Entity {@link Events} is searched for given {@code int evid}
     * @param evid - {@code int} id value of event in the database
     */
    public void loadEvent(int evid){
        EventHandler eh = new EventHandler(SmartOrchestra.getInstance().getEntityManager());
        loadEvent(eh.getEvent(evid));
    }
    
    /**
     * Loads information for given {@link Events} entity. Initializes property {@code EventSettings.event}
     * @param event - {@link Events} entity
     */
    public void loadEvent(Events event){
        this.event = event;
        controled.getFieldName().setText(event.getEventname());
        DateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            controled.getFieldBeginsDate().setText(f.format(event.getBegins()));
        } catch (NullPointerException ex){
            controled.getFieldBeginsDate().setText("");
        }
        try {
            controled.getFieldEndsDate().setText(f.format(event.getEnds()));
        } catch (NullPointerException ex) {
            controled.getFieldEndsDate().setText("");
        }
        
        controled.getFieldAddrInstitution().setText(event.getAddrinstitution());
        controled.getFieldAddrStreet().setText(event.getAddrstreet());
        controled.getFieldAddrHouseNumber().setText(event.getAddrhousenumber());
        controled.getFieldAddrTown().setText(event.getAddrtown());
        try{
            controled.getFieldAddrZipCode().setText(event.getAddrzipcode().toString());
        } catch (NullPointerException ex){
            controled.getFieldAddrZipCode().setText("");
        }
        
        // Loading sections and participanting sections
        EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
        try {
            ea.loadSections();
            controled.getGroupStrings().loadSections(SectionType.STRINGS.getSections());
            controled.getGroupWinds().loadSections(SectionType.WINDS.getSections());
            controled.getGroupOther().loadSections(SectionType.OTHER.getSections());
            Logger.getLogger(EventSettings.class.getName()).log(Level.INFO, "Loaded sections to SectionType enum.");
            if(event.getEvid() != null){            
                ParticipantManager pm = new ParticipantManager(SmartOrchestra.getInstance().getEntityManager());            
                List<Sections> activeSections = pm.getEventSections(event);
                controled.getGroupStrings().checkSections(activeSections);
                controled.getGroupWinds().checkSections(activeSections);
                controled.getGroupOther().checkSections(activeSections);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.SEVERE, "Cannot read sections.", ex);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(),
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Saves the initalized event to the database.
     */
    public void saveEvent(){
        saveEvent(event);
    }
    
    // Saves the given event to the database
    private void saveEvent(Events event){
        
        // Validation
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
        
        if(event.getBegins().after(event.getEnds())){
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Event endsDate must be greater than beginsDate");
            controled.getInfoEndsDate().setText("Neplatné datum");
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
            event.setAddrhousenumber(controled.getFieldAddrHouseNumber().getText());
        } catch (WrongInputException ex) {
            Logger.getLogger(EventSettings.class.getName()).log(Level.FINE, "Validation of event.addrHouseNumber failed: {0}", ex.getMessage());
            controled.getInfoAddrHouseNumber().setText(ex.getMessage());
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
        
        // Saving process
        EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
        try{
            ea.saveEvent(event);
            Logger.getLogger(EventSettings.class.getName()).log(Level.INFO, "Event {0} was created.", event);
            
            // Inviting checked sections
            ea.sendInvitations(event, controled.getGroupStrings().getSections(), SectionType.STRINGS);
            ea.sendInvitations(event, controled.getGroupWinds().getSections(), SectionType.WINDS);
            ea.sendInvitations(event, controled.getGroupOther().getSections(), SectionType.OTHER);
            JOptionPane.showMessageDialog(controled, "Událost byla uložena.", 
                    controled.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            
        // Sanitizing exceptions occured during the saving process
        } catch(Exception ex){
            Logger.getLogger(EventSettings.class.getName()).log(Level.SEVERE, "Unable to create event: " + event, ex);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(), 
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
        } finally {
            controled.dispose();
        }
    }
    
    /**
     * Removes the initalized event from the database. 
     * Before the removal a confirm dialog is shown to prevent unwanted removal.
     */
    public void deleteEvent(){
            int confirmDeletion = JOptionPane.showConfirmDialog(controled
                    , "Opravdu chcete trvale smazat událost?"
                            + "\nToto už nelze vrátit.", "Potvrdit smazání", JOptionPane.YES_NO_OPTION);
            if(confirmDeletion == JOptionPane.YES_OPTION){
                EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
                try{
                    ea.deleteEvent(event);
                    JOptionPane.showMessageDialog(controled, "Událost byla odstraněna.", "Smazat událost", JOptionPane.INFORMATION_MESSAGE);
                } catch(Exception ex){
                    Logger.getLogger(EventSettings.class.getName()).log(Level.SEVERE, "Unable to remove event: " + event, ex);
                    JOptionPane.showMessageDialog(controled, "Smazání události se nezdařilo. \nPro více informací zkontrolujte log.",
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
                } finally {
                    controled.dispose();
                }
            }
    }
}

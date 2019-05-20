/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.EventDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.EventInfo;
import cz.cvut.fel.dbs.smartorchestra.gui.ParticipantView;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.helpers.EventUpdaterResume;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import static cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState.*;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class represents the controller for single {@link EventInfo} from {@link cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class Participation implements UIController<EventInfo>{
    private EventInfo controled;
    private Events event;
    
    /**
     * Initalizes the controller with {@link Events} event.
     * @param event - an {@link Events} event from the database
     */
    public Participation(Events event) {
        this.event = event;
    }
    
    /**
     * Shows up a dialog for displaying/editing event information.
     * Editing of event information is enabled when {@link SmartOrchestra#isAdministrationActive()} is {@code true} 
     */
    public void showDetails() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventDetails dialog = new EventDetails(SmartOrchestra.getInstance().getMainWin());
                dialog.addWindowListener(new EventUpdaterResume());
                dialog.enableAdminAccess(SmartOrchestra.getInstance().isAdministrationActive());
                EventSettings es = new EventSettings(dialog);
                dialog.setUIController(es);
                es.loadEvent(event.getEvid().intValue());                
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {

                }
                //SmartOrchestra.getInstance().getMainWin().getUIController().loadEvents();
            }
        });
    }
    
    /**
     * Shows up a dialog for displaying all participants for the {@link Events} event.
     */
    public void showParticipants() {
        EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                ParticipantView dialog = new ParticipantView(SmartOrchestra.getInstance().getMainWin(), true);
                dialog.loadParticipants(ea.getParticipants(event));
                dialog.loadEvent(event);
                dialog.addWindowListener(new EventUpdaterResume());
                dialog.setVisible(true);
                dialog.doModal();
            }
        });
    }
    
    /**
     * See {@link UIController} fore more information.
     */
    @Override
    public void setControlled(EventInfo controled) {
        this.controled = controled;
    }
    
    /**
     * Sets the {@link ParticipantState} of the current event and the proper user.
     * At the end the method starts new {@link ParticipantUpdater} thread.
     * @param participantState - {@link ParticipantState} of the event
     */
    public void setParticipationState(ParticipantState participantState) {
        SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(true);
        SimpleDateFormat dfBegins = new SimpleDateFormat("dd.MM.YYYY HH:mm");
        String eventIdentifier = String.format("Událost: %s - %s", event.getEventname(), dfBegins.format(event.getBegins()));
        switch (participantState){
            // Forcing user to fill the participation state information
            case NOT_FILLED:
                JOptionPane.showMessageDialog(controled, "Prosím vyplňte vaší docházku!", 
                        eventIdentifier
                        , JOptionPane.INFORMATION_MESSAGE);
                break;
            // Handling if the user checks NOT_COMING
            case NOT_COMING:
                try{
                    String msg = JOptionPane.showInputDialog(controled, "Prosím uveďte důvod nepřítomnosti:"
                            , eventIdentifier
                            , JOptionPane.INFORMATION_MESSAGE);
                    if(msg == null || msg.trim().isEmpty()) { throw new HeadlessException(); }
                    controled.getFieldParticipation().setSelectedIndex(NOT_COMING.intVal());
                    ParticipantUpdater partU = new ParticipantUpdater(event, participantState, msg, controled);
                    partU.start();
                    
                } catch (HeadlessException ex){
                    JOptionPane.showMessageDialog(controled, "Nevyplnili jste důvod absence.", 
                        eventIdentifier
                        , JOptionPane.WARNING_MESSAGE);
                    controled.getFieldParticipation().setSelectedIndex(NOT_FILLED.intVal());
                }
                break;
            // Handling if the user checks COMING
            case COMING:
                ParticipantUpdater partU = new ParticipantUpdater(event, participantState, controled);
                partU.start();
                break;
            // Preventing unwanted toggle
            case NOT_INVITED:
                throw new InputMismatchException("Invalid input value for this function: " + participantState);
            
        }
        SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(false);
                
    }        
}

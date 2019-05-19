/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.EventDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.EventInfo;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.helpers.EventUpdaterResume;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import static cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState.*;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matěj Bartoň
 */
public class Participation implements UIController<EventInfo>{
    private EventInfo controled;
    private Events event;

    public Participation(Events event) {
        this.event = event;
    }

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

    @Override
    public void setControlled(EventInfo controled) {
        this.controled = controled;
    }

    public void setParticipationState(ParticipantState participantState) {
        SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(true);
        SimpleDateFormat dfBegins = new SimpleDateFormat("dd.MM.YYYY HH:mm");
        String eventIdentifier = String.format("Událost: %s - %s", event.getEventname(), dfBegins.format(event.getBegins()));
        switch (participantState){
            case NOT_FILLED:
                JOptionPane.showMessageDialog(controled, "Prosím vyplňte vaší docházku!", 
                        eventIdentifier
                        , JOptionPane.INFORMATION_MESSAGE);
                break;
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
            
            case COMING:
                ParticipantUpdater partU = new ParticipantUpdater(event, participantState, controled);
                partU.start();
                break;
            
            case NOT_INVITED:
                throw new InputMismatchException("Invalid input value for this function: " + participantState);
            
        }
        SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(false);
                
    }
    
}

/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.EventInfo;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * This class represents a Thread for saving the information about participation at the certain event.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class ParticipantUpdater extends Thread implements ThreadEntityManager, UIController<EventInfo>{
    private final EntityManager tem = SmartOrchestra.getEntityManagerFactory().createEntityManager();
    private final EntityManager lock = SmartOrchestra.getInstance().getEntityManager();
    private final EventAdmin ea = new EventAdmin(this);
    
    private String message;
    private ParticipantState state;
    private Events event;
    
    private EventInfo controled;
    private volatile boolean running;
    
    /**
     * Initalizes the class with no default values.
     * @param event - a pointer to the instance of {@link Events}, the event we save information for
     * @param state - an enum {@link ParticipantState} value, the state of participation (for more information see: {@link ParticipantState})
     * @param message - a message that describes the reason of not participating the event (it shall be used when {@code ParticipantUpdater.state} is equal to {@code ParticipantState.NOT_COMING}
     * @param controled - a pointer to the instance of {@link EventInfo} for enabling the interaction with GUI
     */
    ParticipantUpdater(Events event, ParticipantState state, String message, EventInfo controled){
        this.event = event;
        this.state = state;
        this.message = message;
        
        running = true;
    }
   
    /**
     * Initalizes the class with default values for {@code ParticipantUpdater.message} which is {@code null}
     * @param event - a pointer to the instance of {@link Events}, the event we save information for
     * @param state - an enum {@link ParticipantState} value, the state of participation (for more information see: {@link ParticipantState})
     * @param controled - a pointer to the instance of {@link EventInfo} for enabling the interaction with GUI
     */
    ParticipantUpdater(Events event, ParticipantState state, EventInfo controled){
        this(event, state, null, controled);
    }
    
    /**
     * Run method of the thread. Saves the information about the participation in the database.
     */
    @Override
    public void run() {
        while(running){
            Logger.getLogger(ParticipantUpdater.class.getName()).fine("Updating Participant info");
            ea.updateParticipation(event, state, message);            
            SmartOrchestra.getInstance().getEventUpdater().interrupt();
            SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(false);
            terminate();
        }
    }
    
    // Terminates the thread
    private void terminate(){
        running = false;
    }
    
    /**
     * See {@link ThreadEntityManager} for more information. 
     * @return an instance of {@code EntityManager}
     */
    @Override
    public EntityManager getEntityManager() {
        return tem;
    }
    
    /**
     * See {@link UIController} for more information.
     * @param controled 
     */
    @Override
    public void setControlled(EventInfo controled) {
        this.controled = controled;
    }
    
    
    
}

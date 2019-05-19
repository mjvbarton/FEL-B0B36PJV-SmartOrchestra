/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.EventInfo;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj Bartoň
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
    
    ParticipantUpdater(Events event, ParticipantState state, String message, EventInfo controled){
        this.event = event;
        this.state = state;
        this.message = message;
        
        running = true;
    }
   
    ParticipantUpdater(Events event, ParticipantState state, EventInfo controled){
        this(event, state, null, controled);
    }
    
    @Override
    public void run() {
        while(running){
            ea.updateParticipation(event, state, message);            
            SmartOrchestra.getInstance().getEventUpdater().interrupt();
            SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(false);
            terminate();
        }
    }
    
    private void terminate(){
        running = false;
    }
    

    @Override
    public EntityManager getEntityManager() {
        return tem;
    }

    @Override
    public void setControlled(EventInfo controled) {
        this.controled = controled;
    }
    
    
    
}

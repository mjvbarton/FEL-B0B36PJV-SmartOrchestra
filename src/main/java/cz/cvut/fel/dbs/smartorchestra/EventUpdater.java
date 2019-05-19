/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 *
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;

import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.EventDateFilter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * This class is a EventUpdater thread for {@link ShowEvents}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventUpdater extends Thread implements UIController<ShowEvents>, ThreadEntityManager{
    private ShowEvents controled;
    private JTabbedPane tabbedPane;
    private EventAdmin ea;
    private EntityManager em;
    private final Object pauseLock = new Object();
    private volatile boolean waiting;
    private volatile boolean loadingBlocked;
    private volatile EventDateFilter filter;
    
    /**
     * Initalizes the thread with {@link ShowEvents} instance as {@link UIControlled} given.
     * @param controled - an instance of {@link ShowEvents}
     */
    public EventUpdater(ShowEvents controled){
        setControlled(controled);
        em = SmartOrchestra.getEntityManagerFactory().createEntityManager();
        tabbedPane = SmartOrchestra.getInstance().getMainWin().getContent();
        waiting = true;
        loadingBlocked = false;
        filter = EventDateFilter.NEXT;
    }
    
    /**
     * Loads events from database and update their values in GUI every 10 seconds.
     */
    @Override
    public void run() {
        ea = new EventAdmin(EventUpdater.this);
        while(true){
            if(tabbedPane.getSelectedIndex() == Main.TAB_EVENTS && !loadingBlocked){
                updateEvents();
                try {
                    sleep(1000 * 10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater woken up.");
                }
            }
        }
    }
             
    /**
     * See {@link UIController}
     * @param controled 
     */
    @Override
    public void setControlled(ShowEvents controled) {
        this.controled = controled;
    }
    
    /**
     * A pointer to controled element of this controller.
     * @return - pointer to the instance of {@link ShowEvents}
     */
    public ShowEvents getControled() {
        return controled;
    }

    /*
     * <i>NOTE: THIS FEATURE IS DEPRECATED AND LEFT THERE FOR OPTIONAL DEVELOPING PURPOSES</i>
     * Pauses the thread if {@code waiting} is {@code true} and
     * @param waiting 
     */
    /*public void setWaiting(boolean waiting) {
        this.waiting = waiting;
        if(!waiting){
            synchronized(pauseLock){
                Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater resumed");
                controled.repaint();
                pauseLock.notifyAll();
            }    
            interrupt();
        }   
    }*/
    
    /**
     * Sets the flag {@code loadingBlocked} of the thread. 
     * If {@code true} thread does not load any events.
     * @param isBlocked - {@code boolean} value to be set on {@code loadingBlocked} flag
     */
    public synchronized void setBlockUpdate(boolean isBlocked){
        loadingBlocked = isBlocked;
        Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater updateBlocked: {0}", isBlocked);
    }
    
    /**
     * Sets the filter of loaded events from enum {@link EventDateFilter}.
     * For more information about how the filter works see: {@link EventAdmin}
     * @param filter - enumerated filter flag, see: {@link EventDateFilter}
     */
    public synchronized void setFilter(EventDateFilter filter) {
        this.filter = filter;
        interrupt();
    }   
    
    /**
     * This function updates events according to the set filter value via {@link EventUpdater} and
     * loads them into the controled {@link ShowEvents}
     * This function is being called by the method {@code run}, but it can be also 
     * called directly from any other thread.
     */
    public void updateEvents(){
        try {
            List<Events> events = ea.loadEvents(filter);
            Users activeUser = SmartOrchestra.getInstance().getActiveUser();
            if(events.isEmpty()){
                Logger.getLogger(ShowEvents.class.getName()).log(Level.INFO, "No events found.");
                /*JOptionPane.showMessageDialog(controled, "Seznam událostí je prázdný.",
                    "Varování", JOptionPane.INFORMATION_MESSAGE);*/
                controled.loadEvents(events, new HashMap(), filter);
                return;
            }
            controled.loadEvents(events, ea.getParticipationMap(activeUser, events), filter);
        } catch (Exception ex) {
            Logger.getLogger(ShowEvents.class.getName()).log(Level.SEVERE, "Unable to load events.", ex);
            JOptionPane.showMessageDialog(controled, "Chyba v běhu programu: " + ex.getMessage(), 
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * See: {@link ThreadEntityManager} for further information.
     * @return 
     */
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

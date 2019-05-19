/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;

import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.EventDateFilter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Matěj Bartoň
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
    
    public EventUpdater(ShowEvents controled){
        setControlled(controled);
        em = SmartOrchestra.getEntityManagerFactory().createEntityManager();
        tabbedPane = SmartOrchestra.getInstance().getMainWin().getContent();
        waiting = true;
        loadingBlocked = false;
        filter = EventDateFilter.NEXT;
    }
    
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
             
    @Override
    public void setControlled(ShowEvents controled) {
        this.controled = controled;
    }

    public ShowEvents getControled() {
        return controled;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
        if(!waiting){
            synchronized(pauseLock){
                Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater resumed");
                controled.repaint();
                pauseLock.notifyAll();
            }    
            interrupt();
        }   
    }
    
    public synchronized void setBlockUpdate(boolean isBlocked){
        loadingBlocked = isBlocked;
        Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater updateBlocked: {0}", isBlocked);
    }

    public synchronized void setFilter(EventDateFilter filter) {
        this.filter = filter;
        interrupt();
    }   
        
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

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

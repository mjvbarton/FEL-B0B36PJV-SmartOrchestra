/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;

import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
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
    
    public EventUpdater(ShowEvents controled){
        setControlled(controled);
        em = SmartOrchestra.getEntityManagerFactory().createEntityManager();
        tabbedPane = SmartOrchestra.getInstance().getMainWin().getContent();
    }
    
    @Override
    public void run() {
        ea = new EventAdmin(EventUpdater.this);
        while(true){
            if(waiting){
                synchronized(pauseLock){
                    if(waiting){
                        try {
                            Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater waiting");
                            pauseLock.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(EventUpdater.class.getName()).log(Level.WARNING, "EventUpdater interrupted", ex);
                            break;
                        }
                    }
                }
            }
            if(tabbedPane.getSelectedIndex() == Main.TAB_EVENTS){
                try {
                    controled.loadEvents(ea.loadEvents()); 
                } catch (NoResultException ex) {
                    Logger.getLogger(ShowEvents.class.getName()).log(Level.INFO, "No events found.");
                    JOptionPane.showMessageDialog(controled, "Seznam událostí je prázdný.", 
                            "Varování", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(ShowEvents.class.getName()).log(Level.WARNING, "Error while loading events.", ex); 
                } catch (Exception ex) {
                    Logger.getLogger(ShowEvents.class.getName()).log(Level.SEVERE, "Unable to load events.", ex);
                    JOptionPane.showMessageDialog(controled, "Chyba v běhu programu: " + ex.getMessage(), 
                            "Chyba", JOptionPane.ERROR_MESSAGE);
                }
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

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
        if(!waiting){
            synchronized(pauseLock){
                Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "EventUpdater resumed");
                pauseLock.notifyAll();
            }    
            interrupt();
        }   
    } 

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

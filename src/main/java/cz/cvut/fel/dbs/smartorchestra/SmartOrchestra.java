/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.LoginScreen;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.JOptionPane;
import org.hibernate.service.spi.ServiceException;


/**
 *
 * @author Matěj Bartoň
 */
public class SmartOrchestra implements ThreadEntityManager{
    private static SmartOrchestra singleton;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    
    private EntityManager em;
    private static EntityManagerFactory emf;
    
    private Image ico;
    private EventUpdater eventUpdater;
    
    private LoginScreen loginScr;
    private Main mainWin;
    
    private Users activeUser;
    private boolean administrationActive;
    
    private SmartOrchestra(){
        try{
            ico = ImageIO.read(new File("src/main/resources/img/orchestra.png"));
        } catch(IOException ex){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Cannot get icon. ", ex);
            ico = null;
        }
        eventUpdater = null;
        
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Logger.getLogger("").setLevel(Level.INFO);
        for(Handler h : Logger.getLogger("").getHandlers()){
            h.setLevel(Level.INFO);
        }
        Logger.getGlobal().fine("SMARTORCHESTRA STARTED");
        try{        
            SmartOrchestra.getInstance().setupJPA();
        
            SmartOrchestra.getInstance().loginScr = new LoginScreen();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    SmartOrchestra.getInstance().loginScr.setVisible(true);
                }
            });
        } catch (ServiceException err){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Database connection failed.", err);
            JOptionPane.showMessageDialog(null, "Připojení k databázi selhalo.", 
                    "SmartOrchestra", JOptionPane.ERROR_MESSAGE);
            return;
            
        } catch(Exception err){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Cannot run SmartOrchestra", err);
            JOptionPane.showMessageDialog(null, "Chyba při běhu programu: Pro více informací zkontrolujte log.", 
                    "SmartOrchestra", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    @Override
    public final EntityManager getEntityManager(){
        return em;
    }
    
    private void setupJPA() throws ServiceException{
        emf = Persistence.createEntityManagerFactory("smartorchestraPU");
        em = emf.createEntityManager();
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    public void runMainWindow(){
        loginScr.dispose();
        mainWin = new Main();
        mainWin.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {
                getEventUpdater().setWaiting(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {}
                
            @Override
            public void windowClosed(WindowEvent e) {
                getEventUpdater().getEntityManager().close();
                Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "Entity Manager closed.");
                getEntityManager().close();
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Entity Manager closed.");
                emf.close();
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Entity Manager Factory closed.");
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Application quit.");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                getEventUpdater().setWaiting(false);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                getEventUpdater().setWaiting(true);
            }

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
                        
        });
        mainWin.getActiveUserName().setText(
                        String.format(mainWin.getActiveUserName().getText(),
                                activeUser.getFirstName(),
                                activeUser.getFamilyName(),
                                activeUser.getEmail()
                        ));
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                mainWin.setVisible(true);
            }
        });
        startEventUpdater(mainWin.getEvents());
        
    }
    public MainControl getController(Main mainWin){
        return new MainControl(mainWin);
    }
    
    public Main getMainWin() {
        return mainWin;
    }

    public Users getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Users activeUser) {
        this.activeUser = activeUser;
    }

    public boolean isAdministrationActive() {
        return administrationActive;
    }

    public void setAdministrationActive(boolean administrationActive) {
        this.administrationActive = administrationActive;
    }

    public Image getIco() {
        return ico;
    }
        
    public static SmartOrchestra getInstance(){
        if(singleton == null){
            synchronized(SmartOrchestra.class){
                if(singleton == null){
                    singleton = new SmartOrchestra();
                }
            }
        }
        return singleton;
    }
    
    public void startEventUpdater(){
        startEventUpdater(getMainWin().getEvents());
    }

    public void startEventUpdater(ShowEvents events) {
        if(eventUpdater == null){
            synchronized(SmartOrchestra.class){
                if(eventUpdater == null){
                    eventUpdater = new EventUpdater(events);
                    eventUpdater.start();
                    Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "EventUpdater started.");
                }
            }
        } else {
            synchronized(SmartOrchestra.class){
                eventUpdater.notify();
            }
        }
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
}

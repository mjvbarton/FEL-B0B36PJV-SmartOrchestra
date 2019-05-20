/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 *
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
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.JOptionPane;
import org.hibernate.service.spi.ServiceException;


/**
 * This class represents the aplication. It is a singleton class.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class SmartOrchestra implements ThreadEntityManager{
    private static SmartOrchestra singleton;
    /**
     * Date format which the application works with.
     */
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final double TIME_BLOCK_RATIO = 1.5;
    
    private EntityManager em;
    private static EntityManagerFactory emf;
    
    private Image ico;
    private EventUpdater eventUpdater;
    
    private LoginScreen loginScr;
    private Main mainWin;
    
    private Users activeUser;
    private boolean administrationActive;
    
    // Constructor for singletonized class
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
     * Entry point for the system to the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Logger setup
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
        // Sanitize no connection to the database
        } catch (ServiceException err){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Database connection failed.", err);
            JOptionPane.showMessageDialog(null, "Připojení k databázi selhalo.", 
                    "SmartOrchestra", JOptionPane.ERROR_MESSAGE);
            
        // Sanitize other exceptions that may occur during the startup
        } catch(Exception err){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Cannot run SmartOrchestra", err);
            JOptionPane.showMessageDialog(null, "Chyba při běhu programu: Pro více informací zkontrolujte log.", 
                    "SmartOrchestra", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /**
     * See <code>ThreadEntityManager</code> interface.
     * {@link ThreadEntityManager}
     */
    @Override
    public final EntityManager getEntityManager(){
        return em;
    }
    
    // Setup database connection
    private void setupJPA() throws ServiceException{
        emf = Persistence.createEntityManagerFactory("smartorchestraPU");
        em = emf.createEntityManager();
    }
    
    /**
     * Returns the <code>EntityManagerFactory</code> of the application
     * @return <code>EntityManagerFactory</code> instance
     */
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    /**
     * Runs the main window. This method is called from {@link UserLogin} after succesful login.
     * This method also sets up the <code>mainWin</code> property <code>SmartOrchestra</code> instance.
     */
    public void runMainWindow(){
        loginScr.dispose();
        mainWin = new Main();
        // Adds the listener for window close and for EventUpdater manipulation
        mainWin.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {
                                
                //getEventUpdater().setWaiting(false);
                getEventUpdater().setBlockUpdate(false);
                getEventUpdater().interrupt();
            }

            @Override
            // Close database connection after closing the application
            public void windowClosing(WindowEvent e) {
            getEventUpdater().getEntityManager().close();
                Logger.getLogger(EventUpdater.class.getName()).log(Level.INFO, "Entity Manager closed.");
                getEntityManager().close();
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Entity Manager closed.");
                emf.close();
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Entity Manager Factory closed.");
                Logger.getLogger(SmartOrchestra.class.getName()).log(Level.INFO, "Application quit.");
            }
                
            @Override
            public void windowClosed(WindowEvent e) {}
                

            @Override
            public void windowIconified(WindowEvent e) {
                //getEventUpdater().setWaiting(false);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //getEventUpdater().setWaiting(true);
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
    
    /**
     * Creates controller for main window {@link Main}
     * @param mainWin - instance of {@link Main}
     * @return new {@link MainControl} controller
     */
    public MainControl getController(Main mainWin){
        return new MainControl(mainWin);
    }
    
    /**
     * Returns stored pointer to the main window (see: {@link Main})
     * @return pointer to the instance of {@link Main}
     */    
    public Main getMainWin() {
        return mainWin;
    }
    
    /**
     * Returns stored pointer to the active user (alias the user who logged in)
     * @return activeUser as an instance of {@link Users}
     */
    public Users getActiveUser() {
        return activeUser;
    }
    
    /**
     * Sets pointer to the active user (alias the user who logged in). This method is used by {@link UserLogin}
     * @param activeUser - an instance of {@link Users}
     */
    public void setActiveUser(Users activeUser) {
        this.activeUser = activeUser;
    }
    
    /**
     * Gives an information about administrator access to the application.
     * @return {@code true} if the active user is administrator and {@code false} if not so
     */
    public boolean isAdministrationActive() {
        return administrationActive;
    }
    
    /**
     * Sets the information about administrator access to the application.
     * @param administrationActive - {@code true} if the active user is administrator and {@code false} if not so
     */
    public void setAdministrationActive(boolean administrationActive) {
        this.administrationActive = administrationActive;
    }
    
    /**
     * Link to the application icon
     * @return - an instance of <code>java.awt.Image</code>
     */
    public Image getIco() {
        return ico;
    }
    
    /**
     * Access to the singleton class. If the instance was not set up, it creates new instance of <code>SmartOrchestra</code>.
     * @return - pointer to singleton instance of <code>SmartOrchestra</code>
     */
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
    
    /**
     * Starts {@link EventUpdater} thread linked to the {@link ShowEvents} element of main window {@link Main}
     */
    public void startEventUpdater(){
        startEventUpdater(getMainWin().getEvents());
    }
    
    /**
     * Starts {@link EventUpdater} thread linked to the given {@link ShowEvents}.
     * @param events - an instance of {@link ShowEvents}
     */
    public void startEventUpdater(ShowEvents events) {
        if(eventUpdater == null){
            synchronized(SmartOrchestra.class){
                if(eventUpdater == null){
                    eventUpdater = new EventUpdater(events);
                    // Set Idle state of the thread
                    eventUpdater.setBlockUpdate(true);
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

    /**
     * Return pointer to {@link EventUpdater} thread of the application
     * @return - an instance of {@link EventUpdater}
     */
    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
    
    /**
     * Counts the block date to determine if orchestral event can collect information about participation state.
     * The current value is set to <i>1,5 hour</i> before givent date.
     * @param date - usually a value of <code>Events.begins</code> of {@link Events}, theoretically it can be used for any given <code>Date</code> instance
     * @return instance of <code>Date</code> of value from <code>SmartOrchestra.TIME_BLOCK_RATIO</code> in hours
     */
    public Date getBlockDate(Date date){
        Date x = new Date((long) (date.getTime() - TIME_BLOCK_RATIO * 3600 * 1000));
        return x;
    }
}

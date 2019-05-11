/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.LoginScreen;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.swing.JOptionPane;
import org.hibernate.service.spi.ServiceException;


/**
 *
 * @author Matěj Bartoň
 */
public class SmartOrchestra {
    private static SmartOrchestra singleton;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    private LoginScreen loginScr;
    private Main mainWin;
    
    private Users activeUser;
    private boolean administrationActive;
        
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
        } catch(Exception err){
            Logger.getLogger(SmartOrchestra.class.getName()).log(Level.SEVERE, "Cannot run SmartOrchestra", err);
            JOptionPane.showMessageDialog(null, "Chyba při běhu programu. Pro více informací zkontrolujte log.", 
                    "SmartOrchestra", JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    public final EntityManager getEntityManager(){
        return em;
    }
    
    private void setupJPA() throws ServiceException{
        emf = Persistence.createEntityManagerFactory("smartorchestraPU");
        em = emf.createEntityManager();
    }
    
    public void runMainWindow(){
        loginScr.dispose();
        mainWin = new Main();
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                mainWin.setVisible(true);
            }
        });
        
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
}

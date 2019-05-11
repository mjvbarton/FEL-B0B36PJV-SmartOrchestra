/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.LoginScreen;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import javax.persistence.*;


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
        SmartOrchestra.getInstance().setupJPA();
        
        SmartOrchestra.getInstance().loginScr = new LoginScreen();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SmartOrchestra.getInstance().loginScr.setVisible(true);
            }
        });
    }
    
    public final EntityManager getEntityManager(){
        return em;
    }
    
    private void setupJPA(){
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

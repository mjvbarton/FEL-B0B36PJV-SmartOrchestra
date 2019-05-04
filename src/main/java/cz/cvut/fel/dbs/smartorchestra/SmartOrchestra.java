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
import javax.swing.JOptionPane;


/**
 *
 * @author Matěj Bartoň
 */
public class SmartOrchestra {
    private static EntityManager em;
    private static EntityManagerFactory emf;
    
    private static LoginScreen loginScr;
    private static Main mainWin;
    
    private static Users activeUser;
    
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        setupJPA();
        
        loginScr = new LoginScreen();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                loginScr.setVisible(true);
            }
        });
    }
    
    public static final EntityManager getEntityManager(){
        return em;
    }
    
    private static final void setupJPA(){
        emf = Persistence.createEntityManagerFactory("smartorchestraPU");
        em = emf.createEntityManager();
    }
    
    public static void runMainWindow(){
        loginScr.dispose();
        mainWin = new Main();
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                mainWin.setVisible(true);
            }
        });
        
    }
    public static MainControl getController(Main mainWin){
        return new MainControl(mainWin);
    }
    
    public static Main getMainWin() {
        return mainWin;
    }

    public static Users getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(Users activeUser) {
        SmartOrchestra.activeUser = activeUser;
    }
}

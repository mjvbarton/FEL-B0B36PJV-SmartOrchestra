/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.LoginScreen;
import cz.cvut.fel.dbs.smartorchestra.model.UserManager;
import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author Matěj Bartoň
 */
public class SmartOrchestra {
    private static EntityManager em;
    private static EntityManagerFactory emf;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        setupJPA();
        
        LoginScreen login = new LoginScreen();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                login.setVisible(true);
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
}

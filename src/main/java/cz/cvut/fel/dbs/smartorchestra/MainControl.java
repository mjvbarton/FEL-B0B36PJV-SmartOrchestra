/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.UserRegistration;
import cz.cvut.fel.dbs.smartorchestra.gui.ViewProfile;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.UserAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class MainControl implements UIController<Main>{
    private Main controled;
    
    private boolean adminAccess = true;
    private ShowEvents showEvents;
    private UserAdmin ua;
    
    public MainControl(Main mainWin){
        setControlled(mainWin);
        showEvents = controled.getEvents();
        ua = new UserAdmin();        
    }
    
    public void showUserProfile(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewProfile dialog = new ViewProfile(controled, true);
                UserSettings uic = dialog.getUIController();
                uic.loadUser(SmartOrchestra.getInstance().getActiveUser());
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {

                }
            }
        });
    }

    @Override
    public void setControlled(Main controled) {
        this.controled = controled;
    }
    
    public void loadEvents(){
        EventAdmin ea = new EventAdmin();
        try {
            controled.getEvents().loadEvents(ea.loadEvents());
        } catch (NoResultException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.INFO, "No events found.");
            JOptionPane.showMessageDialog(controled, "Seznam událostí je prázdný.", 
                    controled.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, "Unable to load events.", ex);
            JOptionPane.showMessageDialog(controled, "Chyba v běhu programu: " + ex.getMessage(), 
                    controled.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadUsersToTable(){
        ua = new UserAdmin();
        try {
            controled.fetchUserIntoTable(ua.loadUsers());
        } catch (UserAdminException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(controled, ex.getMessage(), controled.getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }

    public void editUserFromTable(int rowAtPoint) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserDetails dialog = new UserDetails(controled, true);
                UserSettings uic = dialog.getUIController();
                Users user = ua.getUserForAdministration(rowAtPoint);
                uic.loadUser(user);
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {
                    
                }
                controled.getUIController().loadUsersToTable();
            }
        });
        
    }

    public void addNewUser() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                UserRegistration dialog = new UserRegistration(controled, true);
                Registration rg = dialog.getUIController();
                rg.loadUser(new Users());
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {
                    
                }
                controled.getUIController().loadUsersToTable();            
            }            
        });
    }
    
}

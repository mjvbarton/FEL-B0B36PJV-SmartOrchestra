/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.PlayerManagerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.UserRegistration;
import cz.cvut.fel.dbs.smartorchestra.gui.ViewProfile;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.PlayerManager;
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
        Logger.getLogger(MainControl.class.getName()).log(Level.INFO, "Unnecessary function toggled");        
    }
    
    public void loadUsersToTable(){
        ua = new UserAdmin();
        try {
            PlayerManager pm = new PlayerManager();
            controled.fetchUserIntoTable(ua.loadUsers(), pm.getPlayers());
        } catch (PlayerManagerException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, "Error loading users", ex);
            JOptionPane.showMessageDialog(controled, ex.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
            
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

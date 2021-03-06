/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.PlayerManagerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.UserRegistration;
import cz.cvut.fel.dbs.smartorchestra.gui.ViewProfile;
import cz.cvut.fel.dbs.smartorchestra.model.PlayerManager;
import cz.cvut.fel.dbs.smartorchestra.model.UserAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class represents the controller of the main window of the applicatiion (see: {@link Main}).
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class MainControl implements UIController<Main>{
    private Main controled;
    
    private boolean adminAccess = true;
    private ShowEvents showEvents;
    private UserAdmin ua;
    
    /**
     * Initalizes the class with the instance of the main window {@link Main}. 
     * This instance is passed via {@link SmartOrchestra} singleton class.
     * @param mainWin - an instance of {@link Main}
     */
    public MainControl(Main mainWin){
        setControlled(mainWin);
        mainWin.enableAdminAccess(SmartOrchestra.getInstance().isAdministrationActive());
        showEvents = controled.getEvents();
        ua = new UserAdmin();        
    }
    
    /**
     * Shows a dialog with a profile of active user. <i>(UC02)</i>
     */
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
    
    /**
     * See {@link UIController} for more information.
     * @param controled 
     */
    @Override
    public void setControlled(Main controled) {
        this.controled = controled;
    }
    
    /**
     * Loads information about all user into user table. <i>(UC21)</i>
     */
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
    
    /**
     * Shows dialog for editing the user from users table. <i>(UC22)</i>
     * @param rowAtPoint - {@code int} row that was double-clicked in users table
     */
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

    /**
     * Shows dialog for adding a new user to the system. <i>(UC23)</i>
     */
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

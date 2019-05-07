/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.ViewProfile;
import cz.cvut.fel.dbs.smartorchestra.model.UserAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matěj Bartoň
 */
public class MainControl implements UIController<Main>{
    private Main controled;
    
    private boolean adminAccess = true;
    private UserAdmin ua;
    
    public MainControl(Main mainWin){
        setControlled(mainWin);
        ua = new UserAdmin();        
    }
    
    public void showUserProfile(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewProfile dialog = new ViewProfile(controled, true);
                UserSettings uic = dialog.getUIController();
                uic.loadUser(SmartOrchestra.getActiveUser());
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {

                }
            }
        });
    }

    @Override
    public void setControlled(Main controled) {
        this.controled = controled;
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
                uic.loadUser(SmartOrchestra.getActiveUser());
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {
                    
                }
                controled.getUIController().loadUsersToTable();
            }
        });
        
    }
    
}

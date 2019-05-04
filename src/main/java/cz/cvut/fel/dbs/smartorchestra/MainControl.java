/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.Main;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.ViewProfile;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matěj Bartoň
 */
public class MainControl implements UIController<Main>{
    private Main controled;
    
    public MainControl(Main mainWin){
        setControlled(mainWin);
        
    }
    
    public void showUserProfile(){
        /*ViewProfile dialog = new ViewProfile(controled, true);
        dialog.setVisible(true);
        dialog.getUIController().loadUser(SmartOrchestra.getActiveUser());*/
        System.out.println("Showing user profile");
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
    
}

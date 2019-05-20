/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.PlayerManagerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.model.PlayerManager;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * This class is an extension of {@link UserSettings}. It represents a special controller for {@link UserDetails}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class Registration extends UserSettings{
    
    /**
     * See {@link UserSettings#UserSettings(cz.cvut.fel.dbs.smartorchestra.gui.UserDetails) for more information.
     * @param controled - an instance of {@link UserDetails}
     */
    public Registration(UserDetails controled) {
        super(controled);
        
    }
    
    /**
     * Edits the method of {@link #super} for proper loading of an empty user
     * @param activeUser - in this case an empty instance of {@link Users}
     */
    @Override
    public void loadUser(Users activeUser) {
        user = activeUser;
        loadSections();
    }
    
    /**
     * Loads sections into {@link UserDetails#fieldSection}
     */
    @Override
    public void loadSections() {
        try {
            PlayerManager pm = new PlayerManager();
            controled.fetchSections(pm.getActiveSections(), 0);
        } catch (PlayerManagerException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(controled, ex.getMessage(), controled.getTitle(), 
                    JOptionPane.WARNING_MESSAGE);
        }
    }   
    
    /**
     * Saves the information about the new user. The diference between {@link UserSettings#saveUser() } is the password validation.
     */
    @Override
    public void saveUser() {
        boolean failFlag = false;
        controled.getInfoNewPasswd().setText("");
        controled.getInfoConfirmPasswd().setText("");
        
        if(controled.getFieldNewPasswd().getText().isEmpty()){
            controled.getInfoNewPasswd().setText("Nevyplnili jste toto pole");
            failFlag = true;
        }
        
        if(controled.getFieldConfirmPasswd().getText().isEmpty()){
            controled.getInfoConfirmPasswd().setText("Nevyplnili jste toto pole");
            failFlag = true;
        }
        
        if(!controled.getFieldNewPasswd().getText().equals(controled.getFieldConfirmPasswd().getText())){
            controled.getInfoConfirmPasswd().setText("Zadaná hesla se neshodují");
            failFlag = true;            
        }
        if(failFlag){
            JOptionPane.showMessageDialog(controled, "Ukládání se nezdařilo", controled.getTitle(), JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            user.setPasswd(BCrypt.hashpw(controled.getFieldNewPasswd().getText(), BCrypt.gensalt(12)));
            //user.setUid(1L);
        } catch (WrongInputException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.saveUser();
        
    }
    
    /**
     * Checks if an entered email is yet stored in the database for certain user.
     * @return - {@code true} if the checking was successful, {@code false} if the email is yet represented in database
     * or the email field is empty
     */
    @Override
    public boolean checkEmail() {
        boolean superChecked = super.checkEmail();
        if(superChecked && 
                (controled.getFieldEmail().getText()
                        .equals(
                                SmartOrchestra.getInstance().getActiveUser().getEmail()
                        ))){
            controled.getInfoEmail().setText("Email je již obsazen");
            return false;
        }
        return superChecked;
    }
    
    /**
     * See {@link UIController} for more information.
     * @param controled - an instance of {@link UserDetails}
     */
    @Override
    public void setControlled(UserDetails controled) {
        super.setControlled(controled);
    }    
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Matěj Bartoň
 */
public class Registration extends UserSettings{
    
    public Registration(UserDetails controled) {
        super(controled);
        
    }

    @Override
    public void loadUser(Users activeUser) {
        user = activeUser;
        loadSections();
    }

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

    @Override
    public void setControlled(UserDetails controled) {
        super.setControlled(controled);
    }    
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.NonExistingUserException;
import cz.cvut.fel.dbs.smartorchestra.gui.LoginScreen;
import cz.cvut.fel.dbs.smartorchestra.model.UserManager;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.logging.Logger;

/**
 *
 * @author Matěj Bartoň
 */
public class UserLogin{
    private LoginScreen scr;
    
    public UserLogin(LoginScreen scr){
        this.scr = scr;
    }
    
    public void login(JTextField email, JPasswordField passwd){
        if(email.getText().isEmpty() || passwd.getText().isEmpty()){
            JOptionPane.showMessageDialog(scr, "Prosím, vyplňte obě pole");
            return;
        }
        try{
            UserManager um = new UserManager();
            Users activeUser = um.login(email.getText(), passwd.getText());
            boolean enableAdminAccess = um.checkAdministrator(activeUser);
            SmartOrchestra.getInstance().setAdministrationActive(enableAdminAccess);
            Logger.getLogger(UserLogin.class.getName()).log(Level.INFO, "User {0} logon", email.getText());
            Logger.getLogger(UserLogin.class.getName()).log(Level.INFO, "Administrator usage for {0} enabled: {1}", new Object[]{email.getText(), enableAdminAccess});
            SmartOrchestra.getInstance().setActiveUser(activeUser);
            SmartOrchestra.getInstance().runMainWindow();
            
        } catch (NonExistingUserException err){
            Logger.getLogger(UserLogin.class.getName()).log(Level.WARNING, "Login failed for email: {0}", email.getText());
            JOptionPane.showMessageDialog(scr, "Špatné uživatelské jméno nebo heslo", "Přihlášení se nezdařilo",
                    JOptionPane.WARNING_MESSAGE);
            email.setText("");
            passwd.setText("");
        } catch (Exception err){
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, err);
            JOptionPane.showMessageDialog(scr, "Chyba při běhu programu. Pro více informací zkontrolujte log.", "Chyba",
                    JOptionPane.ERROR_MESSAGE);
            email.setText("");
            passwd.setText("");
        }
    }   
}

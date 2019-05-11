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
            Logger.getLogger(UserLogin.class.getName()).log(Level.FINE, "User {0} logon", email.getText());
            SmartOrchestra.getInstance().setActiveUser(activeUser);
            SmartOrchestra.getInstance().runMainWindow();
            
        } catch (NonExistingUserException err){
            Logger.getLogger(UserLogin.class.getName()).log(Level.FINE, "Login failed for email: {0}", email.getText());
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

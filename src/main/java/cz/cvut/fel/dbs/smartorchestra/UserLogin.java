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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
            SmartOrchestra.setActiveUser(activeUser);
            SmartOrchestra.runMainWindow();
            
        } catch (NonExistingUserException err){
            JOptionPane.showMessageDialog(scr, "Špatné uživatelské jméno nebo heslo", "Přihlášení se nezdařilo",
                    JOptionPane.WARNING_MESSAGE);
            email.setText("");
            passwd.setText("");
        } catch (Exception err){
            err.printStackTrace();
            JOptionPane.showMessageDialog(scr, "Chyba při běhu programu. Pro více informací zkontrolujte log.", "Chyba",
                    JOptionPane.ERROR_MESSAGE);
            email.setText("");
            passwd.setText("");
        }
    }   
}

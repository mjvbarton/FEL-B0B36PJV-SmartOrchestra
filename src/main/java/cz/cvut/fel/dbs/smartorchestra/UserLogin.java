/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
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
 * This class implements the controller for login process.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserLogin{
    private LoginScreen scr;
    
    /**
     * Initalize controller with given {@link LoginScreen} frame from GUI.
     * @param scr - instance of {@link LoginScreen}
     */
    public UserLogin(LoginScreen scr){
        this.scr = scr;
    }
    
    /**
     * Process user login with given <code>JTextField</code> values
     * @param email - <code>JTextField</code> email from {@link LoginScreen}
     * @param passwd - <code>JTextField</code> password from {@link LoginScreen} 
     */
    public void login(JTextField email, JPasswordField passwd){
        // Validation of fields
        if(email.getText().isEmpty() || passwd.getText().isEmpty()){
            JOptionPane.showMessageDialog(scr, "Prosím, vyplňte obě pole");
            return;
        }
        try{
            UserManager um = new UserManager();
            
            // Try login
            Users activeUser = um.login(email.getText(), passwd.getText());
            Logger.getLogger(UserLogin.class.getName()).log(Level.INFO, "User {0} logon", email.getText());
            
            // Check and enable admin access
            boolean enableAdminAccess = um.checkAdministrator(activeUser);
            SmartOrchestra.getInstance().setAdministrationActive(enableAdminAccess);
            Logger.getLogger(UserLogin.class.getName()).log(Level.INFO, "Administrator usage for {0} enabled: {1}", new Object[]{email.getText(), enableAdminAccess});
            
            // Run application
            SmartOrchestra.getInstance().setActiveUser(activeUser);
            SmartOrchestra.getInstance().runMainWindow();
        
        // Sanitization of exceptions occured during login
        } catch (NonExistingUserException err){
            Logger.getLogger(UserLogin.class.getName()).log(Level.WARNING, "Login failed for email: {0}", email.getText());
            JOptionPane.showMessageDialog(scr, "Špatné uživatelské jméno nebo heslo", "Přihlášení se nezdařilo",
                    JOptionPane.WARNING_MESSAGE);
            email.setText("");
            passwd.setText("");
        } catch (Exception err){
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, "Cannot login.", err);
            JOptionPane.showMessageDialog(scr, "Chyba při běhu programu. Pro více informací zkontrolujte log.", "Chyba",
                    JOptionPane.ERROR_MESSAGE);
            email.setText("");
            passwd.setText("");
        }
    }   
}

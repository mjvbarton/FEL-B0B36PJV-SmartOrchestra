/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.Registration;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import java.awt.Frame;

/**
 *
 * @author Matěj Bartoň
 */
public class UserRegistration extends UserDetails{
    private Registration controller;
    
    public UserRegistration(Frame parent, boolean modal) {
        super(parent, modal);
        caption.setText("Nový uživatel");
        super.setTitle("Nový uživatel");
        labelCurrentPasswd.setEnabled(false);
        fieldCurrentPasswd.setEnabled(false);
        btnPasswdChange.setVisible(false);
        btnDeleteAccount.setVisible(false);  
        setUIController(new Registration(UserRegistration.this));
        
    }
    
    public final void setUIController(Registration controller) {
        this.controller = controller;
    }

    @Override
    public Registration getUIController() {
        return controller;
    }
    
    @Override
    protected void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        controller.saveUser();
    }
    
    
    
}

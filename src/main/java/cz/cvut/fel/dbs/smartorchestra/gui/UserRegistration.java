/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

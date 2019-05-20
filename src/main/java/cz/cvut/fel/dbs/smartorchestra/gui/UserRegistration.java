/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.Registration;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import java.awt.Frame;

/**
 * This is dialog for creating new user in the system. It is an extension of {@link UserDetails}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserRegistration extends UserDetails{
    private Registration controller;
    
    /**
     * Creates new UserRegistration dialog.
     * @param parent - parent {@code Frame} element
     * @param modal - {@code boolean} value if the dialog is modal or not
     */
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
    
    /**
     * Sets given {@link Registration} controller as the controller for this dialog.
     * See {@link UIControlled} interface for more information.
     * @param controller - an instance of {@link Registration}
     */
    public final void setUIController(Registration controller) {
        this.controller = controller;
    }

    /**
     * Returns the controller of this dialog.
     * See {@link UIControlled} interface for more information.
     * @return a {@link Registration} controller
     */
    @Override
    public Registration getUIController() {
        return controller;
    }
    
    /**
     * Mapping submit button clicked event to different controller than in {@link #super}
     * @param evt - a {@code MouseEvent} object
     */
    @Override
    protected void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        controller.saveUser();
    }
    
    
    
}

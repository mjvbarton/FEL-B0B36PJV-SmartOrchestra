/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import java.awt.Frame;
import java.awt.event.MouseListener;

/**
 * This class extends {@link UserDetails} to display profile of user that is logged in the application.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public final class ViewProfile extends UserDetails implements UIControlled<UserSettings>{
    private UserSettings controller;
    
    /**
     * Creates new ViewProfile dialog.
     * @param parent - parent {@code Frame} element
     * @param modal - {@code boolean} value if the dialog is modal or not
     */
    public ViewProfile(Frame parent, boolean modal){
        super(parent, modal);
        
        caption.setText("Můj profil");
        setTitle("Můj profil");
        fieldFirstName.setEnabled(false);
        fieldFamilyName.setEnabled(false);
        fieldBirthDate.setEnabled(false);
        fieldEmail.setEnabled(false);
        fieldSection.setEnabled(false);
        fieldNoFunction.setEnabled(false);
        
        // Disabling interaction at concert master field
        fieldConcertMaster.setEnabled(false);
        for(MouseListener list : fieldConcertMaster.getMouseListeners()){
            fieldConcertMaster.removeMouseListener(list);
        }
        
        // Disabling interaction at second concert master field
        fieldCompConcertMaster.setEnabled(false);
        for(MouseListener list : fieldCompConcertMaster.getMouseListeners()){
            fieldCompConcertMaster.removeMouseListener(list);
        }
        fieldConcertMaster.removeAll();
        fieldCurrentPasswd.setEnabled(true);
        labelCurrentPasswd.setEnabled(true);
        
        fieldPermCommonAccount.setEnabled(false);
        fieldPermSpecialAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);
        
        // Disabling interaction for delete button
        for(MouseListener list : btnDeleteAccount.getListeners(MouseListener.class)){
            btnDeleteAccount.removeMouseListener(list);
        }
        setUIController(new UserSettings(ViewProfile.this));
        
    }
    /**
     * Setting {@link UserSettings} as a controller for this dialog.
     * See {@link UIControlled} interface for more information.
     * @param controller - a {@link UserSettings} controller
     */
    @Override
    public void setUIController(UserSettings controller) {
        super.setUIController(controller);
    }
    
    /**
     * Gets the {@link UserSettings} controller of this dialog.
     * @return a {@link UserSettings} controller
     */
    @Override
    public UserSettings getUIController() {
        return super.getUIController();
    }

    /**
     * Disabling concert master selection change for any value of {@code concertMasterFlag} given.
     * @param concertMasterFlag - a {@code boolean} flag used by {@link #super}
     */
    @Override
    public void setConcertMasterFlag(Boolean concertMasterFlag) {
        super.setConcertMasterFlag(concertMasterFlag);
        fieldConcertMaster.setEnabled(false);
        fieldCompConcertMaster.setEnabled(false);
        fieldNoFunction.setEnabled(false);
    }
}

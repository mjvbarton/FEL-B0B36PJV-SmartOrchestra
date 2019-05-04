/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.UIController;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.awt.Frame;

/**
 *
 * @author Matěj Bartoň
 */
public final class ViewProfile extends UserDetails implements UIControlled<UserSettings>{
    private UserSettings controller;
       
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
        fieldConcertMaster.setEnabled(false);
        fieldCompConcertMaster.setEnabled(false);
        
        fieldPermCommonAccount.setEnabled(false);
        fieldPermSpecialAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);
        setUIController(new UserSettings(ViewProfile.this));
    }
    
    @Override
    public void setUIController(UserSettings controller) {
        super.setUIController(controller);
    }

    @Override
    public UserSettings getUIController() {
        return super.getUIController();
    }
    
    
}

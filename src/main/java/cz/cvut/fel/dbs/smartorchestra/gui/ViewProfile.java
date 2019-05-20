/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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
        for(MouseListener list : fieldConcertMaster.getMouseListeners()){
            fieldConcertMaster.removeMouseListener(list);
        }
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
        for(MouseListener list : btnDeleteAccount.getListeners(MouseListener.class)){
            btnDeleteAccount.removeMouseListener(list);
        }
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

    @Override
    public void setConcertMasterFlag(Boolean concertMasterFlag) {
        super.setConcertMasterFlag(concertMasterFlag);
        fieldConcertMaster.setEnabled(false);
        fieldCompConcertMaster.setEnabled(false);
        fieldNoFunction.setEnabled(false);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserWriter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj Bartoň
 */
public class UserSettings implements UIController<UserDetails>{
    private UserDetails controled;
    private Users user;
    private DateFormat dateFormatter;

    public UserSettings(UserDetails controled) {
        setControlled(controled);
        dateFormatter = new SimpleDateFormat(SmartOrchestra.DATE_FORMAT);        
    }
          
    @Override
    public void setControlled(UserDetails controled) {
        this.controled = controled;
    }

    public void loadUser(Users activeUser) {
        user = activeUser;
        controled.loadUserDetail(user, dateFormatter);
    }
    
    public void saveUser(){
        boolean raisedException = false;
        try {
            user.setFirstName(controled.getFieldFirstName().getText());
        } catch (WrongInputException ex) {
            controled.getInfoFirstName().setText(ex.getMessage());
            raisedException = true;
        }
        
        try {
            user.setFamilyName(controled.getFieldFamilyName().getText());
        } catch (WrongInputException ex) {
            controled.getInfoFamilyName().setText(ex.getMessage());
            raisedException = true;
        }
        
        try {
            user.setBirthDate(controled.getFieldBirthDate().getText());
        } catch (WrongInputException ex) {
            controled.getInfoBirthDate().setText(ex.getMessage());
            raisedException = true;
        }
        
        try {
            user.setEmail(controled.getFieldEmail().getText());
        } catch (WrongInputException ex) {
            controled.getInfoEmail().setText(ex.getMessage());
            raisedException = true;
        }
        
        try {
            user.setPhone(controled.getFieldPhone().getText());
        } catch (WrongInputException ex) {
            controled.getInfoPhone().setText(ex.getMessage());
            raisedException = true;
        }
        
        
        user.setAddrStreet(controled.getFieldAddrStreet().getText());
        user.setAddrHouseNumber(controled.getFieldAddrHouseNumber().getText());
        user.setAddrTown(controled.getFieldAddrTown().getText());
        try {
            user.setAddrZipCode(controled.getFieldAddrZipCode().getText());
        } catch (WrongInputException ex) {
            controled.getInfoAddrZipCode().setText(ex.getMessage());
            raisedException = true;
        }
        
        if(raisedException){
            JOptionPane.showMessageDialog(controled, "Ukládání se nezdařilo", controled.getTitle(), 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        UserWriter uw = new UserWriter();
        try{
            uw.write(user);
        } catch(Exception err){
            err.printStackTrace();
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + err.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR);
        }
        controled.setExitCode(UserDetails.EXIT);
        controled.dispose();
    }
    
}

/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.exceptions.NotAPlayerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.PlayerManagerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserManagerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.model.EventAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.PlayerManager;
import cz.cvut.fel.dbs.smartorchestra.model.UserAdmin;
import cz.cvut.fel.dbs.smartorchestra.model.UserManager;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserWriter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * This class represents a controller for {@link UserSettings}.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserSettings implements UIController<UserDetails>{
    public static final Boolean FUNC_NONE = null;
    public static final Boolean FUNC_COMP_CONCERTMASTER = false;
    public static final Boolean FUNC_CONCERTMASTER = true;
    
    // Variables for ancestor classes
    protected UserDetails controled;
    protected Users user;
    protected DateFormat dateFormatter;
    
    /**
     * Initalizes the controller with the {@link UIControlled} implementation as {@link UserDetails}
     * @param controled - the controled {@link UserDetails}
     */
    public UserSettings(UserDetails controled) {
        setControlled(controled);
        dateFormatter = new SimpleDateFormat(SmartOrchestra.DATE_FORMAT);        
    }
    
    /**
     * See {@link UIController} for more information.
     * @param controled - an instance of {@link UIControlled}
     */
    @Override
    public void setControlled(UserDetails controled) {
        this.controled = controled;
    }

    /**
     * Loads information about user into the {@link UserDetails} dialog.
     * @param activeUser - a {@link Users} entity
     */
    public void loadUser(Users activeUser) {
        user = activeUser;
        controled.loadUserDetail(user, dateFormatter);
        loadSections();
        UserManager um = new UserManager();
        boolean isAdmin = um.checkAdministrator(activeUser);
        controled.loadPermissions(isAdmin);
        if(activeUser.equals(SmartOrchestra.getInstance().getActiveUser())){
            controled.getFieldPermSpecialAccount().setEnabled(false);
            controled.getFieldPermCommonAccount().setEnabled(false);
            controled.getBtnDeleteAccount().setEnabled(false);
        }
        
    }
    
    /**
     * Checks if the {@link #user} email is yet represented in database.
     * @return {@code true} if the email is not in database, {@code false} if the email is in database or if the email field is empty
     */
    public boolean checkEmail(){
        UserAdmin ua = new UserAdmin();
        try {
            ua.checkFreeEmail(controled.getFieldEmail().getText(), user);
            controled.getInfoEmail().setText("");
            return true;
        } catch (UserAdminException ex) {
            controled.getInfoEmail().setText(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Loads sections into the {@link UserDetails#fieldSection}. This method also loads the information whether user belongs to some section.
     */
    public void loadSections(){
        try {
            PlayerManager pm = new PlayerManager();
            controled.fetchSections(pm.getActiveSections());
            Player player = pm.getPlayerInfo(user);
            controled.getFieldSection().setSelectedIndex(pm.getIndexOfPlayerSection(player) + 1);
            controled.setConcertMasterFlag(player.getConcertmaster());
            
        } catch (PlayerManagerException ex) {
            Logger.getLogger(UserSettings.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(controled, ex.getMessage(), controled.getTitle(), 
                    JOptionPane.WARNING_MESSAGE);
            
        } catch (NotAPlayerException ex){
            controled.getFieldSection().setSelectedIndex(0);
            controled.setConcertMasterFlag(FUNC_NONE);
        }
    }
    
    /**
     * Saves information about the user in the database.
     */
    public void saveUser(){
        // Validation
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
        
        if(!checkEmail()){
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
        
        // Writing user directly withou using model component
        UserWriter uw = new UserWriter();
        PlayerManager pm;
        try {
            pm = new PlayerManager();
        } catch (PlayerManagerException ex) {
            Logger.getLogger(UserSettings.class.getName()).log(Level.SEVERE, "Cannot save data for " + user, ex);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR);
            return;
        }
        
        try{
            pm = new PlayerManager();
            EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
            uw.write(user);
            if(controled.getFieldSection().getSelectedIndex() != 0){
                int sectionIndex = controled.getFieldSection().getSelectedIndex() - 1;
                Player player = pm.getPlayerInfo(user);
                player.setSeid(pm.getActiveSections().get(sectionIndex));
                player.setConcertmaster(controled.getConcertMasterFlag());
                pm.updatePlayerInfo(player);
                Logger.getLogger(UserSettings.class.getName()).log(Level.INFO, "Section of player changed - {0}", player);
                invitePlayerToEvents(player);                                                 
            } else{
                Player player = pm.removePlayer(user);
                Logger.getLogger(UserSettings.class.getName()).log(Level.INFO, "Player removed - {0}", player);
            }
            UserAdmin ua = new UserAdmin();
            ua.setAdministrator(user, controled.getFieldPermSpecialAccount().isSelected());
            
        } catch(NotAPlayerException err){
            if(controled.getFieldSection().getSelectedIndex() != 0){
                Player player = pm.createNewPlayer(user, controled.getFieldSection().getSelectedIndex() - 1, controled.getConcertMasterFlag());
                invitePlayerToEvents(player);
                Logger.getLogger(UserSettings.class.getName()).log(Level.INFO, "New player created - {0}", player);
            }
            
        } catch(Exception err){
            Logger.getLogger(UserSettings.class.getName()).log(Level.SEVERE, "Cannot save data for " + user, err);
            JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + err.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            controled.setExitCode(UserDetails.EXIT);
            controled.dispose();
        }
    }
    
    // Invites the user to events (if the user does belong to some section)
    private void invitePlayerToEvents(Player player){
        EventAdmin ea = new EventAdmin(SmartOrchestra.getInstance());
        int invitePlayer = JOptionPane.showConfirmDialog(controled
                , "Chcete uživatele " + user.getEmail() + " pozvat na nadcházející události pro sekci " + player.getSeid().getSectionname() + " ?"
                    + "\n Varování: Neprovedení této operace může způsobit nekonzistenci dat."
                , "Pozvat uživatele na události", JOptionPane.YES_NO_OPTION);
        if(invitePlayer == JOptionPane.YES_OPTION){
            try{
                ea.invitePlayerToEvents(player);
            } catch(Exception ex){
                Logger.getLogger(UserSettings.class.getName()).log(Level.SEVERE, "Cannot invite " + user + " from section " + player.getSeid(), ex);
                JOptionPane.showMessageDialog(controled, "Chyba při běhu programu: " + ex.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR_MESSAGE);
            }
        }    
    }
    
    /**
     * Changes password for the user. <i>(UC03)</i>
     */
    public void changePasswd(){
        
        // Reset of error display
        controled.getInfoCurrentPasswd().setText("");
        controled.getInfoNewPasswd().setText("");
        controled.getInfoConfirmPasswd().setText("");
        
        String currentPasswd = controled.getFieldCurrentPasswd().getText();
        String newPasswd = controled.getFieldNewPasswd().getText();
        String confirmPasswd = controled.getFieldConfirmPasswd().getText();
        
        
        boolean emptyPasswd = false;
        // Different validation when active user is administrator
        boolean adminAccessEnabled = SmartOrchestra.getInstance().isAdministrationActive();
        
        // Validation
        if(currentPasswd.isEmpty() && !adminAccessEnabled){
            controled.getInfoCurrentPasswd().setText("Nevyplnili jste toto pole");
            emptyPasswd = true;                        
        }
        
        if(newPasswd.isEmpty()){
            controled.getInfoNewPasswd().setText("Nevyplnili jste toto pole");
            emptyPasswd = true;                        
        }
        
        if(confirmPasswd.isEmpty()){
            controled.getInfoConfirmPasswd().setText("Nevyplnili jste toto pole");
            emptyPasswd = true;                        
        }
        
        // Check current password
        if(!BCrypt.checkpw(currentPasswd, user.getPasswd()) && !emptyPasswd && !adminAccessEnabled){
            controled.getInfoCurrentPasswd().setText("Neplatné heslo");
            emptyPasswd = true;
        }
        
        if(!confirmPasswd.equals(newPasswd) && !emptyPasswd){
            controled.getInfoConfirmPasswd().setText("Zadaná hesla se neshodují");
            emptyPasswd = true;
        }
        if(emptyPasswd){
            JOptionPane.showMessageDialog(controled, "Změna hesla se nezdařila", controled.getTitle(), 
                    JOptionPane.WARNING_MESSAGE);
            controled.getFieldCurrentPasswd().setText("");
            controled.getFieldNewPasswd().setText("");
            controled.getFieldConfirmPasswd().setText("");
            return;
        }
        
        // Updating the information to the database
        UserManager um = new UserManager();
        try {
            um.changePasswd(user, newPasswd);
            Logger.getLogger(UserSettings.class.getName()).log(Level.INFO, "Password changed for {0}", controled.getFieldEmail().getText());
            JOptionPane.showMessageDialog(controled, "Heslo bylo změněno!", controled.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        
        // Sanitizing exception occured during the process
        } catch (UserManagerException ex) {
            Logger.getLogger(UserSettings.class.getName())
                    .log(Level.SEVERE, "Cannot change password for user " + controled.getFieldEmail().getText(), ex);
            JOptionPane.showMessageDialog(controled, "Chyba v běhu programu: " + ex.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            controled.getFieldCurrentPasswd().setText("");
            controled.getFieldNewPasswd().setText("");
            controled.getFieldConfirmPasswd().setText("");
        }
    }
    
    /**
     * Deletes the selected user from the system. Before the process a confirm dialog is shown to prevent unwanted deletions.
     */
    public void deleteUser() {
        int response = JOptionPane.showConfirmDialog(controled, 
                "Opravdu si přejete smazat uživatele " + user.getEmail() + "?\n"
                + "(Vymazání uživatele odstraní veškeré záznamy o uživateli a jeho aktivitách.)", "Smazat uživatele", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            Logger.getLogger(UserSettings.class.getName()).log(Level.FINE, "Deleting account {0} ...", user);
            UserAdmin ua = new UserAdmin();
            try{
                ua.removeUser(user);
                Logger.getLogger(UserSettings.class.getName())
                    .log(Level.INFO, "{0} deleted", user);
                JOptionPane.showMessageDialog(controled, "Uživatel " + user.getEmail() + " byl smazán", controled.getTitle(), 
                        JOptionPane.INFORMATION_MESSAGE );
            } catch (UserAdminException ex){
                Logger.getLogger(UserSettings.class.getName())
                    .log(Level.WARNING, "Cannot delete deleted user " + controled.getFieldEmail().getText(), ex);
                JOptionPane.showMessageDialog(controled, ex.getMessage(), "Smazat uživatele", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex){
                Logger.getLogger(UserSettings.class.getName())
                    .log(Level.SEVERE, "Cannot delete user " + controled.getFieldEmail().getText(), ex);
                JOptionPane.showMessageDialog(controled, "Chyba v běhu programu: " + ex.getMessage(), controled.getTitle(), 
                    JOptionPane.ERROR_MESSAGE);
            } finally{
                controled.setExitCode(UserDetails.EXIT);
                controled.dispose();
            }
            
        }        
    }
    
}
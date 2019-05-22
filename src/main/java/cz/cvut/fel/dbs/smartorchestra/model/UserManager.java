/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.exceptions.*;
import cz.cvut.fel.dbs.smartorchestra.model.dao.AdministratorHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserWriter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import javax.persistence.NoResultException;
import org.mindrot.jbcrypt.BCrypt;


/**
 * This class is model for basic working with {@link Users} entities.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserManager {
    private UserReader ur;
    
    /**
     * Creates new UserManager
     */
    public UserManager(){
        ur = new UserReader();
    }
    
    /**
     * This method represents the login process.
     * @param userMail - {@code String} email address to identify the user
     * @param passwd - {@code String} uncrypted value of the users password
     * @return a {@link Users} entity to be set as logged in user
     * @throws NonExistingUserException when the password is incorrect or the user with given email does not exist.
     * @throws Exception if the process failed from other reasons
     */
    public final Users login(String userMail, String passwd) throws NonExistingUserException, Exception{
        try{
            Users user = ur.getUserFromEmail(userMail);
            if(BCrypt.checkpw(passwd, user.getPasswd()) != true){
                throw new NonExistingUserException();
            }
            return user;            
        } catch(UserManagerException | NoResultException err){
            throw new NonExistingUserException();
            
        } 
    }
    
    /**
     * Sets given user a new password according to the password given.
     * @param user - a {@link Users} entity of which the password will be changed
     * @param NewPasswd - an uncrypted {@code String} object of the new password
     * @throws UserManagerException when the process fails
     */
    public final void changePasswd(Users user, String NewPasswd) throws UserManagerException{
        try {
            user.setPasswd(BCrypt.hashpw(NewPasswd, BCrypt.gensalt(12)));
            UserWriter uw = new UserWriter();
            uw.write(user);
            
        } catch (Exception ex) {
                    throw new UserManagerException("Chyba v běhu programu: " + ex.getMessage());
        }       
    }
    
    /**
     * Checks if given user has administrator rights.
     * @param user - a {@link Users} entity
     * @return {@code true} if given user has administrator rights, {@code false} if not.
     */
    public final boolean checkAdministrator(Users user){
        AdministratorHandler ah = new AdministratorHandler();
        return ah.getIsAdministrator(user);
    }
}

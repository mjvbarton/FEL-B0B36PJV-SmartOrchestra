/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import cz.cvut.fel.dbs.smartorchestra.exceptions.*;
import cz.cvut.fel.dbs.smartorchestra.model.dao.AdministratorHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserWriter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author Matěj Bartoň
 */
public class UserManager {
    private UserReader ur;
    
    public UserManager(){
        ur = new UserReader();
    }
    public final Users login(String userMail, String passwd) throws UserManagerException, Exception{
        try{
            Users user = ur.getUserFromEmail(userMail);
            if(BCrypt.checkpw(passwd, user.getPasswd()) != true){
                throw new NonExistingUserException();
            }
            return user;            
        } catch(UserManagerException err){
            throw new NonExistingUserException();
            
        } catch(NoResultException err){
            throw new NonExistingUserException();
        } 
    }
        
    public final void changePasswd(Users user, String NewPasswd) throws UserManagerException{
        try {
            user.setPasswd(BCrypt.hashpw(NewPasswd, BCrypt.gensalt(12)));
            UserWriter uw = new UserWriter();
            uw.write(user);
            
        } catch (Exception ex) {
            throw new UserManagerException("Chyba v běhu programu: " + ex.getMessage());
        }       
    }
    
    public final boolean checkAdministrator(Users user){
        AdministratorHandler ah = new AdministratorHandler();
        return ah.getIsAdministrator(user);
    }
}

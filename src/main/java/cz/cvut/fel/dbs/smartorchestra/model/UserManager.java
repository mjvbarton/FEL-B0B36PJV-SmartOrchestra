/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import cz.cvut.fel.dbs.smartorchestra.exceptions.*;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
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
        }
    }
    
    public final void updateUserInformation(UserSettings controller){
        
    }
    
    private void updateUserCommonInformation(UserSettings controller){
        
    }
}

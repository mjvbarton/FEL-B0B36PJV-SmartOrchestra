/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.model.dao.AdministratorHandler;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserWriter;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class UserAdmin extends UserManager{
    private List<Users> adminedUsers;
    
    public UserAdmin(){
        adminedUsers = new ArrayList();
    }
    
    public List<Users> loadUsers() throws UserAdminException{
        UserReader ur = new UserReader();
        adminedUsers = ur.loadUsers();
        if(adminedUsers.isEmpty()){
            throw new UserAdminException("Nenalezen žádný uživatel");
        }
        return adminedUsers;
    }
    
    public Users getUserForAdministration(int arrayIndex){
        return adminedUsers.get(arrayIndex);
    }
    
    public void checkFreeEmail(String email, Users activeUser) throws UserAdminException{
        UserReader ur = new UserReader();
        try{
            Users user = ur.getUserFromEmail(email);
            if(!user.equals(activeUser)){
            throw new UserAdminException("Email je již obsazen");
            }
        } catch(NoResultException err){
        }
    }

    public void setAdministrator(Users user, boolean isAdmin) throws Exception{
        AdministratorHandler ah = new AdministratorHandler();
        boolean wasAdmin = checkAdministrator(user);
        if(!wasAdmin && isAdmin){
            ah.newAdmin(user);
                        
        } else if(wasAdmin && !isAdmin){
            ah.remove(user);
        }
    }    

    public void removeUser(Users user) throws UserAdminException, Exception{
        UserWriter uw = new UserWriter();
        uw.removeUser(user);
    }
}

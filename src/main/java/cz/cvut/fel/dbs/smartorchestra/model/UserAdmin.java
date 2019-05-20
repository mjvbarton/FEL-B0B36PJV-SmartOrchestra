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
 * This class represents model for administrating users. It is an extension of {@link UserManager}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserAdmin extends UserManager{
    private List<Users> adminedUsers;
    
    /**
     * Creates new UserAdmin
     */
    public UserAdmin(){
        adminedUsers = new ArrayList();
    }
    
    /**
     * Loads users for administraion and saves them to the class property.
     * @return a {@code List} of {@link Users} entities
     * @throws UserAdminException when no user was found
     */
    public List<Users> loadUsers() throws UserAdminException{
        UserReader ur = new UserReader();
        adminedUsers = ur.loadUsers();
        if(adminedUsers.isEmpty()){
            throw new UserAdminException("Nenalezen žádný uživatel");
        }
        return adminedUsers;
    }
    
    /**
     * Gets user from and administration based on array index.
     * @param arrayIndex - {@code int} index in {@code List} of admined users
     * @return a {@link Users} entity from list of admined users
     */
    public Users getUserForAdministration(int arrayIndex){
        return adminedUsers.get(arrayIndex);
    }
    
    /**
     * Checks if there is no email in the database for given email.
     * @param email - {@code String} email address
     * @param activeUser - an {@link Users} entity
     * @throws UserAdminException there is email in the database
     */
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

    /**
     * Sets given user to have administrator permissions. If given boolean value is admin is {@code true}
     * the given is user is given administrator rights, otherwise the rights from given user are removed.
     * @param user - a {@code User} entity
     * @param isAdmin - {@code boolean} value,  
     * @throws Exception if the process fails on the DAO level
     */
    public void setAdministrator(Users user, boolean isAdmin) throws Exception{
        AdministratorHandler ah = new AdministratorHandler();
        boolean wasAdmin = checkAdministrator(user);
        if(!wasAdmin && isAdmin){
            ah.newAdmin(user);
                        
        } else if(wasAdmin && !isAdmin){
            ah.remove(user);
        }
    }    

    /**
     * Deletes given user and all dependencies from the database.
     * @param user - a {@link Users} entity to be deleted
     * @throws UserAdminException when dependent {@link UserWriter} throws it.
     * @throws Exception when dependent {@link UserWriter} throws it
     */
    public void removeUser(Users user) throws UserAdminException, Exception{
        UserWriter uw = new UserWriter();
        uw.removeUser(user);
    }
}

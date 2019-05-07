/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.exceptions.UserAdminException;
import cz.cvut.fel.dbs.smartorchestra.model.dao.UserReader;
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
    
    public void checkFreeEmail(String email) throws UserAdminException{
        UserReader ur = new UserReader();
        try{
            Users user = ur.getUserFromEmail(email);
            if(!user.equals(SmartOrchestra.getActiveUser())){
            throw new UserAdminException("Uživatel s tímto emailem již existuje");
            }
        } catch(NoResultException err){
        }
    }
    
    
}

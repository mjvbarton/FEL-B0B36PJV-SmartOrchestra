/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import javax.persistence.*;

/**
 *
 * @author Matěj Bartoň
 */
public class UserReader {
    private EntityManager em;
    
    public UserReader() {
        em = SmartOrchestra.getEntityManager();
    }
    
    public Users getUserFromEmail(String email){
        Users user = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class).
                setParameter("email", email).getSingleResult();
        return user;
    }
}

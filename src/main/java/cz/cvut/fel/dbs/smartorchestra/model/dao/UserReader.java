/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.List;
import javax.persistence.*;

/**
 * Class for reading information about {@link Users} entities from the database.
 * <b>WARNING: This class cannot be used in multiple threads simultaneously!</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserReader extends DAO{
    
    /**
     * Gets the user with given email.
     * @param email - a {@code String} of email address
     * @return a {@link Users} entity
     * @throws NoResultException if the user with given email was not found.
     */
    public synchronized Users getUserFromEmail(String email) throws NoResultException{
        Users user = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class).
                setParameter("email", email).getSingleResult();
        return user;
    }

    /**
     * Gets all the users from the database in ordered list by family name, first name.
     * @return a {@code List} of {@link Users} entities
     */
    public List<Users> loadUsers(){
        synchronized(em){
            List<Users> result = em.createQuery("SELECT u FROM Users u ORDER BY u.familyName, u.firstName").getResultList();
            return result;
        }
    }
    
    /**
     * Gets user by its primary key.
     * @param uid - a {@code Long} number
     * @return a {@link Users} entity from the database
     */
    public Users getUserFromUid(Long uid) {
        return em.find(Users.class, uid);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import javax.persistence.EntityManager;

/**
 *
 * @author Matěj Bartoň
 */
public interface ThreadEntityManager {
    
    public EntityManager getEntityManager();
    
}

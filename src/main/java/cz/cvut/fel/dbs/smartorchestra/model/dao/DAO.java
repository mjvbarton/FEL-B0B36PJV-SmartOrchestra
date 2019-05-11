/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import javax.persistence.EntityManager;

/**
 *
 * @author Matěj Bartoň
 */
public abstract class DAO {
    protected EntityManager em;
    
    protected DAO() {
        em = SmartOrchestra.getInstance().getEntityManager();
    }
}

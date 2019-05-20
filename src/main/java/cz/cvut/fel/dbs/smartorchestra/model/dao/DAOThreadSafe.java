/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import javax.persistence.EntityManager;

/**
 * This is an abstract class of Data Access Object which can be uses in multiple threads at once.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public abstract class DAOThreadSafe{
    protected EntityManager em;
    
    /**
     * Initializes new DAOThreadSafe with given {@code EntityManager} instance
     * @param em - an {@code EntityManager}
     */
    protected DAOThreadSafe(EntityManager em) {
        this.em = em;
    }
    
    
}

/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import javax.persistence.EntityManager;

/**
 * This is an abstract class of Data Access Object.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public abstract class DAO {
    protected EntityManager em;
    
    /**
     * Initializes new DAO with {@code EntityManager} extracted from {@link SmartOrchestra} singleton class.
     */
    protected DAO() {
        em = SmartOrchestra.getInstance().getEntityManager();
    }
}
